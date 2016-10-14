package ru.innopolis.university.dsa.ga1.task3.ast;

class Factor extends Expression {
    enum OpCode {
        MULT,
        DIV,
        NONE
    }

    OpCode opCode;

    Expression left, right;

    Factor(OpCode opCode, Expression left, Expression right) {
        this.opCode = opCode;
        this.left = left;
        this.right = right;
    }

    @Override
    public double calculate() {
        double l = left.calculate();
        double r = right.calculate();
        switch (opCode) {
            case MULT:
                return l * r;
            case DIV:
                if (r == 0) {
                    throw new ArithmeticException("Division by zero!");
                }
                return l / r;
            case NONE:
                return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Factor factor = (Factor) o;

        if (opCode != factor.opCode) return false;
        if (!left.equals(factor.left)) return false;
        return right.equals(factor.right);

    }

    @Override
    public int hashCode() {
        int result = opCode.hashCode();
        result = 31 * result + left.hashCode();
        result = 31 * result + right.hashCode();
        return result;
    }

    @Override
    protected String toJSON(int offset) {
        String tab = new String(new char[offset]).replace("\0", "\t");
        return "{\n" +
                tab + "\t\"factor\": {\n" +
                tab + "\t\t\"operation\": \"" + opCode + "\",\n" +
                tab + "\t\t\"left\": " + left.toJSON(offset + 2) + ",\n" +
                tab + "\t\t\"right\": " + right.toJSON(offset + 2) + "\n" +
                tab + "\t}\n" +
                tab + "}";
    }
}
