import java.util.Scanner;

public class AddFromKbd {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter first integer: ");
        int a = in.nextInt();
        System.out.print("Enter second integer: ");
        int b = in.nextInt();
        int sum = a + b;
        System.out.println("Sum = " + sum);
        in.close();
    }
}
