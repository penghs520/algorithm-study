package com.algorithm.cache.lru;

import java.util.HashMap;

/**
 * 基于自定义哈希链表的LRU缓存
 */
public class SimpleLruCache<K, V> {


    private Node<K, V> head;

    private Node<K, V> tail;

    private int limit;

    private HashMap<K, Node<K, V>> cache;

    public SimpleLruCache(int limit) {
        this.limit = limit;
        cache = new HashMap<>();
    }


    public V get(K key) {
        Node<K, V> node = cache.get(key);
        if (node == null) {
            return null;
        }
        refreshNode(node);
        return node.getValue();
    }

    public void put(K key, V value) {
        Node<K, V> node = cache.get(key);
        if (node == null) {
            if (cache.size() >= limit) {
                K oldKey = removeNode(head);
                cache.remove(oldKey);
            }
            node = new Node<>(key, value);
            addNode(node);
            cache.put(key, node);
        } else {
            node.setValue(value);
            refreshNode(node);
        }
    }

    public V remove(K key) {
        Node<K, V> node = cache.get(key);
        removeNode(node);
        return node.getValue();
    }


    private void refreshNode(Node<K, V> node) {
        //如果是队尾节点，什么都不做
        if (node == tail) {
            return;
        }
        //移除
        removeNode(node);
        //重新添加
        addNode(node);
    }

    private void addNode(Node<K, V> node) {
        if (tail != null) {
            tail.next = node;
            node.pre = tail;
            node.next = null;
        }
        tail = node;
        if (head == null) {
            head = node;
        }
    }

    private K removeNode(Node<K, V> node) {
        if (node == head && node == tail) {
            head = null;
            tail = null;
        } else if (node == tail) {
            tail = tail.pre;
            tail.next = null;
        } else if (node == head) {
            head = head.next;
            head.pre = null;
        } else {
            //移除中间节点
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.getKey();
    }


    private static class Node<K, V> {

        private Node<K, V> pre;

        private Node<K, V> next;

        private K key;

        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getPre() {
            return pre;
        }

        public void setPre(Node<K, V> pre) {
            this.pre = pre;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }
    }
}

