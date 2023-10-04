package exceptionhandling;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//define exception
class UserDefinedException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserDefinedException(String str) {  
        super(str);  
    }  
}

class Temp {  
	void method() throws IOException{  
		throw new IOException("i/o error");  
	}  
}  

public class ThrowException {
	
	//throwing unchecked exception
	public static void example1(int a) {
		if(a >= 10) {  
            //throw Arithmetic exception
            throw new ArithmeticException("a must be lower than 10");    
        } else {  
            System.out.println("a is lower than 10");  
        }  
	}
	//throwing checked exception
	public static void example2() {
        try {  
        	FileReader file = new FileReader("C:\\Users\\temp.txt");  
            BufferedReader fileInput = new BufferedReader(file);  
            throw new FileNotFoundException(); //have to surround by try catch block
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        }
	}	
	//user-defined exception
	public static void example3() {
		try {  
            // throw user defined exception  
            throw new UserDefinedException("This is user-defined exception");  
        } catch (UserDefinedException e) {  
            System.out.println("Caught the exception");  
            System.out.println(e.getMessage());  
        }  
	}
	//Throws keyword
	//only checked exception
	public static void example4() throws IOException { //declare in method -> throw error if main doesn't handle ex
		//using try-catch -> statements in method will be executed fine
		try {
			Temp m = new Temp();  
			m.method();  
		} catch(Exception e) {
			System.out.println("exception handled");
		}
	}
	
	public static void main(String args[]) {
		
	}
}	
