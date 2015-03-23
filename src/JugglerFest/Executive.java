///////////////////////////////////////////////////////////////////
// Executive.java - 	Entry Point for to Solve the 			 //
//						JugglerFest problem						 //
// Ver 1.0                                                       //
// Application: 		JugglerFest Problem				         //
// Platform:    		Dell Inspiron 17R 5721, Core i5,		 // 
//						Windows 8.1, Eclipse IDE - Luna			 //
// 										                         //
// Author:      		Akhil Panchal, Syracuse University		 //
//              		(408) 921-0731, ahpancha@syr.edu	     //
///////////////////////////////////////////////////////////////////

package JugglerFest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import JugglerFest.JugglerFest;

public class Executive {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException{
		JugglerFest jf = new JugglerFest();
		String path = new File("src/Test/jugglerfest_big.txt").getAbsolutePath();
		try{
			jf.readProblem(path);
		}catch(Exception ex){
			System.out.println("File Read error");
		}
		long startTime = System.currentTimeMillis();
		jf.assignJugglersToCircuits();
		long endTime = System.currentTimeMillis();
		long duration = (endTime - startTime);  //divide by 1000 to get milliseconds.
		System.out.println(duration + " milliseconds taken to find solution!\nStoring Solution to File...Wait..");
		try{
		jf.saveAssignments("src/Output/jugglerfestSolution.txt");
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		//jf.showAssignments();
	}
}
