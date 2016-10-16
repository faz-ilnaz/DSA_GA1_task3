package ru.innopolis.university.dsa.ga1.task3.ast;

public class Parser {

    private String input;
    private int pos;

    public Parser(String input) {
        this.input = input;
        pos = 0;
    }

    public Expression parse() {
        return parseTerm();
    }

    private Expression parseTerm() {
        Expression result = parseFactor();
        while (true) {
            if (endOfLn())
                return result;
            Term.OpCode op = parseTermOperator();
            if (op != Term.OpCode.NONE) {
                Expression right = parseFactor();
                result = new Term(op, result, right);
            } else {
                break;
            }
        }
        return result;
    }

    private Expression parseFactor() {
        Expression result = parsePow();
        while (true) {
            if (endOfLn())
                return result;
            Factor.OpCode op = parseFactorOperator();
            if (op != Factor.OpCode.NONE) {
                Expression right = parsePrimary();
                result = new Factor(op, result, right);
            } else {
                break;
            }
        }
        return result;
    }

    private Expression parsePow() {
        Expression result = parsePrimary();
        while (true) {
            if (endOfLn())
                return result;
            Power.OpCode op = parsePowerOperator();
            if (op != Power.OpCode.NONE) {
                Expression right = parsePrimary();
                result = new Power(op, result, right);
            } else {
                break;
            }
        }
        return result;
    }

    private Expression parsePrimary() {
        Expression result = null;

        char currChar = currChar();

        if (Character.isDigit(currChar)) {
            result = parseDouble();
        } else if (currChar == '(') {
            acceptCurrentChar();
            result = new Parenthesized(parse());
            skipNextChar(); // skip ')'
        } else {
            result = handleMathFunc();
        }

        return result;
    }

    private Double parseDouble() {
        StringBuilder sb = new StringBuilder();
        if (Character.isDigit(currChar())) {
            sb.append(currChar());
            acceptCurrentChar();
        }
        while (!endOfLn() &&
                (Character.isDigit(currChar()) || currChar() == '.')) {
            sb.append(currChar());
            acceptCurrentChar();
        }
        double value = java.lang.Double.parseDouble(sb.toString());
        return new Double(value);
    }

    private Expression handleMathFunc() {
        char op = currChar();
        Expression result = null;

        switch (op) {
            case 's':
                acceptCurrentChar();
                if (currChar() == 'q' && acceptCurrentChar()
                        && currChar() == 'r' && acceptCurrentChar()
                        && currChar() == 't') {
                    acceptCurrentChar();
                    if (currChar() == '(') {
                        acceptCurrentChar();
                        result = new Sqrt(parse());
                        skipNextChar(); // skip ')'
                    } else {
                        throw new RuntimeException("Wrong input format found on index: " + pos);
                    }
                } else if (currChar() == 'i' && acceptCurrentChar()
                        && currChar() == 'n') {
                    acceptCurrentChar();
                    if (currChar() == '(') {
                        acceptCurrentChar();
                        result = new Sin(parse());
                        skipNextChar(); // skip ')'
                    } else {
                        throw new RuntimeException("Wrong input format found on index: " + pos);
                    }
                } else {
                    throw new RuntimeException("Wrong input format found on index: " + pos);
                }
                break;
            case 'c':
                acceptCurrentChar();
                if (currChar() == 'o' && acceptCurrentChar()
                        && currChar() == 's') {
                    acceptCurrentChar();
                    if (currChar() == '(') {
                        acceptCurrentChar();
                        result = new Cos(parse());
                        skipNextChar(); // skip ')'
                    } else {
                        throw new RuntimeException("Wrong input format found on index: " + pos);
                    }
                } else if (currChar() == 't' && acceptCurrentChar()
                        && currChar() == 'g') {
                    acceptCurrentChar();
                    if (currChar() == '(') {
                        acceptCurrentChar();
                        result = new Ctg(parse());
                        skipNextChar(); // skip ')'
                    } else {
                        throw new RuntimeException("Wrong input format found on index: " + pos);
                    }
                } else {
                    throw new RuntimeException("Wrong input format found on index: " + pos);
                }
                break;
            case 't':
                acceptCurrentChar();
                if (currChar() == 'g') {
                    acceptCurrentChar();
                    if (currChar() == '(') {
                        acceptCurrentChar();
                        result = new Tg(parse());
                        skipNextChar(); // skip ')'
                    } else {
                        throw new RuntimeException("Wrong input format found on index: " + pos);
                    }
                } else {
                    throw new RuntimeException("Wrong input format found on index: " + pos);
                }
                break;
            default:
                throw new RuntimeException("Wrong input format found on index: " + pos);
        }
        return result;
    }

    private Term.OpCode parseTermOperator() {
        char op = currChar();
        switch (op) {
            case '+':
                acceptCurrentChar();
                return Term.OpCode.ADD;
            case '-':
                acceptCurrentChar();
                return Term.OpCode.SUB;
            default:
                return Term.OpCode.NONE;
        }
    }

    private Factor.OpCode parseFactorOperator() {
        char op = currChar();
        switch (op) {
            case '*':
                acceptCurrentChar();
                return Factor.OpCode.MULT;
            case '/':
                acceptCurrentChar();
                return Factor.OpCode.DIV;
            default:
                return Factor.OpCode.NONE;
        }
    }

    private Power.OpCode parsePowerOperator() {
        char op = currChar();
        switch (op) {
            case '^':
                acceptCurrentChar();
                return Power.OpCode.POWER;
            default:
                return Power.OpCode.NONE;
        }
    }

    private char currChar() {
        while (pos < input.length() && Character.isWhitespace(input.charAt(pos))) {
            pos++;
        }
        return input.charAt(pos);
    }

    private boolean acceptCurrentChar() {
        pos++;
        return true;
    }

    private boolean endOfLn() {
        return pos >= input.length();
    }

    private void skipNextChar() {
        pos++;
    }


}
