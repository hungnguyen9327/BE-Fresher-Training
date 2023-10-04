package collectionsstreams.collections;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetEx {
	//insert example
	public static void example1(HashSet<Integer> numbers) {
		HashSet<Integer> otherNumbers = new HashSet<>();
        // Using addAll, add method
		otherNumbers.addAll(numbers);
        numbers.add(5);
        System.out.println("New HashSet: " + numbers);
	}
	//iterate and access value example
	public static void example2(HashSet<Integer> numbers) {
        // Calling iterator() method
        Iterator<Integer> iterate = numbers.iterator();
        System.out.print("HashSet using Iterator: ");
        // Access el
        while(iterate.hasNext()) {
            System.out.print(iterate.next());
            System.out.print(", ");
        }
	}	
	//remove value example
	public static void example3(HashSet<Integer> numbers) {
        boolean value1 = numbers.remove(7);
        System.out.println("Is 7 removed: " + value1);

        boolean value2 = numbers.removeAll(numbers);
        System.out.println("Are all elements removed: " + value2);
	}		
	//union example
	public static void example4(HashSet<Integer> numbers) {
		HashSet<Integer> otherNumbers = new HashSet<>();
		otherNumbers.add(10);
		otherNumbers.add(39);
        
        //union numbers and otherNumbers
        numbers.addAll(otherNumbers);
        System.out.println("Union is: " + numbers);
	}
	//intersection example
	public static void example5(HashSet<Integer> numbers) {
		HashSet<Integer> otherNumbers = new HashSet<>();
		otherNumbers.add(2);
		otherNumbers.add(4);
		otherNumbers.add(7);
        //intersect numbers and otherNumbers
        numbers.retainAll(otherNumbers);
        System.out.println("Intersection is: " + numbers);
	}	
	//diff example
	public static void example6(HashSet<Integer> numbers) {
		HashSet<Integer> otherNumbers = new HashSet<>();
		otherNumbers.add(2);
		otherNumbers.add(4);
		otherNumbers.add(7);
        //intersect numbers and otherNumbers
        numbers.removeAll(otherNumbers);
        System.out.println("Difference is: " + numbers);
	}		
	//subset example
	public static void example7(HashSet<Integer> numbers) {
		HashSet<Integer> otherNumbers = new HashSet<>();
		otherNumbers.add(2);
		otherNumbers.add(7);
        //Check if otherNumbers is a subset of numbers
        boolean res = numbers.containsAll(otherNumbers);
        System.out.println("Is otherNumbers is subset of numbers: " + res);
	}		
	public static void main(String args[]) {
		HashSet<Integer> numbers = new HashSet<>();
		//init value
		numbers.add(2);
		numbers.add(7);
		numbers.add(9);
        System.out.println("HashSet: " + numbers);
        
        //...
        
	}
}
