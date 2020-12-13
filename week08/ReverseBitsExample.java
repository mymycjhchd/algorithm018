package com.msk.home;

/**
 * 颠倒二进制位
 */
public class ReverseBitsExample {


    /**
     * 按位反转法
     * 直接颠倒计算每一位的数字
     *
     * @param n
     * @return
     */
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i <= 31; i++) {
            res ^= (n & (1 << i)) != 0 ? 1 << (31 - i) : 0;
        }
        return res;
    }

}
