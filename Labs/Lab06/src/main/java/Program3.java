public class Program3 {
  /**
   * take in an array
   * print each element within
   */
  public static <T> void printArray(T[] arr){
    for (T obj : arr){
      System.out.println(obj);
    }
  }

  public static void main(){
    Integer[] intArray = {1, 2, 3, 4, 5};
    Double[] doubleArray = {1.1, 2.2, 3.3, 4.4, 5.5};
    Character[] charArray = {'h', 'e', 'l', 'l', 'o'};
    String[] stringArray = {"once", "upon", "a", "time"};
    printArray(intArray);
    printArray(doubleArray);
    printArray(charArray);
    printArray(stringArray);
  }


}
