package oopconcepts.polymorphism;

class A {
	A() {
		System.out.println("Parrent class..."); //first
	}
}

public class InitBlock extends A {
	InitBlock(){  
		super();  
		System.out.println("Child class...");  //last
	}
	{
		System.out.println("instance initializer block..."); //second
	}//block
	public static void main(String args[]) {
		new InitBlock();
		
	}
}
