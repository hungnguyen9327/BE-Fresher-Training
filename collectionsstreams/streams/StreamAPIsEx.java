package collectionsstreams.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Product{  
    private int id;  
    private String name;  
    private float price;  
    public Product(int id, String name, float price) {  
        this.id = id;  
        this.name = name;  
        this.price = price;  
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}  
    
}  

public class StreamAPIsEx {
	//filtering collection + count
	public static void example1(List<Product> productsList) {
        List<Float> priceList = productsList.stream()  
                .filter(p -> p.getPrice() > 30000) 
                .map(p -> p.getPrice()) 
                .collect(Collectors.toList());  
        System.out.println(priceList);
        System.out.println("Length: " + priceList.stream().count());
	}
	//iterating collection
	public static void example2(List<Product> productsList) {
		//iterating
		Stream.iterate(1, e -> e + 1)  
	        .filter(element -> element % 2 == 0)  
	        .limit(7)
	        .forEach(System.out::println); 
		
		//iterating collection
        productsList.stream()  
	        .filter(product -> product.getPrice() == 30000)  
	        .forEach(product -> System.out.println(product.getName() + ": " + product.getPrice())); 
	}
	//reduce
	public static void example3(List<Product> productsList) {
        float totalPrice = productsList.stream()
        		.map((product) -> product.getPrice())
        		.reduce(0.0f, (acc, cur) -> acc + cur);
        // .reduce(0.0f, Float::sum)
        
        System.out.println(totalPrice);
	}
	//Sum by collection method
	public static void example4(List<Product> productsList) {
		double totalPrice = productsList.stream()  
                .collect(Collectors.summingDouble(product -> product.getPrice()));  
		System.out.println(totalPrice);
	}
	//Max and min
	public static void example5(List<Product> productsList) {
		Product maxPriceProduct = productsList.stream()
				.max((product1, product2) -> product1.getPrice() > product2.getPrice() ? 1: -1)
				.get();        
        Product minPriceProduct = productsList.stream()
        		.min((product1, product2)->product1.getPrice() > product2.getPrice() ? 1: -1)
        		.get(); 
        
        System.out.println("Max: " + maxPriceProduct.getName() + "-" + maxPriceProduct.getPrice());
        System.out.println("Min: " + minPriceProduct.getName() + "-" + minPriceProduct.getPrice());
	}
	//List -> Set, Map
	public static void example6(List<Product> productsList) {
		Set<Float> productSet =   
	            productsList.stream()  
	            .map(product -> product.getPrice())  
	            .collect(Collectors.toSet());    
		System.out.println(productSet);
		Map<Integer, String> productMap = productsList.stream()   
	            .collect(Collectors.toMap(p -> p.getId(), p -> p.getName()));    
		System.out.println(productMap);
	}
	//allMatch vs anyMatch vs noneMatch
	public static void example7(List<Product> productsList) {
		boolean allAbove15 = productsList.stream()
				.allMatch(product -> product.getPrice() > 15000f);
		boolean idAllAbove3 = productsList.stream()
				.anyMatch(product -> product.getId() >= 3);
		boolean isExistedLaptop = productsList.stream()
				.noneMatch(product -> product.getName().contains("Laptop"));
		System.out.println("all product's price are above 15k: " + allAbove15);
		System.out.println("any product's id are above 3: " + idAllAbove3);
		System.out.println("none product is laptop product: " + isExistedLaptop);
	}
	//flatMap and flatMapToDouble
	public static void example8() {
		List<List<Integer>> numbers = Arrays.asList(
			Arrays.asList(1, 2),
			Arrays.asList(3, 4)
		);

		List<Integer> flattenedList = numbers.stream()
			.flatMap(List::stream)
			.collect(Collectors.toList());
		System.out.println("Flattened List: " + flattenedList);
		
		double[] flattenedArray = numbers.stream()
			.flatMapToDouble(list -> list.stream().mapToDouble(Integer::doubleValue))//
			.toArray();

		System.out.println("Flattened (double) Array: " + Arrays.toString(flattenedArray));
		
	}
	//skip and sorted
	public static void example9() {
		Stream<Integer> stream = Stream.of(5, 3, 9, 1, 7, 2, 6, 8, 4, 10);
		//skip
        Stream<Integer> skipedStream = stream.skip(3);
        skipedStream.forEach(System.out::println);
        //sort
        Stream<Integer> sortedStream = stream.sorted();
        sortedStream.forEach(System.out::println);
	}
	
	public static void main(String args[]) {
		List<Product> productsList = new ArrayList<Product>();  
		 
        productsList.add(new Product(1,"HP Laptop", 24000f));  
        productsList.add(new Product(2,"Dell Laptop", 18000f));  
        productsList.add(new Product(3,"Lenovo Laptop", 20000f));  
        productsList.add(new Product(4,"Apple Laptop", 40000f)); 
        
        example1(productsList);
	}
}

//builder, concat, distinct, empty, findFirst, findAny, of, peek, 	
