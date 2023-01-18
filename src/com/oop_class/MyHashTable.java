package com.oop_class;

import java.util.Objects;

public class MyHashTable<K, V> {

    private final Entry[] table;
    private int size = 16;

    public MyHashTable(){
        table = new Entry[size];
    }

    public MyHashTable(int newSize){
        this.size = newSize;
        table = new Entry[size];
    }

    private int hash(K key){
        if(key == null)
            return 0;
        int result = 19 * 31 * Objects.hash(key);
        return result % size;
    }

    public void put(K key, V value){
        //tworzymy nowe wejscie
        Entry entry = new Entry(key, value, null);
        //ustalamy index haszujac key
        int index = hash(key);
        //jezeli w tabeli jest null to dodajemy poprostu nowe wejscie
        if(table[index] == null)
            table[index] = entry;
        else{
            //cos juz jest w tabeli, dodajemy element do pola next
            //jezeli key jest w tabeli to zamieniamy element z takim samym key
            Entry previous = null;
            Entry current = table[index];

            //sprawdzamy czy klucz juz jest w liscie i jak go znajdziemy to aktualizujemy value
            while(current != null){
                if(current.key == key) {
                    current.value = value;
                    break;
                }
                previous = current;
                current = previous.next;
            }
            //ustawiamy nowe wejscie
            if(previous != null)
                previous.next = entry;
        }
    }

    public void get(K key){
        //sprawdzmy odpowiedni index tabeli i potem sprawdzamy liste czy jest tam nasz szukany element
        V value = null;
        //ustalamy index haszujac key
        int index = hash(key);
        Entry entry = table[index];

        //sprawdzamy liste w poszukiwaniu odpowiedniego klucza
        while(entry != null){
            if(entry.key == key) {
                System.out.println("Element o kluczu " + entry.key + " ma wartosc: " + entry.value);
                return;
            }
            entry = entry.next;
        }
        System.out.println("Nie odnaleziono elementu.");
    }

    public void remove(K key){
        int index = hash(key);
        Entry current = table[index];
        Entry previous = null;
        //mamy do rozpatrzenia 3 przypadki
        //1) lista jest pusta - nic sie nie dzieje
        //2) element nie ma poprzednia - na miejsce elemenru trafia nastepny
        //3) element ma nastepce - usuwamy srodkowy i do previous.next = current.next;

        while(current != null){
            if(current.key == key){
                if(previous == null){
                    current = current.next;
                    table[index] = current;
                    System.out.println("Element o kluczu " + current.key + " zostal usuniety.");
                    return;
                }else {
                    previous.next = current.next;
                    System.out.println("Element o kluczu " + current.key + " zostal usuniety.");
                    return;
                }
            }
            previous = current;
            current = current.next;
        }
        System.out.println("Brak elementu o danym kluczu w hashMapie!");
    }

    public void clear(){
        //wystarczy wyzerowac cala tabele
        for(int i=0; i<size; i++)
            table[i] = null;
    }

    public void show(){
        for(int i = 0; i < size; i++) {
            System.out.println("Table[" + i + "]");
            if (table[i] != null) {
                Entry current = table[i];
                while (current != null) {
                    System.out.println("    Kluczem jest " + current.key + " z wartoscia " + current.value);
                    current = current.next;
                }
            }
            System.out.println();
        }
    }
}
