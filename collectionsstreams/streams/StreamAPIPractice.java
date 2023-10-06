package collectionsstreams.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamAPIPractice {
  // exercise-1
  // get even numbers from number list
  public static void exercise1(List<Integer> arr) {
    List<Integer> evenList = arr.stream().filter(num -> num % 2 == 0).collect(Collectors.toList());
    evenList.forEach(System.out::println);
  }

  // exercise-2
  // calc sum / avg of numbers list
  public static Double exercise2(List<Integer> arr) {
    // return arr.stream()
    // .reduce(0, (t, u) -> t + u);
    return (double) (arr.stream().reduce(0, (acc, cur) -> acc + cur) / (arr.size()));
  }

  // exercise-3
  // find duplicate value
  public static void exercise3(List<Integer> arr) {
    // solution1
    // Set<Integer> temp = new HashSet<>();
    // Set<Integer> duplicateValues = arr.stream()
    // .filter(n -> !temp.add(n))
    // .collect(Collectors.toSet());
    // solution2
    // Set<Integer> duplicateValues = arr.stream()
    // .filter(i -> Collections.frequency(arr, i) > 1)
    // .collect(Collectors.toSet());
    // solution3
    // Set<Integer> duplicateValues = arr.stream()
    // .collect(
    // Collectors.groupingBy(
    // Function.identity(),
    // Collectors.counting()
    // )
    // )
    // .entrySet().stream()
    // .filter(m -> m.getValue() > 1)
    // .map(Map.Entry::getKey)
    // .collect(Collectors.toSet());
    // System.out.print(duplicateValues);
  }

  // exercise-4
  // ...
  public static void exercise4(List<Integer> arr) {

  }

  public static void main(String args[]) {
    List<Integer> arr = Arrays.asList(5, 3, 9, 1, 7, 2, 6, 8, 4, 10, 10, 4, 3, 1, 7);
    exercise3(arr);
  }
}
