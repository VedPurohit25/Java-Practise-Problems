import java.util.Stack;

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        
        for (String token : tokens) {
            if (isOperator(token)) {
                // The order of popping matters for subtraction and division
                int b = stack.pop(); 
                int a = stack.pop(); 
                
                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        stack.push(a / b); // Java integer division truncates toward zero automatically
                        break;
                }
            } else {
                // Token is an integer
                stack.push(Integer.parseInt(token));
            }
        }
        
        // The final remaining element in the stack is the answer
        return stack.pop();
    }
    
    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }
}