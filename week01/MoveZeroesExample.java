package com.msk.home;

/**
 * 零移动问题
 */
public class MoveZeroesExample {

    /**
     * 快慢指针解法
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        if (nums == null) return;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

}
