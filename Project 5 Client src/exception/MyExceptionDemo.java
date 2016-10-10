package exception;


class MyException extends Exception {} 
 
public class MyExceptionDemo { 
 
 public void f() throws MyException { 
	System.out.println("Throw MyException from f()"); 
 	throw new MyException(); 
 }
 
 public static void main(String[] args) { 
  MyExceptionDemo sed = new MyExceptionDemo(); 
  try { 
    sed.f(); 
  } catch(MyException e) { 
     System.err.println("Caught it!"); 
  } 
 } 
} 