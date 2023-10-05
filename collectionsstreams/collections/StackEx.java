package collectionsstreams.collections;

// FILO
import java.util.EmptyStackException;
import java.util.Stack;

public class StackEx {
  // methods example
  public static void example1(Stack<Integer> stk) {
    try {
      Integer searchValue = stk.search(456);
      System.out.println("search value at index: " + searchValue);
      Integer peekValue = stk.peek();
      System.out.println("peek value: " + peekValue);
      Integer popValue = stk.pop();
      System.out.println("pop value: " + popValue);
    } catch (EmptyStackException e) {
      System.out.println("empty stack");
    }
  }

  // iterator stack
  public static void example2(Stack<Integer> stk) {
    // str.forEach(num -> System.out.println(num);
    // or
    // ListIterator<Integer> ListIterator = stk.listIterator(stk.size());
    // System.out.println("Iteration over the Stack from top to bottom:");
    // while (ListIterator.hasPrevious()) {
    // Integer avg = ListIterator.previous();
    // System.out.println(avg);
    // }
    // or
    // Iterator<Integer> iterator = stk.iterator();
    // while(iterator.hasNext()) {
    // Object values = iterator.next();
    // System.out.println(values);
    // }
  }

  public static void main(String args[]) {
    Stack<Integer> stk = new Stack<>();
    boolean result = stk.empty();
    System.out.println("Is the stack empty? " + result);

    stk.push(123);
    stk.push(456);
    stk.push(789);
    stk.push(821);
    System.out.println("Elements: " + stk);

  }
}

// methods:
// empty(): stack is empty
// push(E item): push el on top of stack
// pop(): get and remove el from top of stack
// peek(): get el from top of stack (not removing)
// search(Object o): return position of o
