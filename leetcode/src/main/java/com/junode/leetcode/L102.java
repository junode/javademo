package com.junode.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * @author junode
 * @version 1.0.0
 * @Description
 * @createTime 2024年05月24日 10:26:00
 */
public class L102 {

    @Test
    public void queueTest() {
        TreeNode root = TreeNode.build();
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            System.out.println("result = " + result);
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> curLevelTreeResult = new ArrayList<>();
            while (size-- > 0) {
                TreeNode poll = queue.poll();
                curLevelTreeResult.add(poll.val);
                if(poll.left != null) {
                    queue.add(poll.left);
                }
                if(poll.right != null) {
                    queue.add(poll.right);
                }
            }
            result.add(curLevelTreeResult);
        }
        for (List<Integer> integers : result) {
            System.out.println("Arrays.toString(integers.toArray()) = " + Arrays.toString(integers.toArray()));
        }
    }
}
