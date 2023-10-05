package controlstructures;

import java.util.Arrays;
import java.util.List;

public class LoopStm {
  // For loop
  public static void example1() {
    int sum = 0;
    for (int i = 1; i <= 10; i++) {
      if (sum == 5)
        break;
      sum += i;
    }
    System.out.println("sum of first 10 n numbers is " + sum);
  }

  // For-each loop
  public static void example2() {
    List<Integer> nums = Arrays.asList(1, 2, 3, 4);
    int sum = 0;
    nums.forEach(num -> System.out.println(num));
  }

  // while loop
  public static void example3() {
    int num = 123211;
    int temp = num, acc = 0;
    while (temp != 0) {
      acc = acc * 10 + temp % 10;
      temp /= 10;
    }
    if (num == temp) {
      System.out.println("be symmetrical num");
    } else {
      System.out.println("not be symmetrical num");
    }
  }

  // do-while loop
  public static void example4() {
    int i = 1;
    System.out.println("Printing the list of odd nums lower than 10 \n");
    do {
      System.out.println(i);
      i++;
    } while (i <= 10);

  }

  public static void main(String[] args) {
    example4();
  }

}
