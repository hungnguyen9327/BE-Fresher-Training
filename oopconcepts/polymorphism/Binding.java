package oopconcepts.polymorphism;

//private, final, static => static binding
class Dog {
	private void eat(){
		System.out.println("dog is eating...");
	}  
	  
	public static void main(String args[]){  
		Dog d1 = new Dog();  
		d1.eat();  
	}  
}
//object type is determined in run-time => dynamic binding
//class Animal{  
//	 void eat(){System.out.println("animal is eating...");}  
//}  
//	  
//class Dog extends Animal{  
//	void eat(){System.out.println("dog is eating...");}  
//	  
//	public static void main(String args[]){  
//		 Animal a=new Dog();  
//		 a.eat();  
//	}  
//}  

public class Binding {

	public static void main(String[] args) {
		
		

	}

}
