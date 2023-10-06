package controlstructures;

public class ConditionStm {

  // If Statement
  public static void example1() {
    int a = 5, b = 10;
    if (a - b < 0) {
      System.out.println("a - b < 0");
    }
  }

  // If-else Statement
  public static void example2() {
    int a = 5, b = 10;
    if (a - b < 0) {
      System.out.println("a - b < 0");
    } else {
      System.out.println("a - b > 0");
    }
  }

  // If-else-if Statement
  public static void example3() {
    int a = 12;
    if (a > 100) {
      System.out.println("a > 100");
    } else if (a > 10) {
      System.out.println("a > 10 and a <= 100");
    } else {
      System.out.println("a <= 10");
    }
    // test
  }

  // Nested If Statement
  public static void example4() {
    int a = 5;
    if (a > 10) {
      if (a % 2 != 0) {
        System.out.println("a > 10 and be odd");
      } else {
        System.out.println("a > 10 and be even");
      }
    } else {
      System.out.println("a <= 10");
    }
  }

  // Switch case Statement
  public static int example5() {
    int a = 5;

    switch (a) {
      case 1:
      case 3:
      case 5:
        return 31;


      default:
        return 30;
    }

  }

  public static void main(String args[]) {
    example3();
  }
}
