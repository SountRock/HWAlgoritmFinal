package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MyTree<K, V> {

    Node root;
    int size;

    public V get(K key){
        List<Node<K, V>> line = new ArrayList<>();
        line.add(root);
        while (line.size() > 0){
            List<Node<K, V>> nextLine = new ArrayList<>();
            for (Node<K, V> node : line){
                if(node != null) {
                    if (node.key == key) {
                        return node.value;
                    }
                    nextLine.add(node.leftChild);
                    nextLine.add(node.rightChild);
                }
            }
            line = nextLine;
        }

        return null;
    }

    public V remove(K key){
        Node<K, V> temp = getNode(key);
        if(temp == null){
            return null;
        }

        size--;
        V value = temp.value;

        temp = downInLeftEnd(temp);
        temp.leftChild = null;

        keyRebalance(root);

        return value;
    }

    /**
     * Возвращает корень дерева
     * @return
     */
    public Node<K, V> getRoot(){
        return root;
    }

    /**
     * Получить значения из дерева по ключу
     * @param key
     * @return value
     */
    public Node<K, V> getNode(K key){
        List<Node> line = new ArrayList<>();
        line.add(root);
        while (line.size() > 0){
            List<Node> nextLine = new ArrayList<>();
            for (Node node : line){
                if(node != null){
                    if(node.key == key){
                        return node;
                    }
                    nextLine.add(node.leftChild);
                    nextLine.add(node.rightChild);
                }
            }
            line = nextLine;
        }

        return null;
    }

    /**
     * Добавление значения в дерево
     * @param key
     * @param value
     * @return addSuccess
     */
    public boolean add(K key, V value){
        if(root != null){
            boolean res = addNode(root, key, value);
            root = ColourRebalance(root);
            root.color = Color.BLACK;
            keyRebalance(root);
            size++;
            return res;
        } else {
            root = new Node(key, value, Color.BLACK);
            size++;
            return true;
        }
    }

    /**
     * Добавление Node, или изменение значения Node, если уже существует вводимый ключ в дереве.
     * @param node
     * @param key
     * @param value
     * @return addSuccess
     */
    private boolean addNode(Node<K, V> node, K key, V value){
        if(node.key == key){
            getNode(key).value = value;
            return false;
        } else {
            if(node.rightChild != null) {
                boolean res = addNode(node.rightChild, key, value);
                node.rightChild = ColourRebalance(node.rightChild);
                keyRebalance(node.rightChild);
                return res;
            } else if(node.leftChild != null){
                boolean res = addNode(node.leftChild, key, value);
                node.leftChild = ColourRebalance(node.leftChild);
                keyRebalance(node.leftChild);
                return res;
            } else {
                node.leftChild = new Node(key, value);

                return true;
            }
        }
    }

    /**
     * Ребаллансировка дерева по цвету
     * @param root
     * @return newRoot
     */
    private Node<K, V> ColourRebalance(Node<K, V> root){
        Node<K, V> res = root;
        boolean needRebalance;
        do{
            needRebalance = false;
            if(res.rightChild != null && res.rightChild.color == Color.RED &&
                    (res.leftChild == null || res.leftChild.color == Color.BLACK)){
                needRebalance = true;
                res = rightSwap(res);
            }
            if(res.leftChild != null && res.leftChild.color == Color.RED &&
                    res.leftChild.leftChild != null && res.leftChild.leftChild.color == Color.RED){
                needRebalance = true;
                res = leftSwap(res);
            }
            if(res.leftChild != null && res.leftChild.color == Color.RED &&
                    res.rightChild != null && res.rightChild.color == Color.RED){
                needRebalance = true;
                colorSwap(res);
            }
        } while (needRebalance);

        return res;
    }

    /**
     * Ребалансировка дерева по значению ключей, без именения формы дерева
     * @param root
     */
    private void keyRebalance(Node<K, V> root){
        List<Node<K, V>> line = new ArrayList<>();
        line.add(root);

        for (int i = 0; i < size / 2; i++) {
            List<Node<K, V>> nextLine = new ArrayList<>();
            for (Node<K, V> elem : line){
                if(elem != null){
                    triangleBalancing(elem);

                    nextLine.add(elem.leftChild);
                    nextLine.add(elem.rightChild);
                }
            }
            line = nextLine;
        }
    }

    /**
     * Сдвиг в лево по дереву
     * @param root
     * @return newRoot
     */
    private Node<K, V> leftSwap(Node<K, V> root){
        Node<K, V> left = root.leftChild;
        Node<K, V> beet = left.rightChild;

        /*
        try {
            System.out.println("node: " + node.key +
                    ", left: " + node.leftChild.key +
                    ", right: " + node.rightChild.key);
            System.out.println("left: " + left.key +
                    ", left: " + left.leftChild.key +
                    ", right: " + left.rightChild.key);
        } catch (NullPointerException e) {}
         */

        left.rightChild = root;

        /*
        try {
            System.out.println("node: " + node.key +
                    ", left: " + node.leftChild.key +
                    ", right: " + node.rightChild.key);
            System.out.println("left: " + left.key +
                    ", left: " + left.leftChild.key +
                    ", right: " + left.rightChild.key);
        }catch (NullPointerException e) {}
         */

        root.leftChild = beet;

        /*
        try {
            System.out.println("node: " + node.key +
                    ", left: " + node.leftChild.key +
                    ", right: " + node.rightChild.key);
            System.out.println("left: " + left.key +
                    ", left: " + left.leftChild.key +
                    ", right: " + left.rightChild.key);
        }catch (NullPointerException e) {}
         */

        left.color = root.color;
        root.color = Color.RED;
        return left;
    }

    /**
     * Сдвиг в право по дереву
     * @param root
     * @return newRoot
     */
    private Node<K, V> rightSwap(Node<K, V> root){
        Node<K, V> right = root.rightChild;
        Node<K, V> beet = right.leftChild;

        /*
        try {
            System.out.println("node: " + node.key +
                    ", left: " + node.leftChild.key +
                    ", right: " + node.rightChild.key);
            System.out.println("right: " + right.key +
                    ", left: " + right.leftChild.key +
                    ", right: " + right.rightChild.key);
        } catch (NullPointerException e) {}
         */

        right.leftChild = root;

        /*
        try {
            System.out.println("node: " + node.key +
                    ", left: " + node.leftChild.key +
                    ", right: " + node.rightChild.key);
            System.out.println("right: " + right.key +
                    ", left: " + right.leftChild.key +
                    ", right: " + right.rightChild.key);
        } catch (NullPointerException e) {}
         */

        root.rightChild = beet;

        /*
        try {
            System.out.println("node: " + node.key +
                    ", left: " + node.leftChild.key +
                    ", right: " + node.rightChild.key);
            System.out.println("right: " + right.key +
                    ", left: " + right.leftChild.key +
                    ", right: " + right.rightChild.key);
        } catch (NullPointerException e) {}
         */

        right.color = root.color;
        root.color = Color.RED;
        return right;
    }

    /**
     * Меняет цвета ноды и ее детей
     * @param node
     */
    private void colorSwap(Node node){
        node.leftChild.color = Color.BLACK;
        node.rightChild.color = Color.BLACK;
        node.color = Color.RED;
    }

    /**
     * Смещает значения node-ов опуская вводимое значения в левый конец дерева
     * @param node
     * @return
     */
    private Node<K, V> downInLeftEnd(Node<K, V> node){
        for (Node<K, V> index = node; index.leftChild != null; index = index.leftChild){
            V tempValue = index.value;
            K tempKey = index.key;

            index.value = index.leftChild.value;
            index.key = index.leftChild.key;

            index.leftChild.value = tempValue;
            index.leftChild.key = tempKey;
        }

        return node;
    }

    /**
     * Меняет местами параметры элементов
     * @param node1
     * @param node2
     */
    private void swapElems(Node<K, V> node1, Node<K, V> node2){
        V tempValue = node1.value;
        K tempKey = node1.key;

        node1.value = node2.value;
        node1.key = node2.key;

        node2.value = tempValue;
        node2.key = tempKey;
    }

    /**
     * Операция баллансировки треуголька из вводимой Node и его его детей.
     * @param node
     */
    public void triangleBalancing(Node<K, V> node){
        List<Node<K, V>> triangle = new ArrayList<>();
        if(node.leftChild != null){
            triangle.add(node.leftChild);
        }
        triangle.add(node);
        if(node.rightChild != null){
            triangle.add(node.rightChild);
        }

        /*
        System.out.print("triangle In: ");
        for(int i = 0; i < triangle.size(); i++) {
            System.out.print(triangle.get(i).key + " ");
        }
        System.out.println();
         */

        boolean needRebalance = true;
        while (needRebalance){
            needRebalance = false;
            for (int i = 1; i < triangle.size(); i++) {
                if(triangle.get(i - 1).key.hashCode() > triangle.get(i).key.hashCode()){
                    swapElems(triangle.get(i - 1), triangle.get(i));
                    needRebalance = true;
                }
            }
        }
    }

    public static class Node<K, V>{
        private K key;
        private V value;
        private Node<K, V> leftChild, rightChild;
        private Color color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            color = Color.RED;
        }

        public Node(K key, V value, Color color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public Node<K, V> getLeftChild() {
            return leftChild;
        }

        public Node<K, V> getRightChild() {
            return rightChild;
        }

        public Color getColor() {
            return color;
        }
    }
}

