package ru.innopolis.university.dsa.ga1.task3;

import ru.innopolis.university.dsa.ga1.task3.ast.Expression;
import ru.innopolis.university.dsa.ga1.task3.ast.Parser;
import ru.innopolis.university.dsa.ga1.task3.utils.FileUtils;

import java.io.FileNotFoundException;

public class Program {
    public static void main(String[] args) throws FileNotFoundException {
//        String input = "sqrt(sin(sin(1))^2 + cos(sin(1))^2)";
        String input = "sqrt((sin(2)^2)^2)";

        Parser parser = new Parser(input);
        Expression expressionTree = parser.parse();

        double val = expressionTree.calculate();
        System.out.println(val);

//        String serialized = expressionTree.toJSON();
//        System.out.println(serialized);
//        FileUtils.writeToFile(serialized, "ast.json");
    }
}
