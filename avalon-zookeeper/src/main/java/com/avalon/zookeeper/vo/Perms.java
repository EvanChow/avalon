package com.avalon.zookeeper.vo;

public class Perms {
    private int perm;
    private String permname;
    private String permnamen;
    private boolean yesno;
    private int read=1;
    private int write=2;
    private int create=4;
    private int delete=8;
    private int admin=16;
    public Perms(int perm,boolean yesno){
        this.perm=perm;
        this.yesno=yesno;
        if(perm==admin){
            this.permname="管理";
            this.permnamen="Admin";
        }else if(perm==delete){
            this.permname="删除";
            this.permnamen="Delete";
        }else if(perm==create){
            this.permname="创建";
            this.permnamen="Create";
        }else if(perm==write){
            this.permname="写入";
            this.permnamen="Write";
        }else if(perm==read){
            this.permname="读取";
            this.permnamen="Read";
        }
    }
    
    public int getPerm() {
        return perm;
    }
    public void setPerm(int perm) {
        this.perm = perm;
    }
    public String getPermname() {
        return permname;
    }
    public void setPermname(String permname) {
        this.permname = permname;
    }
    public boolean isYesno() {
        return yesno;
    }
    public void setYesno(boolean yesno) {
        this.yesno = yesno;
    }

    public String getPermnamen() {
        return permnamen;
    }

    public void setPermnamen(String permnamen) {
        this.permnamen = permnamen;
    }
    
    public String toString(){
        return "{权限:"+permname+",ID:"+perm+",yesno:"+yesno+"}";
    }
    
    
}
