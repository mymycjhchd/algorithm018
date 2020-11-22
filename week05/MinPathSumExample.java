package com.msk.home;

/**
 * 最小路径和
 */
public class MinPathSumExample {

    /**
     * 最小路径和
     * 用DP
     *
     * @param grid
     * @return
     */
    public int minPathSum1(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) dp[i][j] = grid[i][j];
                else if (i == 0) dp[i][j] = grid[i][j] + dp[i][j - 1];
                else if (j == 0) dp[i][j] = grid[i][j] + dp[i - 1][j];
                else dp[i][j] = grid[i][j] + Math.min(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 最小路径和-优化
     * 其实我们完全不需要建立 dp 矩阵浪费额外空间，直接遍历 grid[i][j] 修改即可
     * 由于原 grid 矩阵元素中被覆盖为 dp 元素后（都处于当前遍历点的左上方），不会再被使用到。
     *
     * @param grid
     * @return
     */
    public int minPathSum2(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) continue;
                else if (i == 0) grid[0][j] = grid[0][j] + grid[0][j - 1];
                else if (j == 0) grid[i][0] = grid[i][0] + grid[i - 1][0];
                else grid[i][j] = grid[i][j] + Math.min(grid[i][j - 1], grid[i - 1][j]);
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    /**
     * 最小路径和-优化
     * 基于2的写法，进一步优化，加快速速
     *
     * @param grid
     * @return
     */
    public int minPathSum3(int[][] grid) {
        //处理上边界
        for (int i = 1; i < grid[0].length; i++) {
            grid[0][i] = grid[0][i] + grid[0][i - 1];
        }
        //处理左边界
        for (int i = 1; i < grid.length; i++) {
            grid[i][0] = grid[i][0] + grid[i - 1][0];
        }
        //处理中间
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                grid[i][j] = grid[i][j] + Math.min(grid[i][j - 1], grid[i - 1][j]);
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

}
