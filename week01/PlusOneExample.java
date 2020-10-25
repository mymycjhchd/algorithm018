package com.msk.home;

/**
 * 加一问题
 */
public class PlusOneExample {

    /**
     * 考虑末位满10进1且一直到最高位都进1的情况，需要增加数组长度且nums[0]=0即可
     *
     * @param nums
     * @return
     */
    public int[] plusOne(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            nums[i]++;
            nums[i] = nums[i] % 10;
            if (nums[i] != 0) return nums;
        }
        nums = new int[nums.length + 1];
        nums[0] = 1;
        return nums;
    }

}
