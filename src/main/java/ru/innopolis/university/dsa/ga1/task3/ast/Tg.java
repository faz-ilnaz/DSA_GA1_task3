package ru.innopolis.university.dsa.ga1.task3.ast;

class Tg extends Parenthesized {

    Tg(Expression expression) {
        super(expression);
    }

    @Override
    public double calculate() {
        return Math.tan(expression.calculate());
    }

    @Override
    protected String toJSON(int offset) {
        String tab = new String(new char[offset]).replace("\0", "\t");
        return "{\n" +
                tab + "\t\"tg\": " + expression.toJSON(offset + 1) + "\n" +
                tab + "}";
    }
}
