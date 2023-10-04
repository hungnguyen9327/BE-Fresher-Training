package exceptionhandling;

class Temp1 {   
	  
	//not declare exception
	void msg1() {  
		System.out.println("parent method1");  
	}   
	//declare exception
	void msg2() throws ArithmeticException {  
		System.out.println("parent method2");  
	}    
}    

public class MethodOverriding extends Temp1 {
	//cannot override by checked exception
	//but can with unchecked exception
	void msg1() throws ArithmeticException {
	//void msg1() throws IOException -> compile error
	    System.out.println("Test1");    
	}  
	//can declare same subclass exception or with no exception
	//but cannot declare parrent exception
	void msg2() throws ClassCastException {
		//void msg1() throws IOException -> compile error
		    System.out.println("Test2");    
		}  
}
