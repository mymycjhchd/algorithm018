package com.msk.home;

/**
 * 爬楼梯
 */
public class ClimbStairsExample {

    /**
     * 爬楼梯问题方法一动态规划
     *
     * @param n
     * @return
     */
    public int climbStairs1(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }


    /**
     * 爬楼梯问题方法二斐波那契数
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1 || n == 2) return n;
        int res = 0;
        int a = 1;
        int b = 2;
        for (int i = 3; i <= n; i++) {
            res = a + b;
            a = b;
            b = res;
        }
        return res;
    }

}
