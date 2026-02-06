public class PersonDemo {
  public static void main (String[]args)
  {
    Person bach = new Person ("Johann Sebastian Bachh", new Date ("March", 21, 1685), new Date ("July", 28, 1750));
    Person stravinsky = new Person ("Igor stravinsky", new Date ("June",17,1882), new Date ("April", 6, 1971));
    Person Adams= new Person ("John Adams", new Date ("February",15, 1974), null);

    System.out.println("A Short List of Composers:");
    System.out.println(bach);
    System.out.println(stravinsky);
    System.out.println(Adams);

    Person bachTwin = new Person(bach);
    System.out.println("Comparing bach and bach Twin:");
    if (bachTwin == bach)
      System.out.println("same reference for both.");
    else
      System.out.println("distinct copies");

    if (bachTwin.equals(bach))
      System.out.println("same data");
    else
      System.out.println("not same data");


  }
}

