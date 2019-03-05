package com.avalon.zookeeper.controller.zookeeper;

import java.util.ArrayList;
import java.util.List;

import com.avalon.zookeeper.service.zookeeper.ZookeeperService;
import com.avalon.zookeeper.vo.Json;
import com.avalon.zookeeper.vo.TableData;
import com.avalon.zookeeper.vo.Tree;
import com.avalon.zookeeper.vo.ZkNode;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/zookeeper")
public class ZookeeperController {
    
    @Autowired
    private ZookeeperService zookeeperService;
    
    @RequestMapping("/list")
    @ResponseBody
    public Json getListNode(String ip, int timeout, int sleep, String node){
        Json j=new Json();
        ZooKeeper  zookeeper = zookeeperService.getZkcli(ip, timeout);
        j.setObj(zookeeperService.getListNode(zookeeper, node));
        zookeeperService.closeZkcli(zookeeper, sleep);
        j.setSuccess(true);
        return j;
    }
    
    @RequestMapping("/tablelist")
    @ResponseBody
    public TableData Data(String ip, int timeout, int sleep, String node){
        TableData table=new TableData();
        ZooKeeper  zookeeper = zookeeperService.getZkcli(ip, timeout);
        List<ACL> acls=zookeeperService.getAcl(zookeeper, node);
        
        ZkNode zknode=new ZkNode(node,acls);
        
        table.setData(zknode.getPermsid());
        table.setCount(zknode.getPermsid().size());
        //List<AclNode> zknode=new ArrayList<AclNode>();
        
        
        zookeeperService.closeZkcli(zookeeper, sleep);
        
        return table;
    }
    
    
    @RequestMapping("/addNode")
    @ResponseBody
    public Json addNode(String ip, int timeout,int sleep,String node,String data){
        Json j=new Json();
        ZooKeeper  zookeeper = zookeeperService.getZkcli(ip, timeout);
        j.setObj(zookeeperService.createNode(zookeeper, node, data, 0, 0));
        zookeeperService.closeZkcli(zookeeper, sleep);
        j.setSuccess(true);
        return j;
    }
    
    @RequestMapping("/delNode")
    @ResponseBody
    public Json delNode(String ip, int timeout,int sleep,String node){
        Json j=new Json();
        ZooKeeper  zookeeper = zookeeperService.getZkcli(ip, timeout);
        j.setSuccess(zookeeperService.deleteNode(zookeeper, node));
        zookeeperService.closeZkcli(zookeeper, sleep);
        return j;
    }
    
    @RequestMapping("/editData")
    @ResponseBody
    public Json editData(String ip, int timeout,int sleep,String node,String data){
        Json j=new Json();
        ZooKeeper  zookeeper = zookeeperService.getZkcli(ip, timeout);
        zookeeperService.setNodeData(zookeeper, node, data);
        zookeeperService.closeZkcli(zookeeper, sleep);
        j.setSuccess(true);
        return j;
    }
    
    
    @RequestMapping("/setAcl")
    @ResponseBody
    public Json setAcl(String ip, int timeout,int sleep,String node,String iperms,String power){
        Json j=new Json();
        ZooKeeper  zookeeper = zookeeperService.getZkcli(ip, timeout);
        j.setSuccess(zookeeperService.serAcl(zookeeper, node, iperms, power));
        zookeeperService.closeZkcli(zookeeper, sleep);
        return j;
    }
    
    
    
    
    @RequestMapping("/tree")
    @ResponseBody
    public Tree getTree(String ip, int timeout,int sleep){
        ZooKeeper  zookeeper = zookeeperService.getZkcli(ip, timeout);
        Tree result=new Tree();
        result.setName("根节点");
        List<Tree> trees=new ArrayList<Tree>();
        result.setChildren(trees);
        if(zookeeper==null){
          return result;  
        }
        
        List<String> nodes=zookeeperService.getListNode(zookeeper, "/");
        if(nodes==null){
            result.setNode(new ZkNode("/","NoAuth",new ArrayList<ACL>()));
            return result;
        }
        
        result.setNode(new ZkNode("/",zookeeperService.getNodeData(zookeeper, "/"),zookeeperService.getAcl(zookeeper, "/")));
        
        result.setValue(nodes.size());
        Tree tree=new Tree();
        for(String node:nodes){
            tree=new Tree();
            tree.setName(node);
            List<String> nd=zookeeperService.getListNode(zookeeper, "/"+node);
            if(nd==null){
                tree.setNode(new ZkNode("/"+node,"NoAuth",new ArrayList<ACL>()));
                trees.add(tree);
                continue;
            }
            int size=nd.size();
            tree.setValue(size);

            if(size>0){
                tree.setChildren(getNode(zookeeper,"/"+node));
                tree.setNode(new ZkNode("/"+node,zookeeperService.getNodeData(zookeeper, "/"+node),zookeeperService.getAcl(zookeeper, "/"+node)));
            }else{
                tree.setNode(new ZkNode("/"+node,zookeeperService.getNodeData(zookeeper, "/"+node),zookeeperService.getAcl(zookeeper, "/"+node)));
            }
            trees.add(tree);
        }
        zookeeperService.closeZkcli(zookeeper, sleep);
        result.setChildren(trees);
        
        return result;
        
    }
    
    
    private List<Tree> getNode(final ZooKeeper zookeeper,String root){
        List<Tree> trees=new ArrayList<Tree>();
        Tree tree=new Tree();
        List<String> nodes=zookeeperService.getListNode(zookeeper, root);
        if(nodes==null){
            tree.setName("NoAuth");
            tree.setNode(new ZkNode(root,"NoAuth",new ArrayList<ACL>()));
            trees.add(tree);
            return trees;
        }
        
        for(String node:nodes){
            tree=new Tree();
            tree.setName(node);
            List<String> nd=zookeeperService.getListNode(zookeeper, root+"/"+node);
            if(nd==null){
                tree.setNode(new ZkNode(root+"/"+node,"NoAuth",new ArrayList<ACL>()));
                trees.add(tree); 
                continue;
            }
            int size=nd.size();
            tree.setValue(size);
            //
            if(size>0){
                tree.setChildren(getNode(zookeeper,root+"/"+node));
                tree.setNode(new ZkNode(root+"/"+node,zookeeperService.getNodeData(zookeeper, root+"/"+node),zookeeperService.getAcl(zookeeper, root+"/"+node)));
            }else{
                tree.setNode(new ZkNode(root+"/"+node,zookeeperService.getNodeData(zookeeper, root+"/"+node),zookeeperService.getAcl(zookeeper, root+"/"+node)));
            }
            trees.add(tree);
        }
        return trees;
    }
    
    
    
}
