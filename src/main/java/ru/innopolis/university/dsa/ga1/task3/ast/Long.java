package ru.innopolis.university.dsa.ga1.task3.ast;

class Long extends Primary {

    private long value;

    Long(long value) {
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

        Long aLong = (Long) o;

        return value == aLong.value;

    }

    @Override
    public int hashCode() {
        return (int) (value ^ (value >>> 32));
    }

    @Override
    protected String toJSON(int offset) {
        String tab = new String(new char[offset]).replace("\0", "\t");
        return String.valueOf(value);
    }
}
