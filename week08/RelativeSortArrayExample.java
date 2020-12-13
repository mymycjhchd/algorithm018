package com.msk.home;

/**
 * 数组的相对排序
 */
public class RelativeSortArrayExample {

    /**
     * 计数排序法
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int max = 0;
        //找到arr1数组中最大的元素，用于初始化频率数组长度
        for (int x : arr1) {
            max = Math.max(max, x);
        }
        //frequency数组记录每一个元素在数组arr1中出现的次数
        int[] frequency = new int[max + 1];
        for (int x : arr1) {
            frequency[x]++;
        }
        int index = 0;
        //返回结果数组
        int[] res = new int[arr1.length];
        //遍历arr2数组，将frequency[x]个x 加入答案中，并将frequency[x]清零。
        for (int x : arr2) {
            for (int i = 0; i < frequency[x]; i++) {
                res[index++] = x;
            }
            frequency[x] = 0;
        }
        //此时还剩下没有在arr2中出现过的元素
        for (int i = 0; i < frequency.length; i++) {
            for (int j = 0; j < frequency[i]; j++) {
                res[index++] = i;
            }
        }
        return res;
    }

}
