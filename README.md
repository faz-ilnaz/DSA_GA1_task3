## Simple expression parser&calculator based on abstract syntax tree
### Supported operators
* +, -, * and /
* a^b (raise to the power)
* sqrt()
* sin()
* cos()
* tg()
* ctg()

Usage example:
```java
String input = "sin(1)^2 + cos(1)^2";
Parser parser = new Parser(input);
Expression exp = parser.parse();

double result = exp.calculate();
