/*
 * CSCI 2336 Programming Fundamentals III Fall 2017
 * Artem Skitenko
 * 9/15/2017
 * Evaluation of postfix expressions
 */


package assignment;

import java.util.*;
import java.util.regex.Pattern;

public class Assignment {

    static boolean unaryOperatorCheck(Stack<Double> stack)
    /*Check if there is enough operands for the operation*/ {
        if (stack.size() < 2) {
            System.out.println("Error: Entered unary operator!");
            stack.clear();
            return true;
        }
        return false;
    }

    static void count(String inputString, Stack<Double> stack) {
        // Create scanner to scan the string
        Scanner sc = new Scanner(inputString);
        // Create temp variable to hold value of the first operand
        Double temp;
        while (sc.hasNextDouble()) {
//            While the fist not empty symbol is a number
            stack.push(sc.nextDouble());
//            Push is on the stack
            if (!sc.hasNextDouble()) {
//            If the fist not empty symbol is not number
                sc.useDelimiter("");
                while (sc.hasNext()) {
//            Cycle to iterate through all of the symbols that are not numbers
                    if (sc.hasNextDouble()) {
//            If a digit is encountered reset the delimiter to get floating point number on the next iteration
                        sc.useDelimiter("\\p{javaWhitespace}+");
                        break;
                    }
//            Select correct arithmetic operation
                    switch (sc.next().charAt(0)) {
                        case '-':
                            if (unaryOperatorCheck(stack)) return;
                            temp = stack.pop();
                            stack.push(stack.pop() - temp);
                            break;
                        case '+':
                            if (unaryOperatorCheck(stack)) return;
                            stack.push(stack.pop() + stack.pop());
                            break;
                        case '*':
                            if (unaryOperatorCheck(stack)) return;
                            stack.push(stack.pop() * stack.pop());
                            break;
                        case '/':
                            if (unaryOperatorCheck(stack)) return;
                            temp = stack.pop();
                            if (temp == 0) {
                                System.out.println("Division by zero");
                                return;
                            }
                            stack.push(stack.pop() / temp);
                            break;
                        case '%':
                            if (unaryOperatorCheck(stack)) return;
                            temp = stack.pop();
                            stack.push(stack.pop() % temp);
                            break;
                    }
                }
            }
        }
        sc.close();
        if (stack.size() > 1) {
//        If more than two operands are entered, clear the stack and leave the last operand
            System.out.println("More than two operands were entered");
            temp = stack.pop();
            stack.clear();
            stack.push(temp);
        }
//        Output the result
        if (!stack.isEmpty()) System.out.println(stack.firstElement());
        else System.out.println("Invalid input format");
    }

    static public void main(String[] a) {
        Stack<Double> stack = new Stack<>();
        Scanner scIn = new Scanner(System.in);
        System.out.println("c - clear memory; q - quit");
//        Get user input
        while (scIn.hasNextLine()) {
            String inputString = scIn.nextLine();
//        Exit option
            if (inputString.charAt(0) == 'q') {
                scIn.close();
                break;
            }
//        Clear stack option
            if (inputString.charAt(0) == 'c') {
                stack.clear();
                System.out.println("Memory cleared");
                continue;
            }
//        Run arithmetic function
            count(inputString, stack);
        }
    }
}


