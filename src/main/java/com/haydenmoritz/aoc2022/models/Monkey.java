package com.haydenmoritz.aoc2022.models;

import java.util.Queue;

public class Monkey {
    // region VARIABLES
    private int id;
    private Queue<Long> items;
    private int itemsSeen;
    private long throwCondition;
    private long trueThrowId;
    private long falseThrowId;
    //endregion

    //region CONSTRUCTOR
    public Monkey(int id, Queue<Long> items, long throwCondition, long trueThrowId, long falseThrowId) {
        this.id = id;
        this.items = items;
        this.itemsSeen = items.size();
        this.throwCondition = throwCondition;
        this.trueThrowId = trueThrowId;
        this.falseThrowId = falseThrowId;
    }
    //endregion

    //region GETTERS
    public int getId() {
        return id;
    }

    public Queue<Long> getItems() {
        return items;
    }

    public int getItemsSeen() {
        return itemsSeen;
    }

    public long getThrowCondition() {
        return throwCondition;
    }

    public long getTrueThrowId() {
        return trueThrowId;
    }

    public long getFalseThrowId() {
        return falseThrowId;
    }
    //endregion

    //region METHODS
    public void catchItem(long worryLevel) {
        this.items.add(worryLevel);
        this.itemsSeen++;
    }

    public void throwItem(long worryLevel) {
        // TODO: STUB
    }
    //endregion
}
