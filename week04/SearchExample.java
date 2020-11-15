package com.msk.home;

/**
 * 搜索旋转排序数组
 */
public class SearchExample {

    /**
     * 二分法
     *
     * @param nums   数组
     * @param target 旋转数
     * @return 数组下标
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        //处理数组长度为0和1的情况
        if (n == 0) return -1;
        if (n == 1) return nums[0] == target ? 0 : -1;
        //定义左中右边界属性
        int left = 0, right = n - 1, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            //找到目标数在数组中的下标并返回
            if (target == nums[mid]) return mid;
            //左边有序情况处理
            if (nums[0] <= nums[mid]) {
                //目标在左边有序区间中
                if (nums[0] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    //目标不在左边有序区间中
                    left = mid + 1;
                }
            } else {
                //右边有序情况处理
                //目标在右边有序区间中
                if (nums[mid] < target && target <= nums[n - 1]) {
                    left = mid + 1;
                } else {
                    //目标不在右边有序区间中
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

}
