package classes.main;

import java.util.HashMap;
import java.util.LinkedList;

public class HashMapList<K, V> { // K - klucz, V - value
    private HashMap<K, LinkedList<V>> HashMapList;

    public HashMapList(){
        HashMapList = new HashMap<>();
    }


    public void put(K key, V object){
        if(this.HashMapList.containsKey(key)) // jak jest cos pod kluczem to dopisuje do listy
            this.HashMapList.get(key).add(object); // jak wyciagne liste i wsadze do niej object to sie zaktualizuje

        else { // nie ma pod kluczem to tworze liste, wsadzam do listy i dopisuje
            LinkedList<V> newList = new LinkedList<>();
            newList.add(object);
            this.HashMapList.put(key, newList);
        }
    }

    public void remove(K key, V object) {
        if(this.HashMapList.get(key) == null)
            return; // nie ma nic pod kluczem
        this.HashMapList.get(key).remove(object);
        if(this.HashMapList.get(key).isEmpty()) // pusta lista
            this.HashMapList.remove(key);
    }

    public LinkedList<V> get(K key) {
        if(!this.HashMapList.containsKey(key))
            return null;
        return this.HashMapList.get(key);
    }

    public LinkedList<V> allElementsList(){
        var result = new LinkedList<V>();
        this.HashMapList.values().forEach(result::addAll);
        return result;
    }

    public int size(){
        return this.HashMapList.size();
    }

    public HashMap<K, LinkedList<V>> getMap(){
        return this.HashMapList;
    }
}