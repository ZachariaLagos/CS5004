/*
three differences from a python program:
    1. you need to define a class, and the class name must be the same as the file name;
    2. syntax is different, for example, print in python is stated as System.out.println;
    3. you need to distinguish between in t and long, even though they both represent integers,
    because 2 to the power of 31 causes an overflow in int type;
*/
public class Power {
    public static void main(String[] args) {
        long result = (long) Math.pow(2, 31);
        System.out.println(result);
    }
}
