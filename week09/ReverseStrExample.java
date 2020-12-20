package com.msk.home;

/**
 * 反转字符串
 */
public class ReverseStrExample {

    /**
     * 暴力法
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i += k * 2) {
            //当当前段元素不足k个时（j取不到i+k-1了），就将剩余元素全部反转，即j=len-1；
            int m = i, n = Math.min(i + k - 1, ch.length - 1);
            while (m < n) {
                char temp = ch[m];
                ch[m++] = ch[n];
                ch[n--] = temp;
            }
        }
        return new String(ch);
    }
}
