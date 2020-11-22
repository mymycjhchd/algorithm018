package com.msk.home;

import java.util.HashMap;

/**
 * 解码方法
 */
public class NumDecodingsExample {

    /**
     * 解码方法
     * 递归+缓存（记忆），因为递归树中有很多是重复的，所以加缓存来避免重复计算
     *
     * @param s
     * @return
     */
    public int numDecodings1(String s) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int res = getAns(s, 0, hashMap);
        return res;
    }

    private int getAns(String s, int start, HashMap<Integer, Integer> hashMap) {
        //划分到了最后返回 1
        if (start == s.length()) return 1;
        //开头是 0,0 不对应任何字母，直接返回 0
        if (s.charAt(start) == '0') return 0;
        //判断之前是否计算过
        int memory = hashMap.getOrDefault(start, -1);
        if (memory != -1) {
            return memory;
        }
        //得到第一种的划分的解码方式
        int ans1 = getAns(s, start + 1, hashMap), ans2 = 0;
        //判断前两个数字是不是小于等于 26 的
        if (start < s.length() - 1) {
            int ten = (s.charAt(start) - '0') * 10;
            int one = s.charAt(start + 1) - '0';
            if (ten + one <= 26) {
                ans2 = getAns(s, start + 2, hashMap);
            }
        }
        //将结果保存
        hashMap.put(start, ans1 + ans2);
        return ans2 + ans1;
    }

    /**
     * 解码方法
     * DP大法
     * 同样的，递归就是压栈压栈压栈，出栈出栈出栈的过程，我们可以利用动态规划的思想，省略压栈的过程，直接从 bottom 到 top。
     * 用一个 dp 数组， dp [ i ] 代表字符串 s [ i, s.len-1 ]，也就是 s 从 i 开始到结尾的字符串的解码方式。
     * 这样和递归完全一样的递推式。
     * 如果 s [ i ] 和 s [ i + 1 ] 组成的数字小于等于 26，那么
     * dp [ i ] = dp[ i + 1 ] + dp [ i + 2 ]
     *
     * @param s
     * @return
     */
    public int numDecodings2(String s) {
        int len = s.length();
        int end = 1;
        int cur = 0;
        if (s.charAt(len - 1) != '0') {
            cur = 1;
        }
        for (int i = len - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                end = cur;//end 前移
                cur = 0;
                continue;
            }
            int ans1 = cur;
            int ans2 = 0;
            int ten = (s.charAt(i) - '0') * 10;
            int one = s.charAt(i + 1) - '0';
            if (ten + one <= 26) {
                ans2 = end;
            }
            end = cur; //end 前移
            cur = ans1 + ans2;

        }
        return cur;
    }

}
