package controlstructures;

public class LoopStm {
	//For loop
	public static void example1() {
		int sum = 0;
		for (int i = 1; i <= 10; i++) {
			sum += i;
		}
		System.out.println("sum of first 10 n numbers is " + sum);
	}
	//For-each loop
	public static void example2() {
		int[] nums = {1, 2, 3, 4};
		int sum = 0;
		for (int num: nums) {
			sum += num;
		}
		System.out.println("sum of nums list is " + sum);
	}
	//while loop
	public static void example3() {
		int num = 123211;
		int temp = num, acc = 0;
		while (temp != 0) {
			acc = acc*10 + temp % 10;
			temp /= 10;
		}
		if (num == temp) {
			System.out.println("be symmetrical num");
		} else {
			System.out.println("not be symmetrical num");
		}
	}
	//do-while loop
	public static void example4() {
		int i = 1;    
		System.out.println("Printing the list of odd nums lower than 10 \n");    
		do {    
			System.out.println(i);    
			i = i + 2;    
		}while(i <= 10);
	}
	
	public static void main(String[] args) {
		
	}
	
}
