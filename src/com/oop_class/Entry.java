package com.oop_class;

public class Entry <K, V>{
    public K key;
    public V value;
    public Entry<K, V> next;

    public Entry(K key, V value, Entry<K, V> next){
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
