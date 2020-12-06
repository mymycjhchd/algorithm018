package com.msk.home;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成DFS 加法
 */
public class GenerateParenthesisExample {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        dfs("", n, n, res);
        return res;
    }

    private void dfs(String curStr, int left, int right, List<String> res) {
        //因为每一次尝试，都使用新的字符串变量，所以无需回溯
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }
        //减枝 左括号可以使用的个数严格大于右括号可以使用的个数
        if (left > right) return;
        if (left > 0) dfs(curStr + "(", left - 1, right, res);
        if (right > 0) dfs(curStr + ")", left, right - 1, res);
    }

}
