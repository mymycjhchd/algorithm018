package com.msk.home;

public class MaximalSquareExample {

    /**
     * 最大正方形
     * DP大法
     * 则 dp(i, j) 的值由其上方、左方和左上方的三个相邻位置的 dp 值决定。具体而言，当前位置的元素值等于三个相邻位置的元素中的最小值加 1，状态转移方程如下
     * dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        int maxSize = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                    maxSize = Math.max(maxSize, dp[i][j]);
                }
            }
        }
        return maxSize * maxSize;
    }


}
