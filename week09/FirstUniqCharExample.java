package com.msk.home;

import java.util.HashMap;

/**
 * 字符串中的第一个唯一字符
 */
public class FirstUniqCharExample {

    /**
     * 哈希表法
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            hashMap.put(ch, hashMap.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (hashMap.get(s.charAt(i)) == 1) return i;
        }
        return -1;
    }
}
