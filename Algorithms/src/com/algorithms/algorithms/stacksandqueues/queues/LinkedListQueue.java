package com.algorithms.algorithms.stacksandqueues.queues;

import com.algorithms.algorithms.stacksandqueues.queues.interfaces.IQueue;

/**
 * Created by Who on 2014/4/19.
 */
public class LinkedListQueue<T> implements IQueue<T> {

    private Node<T> top = null;
    private Node<T> bottom = null;

    @Override
    public void enqueue(T item) {
        Node oldBottom = bottom;
        bottom = new Node();
        bottom.setItem(item);
        if (oldBottom != null) {
            oldBottom.setNext(bottom);
        } else {
            top = bottom;
        }
    }

    @Override
    public T dequeue() {
        if (top == null) {
            return null;
        }
        T item = top.getItem();
        top = top.getNext();
        if (top == null) {
            bottom = null;
        }
        return item;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public String getQueueItems() {
        if (isEmpty()) {
            return "Get queue items failed. Linked list queue is empty now.";
        }
        return getString(top);
    }

    private String getString(Node node) {
        String string = "";
        if (node == null) {
            return string;
        }
        string = node.getItem().toString() + " ";
        if (node.getNext() != null) {
            string = getString(node.getNext()) + string;
        }
        return string;
    }

    private class Node<T> {
        T item = null;
        Node next = null;

        public T getItem() {
            return item;
        }

        public void setItem(T item) {
            this.item = item;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}