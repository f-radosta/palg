package com.example.cviceni.dynStruct;

public class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    @Override
    public String toString() {
        return "TreeElem{" +
                "data=" + data +
                '}';
    }
}
