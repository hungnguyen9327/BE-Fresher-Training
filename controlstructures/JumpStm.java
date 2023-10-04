package controlstructures;

public class JumpStm {
	//Break keyword
	public static void example1() {
		int sum = 0;
		for (int i = 1; i <= 10; i++) {
			if (i == 8) {
				break;
			}
			sum += i;
		}
		System.out.println("sum of first 7 n numbers is " + sum);
	}
	//Continue keyword
	public static void example2() {
		int sum = 0;
		for (int i = 1; i <= 10; i++) {
			if (i == 8) {
				continue;
			}
			sum += i;
		}
		System.out.println("sum of first 10 n numbers (except 8) is " + sum);
	}
	public static void main(String[] args) {
		
	}

}
