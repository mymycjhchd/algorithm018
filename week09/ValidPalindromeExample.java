package com.msk.home;

/**
 * 验证回文字符串②
 */
public class ValidPalindromeExample {

    /**
     * 双指针法
     *
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                boolean b1 = true, b2 = true;
                //left + 1 ~ right
                for (int i = left + 1, j = right; i < j; i++, j--) {
                    if (s.charAt(i) != s.charAt(j)) {
                        b1 = false;
                        break;
                    }
                }
                //left ~ right - 1
                for (int i = left, j = right - 1; i < j; i++, j--) {
                    if (s.charAt(i) != s.charAt(j)) {
                        b2 = false;
                        break;
                    }
                }
                return b1 || b2;
            }
        }
        return true;
    }
}
