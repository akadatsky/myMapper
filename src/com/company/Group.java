package com.company;

public class Group {

    private String name;

    private int count;

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
