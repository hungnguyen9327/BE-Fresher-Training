package controlstructures;

public class ConditionStm {
	
	public static void main(String[] args) {

	}
	
	//If Statement
	public static void example1() {
		int a = 5, b = 10;
		if (a - b < 0) {
			System.out.println("a - b < 0");
		}
	}
	//If-else Statement
	public static void example2() {
		int a = 5, b = 10;
		if (a - b < 0) {
			System.out.println("a - b < 0");
		} else {
			System.out.println("a - b > 0");
		}
	}
	//If-else-if Statement
	public static void example3() {
		int a = 123;
		if (a > 10) {
			System.out.println("a > 10");
		} else if (a > 100) {
			System.out.println("a > 100");
		} else {
			System.out.println("a < 10");
		}
	}
	//Nested If Statement
	public static void example4() {
		int a = 5;
		if (a > 10) {
			if (a % 2 != 0) {
				System.out.println("a > 10 and be odd");
			} else {
				System.out.println("a > 10 and be even");
			}
		} else {
			System.out.println("a < 10");
		}
	}
	//Switch case Statement
	public static void example5() {
		int a = 5;
		switch (a) {
			case 1: System.out.println("a = 1"); break;
			case 2: System.out.println("a = 2"); break;
			case 3: System.out.println("a = 3"); break;
			case 4: System.out.println("a = 4"); break;
			case 5: System.out.println("a = 5"); break;
			default: System.out.println("a < 1 and a > 5"); break;
		}
	}
}
