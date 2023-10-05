package collectionsstreams.collections;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

class CustomComparator implements Comparator<Integer> {
  @Override
  public int compare(Integer number1, Integer number2) {
    int value = number1.compareTo(number2);
    if (value > 0) {
      return -1;
    } else if (value < 0) {
      return 1;
    } else {
      return 0;
    }
  }
}


public class PriorityQueueEx {
  // insert example
  public static void example1(PriorityQueue<Integer> numbers) {
    numbers.add(3);
    System.out.println("Updated PriorityQueue: " + numbers);

    numbers.offer(1);
    System.out.println("Updated PriorityQueue: " + numbers);
  }

  // access value example
  public static void example2(PriorityQueue<Integer> numbers) {
    int number = numbers.peek();
    System.out.println("Accessed Element: " + number);
  }

  // remove example
  public static void example3(PriorityQueue<Integer> numbers) {
    boolean result = numbers.remove(2);
    System.out.println("Is 2 el removed: (by remove method) " + result);

    int number = numbers.poll();
    System.out.println("Removed Element by poll method: " + number);
  }

  // iterating example
  public static void example4(PriorityQueue<Integer> numbers) {
    Iterator<Integer> iterate = numbers.iterator();
    while (iterate.hasNext()) {
      System.out.print(iterate.next());
      System.out.print(", ");
    }
  }

  // priorityqueue comparator and custom example
  public static void example5() {
    PriorityQueue<Integer> numbers = new PriorityQueue<>(new CustomComparator());
    numbers.add(4);
    numbers.add(2);
    numbers.add(1);
    numbers.add(3);
    System.out.print("PriorityQueue: " + numbers);
  }

  public static void main(String[] args) {
    // Creating a priority queue
    PriorityQueue<Integer> numbers = new PriorityQueue<>();
    // insert example
    numbers.add(4);
    numbers.add(2);
    System.out.println("PriorityQueue: " + numbers);
    // ...
  }
}
