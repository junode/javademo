package com.junode.leetcode;

import sun.reflect.generics.tree.Tree;

/**
 * @author junode
 * @version 1.0.0
 * @Description
 * @createTime 2024年05月23日 23:22:00
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode build() {
        TreeNode node1 = new TreeNode(1,null,null);
        TreeNode node5 = new TreeNode(5, null, null);
        TreeNode node3 = new TreeNode(3, node1, node5);
        TreeNode node9 = new TreeNode(9, null, null);
        return new TreeNode(7, node3, node9);


    }
}
