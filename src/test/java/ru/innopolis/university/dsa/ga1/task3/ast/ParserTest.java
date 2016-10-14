package ru.innopolis.university.dsa.ga1.task3.ast;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void parseAdd() throws Exception {
        String input = "2+2";
        Parser parser = new Parser(input);
        Expression actual = parser.parse();

        Expression expected = new Term(Term.OpCode.ADD, new Long(2), new Long(2));
        assertEquals(expected, actual);
        assertEquals(4, actual.calculate(), 0);
    }

    @Test
    public void parseSub() throws Exception {
        String input = "5-0";
        Parser parser = new Parser(input);
        Expression actual = parser.parse();

        Expression expected = new Term(Term.OpCode.SUB, new Long(5), new Long(0));
        assertEquals(expected, actual);
        assertEquals(5, actual.calculate(), 0);
    }

    @Test
    public void parseTrigonometric1() throws Exception {
        String input = "sin(1)*sin(1) + cos(1)*cos(1)";
        Parser parser = new Parser(input);
        Expression actual = parser.parse();

        Expression expected = new Term(Term.OpCode.ADD,
                new Factor(Factor.OpCode.MULT, new Sin(new Long(1)), new Sin(new Long(1))),
                new Factor(Factor.OpCode.MULT, new Cos(new Long(1)), new Cos(new Long(1))));
        assertEquals(expected, actual);
        assertEquals(1, actual.calculate(), 0);
    }

    @Test
    public void parseTrigonometricAndPower() throws Exception {
        String input = "sin(1)^2 + cos(1)^2";
        Parser parser = new Parser(input);
        Expression actual = parser.parse();

        Expression expected = new Term(Term.OpCode.ADD,
                new Power(Power.OpCode.POWER, new Sin(new Long(1)), new Long(2)),
                new Power(Power.OpCode.POWER, new Cos(new Long(1)), new Long(2)));
        assertEquals(expected, actual);
        assertEquals(1, actual.calculate(), 0);
    }

    @Test
    public void parseComplexPower() throws Exception {
        String input = "(2*2+2)^(2+2*2)";
        Parser parser = new Parser(input);
        Expression actual = parser.parse();

        Expression expected = new Power(Power.OpCode.POWER,
                new Parenthesized(
                        new Term(Term.OpCode.ADD,
                                new Factor(
                                        Factor.OpCode.MULT,
                                        new Long(2),
                                        new Long(2)
                                ),
                                new Long(2))
                ),
                new Parenthesized(
                        new Term(Term.OpCode.ADD,
                                new Long(2),
                                new Factor(
                                        Factor.OpCode.MULT,
                                        new Long(2),
                                        new Long(2)
                                ))
                ));
        assertEquals(expected, actual);
        assertEquals(46656, actual.calculate(), 0);

    }

    @Test
    public void parseSqrt() throws Exception {
        String input = "sqrt(9)";
        Parser parser = new Parser(input);
        Expression actual = parser.parse();

        Expression expected = new Sqrt(new Long(9));
        assertEquals(expected, actual);
        assertEquals(3.0, actual.calculate(), 0);

    }

    @Test
    public void parseSin() throws Exception {
        String input = "sin(0)";
        Parser parser = new Parser(input);
        Expression actual = parser.parse();

        Expression expected = new Sin(new Long(0));
        assertEquals(expected, actual);
        assertEquals(0, actual.calculate(), 0);

    }

    @Test
    public void parseCos() throws Exception {
        String input = "cos(0)";
        Parser parser = new Parser(input);
        Expression actual = parser.parse();

        Expression expected = new Cos(new Long(0));
        assertEquals(expected, actual);
        assertEquals(1, actual.calculate(), 0);
    }

    @Test
    public void parseTg() throws Exception {
        String input = "tg(0)";
        Parser parser = new Parser(input);
        Expression actual = parser.parse();

        Expression expected = new Tg(new Long(0));
        assertEquals(expected, actual);
        assertEquals(0, actual.calculate(), 0);
    }

    @Test
    public void parseCtg() throws Exception {
        String input = "ctg(0)";
        Parser parser = new Parser(input);
        Expression actual = parser.parse();

        Expression expected = new Ctg(new Long(0));
        assertEquals(expected, actual);
        assertTrue(Double.isInfinite(actual.calculate()));
    }

}