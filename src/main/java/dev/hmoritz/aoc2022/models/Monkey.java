package dev.hmoritz.aoc2022.models;

import java.util.Map;
import java.util.Queue;

public class Monkey {
    // region VARIABLES
    private int id;
    private Queue<Long> items;
    private long itemsSeen;
    private Map<String, Integer> operation;
    private int throwConditionOperand;
    private int trueThrowId;
    private int falseThrowId;
    //endregion

    //region CONSTRUCTOR
    public Monkey(int id,
                  Queue<Long> items,
                  Map<String, Integer> operation,
                  int throwConditionOperand,
                  int trueThrowId,
                  int falseThrowId) {
        this.id = id;
        this.items = items;
        this.itemsSeen = 0L;
        this.operation = operation;
        this.throwConditionOperand = throwConditionOperand;
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

    public long getItemsSeen() {
        return itemsSeen;
    }

    public Map<String, Integer> getOperation() {
        return operation;
    }

    public int getThrowConditionOperand() {
        return throwConditionOperand;
    }

    public int getTrueThrowId() {
        return trueThrowId;
    }

    public int getFalseThrowId() {
        return falseThrowId;
    }
    //endregion

    //region METHODS
    public Long increaseWorry(long worryLevel) {
        Long newWorryLevel = 0L;
        if (this.operation.containsKey("*")) {
            long operationValue = this.operation.get("*");
            if (operationValue == -1) {
                operationValue = worryLevel;
            }
            newWorryLevel = worryLevel * operationValue;
        } else if (this.operation.containsKey("+")) {
            long operationValue = this.operation.get("+");
            if (operationValue == -1) {
                operationValue = worryLevel;
            }
            newWorryLevel = worryLevel + operationValue;
        }

        return newWorryLevel;
    }

    public void inspectItem() {
        this.itemsSeen += 1L;
    }

    public int findMonkeyIdToThrowTo(long worryLevel) {
        return worryLevel % this.throwConditionOperand == 0 ? trueThrowId : falseThrowId;
    }

    public void catchItem(long worryLevel) {
        this.items.add(worryLevel);
    }
    //endregion
}
