package exceptionhandling;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TryCatchEx {
  // try-catch block
  // case: not use throws and using
  public static void example1() throws NullPointerException {

    // without try-catch block
    int a = 5 / 0; // throw exception

    // ArithmeticException example
    try {
      int b = 5 / 0; // throw exception
    } catch (ArithmeticException e) {
      System.out.println(e);
    }

    // statement after exception will not be executed
    try {
      int b = 5 / 0; // throw exception
      System.out.println("temp..."); // not execute
    } catch (ArithmeticException e) {
      // or catch(Exception e)
      // Exception class (parrent class) can be alternative all of it's child class
      System.out.println(e);
      // Custom message: System.out.println("Can't divided by zero");
    }

    // Generated exception if current catch exception doesn't match. ex: SecurityException
    try {
      int b = 5 / 0; // throw exception
    } catch (EnumConstantNotPresentException e) {
      System.out.println(e);
    }

    // Checked and unchecked exception
    PrintWriter pw;
    try {
      int arr[] = {1, 3, 5, 7};
      System.out.println(arr[5]); // throw exception
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println(e);
    }
    try {
      pw = new PrintWriter("test.txt"); // throw exception
      pw.println("saved");
    } catch (FileNotFoundException e) {
      System.out.println(e);
    }
  }

  // multiple try-catch block
  public static void example2() {
    try {
      // 1st exception (after that not execute)
      int a[] = new int[5];
      a[5] = 5 / 0; // -> Arithmetic Exception...

      // 2nd exception (skip before exception)
      System.out.println(a[6]); // -> ArrayIndexOutOfBounds Exception...

      // 1st exception (after that not execute)
      a[7] = 10 / 0; // -> Arithmetic Exception...
      System.out.println(a[10]); // not execute

      // even all exceptions doesn't match,
      // would match with Exception class (parrent) if it defined
      String s = null;
      System.out.println(s.length());

      // should order exception (child -> parrent),
      // if not, can get error compile-time error

    } catch (ArithmeticException e) {
      System.out.println("Arithmetic Exception...");
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("ArrayIndexOutOfBounds Exception...");
    } catch (Exception e) {
      System.out.println("Parent Exception...");
    }
  }

  // Nested try-catch block
  public static void example3() {
    try {
      // If inner not handle exception,
      // outer exception handling will be execute
      // inner block 1

      try {
        System.out.println("divide by 0");
        int b = 39 / 0;
      } catch (ArithmeticException e) {
        System.out.println(e);
      }
      // inner block 2
      try {
        System.out.println("out of bounds arr");
        int a[] = new int[5];
        a[5] = 13;
      } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println(e);
      }
    } catch (Exception e) {
      System.out.println("handled the exception (outer)");
    }
  }

  // Finally block
  public static void example4() {
    try {
      int data = 25 / 5;// may throw exception
      throw new NullPointerException();
      // System.out.println(data);

    } catch (NullPointerException e) {// not execute
      System.out.println(e);

    } finally {
      // first of all try-catch block, before exception statement in try block
      // (if exception match with catch)
      System.out.println("always executed");
    }
  }

  public static void main(String args[]) {
    example4();
  }
}
