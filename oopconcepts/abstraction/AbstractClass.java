package oopconcepts.abstraction;

interface Temp {
	void change1();
	void change2();
}

abstract class Shape implements Temp {
	Shape() { //have constructor
		System.out.println("Shape instance created...");
	}  
	abstract void draw();//abstract method
	
	public void change1(){//have methods
		System.out.println("1st Shape Changed...");
	}  
	//..and have data member (field) 
}

class Triangle extends Shape {
	@Override
	void draw() {
		System.out.println("Drawing Triangle...");
	}
	public void change2() {
		System.out.println("Triangle Shape Changed...");//override for abstract class
	}
}

class Circle extends Shape {
	@Override
	void draw() {
		System.out.println("Drawing Circle...");
	}
	public void change2() {
		System.out.println("Triangle Shape Changed...");//override for abstract class
	}
}

public class AbstractClass {
	public static void main(String args[]){  
		Shape t;
		t = new Triangle();
		t.draw(); 
		t = new Circle();
		t.draw();
	}  
}
