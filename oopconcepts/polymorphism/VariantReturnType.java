package oopconcepts.polymorphism;

class Temp1{  
	Temp1 get(){
		return this;
	}
	void message(){
	    System.out.println("Hello T1...");
	}
}

class Temp2 extends Temp1 {  
	@Override
	Temp2 get(){
		return this;
	}  
	@Override
	void message(){
	    System.out.println("Hello T2...");
	}
}  

class Temp3 extends Temp2 {  
	@Override
	Temp3 get(){
		return this;
	}  
	@Override
	void message(){
	    System.out.println("Hello T3...");
	}
}  
	  
class VariantReturnType {  
	  
	public static void main(String args[]){  
	    Temp1 t1 = new Temp1();
	    t1.message();
	    Temp2 t2 = new Temp2();
	    t2.message();
	    Temp3 t3 = new Temp3();
	    t3.message();
	}  
} 
