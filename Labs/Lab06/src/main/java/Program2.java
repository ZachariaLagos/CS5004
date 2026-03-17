public class Program2 {
  public static void printArray(Integer[] arr){
    for (Integer ints: arr){
      System.out.println(ints);
    }
  }

  public static void printArray(Double[] arr){
    for (Double doubles: arr){
      System.out.println(doubles);
    }
  }

  public static void printArray(Character[] arr){
    for (Character chars: arr){
      System.out.println(chars);
    }
  }

  public static void printArray(String[] arr){
    for (String str: arr){
      System.out.println(str);
    }
  }

  public static void main(){
    Integer[] intArray = {5, 4, 3, 2, 1};
    Double[] doubleArray = {1.0, 2.0, 3.0, 4.0};
    Character[] charArray = {'H', 'E', 'L', 'L', 'O'};
    String[] strArray = {"there", "once", "upon", "a", "time"};
    printArray(intArray);
    printArray(doubleArray);
    printArray(charArray);
    printArray(strArray);

  }

}
