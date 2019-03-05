package com.avalon.zookeeper.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.data.ACL;

public class ZkNode {
    private String path;
    private String data;
    private List<AclNode> permsid=new ArrayList<AclNode>();
    
    public ZkNode(){
        
    }
    
    public ZkNode(String path,String data){
        this.path=path;
        this.data=data;
    }
    
    public ZkNode(String path,String data,List<ACL> acls){
        this.path=path;
        this.data=data;
        for(ACL acl:acls){
            permsid.add(new AclNode(acl.getPerms(),(acl.getId().getScheme()),acl.getId().getId()));
        }
    }
    
    public ZkNode(String path,List<ACL> acls){
        this.path=path;
        for(ACL acl:acls){
            permsid.add(new AclNode(acl.getPerms(),(acl.getId().getScheme()),acl.getId().getId()));
        }
    }
    
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public List<AclNode> getPermsid() {
        return permsid;
    }

    public void setPermsid(List<AclNode> permsid) {
        this.permsid = permsid;
    }

    
    
}
