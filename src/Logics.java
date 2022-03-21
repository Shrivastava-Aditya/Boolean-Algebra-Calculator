import java.util.*;
public class Logics {
    public int noOfGatesUsed;
    public int[][] truthTable;

    public Logics(int noOfGatesUsed){
        this.noOfGatesUsed = noOfGatesUsed;
        this.truthTable = fillTable(this.noOfGatesUsed);
    }
    public int[][] fillTable(int noOfGatesUsed){
        int rows = (int) Math.pow(2,noOfGatesUsed);
        int[][] truthTable = new int[rows][noOfGatesUsed];
        for (int i=0; i<rows; i++) {
            for (int j=noOfGatesUsed-1; j>=0; j--) {
                truthTable[i][(noOfGatesUsed - 1) - j] = (i/(int) Math.pow(2, j))%2;
            }
        }
        return truthTable;
        }


    public int[] operate(int[] t1, int[] t2,char c) {
        int[] res = new int[t1.length];
        //Make changes in the string such that character operates on the table
        for (int i = 0; i < t1.length; i++) {
            res[i] = calculate(t1[i],t2[i],c);
        }
        return res;
    }
    public int calculate(int a,int b,char c){
        boolean var1 = a == 1;
        boolean var2 = b == 1;
        boolean result = operation(var1,var2,c);
        return result ? 1 : 0;
    }
    public boolean operation(boolean a, boolean b,char c){
        switch (c){
            case '.' : return a && b;
            case '+' : return a || b;
            case '^' : return a ^ b;
            case '!' : return !a;
        }
        return false;
    }
    static Boolean isOperand(char c)
    {
        return c >= 65 && c <= 90;
    }
    public int[] evaluatePrefix(String string,int[][] table)
    {
        Stack<int[]> st = new Stack<>();

        for (int j = string.length() - 1; j >= 0; j--) {

            if (isOperand(string.charAt(j))) {
                int size = (int) Math.pow(2, noOfGatesUsed);
                int[] t = new int[size];
                for (int in = 0; in < size; in++) {
                    t[in] = table[in][((int) string.charAt(j) - 65)%noOfGatesUsed];
                }
                st.push(t);

            }

            else {
                int[] o1 = st.peek();
                st.pop();
                int[] o2 = st.peek();
                st.pop();

                // Use switch case to operate on o1
                // and o2 and perform o1 O o2.
                char c = string.charAt(j);
                switch (c) {
                    case '+':
                    case '.':
                    case '^':
                    case '!':
                        st.push(operate(o1,o2,c));
                        break;
                    default:
                        System.out.println("Invalid operator") ; break  ;
                }
             }

        }

        return st.peek();
    }

    public void setNoOfGatesUsed(int noOfGatesUsed) {
        this.noOfGatesUsed = noOfGatesUsed;
    }

    public void setTruthTable(int[][] truthTable) {
        this.truthTable = truthTable;
    }

    public int getNoOfGatesUsed() {
        return noOfGatesUsed;
    }

    public int[][] getTruthTable() {
        return truthTable;
    }



}
