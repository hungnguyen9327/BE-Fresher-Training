package oopconcepts.encapsulation;

//private
class A {
	//only can be access in A class
	private int data;
	private void message() {
		System.out.print("Test...");
	}
	
	private A() {//cant create instance of constructor, error when use new keyword
		
	}
}
//default
class B {
	//only can be access in package
	int data;
	void message() {
		System.out.print("Test...");
	}
}
//protected
class C {
	//only can be access in class and it's child class
	protected int data;
	protected void message() {
		System.out.print("Test...");
	}
}

//public
class D {
	//can be access anywhere
	public int data;
	public void message() {
		System.out.print("Test...");
	}
}


public class AccessModifiers {
	
}
