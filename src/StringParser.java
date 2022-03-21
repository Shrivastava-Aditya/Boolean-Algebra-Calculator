import java.util.*;

public class StringParser {
    public String inputString;
    public char[] arrOfChars;
    public String prefixString;
    public StringParser(String string){
        if(stringChecker(string)) {
            this.inputString = string;
            this.prefixString = new String(infixToPreFix(string));
            this.arrOfChars = new String(infixToPreFix(string)).toCharArray();
        }
        else
            System.out.println("Invalid String");
    }
    public static int precedence(char c){
        switch (c){
            case '^':
                return 2;
            case '!':
                return 1;
            case '+':
                return 4;
            case '.':
                return 3;
        }
        return -1;
    }
    static StringBuilder infixToPreFix(String inputString){

        StringBuilder result = new StringBuilder();
        StringBuilder input = new StringBuilder(inputString);
        input.reverse();
        Stack<Character> stack = new Stack<Character>();

        char [] charsExp = new String(input).toCharArray();
        for (int i = 0; i < charsExp.length; i++) {

            if (charsExp[i] == '(') {
                charsExp[i] = ')';
                i++;
            }
            else if (charsExp[i] == ')') {
                charsExp[i] = '(';
                i++;
            }
        }
        for (int i = 0; i <charsExp.length ; i++) {
            char c = charsExp[i];

            //check if char is operator or operand
            if(precedence(c)>0){
                while(stack.isEmpty()==false && precedence(stack.peek())>=precedence(c)){
                    result.append(stack.pop());
                }
                stack.push(c);
            }else if(c==')'){
                char x = stack.pop();
                while(x!='('){
                    result.append(x);
                    x = stack.pop();
                }
            }else if(c=='('){
                stack.push(c);
            }else{
                //character is neither operator nor "("
                result.append(c);
            }
        }

        for (int i = 0; i <=stack.size() ; i++) {
            result.append(stack.pop());
        }
        return result.reverse();
    }


    public String getInputString() {
        return inputString;
    }
    public boolean stringChecker(String inputString) {
        return !inputString.contains(" ");
    }

    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    public char[] getArrOfChars() {
        return arrOfChars;
    }

    public void setArrOfChars(char[] arrOfChars) {
        this.arrOfChars = arrOfChars;
    }

    public void setPrefixString(String prefixString) {
        this.prefixString = prefixString;
    }

    public String getPrefixString() {
        return prefixString;
    }
}