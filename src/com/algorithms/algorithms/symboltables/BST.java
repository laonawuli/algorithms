package com.algorithms.algorithms.symboltables;

import com.algorithms.algorithms.stacksandqueues.queues.LinkedListQueue;

/**
 * Created by Who on 2014/5/6.
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node root;

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value);
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult > 0) {
            node.right = put(node.right, key, value);
        } else if (compareResult < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.value = value;
        }
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    public Value get(Key key) {
        Node node = root;
        while (node != null) {
            int compareResult = key.compareTo(node.key);
            if (compareResult < 0) {
                node = node.left;
            } else if (compareResult > 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    public Key floor(Key key) {
        Node node = getFloor(root, key);
        if (node != null) {
            return node.key;
        }
        return null;
    }

    private Node getFloor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult > 0) {
            Node t = getFloor(node.right, key);
            if (t != null) {
                return t;
            }
            return node;
        } else if (compareResult < 0) {
            return getFloor(node.left, key);
        } else {
            return node;
        }
    }

    public Key ceiling(Key key) {
        Node node = getCeiling(root, key);
        if (node != null) {
            return node.key;
        }
        return null;
    }

    private Node getCeiling(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult > 0) {
            return getCeiling(node.right, key);
        } else if (compareResult < 0) {
            Node t = getCeiling(node.left, key);
            if (t != null) {
                return t;
            }
            return node;
        } else {
            return node;
        }
    }

    public int rank(Key key) {
        return getRank(root, key);
    }

    private int getRank(Node node, Key key) {
        if (node == null) {
            return 0;
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult > 0) {
            return 1 + size(node.left) + getRank(node.right, key);
        } else if (compareResult < 0) {
            return getRank(node.left, key);
        } else {
            return size(node.left);
        }
    }

    public Key min() {
        Node node = getMin(root);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    private Node getMin(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        } else {
            return getMin(node.left);
        }
    }

    public Key max() {
        Node node = getMax(root);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    private Node getMax(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node;
        } else {
            return getMax(node.right);
        }
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    public Node deleteMin(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left != null) {
            node.left = deleteMin(node.left);
        } else {
            return node.right;
        }
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            node.right = deleteMax(node.right);
        } else {
            return node.left;
        }
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult > 0) {
            node.right = delete(node.right, key);
        } else if (compareResult < 0) {
            node.left = delete(node.left, key);
        } else {
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            Node t = node;
            node = getMin(t.right);
            node.right = deleteMin(node.right);
            node.left = t.left;
        }
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.count;
    }

    public Iterable<Key> iterator(OrderType orderType) {
        LinkedListQueue<Key> queue = new LinkedListQueue<Key>();
        if (orderType == OrderType.PRE_ORDER) {
            preorder(queue, root);
        } else if (orderType == OrderType.IN_ORDER) {
            inorder(queue, root);
        } else if (orderType == OrderType.POST_ORDER) {
            postorder(queue, root);
        }
        return queue;
    }

    private void inorder(LinkedListQueue<Key> linkedListQueue, Node node) {
        if (node == null) {
            return;
        }
        inorder(linkedListQueue, node.left);
        linkedListQueue.enqueue(node.key);
        inorder(linkedListQueue, node.right);
    }

    private void preorder(LinkedListQueue<Key> linkedListQueue, Node node) {
        if (node == null) {
            return;
        }
        linkedListQueue.enqueue(node.key);
        preorder(linkedListQueue, node.left);
        preorder(linkedListQueue, node.right);
    }

    private void postorder(LinkedListQueue<Key> linkedListQueue, Node node) {
        if (node == null) {
            return;
        }
        postorder(linkedListQueue, node.left);
        postorder(linkedListQueue, node.right);
        linkedListQueue.enqueue(node.key);
    }

    //Unuseful. It can not implement the count field.
    public void putMyMethod(Key key, Value value) {
        if (root == null) {
            root = new Node(key, value);
            return;
        }
        Node node = root;
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            } else {
                if (node.key.compareTo(key) > 0) {
                    if (node.left == null) {
                        node.left = new Node(key, value);
                        node.count++;
                        return;
                    }
                    node = node.left;
                } else {
                    if (node.right == null) {
                        node.right = new Node(key, value);
                        node.count++;
                        return;
                    }
                    node = node.right;
                }
            }
        }
    }

    //It works.
    public Value getMyMethod(Key key) {
        Node node = root;
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            } else {
                if (node.key.compareTo(key) > 0) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
        }
        return null;
    }

    //It works, but not recursively.
    public Key floorMyMethod(Key key) {
        Node node = root;
        Node found = null;
        while (node != null) {
            int compareResult = key.compareTo(node.key);
            if (compareResult > 0) {
                found = node;
                node = node.right;
            } else if (compareResult < 0) {
                node = node.left;
            } else {
                return key;
            }
        }
        if (found == null) {
            return null;
        }
        return found.key;
    }

    //It works, but not recursively.
    public Key ceilingMyMethod(Key key) {
        Node node = root;
        Node found = null;
        while (node != null) {
            int compareResult = key.compareTo(node.key);
            if (compareResult > 0) {
                node = node.right;
            } else if (compareResult < 0) {
                found = node;
                node = node.left;
            } else {
                return key;
            }
        }
        if (found == null) {
            return null;
        }
        return found.key;
    }

    private class Node {
        private Key key = null;
        private Value value = null;
        private Node left = null;
        private Node right = null;
        private int count = 1;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public enum OrderType {
        PRE_ORDER,
        IN_ORDER,
        POST_ORDER
    }
}
