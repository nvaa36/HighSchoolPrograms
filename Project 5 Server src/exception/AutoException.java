package exception;

import java.io.*;

// Creates an exception handler for not being able to find a file with the name specified, fixes the error, and logs the error in a file
public class AutoException extends Exception{
	private int errorno;
	private String errormsg;
	
	public AutoException() {
		super();
		errorno = 1;
		errormsg = "File Not Found";
		printmyproblem();
	}
	
	public AutoException(String errormsg) {
		super();
		this.errormsg = errormsg;
		printmyproblem();
	}
	
	public AutoException(int errorno) {
		super();
		this.errorno = errorno;
		switch(errorno) {
		case 1: errormsg = "File Not Found"; break;
		case 2: errormsg = "Missing length for OptionSet array"; break;
		case 3: errormsg = "Missing Price, price was set to the default value of 0"; break;
		case 4: errormsg = "Missing length of OptionSet"; break;
		case 5: errormsg = "Can't find the Option to update the price of"; break;
		default: errormsg = "error";
		}
		printmyproblem();
	}
	
	public AutoException(int errorno, String errormsg) {
		super();
		this.errorno = errorno;
		this.errormsg = errormsg;
		printmyproblem();
	}
	
	public int getErrorno() {
		return errorno;
	}
	
	public void setErrorno(int errorno) {
		this.errorno = errorno;
	}
	
	public String getErrormsg() {
		return errormsg;
	}
	
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	
	public void printmyproblem() {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("errorlog.txt", true));
			out.write("\nAutoException errorno=" + errorno + ", errormsg=" + errormsg);
			out.close();
		}catch (IOException e) {
		}
	}

	public void fix()
	{
		FixErrors f = new FixErrors();
		switch(errorno) {
		case 3: f.fix3(errorno, errormsg);
		// fix3 for fixing a missing price
		}
	}
}