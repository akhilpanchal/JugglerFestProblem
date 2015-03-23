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

public class Circuit extends Festival {

	//-----< Default constructor for a Circuit >-----
	public Circuit(){
	}

	//-----< Setters and Getters for Name of the Circuit >-----
	public void setName(String s){
		name = s;
	}	
	public String getName(){
		return name;
	}
	
	//-----< Setters and Getters for the SkillSet of the Circuit >-----
	public void setSkillSet(int h, int e, int p){
		skill = new SkillSet(h, e, p);
	}
	public SkillSet getSkillSet(){
		return skill;
	}
}
