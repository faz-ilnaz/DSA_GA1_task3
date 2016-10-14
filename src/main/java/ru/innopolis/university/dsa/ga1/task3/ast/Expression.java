package ru.innopolis.university.dsa.ga1.task3.ast;

public abstract class Expression {
    public abstract double calculate();

    public String toJSON() {
        return toJSON(0);
    }

    protected abstract String toJSON(int offset);
}
