package com.msk.home;

/**
 * 最长递增子序列
 */
public class LengthOfLISExample {

    /**
     * dp + 贪心 + 二分查找
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }
        int[] tail = new int[len];
        tail[0] = nums[0];
        int end = 0;
        for (int i = 1; i < len; i++) {
            //比 tail 数组实际有效的末尾的那个元素还大
            if (nums[i] > tail[end]) {
                // 直接添加在那个元素的后面，所以 end 先加 1
                tail[++end] = nums[i];
            } else {
                // 使用二分查找法，在有序数组 tail 中 找到第 1 个大于等于 nums[i] 的元素，尝试让那个元素更小
                int left = 0, right = end;
                while (left < right) {
                    int mid = left + ((right - left) >>> 1);
                    if (tail[mid] < nums[i]) {
                        // 中位数肯定不是要找的数，把它写在分支的前面
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                //因此一定能找到第 1 个大于等于 nums[i] 的元素
                tail[left] = nums[i];
            }
        }
        // 此时 end 是有序数组 tail 最后一个元素的索引,题目要求返回的是长度，因此 +1 后返回
        end++;
        return end;
    }
}
