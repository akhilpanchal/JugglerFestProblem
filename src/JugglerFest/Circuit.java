///////////////////////////////////////////////////////////////////
// Circuit.java - 		Represents a Team or a Circuit in the	 //
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

public class Circuit {

	private String name;
	private Aspect skill;
	private static int count;	
	
	//-----< Default constructor for a Circuit >-----
	public Circuit(){
		count++;
	}

	//-----< Getter for number of Circuits in the problem >-----
	public static int getCount(){
		return count;
	}
	
	
	//-----< Setters and Getters for Name of the Circuit >-----
	public void setName(String s){
		name = s;
	}	
	public String getName(){
		return name;
	}
	
	//-----< Setters and Getters for the Aspect of the Circuit >-----
	public void setAspect(int h, int e, int p){
		skill = new Aspect(h, e, p);
	}
	public Aspect getAspect(){
		return skill;
	}
}
