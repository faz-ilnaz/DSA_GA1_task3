package ru.innopolis.university.dsa.ga1.task3.ast;

class Parenthesized extends Primary {
    protected Expression expression;

    Parenthesized(Expression expression) {
        this.expression = expression;
    }

    @Override
    public double calculate() {
        return expression.calculate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parenthesized that = (Parenthesized) o;

        return expression.equals(that.expression);

    }

    @Override
    public int hashCode() {
        return expression.hashCode();
    }

    @Override
    protected String toJSON(int offset) {
        String tab = new String(new char[offset]).replace("\0", "\t");
        return "{\n" +
                tab + "\t\"parenthesized\": " + expression.toJSON(offset + 1) + "\n" +
                tab + "}";
    }
}
