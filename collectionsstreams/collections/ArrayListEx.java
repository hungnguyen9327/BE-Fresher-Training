package collectionsstreams.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class Temp {
  String A;
  String B;
  int C;

  public Temp(String a, String b, int c) {
    A = a;
    B = b;
    C = c;
  }
}


public class ArrayListEx {
  // Iterator ArrayList example
  public static void example1() {
    int sum = 0;
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    Iterator<Integer> itr = numbers.iterator();
    while (itr.hasNext()) {
      sum += itr.next();
    }
    // or for-each: for(Integer num: numbers)
    System.out.println("Sum: " + sum);
  }

  // Get & Set ArrayList example
  public static void example2() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

    System.out.println("Returning element: " + numbers.get(1));
    numbers.set(1, 6);
    for (Integer num : numbers) {
      System.out.println(num);
    }
  }

  // Sort ArrayList example
  public static void example3() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    Collections.sort(numbers);
    // or numbers.sort(((o1, o2) -> o1.compareTo(o2)));
    for (Integer num : numbers) {
      System.out.println(num);
    }
  }

  // Defined class obj example
  public static void example4() {
    List<Temp> list = new ArrayList<Temp>();
    Temp t1 = new Temp("A1", "B1", 1);
    Temp t2 = new Temp("A2", "B2", 2);
    Temp t3 = new Temp("A3", "B3", 3);
    list.add(t1);
    list.add(t2);
    list.add(t3);

    for (Temp t : list) {
      System.out.println(t.A + " " + t.B + " " + t.C);
    }
  }

  // Add el example
  public static void example5() {
    List<String> list = new ArrayList<String>();
    System.out.println("Initinumbers list of elements: " + list);

    list.add("A");
    list.add("B");
    list.add("C");
    System.out.println("add(E e) method: " + list);

    list.add(1, "D");
    System.out.println("add(int index, E element) method: " + list);

    List<String> numbers2 = new ArrayList<String>();
    numbers2.add("E");
    numbers2.add("F");
    list.addAll(numbers2);
    System.out.println("addAll(Collection<? extends E> c) method: " + list);

    List<String> numbers3 = new ArrayList<String>();
    numbers3.add("G");
    numbers3.add("H");
    list.addAll(1, numbers3);
    System.out.println("addAll(int index, Collection<? extends E> c) method: " + list);

  }

  // Remove el example
  public static void example6() {
    List<String> list = new ArrayList<String>();
    list.add("A");
    list.add("B");
    list.add("C");
    list.add("D");
    list.add("E");
    System.out.println("init list of elements: " + list);

    list.remove("F");
    System.out.println("remove(object) method: " + list);

    list.remove(0);
    System.out.println("remove(index) method: " + list);

    List<String> list2 = new ArrayList<String>();
    list2.add("G");
    list2.add("H");
    list.addAll(list2);
    System.out.println("Updated list :  " + list);

    list.removeAll(list2);
    System.out.println("removenumbersl() method: " + list);

    list.removeIf(str -> str.contains("E"));
    System.out.println("RemoveIf() method: " + list);

    list.clear();
    System.out.println("clear() method: " + list);
  }

  // isEmpty() and retainAll()
  public static void example7() {
    List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> numbers2 = Arrays.asList(6, 7, 8, 9, 10);

    numbers1.retainAll(numbers2);
    System.out.println("iterating numbers1 after retain numbers2: ");
    for (Integer num : numbers1) {
      System.out.println(num);
    }

    numbers1.clear();
    if (numbers1.isEmpty()) {
      System.out.println("numbers1 is empty");
    }

  }

  public static void main(String[] args) {
    // ....
  }

}
// can contain duplicate el
// maintains insertion order
// non sync
// random access with index
// manipulation slower than LinkedList
// not use int, float, char,... to create ArrayList -> Integer, Float, Char,...

// public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable,
// Serinumbersizable

// constructor:
// ArrayList()
// ArrayList(Collection<? extends E> c)
// ArrayList(int capacity)

// methods:
// get(int index): get el
// set(int index, E el): set el at index
// isEmpty()
// Iterator() | listIterator()
// lastIndexOf(Object o)
// toArray()
// contains(Object o)
// indexOf(Object o)
// clone
// sort(Comparator<? super E> c)
// size()
// retainl(Collection<?> c)

// loop:
// By Iterator interface.
// By for-each loop.
// By ListIterator interface.
// By for loop.
// By forEach() method.
// By forEachRemaining() method.

