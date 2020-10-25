package com.msk.exercises;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 双端队列
 */
public class MyCircularDeque {

    /**
     * 容量
     */
    private int capacity;

    private DoubleNode head;

    private DoubleNode tail;

    int size;

    /**
     * 构造函数,双端队列的大小为k
     */
    public MyCircularDeque(int k) {
        head = new DoubleNode(-1);
        tail = new DoubleNode(-1);
        head.pre = tail;
        tail.next = head;
        this.capacity = k;
        this.size = 0;
    }

    /**
     * 将一个元素添加到双端队列头部。 如果操作成功返回 true。
     *
     * @param value
     * @return
     */
    public boolean insertFront(int value) {
        if (capacity == size) return false;
        DoubleNode node = new DoubleNode(value);
        node.next = head;
        node.pre = head.pre;
        head.pre.next = node;
        head.pre = node;
        size++;
        return true;
    }

    /**
     * 将一个元素添加到双端队列尾部。如果操作成功返回 true。
     *
     * @param value
     * @return
     */
    public boolean insertLast(int value) {
        if (capacity == size) return false;
        DoubleNode node = new DoubleNode(value);
        node.next = tail.next;
        tail.next.pre = node;
        tail.next = node;
        node.pre = tail;
        size++;
        return true;
    }

    /**
     * 从双端队列头部删除一个元素。 如果操作成功返回 true。
     *
     * @return
     */
    public boolean deleteFront() {
        if (size == 0)
            return false;
        head.pre.pre.next = head;
        head.pre = head.pre.pre;
        size--;
        return true;
    }

    /**
     * 从双端队列尾部删除一个元素。如果操作成功返回 true。
     */
    public boolean deleteLast() {
        if (size == 0)
            return false;
        tail.next.next.pre = tail;
        tail.next = tail.next.next;
        size--;
        return true;
    }

    /**
     * 从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
     */
    public int getFront() {
        if (isEmpty()) return -1;
        return head.pre.data;
    }

    /**
     * 获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
     */
    public int getRear() {
        if (isEmpty()) return -1;
        return tail.next.data;
    }

    /**
     * 检查双端队列是否为空。
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 检查双端队列是否满了。
     */
    public boolean isFull() {
        return size == capacity;
    }


}

class DoubleNode {
    int data;

    DoubleNode next;

    DoubleNode pre;

    DoubleNode() {

    }

    DoubleNode(int data) {
        this.data = data;
    }
}
