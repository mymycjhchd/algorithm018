package com.msk.home;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 单词接龙--双向DFS
 */
public class LadderLengthExample {

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        // 第 1 步：先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) return 0;
        // 第 2 步：已经访问过的 word 添加到 visited 哈希表里
        Set<String> visited = new HashSet<>();
        // 分别用左边和右边扩散的哈希表代替单向 BFS 里的队列，它们在双向 BFS 的过程中交替使用
        Set<String> start = new HashSet<>();
        start.add(beginWord);
        Set<String> end = new HashSet<>();
        end.add(endWord);
        // 第 3 步：执行双向 BFS，左右交替扩散的步数之和为所求
        int step = 1;
        while (!start.isEmpty() && !end.isEmpty()) {
            // 优先选择小的哈希表进行扩散，考虑到的情况更少
            if (start.size() > end.size()) {
                Set<String> temp = start;
                start = end;
                end = temp;
            }
            // 逻辑到这里，保证 beginVisited 是相对较小的集合，nextLevelVisited 在扩散完成以后，会成为新的 beginVisited
            Set<String> nextLevelVisited = new HashSet<>();
            for (String word : start) {
                // 如果 currentWord 能够修改 1 个字符与 endWord 相同，则返回 step + 1
                if (changeWordEveryOneLetter2(word, end, visited, wordSet, nextLevelVisited)) return step + 1;
            }
            // 原来的 beginVisited 废弃，从 nextLevelVisited 开始新的双向 BFS
            start = nextLevelVisited;
            step++;
        }
        return 0;
    }

    //尝试对word修改每一个字符，看看是不是能落在end中，扩展得到的新的word添加到nextLevelVisited里
    private boolean changeWordEveryOneLetter2(String word, Set<String> end, Set<String> visited, Set<String> wordSet, Set<String> nextLevelVisited) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            char originChar = chars[i];
            for (char j = 'a'; j < 'z'; j++) {
                if (originChar == j) continue;
                chars[i] = j;
                String nextWord = String.valueOf(chars);
                if (wordSet.contains(nextWord)) {
                    if (end.contains(nextWord)) return true;
                    if (!visited.contains(nextWord)) {
                        nextLevelVisited.add(nextWord);
                        visited.add(nextWord);
                    }
                }
            }
            // 恢复，下次再用
            chars[i] = originChar;
        }
        return false;
    }
}
