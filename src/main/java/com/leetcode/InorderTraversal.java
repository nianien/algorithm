package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class InorderTraversal {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        return inorderTraversal(root,new ArrayList<>());
    }


    public List<Integer> inorderTraversal(TreeNode root, List<Integer> result) {
        if (root != null) {
            inorderTraversal(root.left,result);
            result.add(root.val);
            inorderTraversal(root.right,result);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode treeNode=new TreeNode(1);
        treeNode.right=new TreeNode(2);
        treeNode.right.left=new TreeNode(3);
        System.out.println(new InorderTraversal().inorderTraversal(treeNode));
    }
}
