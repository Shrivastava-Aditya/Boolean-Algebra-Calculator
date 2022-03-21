import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    int gates;
    String string;
    int[] array = new int[(int)Math.pow(2,gates)];

    public Calculator(int gates, String string) {
        this.gates = gates;
        this.string = string;
        this.array = mainLogic(gates,string);

    }
    public static int[] mainLogic(int gates, String string){
        Logics lo = new Logics(gates);
        StringParser sp = new StringParser(string);
        int[] array = lo.evaluatePrefix(sp.prefixString, lo.truthTable);
        return array;
    }
    public void printArray(int[] array){
        for(int i : array){
            System.out.println(i);
        }
    }
    public static void main(String[] args) {
        /*

        Current issues
        1. Not able to handle complexly written equation with more nested equations and multiple operators acting at once
        2. priority level mismatch can be seen in some equations,
        3. will have to tweak in NOT operation so that NOT operation can perform on only one operator.
        Good for
        1. Not so complex equations
        2. Can handle relatively larger equation
        3. Any symbols can be solved as long as they follow boolean law and operands are adjacent.
        4. Sum of products form is preferred as input

         */
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter The no. of gates\n(Minimum 2) >> ");
            int gates = sc.nextInt();
            if(gates < 2){
                System.out.println("This is fairly small value for a computer to calculate");
            }
            Logics lg = new Logics(gates);
            try{
                System.out.print("Enter The Boolean Equation\n(Must have adjacent capital letters)\naccepted operations {!,.,+,^} >> " );
                String eqn = sc.next();
                if(eqn.length() < 2){
                    System.out.println("Inappropriate Length");
                }
                StringParser sp = new StringParser(eqn);
                int[] array = mainLogic(gates,eqn);
                int j = 1;
                for(int i : array){
                    System.out.println(j+ "-->" + i);
                    j++;
                }
            } catch (Exception | Error e){
                System.out.println("Check the equation once again.");
            }
        } catch (OutOfMemoryError | NegativeArraySizeException | InputMismatchException e){
            System.out.println("You should check the no. once again.");
        }

    }
}
