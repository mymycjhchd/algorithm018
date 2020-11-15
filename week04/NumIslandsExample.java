package com.msk.home;

/**
 * 岛屿数量
 * 岛屿问题-DFS通用解法，从二叉树到网格（类似图）
 */
public class NumIslandsExample {

    /**
     * DFS通用解法
     *
     * @param grid 网格
     * @return 岛屿数量
     */
    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 49) {
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 深度优先遍历
     *
     * @param grid 网格
     * @param x    网格的x坐标轴
     * @param y    网格的y坐标轴
     */
    private void dfs(char[][] grid, int x, int y) {
        //若不在方格内
        if (!inArea(grid, x, y)) return;
        //如果被遍历过了就直接返回，防止重复遍历
        if (grid[x][y] != 49) return;
        //遍历过的岛屿做个标记
        grid[x][y] = 50;
        //上下左右遍历
        dfs(grid, x, y + 1);
        dfs(grid, x, y - 1);
        dfs(grid, x - 1, y);
        dfs(grid, x + 1, y);
    }

    /**
     * 判断是否超过了网格边界
     *
     * @param grid 网格
     * @param x    网格的x坐标轴
     * @param y    网格的y坐标轴
     * @return
     */
    private boolean inArea(char[][] grid, int x, int y) {
        return x > 0 && grid.length > x && y > 0 && grid[0].length > y;
    }

}
