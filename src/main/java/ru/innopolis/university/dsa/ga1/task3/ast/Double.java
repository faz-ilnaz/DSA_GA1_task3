package ru.innopolis.university.dsa.ga1.task3.ast;

class Double extends Primary {

    private double value;

    Double(double value) {
        this.value = value;
    }

    @Override
    public double calculate() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Double aDouble = (Double) o;

        return java.lang.Double.compare(aDouble.value, value) == 0;

    }

    @Override
    public int hashCode() {
        long temp = java.lang.Double.doubleToLongBits(value);
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    protected String toJSON(int offset) {
        String tab = new String(new char[offset]).replace("\0", "\t");
        return String.valueOf(value);
    }
}
