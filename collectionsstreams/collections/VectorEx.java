package collectionsstreams.collections;

import java.util.Spliterator;
import java.util.Vector;

public class VectorEx {
  // Add method example
  public static void example1() {
    Vector<Integer> vec = new Vector<>();
    // method of list
    vec.add(1);
    vec.add(2);
    vec.add(3);
    vec.add(4);
    // method of vec
    vec.addElement(5);
    vec.addElement(6);
    vec.addElement(7);

    System.out.println("Elements: " + vec);
  }

  // Methods example
  public static void example2() {
    Vector<Integer> vec = new Vector<>(4);
    // Adding el
    vec.add(1);
    vec.add(2);
    vec.add(3);
    vec.add(4);

    // size and capacity method
    System.out.println("Size is: " + vec.size());
    System.out.println("Default capacity is: " + vec.capacity());

    // Display
    System.out.println("Elements : " + vec);
    vec.addElement(5);
    vec.addElement(6);
    vec.addElement(7);

    System.out.println("Size after addition: " + vec.size());
    System.out.println("Capacity after addition is: " + vec.capacity());

    // Display
    System.out.println("Elements are: " + vec);

    // contains method
    if (vec.contains(4)) {
      System.out.println("4 is present at the index " + vec.indexOf("Tiger"));
    } else {
      System.out.println("4 is not present in the list.");
    }

    System.out.println("The first el of the vector is = " + vec.firstElement());
    System.out.println("The last el of the vector is = " + vec.lastElement());
  }

  // Methods example
  public static void example3() {
    Vector<Integer> v1 = new Vector<>();

    v1.add(15);
    v1.add(25);
    v1.add(15);
    v1.add(35);
    v1.add(40);
    v1.add(60);
    v1.add(15);
    v1.add(100);
    System.out.println("Values in vector: " + v1);

    // remove first 15
    v1.remove((Integer) 15);
    System.out.println("Values in vector: " + v1);

    // remove at index 3
    v1.remove(3);
    System.out.println("Values in vector: " + v1);

    // Remove an element
    v1.removeElementAt(5);
    System.out.println("Values after removal: " + v1);

    // Get the hashcode
    System.out.println("Hash code = " + v1.hashCode());

    // Get the el at specified index
    System.out.println("Element at index 1 is = " + v1.get(1));
  }

  // spliterator
  public static void example4() {
    Vector<Integer> v1 = new Vector<>();

    v1.add(10);
    v1.add(20);
    v1.add(30);
    v1.add(40);
    v1.add(50);
    v1.add(60);
    v1.add(70);
    v1.add(80);
    v1.add(90);

    // Get Spliterator
    Spliterator<Integer> splitr1 = v1.spliterator();

    Spliterator<Integer> part1 = splitr1.trySplit();
    Spliterator<Integer> part2 = splitr1;

    // Printing even numbers.
    System.out.println("Numbers Part 1:");
    part1.forEachRemaining(System.out::println);

    // Printing odd numbers.
    System.out.println("Numbers Part 2:");
    part2.forEachRemaining(System.out::println);
  }

  public static void main(String args[]) {
    example4();
  }
}

// sync
// many methods not be part of collections fw

// constructor:
// Vector()
// Vector(int iniCapacity)
// Vector(int iniCapacity, int capacityIncre)
// Vector( Collection<? extends E> c)
