package com.rommelrico.model.table;

import java.util.Comparator;

public class FormattableInteger implements Comparable<FormattableInteger> {

    private int size;

    public FormattableInteger(int size) {
        this.size = size;
    }

    @Override
    public int compareTo(FormattableInteger o) {
        Integer int1 = this.size;
        Integer int2 = o.size;
        return int1.compareTo(int2);
    }

    @Override
    public String toString() {
        String returnValue;

        if (size <= 0) {
            returnValue = "0";
        } else if (size < 1024) {
            returnValue = size + " B";
        } else if (size < 1048576) {
            returnValue = size/1024 + " kB";
        } else {
            returnValue = size / 1048576 + " MB";
        }

        return returnValue;
    }

}
