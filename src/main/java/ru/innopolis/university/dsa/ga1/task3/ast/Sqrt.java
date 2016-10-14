package ru.innopolis.university.dsa.ga1.task3.ast;

class Sqrt extends Parenthesized {

    Sqrt(Expression expression) {
        super(expression);
    }

    @Override
    public double calculate() {
        return Math.sqrt(expression.calculate());
    }

    @Override
    protected String toJSON(int offset) {
        String tab = new String(new char[offset]).replace("\0", "\t");
        return "{\n" +
                tab + "\t\"sqrt" +
                "\": " + expression.toJSON(offset + 1) + "\n" +
                tab + "}";
    }
}
