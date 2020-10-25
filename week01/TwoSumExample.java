package com.msk.home;

import java.util.HashMap;

/**
 * 两数之和问题
 */
public class TwoSumExample {

    /**
     * 哈希表解法
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i])) return new int[]{hashMap.get(target - nums[i]), i};
            hashMap.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

}
