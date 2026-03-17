public class Program1 {
  public static void printArray(Object[] arr){
    for (Object obj: arr){
      System.out.println(obj);
    }
  }

  public static void main(){
    Integer[] intArry = {1, 2, 3, 4, 5};
    Double[] doubArry = {1.1, 2.2, 3.3, 4.4};
    Character[] charArry = {'H', 'E', 'L', 'L', 'O'};
    String[] strArry = {"once", "upon", "a", "time"};
    printArray(intArry);
    printArray(doubArry);
    printArray(charArry);
    printArray(strArry);
  }


}
