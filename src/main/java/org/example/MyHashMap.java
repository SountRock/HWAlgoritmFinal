package org.example;

public class MyHashMap<K, V> {

    Node<K, V>[] arr;
    private int size = 0;
    static final int HASH_MAP_SIZE =16;
    public MyHashMap() {
        arr = new Node[HASH_MAP_SIZE];
    }

    public MyHashMap(int size) {
        arr = new Node[size];
    }

    public Object put(Object key, Object value){
        Node tmp = new Node(key, value, key.hashCode());
        int i = Math.abs(key.hashCode()) % arr.length;
        V ret = null;
        if(arr[i] == null) {
            size++;
            arr[i] = tmp;
            return null;
        }
        ret = arr[i].getValue();
        arr[i] = tmp;

        return ret;
    }

    public V get(Object key){
        int i = Math.abs(key.hashCode()) % arr.length;
        if (arr[i]!= null) return arr[i].value;
        return null;
    }

    public V remove(Object key){
        int i = Math.abs(key.hashCode()) % arr.length;
        if(arr[i] == null) {
            return null;
        };
        size--;
        V tmp = arr[i].value;
        arr[i] = null;

        return tmp;
    }

    public int getSize() {
        return size;
    }

    static class Node<K, V>{
        K key;
        V value;
        final int hash;

        public Node(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        public V getValue() {
            return value;
        }
    }

}
