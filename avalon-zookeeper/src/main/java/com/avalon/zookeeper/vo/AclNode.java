package com.avalon.zookeeper.vo;

public class AclNode {
    
    private Perms[] perms;
    private String scheme;
    private String id;
   
    
    public AclNode(String scheme,String id){
        this.scheme=scheme;
        this.id=id; 
        this.perms=new Perms[5];
        int[] arr={16,8,4,2,1};
        for(int i=0;i<5;i++){
            this.perms[4-i]=new Perms(arr[i],false); 
        }
    }
    
    public AclNode(int all,String scheme,String id){
        this.scheme=scheme;
        this.id=id;
        this.perms=new Perms[5];
        int[] arr={16,8,4,2,1};
        for(int i=0;i<5;i++){
            if(arr[i]<=all){
                all=all-arr[i];
                this.perms[4-i]=new Perms(arr[i],true);
            }else{
                this.perms[4-i]=new Perms(arr[i],false);
            } 
        }
    }

    public Perms[] getPerms() {
        return perms;
    }
    public void setPerms(Perms[] perms) {
        this.perms = perms;
    }
    public String getScheme() {
        return scheme;
    }
    public void setScheme(String scheme) {
        this.scheme = scheme;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
