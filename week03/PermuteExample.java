package com.msk.home;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 全排列
 */
public class PermuteExample {

    /**
     * @param nums 数组
     * @return 该数组的全排列结果集
     */
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) return res;
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[len];
        //深度优先遍历，这里也可以用广度优先遍历实现，后续尝试
        dfs(nums, len, 0, path, used, res);
        return res;
    }

    /**
     * @param nums  数组
     * @param len   数组长度
     * @param depth 在树的第几层
     * @param path  栈，在合适的节点保存想要的结果
     * @param used  标记数组中的元素是否被使用过
     * @param res   结果集
     */
    private void dfs(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        //如果len == depth 则说明递归到了最后一层，此时记录需要的结果树
        if (len == depth) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            //数组中的该元素被使用过了，直接跳过
            if (used[i]) continue;
            //每一层的结果存入栈中
            path.offerLast(nums[i]);
            //数组元素被使用，状态改为true
            used[i] = true;
            //深度优先遍历
            dfs(nums, len, depth + 1, path, used, res);
            //回溯时，状态重置，即回到上一节点时的状态
            path.removeLast();
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> permute = new PermuteExample().permute(nums);
        System.out.println(permute);
    }

}
