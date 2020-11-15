package com.msk.home;

/**
 * 买卖股票的最佳时机-2
 * 买卖股票是一个系列问题--通用解法为动态规划
 */
public class MaxProfitExample {

    /**
     * 思路1：因为已经提前知道每天的价格，若连续上涨则每天都买卖股票，若连续下跌则不买卖股票
     *
     * @param nums
     * @return
     */
    public int maxProfit(int[] nums) {
        int res = 0;
        int temp = 0;
        for (int i = 1; i < nums.length; i++) {
            temp = nums[i] - nums[i - 1];
            if (temp > 0) res += temp;
        }
        return res;
    }

    /**
     * 动态规划
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        //状态转移：cash → hold → cash → hold → cash → hold → cash
        //持有的现金
        int cash = 0;
        //持有的股票
        int hold = -prices[0];
        int preCash = cash;
        int preHold = hold;
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(preCash, preHold + prices[i]);
            hold = Math.max(preHold, preCash - prices[i]);
            preCash = cash;
            preHold = hold;
        }
        return cash;
    }

}
