package com.serializer.utils;

import java.util.ArrayList;
import java.util.List;

public class GcInfo {
    List<SingleGcCollection> collections = new ArrayList<>();
    public void addGcCollection(String name, long collectionCount, long collectionTime) {
        this.collections.add(new SingleGcCollection(name, collectionCount, collectionTime));
    }
    public List<SingleGcCollection> getCollections() {
        return this.collections;
    }
    public void printGcInfo() {
        this.collections.stream().forEach(System.out::println);
    }
    private class SingleGcCollection {
        final String name;
        final long collectionTime;
        final long collectionCount;
        public SingleGcCollection(String name, long collectionCount, long collectionTime) {
            this.name = name;
            this.collectionCount = collectionCount;
            this.collectionTime = collectionTime;
        }

        @Override
        public String toString() {
            return String.format("%s: %s times %s ms", this.name, this.collectionCount, this.collectionTime);
        }
    }
}
