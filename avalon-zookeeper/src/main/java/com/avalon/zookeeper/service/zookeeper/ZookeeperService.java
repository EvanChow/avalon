package com.avalon.zookeeper.service.zookeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Service;

@Service
public class ZookeeperService {
    
    /** create /node node
     *  addauth digst admin:123456
     *  setAcl /node auth:admin:123456:rwcda
     *  getAcl /node
     * 
     * 
     * 
     * 
     */
    
    
    
    
    /**
     * 
     * @方法名: getList
     * @功能描述: 获取zookeeper下节点
     * @参数：@param
     * @返回：@return
     * @创建人: Evan
     * @创建时间： 2018-8-22 下午05:34:50
     */
    public List<String> getListNode(final ZooKeeper zookeeper,String node){
        List<String> result=new ArrayList<String>();
        try {
            result= zookeeper.getChildren(node, true);
        } catch (KeeperException e) {
            //e.printStackTrace();
            //result.add("NoAuth");
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 
     * @方法名: createNode
     * @功能描述: 创建节点
     * @参数：@param
     * * 参数一：路径地址
             * 参数二：想要保存的数据，需要转换成字节数组
             * 参数三：ACL访问控制列表（Access control list）,
             *      参数类型为ArrayList<ACL>，Ids接口提供了一些默认的值可以调用。
             *      OPEN_ACL_UNSAFE     This is a completely open ACL 
             *                          这是一个完全开放的ACL，不安全
             *      CREATOR_ALL_ACL     This ACL gives the
             *                           creators authentication id's all permissions.
             *                          这个ACL赋予那些授权了的用户具备权限
             *      READ_ACL_UNSAFE     This ACL gives the world the ability to read.
             *                          这个ACL赋予用户读的权限，也就是获取数据之类的权限。
             * 参数四：创建的节点类型。枚举值CreateMode
             *      PERSISTENT (0, false, false)
             *      PERSISTENT_SEQUENTIAL (2, false, true)
             *          这两个类型创建的都是持久型类型节点，回话结束之后不会自动删除。
             *          区别在于，第二个类型所创建的节点名后会有一个单调递增的数值
             *      EPHEMERAL (1, true, false)
             *      EPHEMERAL_SEQUENTIAL (3, true, true)
             *          这两个类型所创建的是临时型类型节点，在回话结束之后，自动删除。
             *          区别在于，第二个类型所创建的临时型节点名后面会有一个单调递增的数值。
             * 最后create()方法的返回值是创建的节点的实际路径
     * @返回：@return
     * @创建人: Evan
     * @创建时间： 2018-8-23 下午04:55:30
     */
    public boolean createNode(final ZooKeeper zookeeper,String node,String data,int acl,int mode){
        Stat stat=null;
        try {
            stat = zookeeper.exists(node, true);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        if (stat == null) {
            List<ACL> acls=Ids.OPEN_ACL_UNSAFE;
            if(acl==1)
                acls=Ids.CREATOR_ALL_ACL;
            else if(acl==2)
                acls=Ids.READ_ACL_UNSAFE;
            
            CreateMode createMode= CreateMode.PERSISTENT;
            if(mode==1)
                createMode=CreateMode.PERSISTENT_SEQUENTIAL;
            else if(mode==2)
                createMode=CreateMode.EPHEMERAL;
            else if(mode==3)
                createMode=CreateMode.EPHEMERAL_SEQUENTIAL;            
            try {
                zookeeper.create(node,data.getBytes(),acls,createMode);
            } catch (KeeperException e) {
                e.printStackTrace();
                return false;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }           
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @方法名: getNodeData
     * @功能描述: 获取节点数据
     * @参数：@param
     * @返回：@return
     * @创建人: Evan
     * @创建时间： 2018-8-23 下午04:55:17
     */
    public String getNodeData(final ZooKeeper zookeeper,String node){
        try {
            
            return new String(zookeeper.getData(node, false,zookeeper.exists(node, true))==null?"".getBytes():zookeeper.getData(node, false,zookeeper.exists(node, true)));
        } catch (KeeperException e) {
            //e.printStackTrace();
            return "NoAuth";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Stat setNodeData(final ZooKeeper zookeeper,String node,String data){
       try {
        return zookeeper.setData(node, data.getBytes(), zookeeper.exists(node, true).getVersion());
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 
     * @方法名: deleteNode
     * @功能描述: 删除节点
     * @参数：@param
     * @返回：@return
     * @创建人: Evan
     * @创建时间： 2018-8-23 下午04:55:05
     */
    public boolean deleteNode(final ZooKeeper zookeeper,String node){
        Stat stat=null;
        try {
            stat = zookeeper.exists(node, true);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if ( stat!= null) {
            try {
                zookeeper.delete(node, -1);
                return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (KeeperException e) {
                e.printStackTrace();
            }
            return false;
        }
        return false;
    }
    
    /**
     * 
     * @方法名: serIPAcl
     * @功能描述: 给节点赋权
     * @参数：@param setAcl / world:anyone:crwda
     * @返回：@return
     * @创建人: Evan
     * @创建时间： 2018-8-23 上午10:03:22
     */
    public boolean serAcl(final ZooKeeper zookeeper,String node,String iperms,String power){
         // ip方式的acl
        List<ACL> aclsIP = new ArrayList<ACL>();  // 权限列表
        
        
        //参数格式:IP#127.0.0.1#IP:127.0.0.2
        //第一个参数是权限scheme，第二个参数是ip地址
        String[] ips=iperms.split(",");
        Id ipid = null;
        
        for(String ip:ips){
            String[] p=ip.split("#");
            ipid = new Id(p[0], p[1]);
            
            int perms=ZooDefs.Perms.ALL;
            if("READ".equals(power))
                perms=ZooDefs.Perms.READ;
            else if("WRITE".equals(power))
                perms=ZooDefs.Perms.WRITE;
            else if("CREATE".equals(power))
                perms=ZooDefs.Perms.CREATE;
            else if("DELETE".equals(power))
                perms=ZooDefs.Perms.DELETE;
            else if("ADMIN".equals(power))
                perms=ZooDefs.Perms.ADMIN;
            else if("ALL".equals(power))
                perms=ZooDefs.Perms.ALL;
            System.out.println(p[0]+","+p[1]+",perms:"+perms);
            aclsIP.add(new ACL(perms, ipid));  // 给予权限
            System.out.println(aclsIP);
        }
        
        try {
            zookeeper.setACL(node, aclsIP,zookeeper.exists(node, true).getAversion());
            return true;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
        
    }
    
    /**
     * 
     * @方法名: getAcl
     * @功能描述: 获取权限
     * @参数：@param
     * @返回：@return
     * @创建人: Evan
     * @创建时间： 2018-8-23 下午04:54:38
     */
    public List<ACL> getAcl(final ZooKeeper zookeeper,String node){
        try {
          return zookeeper.getACL(node, zookeeper.exists(node, true));
        } catch (KeeperException e) {
            //e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    /**
     * 
     * @方法名: getZkcli
     * @功能描述: ZooKeeper客户端
     * @参数：@param
     * @返回：@return
     * @创建人: Evan
     * @创建时间： 2018-8-23 下午04:54:19
     */
    public ZooKeeper getZkcli(String connectString,int sessionTimeout){
        ZooKeeper zookeeper;
        try {
            zookeeper = new ZooKeeper(connectString, sessionTimeout,  new Watcher(){
                public void process(WatchedEvent event){
                    System.out.println(event.toString());
                }
            });
            return zookeeper;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 
     * @方法名: closeZkcli
     * @功能描述: 关闭客户端
     * @参数：@param
     * @返回：@return
     * @创建人: Evan
     * @创建时间： 2018-8-23 下午05:21:48
     */
    public void closeZkcli(final ZooKeeper zookeeper,int sleep){
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            zookeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
    
}
