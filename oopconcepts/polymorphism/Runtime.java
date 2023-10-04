package oopconcepts.polymorphism;

class E {
	void temp() {
		System.out.println("Rush E...");
	}
}

class F extends E {
	void temp() {
		System.out.println("Rush F...");
	}
}

public class Runtime {
	public static void main(String args[]) {
		F f = new F();
        E e = f;//upcasting
        
        e.temp();
	}	
}
