package ru.innopolis.university.dsa.ga1.task3.ast;

class Term extends Expression {

    enum OpCode {
        ADD,
        SUB,
        NONE
    }

    OpCode opCode;

    Expression left, right;

    Term(OpCode opCode, Expression left, Expression right) {
        this.opCode = opCode;
        this.left = left;
        this.right = right;
    }

    @Override
    public double calculate() {
        double l = left.calculate();
        double r = right.calculate();
        switch (opCode) {
            case ADD:
                return l + r;
            case SUB:
                return l - r;
            case NONE:
                return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Term term = (Term) o;

        if (opCode != term.opCode) return false;
        if (!left.equals(term.left)) return false;
        return right.equals(term.right);

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
                tab + "\t\"term\": {\n" +
                tab + "\t\t\"operation\": \"" + opCode + "\",\n" +
                tab + "\t\t\"left\": " + left.toJSON(offset + 2) + ",\n" +
                tab + "\t\t\"right\": " + right.toJSON(offset + 2) + "\n" +
                tab + "\t}\n" +
                tab + "}";
    }
}
