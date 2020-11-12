package com.algorithm.datastruct;

import org.omg.CORBA.Object;

import java.util.ArrayList;

/**
 * @author ：penghs
 * @date ：Created in 2020/11/12 21:15
 * @description：循环队列：让队尾指针重新指回数组的首位
 * @version: 1.0.0
 */
public class ArrayQueue {

    private int[] array;

    private int headIndex;

    private int tailIndex;

    private int size;//队列的大小比数组大小小1

    public ArrayQueue(int capacity) {
        array = new int[capacity];
    }

    public void enq(int e) {
        if ((tailIndex + 1) % array.length == headIndex) {
            throw new IllegalStateException("队列已满");
        }
        array[tailIndex] = e;
        tailIndex = (tailIndex + 1) % array.length;
    }

    public int deq() {
        if (tailIndex == headIndex) {
            throw new IllegalStateException("队列为空");
        }
        int element = array[headIndex];
        headIndex = (headIndex + 1) % array.length;
        return element;
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(7);
        queue.enq(1);
        queue.enq(2);
        queue.enq(3);
        queue.enq(4);
        queue.enq(5);
        System.out.println(queue.deq());
        System.out.println(queue.deq());
        System.out.println(queue.deq());
        System.out.println(queue.deq());
        System.out.println(queue.deq());
        queue.enq(1);
        queue.enq(2);
        queue.enq(3);
        queue.enq(4);
        queue.enq(5);
        System.out.println(queue.deq());
        System.out.println(queue.deq());
        System.out.println(queue.deq());
        System.out.println(queue.deq());
        System.out.println(queue.deq());

    }

}
