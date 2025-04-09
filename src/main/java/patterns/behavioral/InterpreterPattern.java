package patterns.behavioral;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class InterpreterPattern {

  public static class Context {
    private String input;
    private int position;
    private Map<String, Double> variables;

    public Context(String input) {
      this.input = input.replaceAll("\\s+", ""); // Loại bỏ khoảng trắng
      this.position = 0;
      this.variables = new HashMap<>();
    }

    public void setVariable(String name, double value) {
      variables.put(name, value);
    }

    public double getVariable(String name) {
      return variables.getOrDefault(name, 0.0);
    }

    public char getCurrentChar() {
      return position < input.length() ? input.charAt(position) : '\0';
    }

    public void advance() {
      position++;
    }
  }

  public interface Expression {
    double  interpret(Context context);
  }

  public static class NumberExpression implements Expression {
    private double number;

    public NumberExpression(double number) {
      this.number = number;
    }

    @Override
    public double interpret(Context context) {
      return number;
    }
  }

  public static class VariableExpression implements Expression {
    private String name;

    public VariableExpression(String name) {
      this.name = name;
    }

    @Override
    public double interpret(Context context) {
      return context.getVariable(name); // Sử dụng context để lấy giá trị biến
    }
  }

  public static class AddExpression implements Expression {
    private Expression left;
    private Expression right;

    public AddExpression(Expression left, Expression right) {
      this.left = left;
      this.right = right;
    }

    @Override
    public double interpret(Context context) {
      return left.interpret(context) + right.interpret(context);
    }
  }

  public static class SubtractExpression implements Expression {
    private Expression left;
    private Expression right;

    public SubtractExpression(Expression left, Expression right) {
      this.left = left;
      this.right = right;
    }

    @Override
    public double interpret(Context context) {
      return left.interpret(context) - right.interpret(context);
    }
  }

  public static class MultiplyExpression implements Expression {
    private Expression left;
    private Expression right;

    public MultiplyExpression(Expression left, Expression right) {
      this.left = left;
      this.right = right;
    }

    @Override
    public double interpret(Context context) {
      return left.interpret(context) * right.interpret(context);
    }
  }

  public static class DivideExpression implements Expression {
    private Expression left;
    private Expression right;

    public DivideExpression(Expression left, Expression right) {
      this.left = left;
      this.right = right;
    }

    @Override
    public double interpret(Context context) {
      double divisor = right.interpret(context);
      if (divisor == 0) throw new ArithmeticException("Chia cho 0!");
      return left.interpret(context) / divisor;
    }
  }

  public static class  ExpressionParser {
    public static Expression parse(Context context) {
      Stack<Expression> numbers = new Stack<>();
      Stack<Character> operators = new Stack<>();

      while (context.getCurrentChar() != '\0') {
        char current = context.getCurrentChar();

        // Xử lý số
        if (Character.isDigit(current)) {
          StringBuilder number = new StringBuilder();
          while (context.getCurrentChar() != '\0' && (Character.isDigit(context.getCurrentChar()) || context.getCurrentChar() == '.')) {
            number.append(context.getCurrentChar());
            context.advance();
          }
          numbers.push(new NumberExpression(Double.parseDouble(number.toString())));
        }
        // Xử lý biến (giả sử biến là chữ cái)
        else if (Character.isLetter(current)) {
          StringBuilder varName = new StringBuilder();
          while (context.getCurrentChar() != '\0' && Character.isLetter(context.getCurrentChar())) {
            varName.append(context.getCurrentChar());
            context.advance();
          }
          numbers.push(new VariableExpression(varName.toString()));
        }
        // Xử lý toán tử
        else if (isOperator(current)) {
          while (!operators.isEmpty() && hasPrecedence(operators.peek(), current)) {
            applyOperator(numbers, operators.pop());
          }
          operators.push(current);
          context.advance();
        } else {
          context.advance(); // Bỏ qua ký tự không hợp lệ
        }
      }

      while (!operators.isEmpty()) {
        applyOperator(numbers, operators.pop());
      }

      return numbers.pop();
    }

    private static boolean isOperator(char c) {
      return c == '+' || c == '*';
    }

    private static boolean hasPrecedence(char op1, char op2) {
      return (op1 == '*' && op2 == '+');
    }

    private static void applyOperator(Stack<Expression> numbers, char operator) {
      Expression right = numbers.pop();
      Expression left = numbers.pop();
      switch (operator) {
        case '+':
          numbers.push(new AddExpression(left, right));
          break;
        case '*':
          numbers.push(new MultiplyExpression(left, right));
          break;
      }
    }
  }

  public static void main(String[] args) {
    // Tạo context và gán giá trị cho biến
    Context context = new Context("x + 2 * y");
    context.setVariable("x", 5.0);
    context.setVariable("y", 3.0);

    // Phân tích và diễn giải
    Expression expression = ExpressionParser.parse(context);
    double result = expression.interpret(context);
    System.out.println("Kết quả của 'x + 2 * y' với x=5, y=3 là: " + result); // 5 + 2 * 3 = 11
  }
}
