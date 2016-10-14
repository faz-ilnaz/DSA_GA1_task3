package ru.innopolis.university.dsa.ga1.task3.ast;

class Power extends Expression {

    enum OpCode {
        POWER,
        NONE
    }

    OpCode opCode;

    private Expression a;
    private Expression b;

    Power(OpCode opCode, Expression a, Expression b) {
        this.opCode = opCode;
        this.a = a;
        this.b = b;
    }

    @Override
    public double calculate() {
        return Math.pow(a.calculate(), b.calculate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Power power = (Power) o;

        if (opCode != power.opCode) return false;
        if (!a.equals(power.a)) return false;
        return b.equals(power.b);

    }

    @Override
    public int hashCode() {
        int result = opCode.hashCode();
        result = 31 * result + a.hashCode();
        result = 31 * result + b.hashCode();
        return result;
    }

    @Override
    protected String toJSON(int offset) {
        String tab = new String(new char[offset]).replace("\0", "\t");
        return "{\n" +
                tab + "\t\"power\": {\n" +
                tab + "\t\t\"a\": " + a.toJSON(offset + 2) + ",\n" +
                tab + "\t\t\"b\": " + b.toJSON(offset + 2) + "\n" +
                tab + "\t}\n" +
                tab + "}";
    }
}
