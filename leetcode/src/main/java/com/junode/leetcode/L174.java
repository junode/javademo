package com.junode.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author junode
 * @version 1.0.0
 * @Description 返回二叉搜索树第n大的数值
 * @createTime 2024年05月23日 23:25:00
 */
public class L174 {

    @Test
    public void test() {
        TreeNode root = TreeNode.build();
        int cnt = 2;
        List<Integer> result = new ArrayList<>();
        centerBroad(root, result);
        System.out.println("result.get(result.size()-cnt) = " + result.get(result.size() - cnt));
    }

    public void centerBroad(TreeNode root, List<Integer> result) {
        if (root != null) {
            centerBroad(root.left,result);
            result.add(root.val);
            System.out.println("root.val = " + root.val);
            centerBroad(root.right, result);
        }
    }
}
