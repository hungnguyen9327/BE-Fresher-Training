package oopconcepts.abstraction;
//used to achieve abstraction.
//multiple inheritance.
//used to achieve loose coupling.
interface Drawable {
	void draw();
}
interface Showable extends Drawable { //inherit drawable interface
	void show();
}

class Eclipse implements Showable {
	public void draw() {System.out.println("Drawing eclipse...");}
	public void show() {System.out.println("Showing eclipse...");}  
}

class Square implements Showable {
	public void draw() {System.out.println("Drawing square...");}  
	public void show() {System.out.println("Showing square...");}  
}

public class Interface {
	public static void main(String args[]) {
		Drawable d = new Eclipse();
		d.draw();  
	}
}

//Default method
//Static method
//a marker or tagged interface, e.g Serializable, Cloneable, Remote
//Nested Interface