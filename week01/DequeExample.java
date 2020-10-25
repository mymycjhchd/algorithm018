package com.msk.home;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 用Deque的新API方法操作Deque
 */
public class DequeExample {

    /**
     * Throws exception--addFirst, Special value--offerFirst
     * addFirst,removeFirst,getFirst || offerFirst,pollFirst,peekFirst
     *
     * @param args
     */
    public static void main(String[] args) {
        Deque<String> deque = new LinkedList<>();
        deque.offerFirst("a");
        deque.offerLast("b");
        deque.addFirst("c");
        System.out.println(deque);
        String s = deque.peekFirst();
        System.out.println(s);
        System.out.println(deque);
        String s1 = deque.peekLast();
        System.out.println(s1);
        System.out.println(deque);
        while (deque.size() > 0) {
            System.out.println(deque.pollFirst());
            System.out.println(deque.pollLast());
        }
        System.out.println(deque);
    }

}
