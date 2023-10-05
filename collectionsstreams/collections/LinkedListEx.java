package collectionsstreams.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedListEx {
  // Simple linkedlist example
  public static void example1() {
    int sum = 0;
    List<Integer> numbers = new LinkedList<Integer>(Arrays.asList(1, 2, 3, 4, 5));

    Iterator<Integer> itr = numbers.iterator();
    while (itr.hasNext()) {
      sum += itr.next();
    }
    System.out.println("Sum: " + sum);
  }

  // Add in linkedlist example
  public static void example2() {
    LinkedList<Integer> numbers = new LinkedList<Integer>();
    System.out.println("Initial list of elements: " + numbers);

    numbers.add(1);
    numbers.add(2);
    numbers.add(3);
    System.out.println("add(E e) method: " + numbers);

    numbers.add(1, 4);
    System.out.println("add(int index, E element) method: " + numbers);

    LinkedList<Integer> numbers2 = new LinkedList<Integer>();
    numbers2.add(5);
    numbers2.add(6);
    numbers.addAll(numbers2);
    System.out.println("addAll(Collection<? extends E> c) method: " + numbers);

    LinkedList<Integer> numbers3 = new LinkedList<Integer>();
    numbers3.add(7);
    numbers3.add(8);
    numbers.addAll(1, numbers3);
    System.out.println("addAll(int index, Collection<? extends E> c) method: " + numbers);

    numbers.addFirst(9);
    System.out.println("addFirst(E e) method: " + numbers);

    numbers.addLast(10);
    System.out.println("addLast(E e) method: " + numbers);
  }

  // Remove in linkedlist example
  public static void example3() {
    LinkedList<String> numbers = new LinkedList<String>();
    numbers.add("A");
    numbers.add("B");
    numbers.add("C");
    numbers.add("D");
    numbers.add("K");
    numbers.add("D");
    numbers.add("G");
    numbers.add("H");
    numbers.add("I");
    System.out.println("Initial list of elements: " + numbers);

    numbers.remove("C");
    System.out.println("remove(E e) method: " + numbers);

    numbers.remove(0);
    System.out.println("remove(index) method: " + numbers);

    LinkedList<String> numbers2 = new LinkedList<String>();
    numbers2.add("J");
    numbers2.add("K");
    numbers.addAll(numbers2);
    System.out.println("Updated list : " + numbers);

    numbers.removeAll(numbers2);
    System.out.println("removeAll() method: " + numbers);

    numbers.removeFirst();
    System.out.println("removeFirst() method: " + numbers);

    numbers.removeLast();
    System.out.println("removeLast() method: " + numbers);

    numbers.removeFirstOccurrence("D");
    System.out.println("removeFirstOccurrence() method: " + numbers);

    numbers.removeLastOccurrence("K");
    System.out.println("removeLastOccurrence() method: " + numbers);

    numbers.clear();
    System.out.println("clear() method: " + numbers);
  }

  // Reverse linkedlist example
  public static void example4() {
    LinkedList<Integer> numbers = new LinkedList<Integer>();
    numbers.add(1);
    numbers.add(2);
    numbers.add(3);
    Iterator i = numbers.descendingIterator();
    while (i.hasNext()) {
      System.out.println(i.next());
    }
  }

  public static void main(String args[]) {

  }

}

// can contain duplicate el
// maintains insertion order
// non sync
// random access with index
// manipulation faster than ArrayList
// used as list, stack, queue

// constructors:
// LinkedList()
// LinkedList(Collection<? extends E> c)

// methods:
// element(): get first element
// offer(E e): insert e as last el
// offerFirst(E e): insert at first of list
// offerLast(E e): insert at last of list
// peek()|peekFirst(): retrieve first el
// peekLast(): retrieve last el
// poll()|pollFirst(): retrieve + remove first el
// pollLast(): retrieve + remove last el
// pop(): pop from stack
// push(E e): push to stack
// toArray()


