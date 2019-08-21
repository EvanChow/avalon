package com.avalon.common.avaloncommonzk.vo;

import java.util.List;

public class Tree {
    
    private String name;
    private int value;
    private Object node;
    List<Tree> children;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public Object getNode() {
        return node;
    }
    public void setNode(Object node) {
        this.node = node;
    }
    public List<Tree> getChildren() {
        return children;
    }
    public void setChildren(List<Tree> children) {
        this.children = children;
    }
    
    
    
}
