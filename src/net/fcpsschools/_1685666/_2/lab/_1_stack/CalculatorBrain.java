package net.fcpsschools._1685666._2.lab._1_stack;

import java.util.Stack;

/**
 * @author ApolloZhu, Pd. 1
 */
public class CalculatorBrain {
    private static <E> String toString(Stack<E> stack) {
        StringBuilder sb = new StringBuilder();
        for (E element : stack) {
            sb.append(element);
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length());
        return sb.toString();
    }

    public static double evaluatePostfix(String s) {
        Stack<String> operands = new Stack<>();
        for (String token : s.split(" ")) {
            if (Operator.isConstant(token))
                operands.push(token);
            else if (Operator.isUnary(token))
                operands.push("" + Operator
                        .evaluate(token, operands.pop()));
            else if (Operator.isBinary(token)) {
                String rhs = operands.pop();
                operands.push("" + Operator
                        .evaluate(token, operands.pop(), rhs));
            } else throw new IllegalArgumentException(
                    "unrecognized token: " + token);
        }
        double result = Operator.evaluate(operands.pop());
        if (!operands.isEmpty())
            throw new IllegalArgumentException(
                    "unmatched " + toString(operands));
        return result;
    }
}
