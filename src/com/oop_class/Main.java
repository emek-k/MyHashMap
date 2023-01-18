package com.oop_class;

public class Main {

    public static void main(String[] args) {

        MyHashTable map = new MyHashTable(3);


        map.put(1,"aa");
        map.put(2,"bb");
        map.put(3,"cc");
        map.put(4,"dd");
        map.put(5,"ee");
        map.put(6,"ff");

        map.show();
        map.get(3);
        map.remove(5);
        map.show();
    }
}
