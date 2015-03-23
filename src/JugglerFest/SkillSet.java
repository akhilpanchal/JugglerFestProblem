///////////////////////////////////////////////////////////////////
// SkillSet.java - 		Represents various skills required for	 //
//						a Circuit or a Juggler					 //
// Ver 1.0                                                       //
// Application: 		JugglerFest Problem				         //
// Platform:    		Dell Inspiron 17R 5721, Core i5,		 // 
//						Windows 8.1, Eclipse IDE - Luna			 //
// 										                         //
// Author:      		Akhil Panchal, Syracuse University		 //
//              		(408) 921-0731, ahpancha@syr.edu	     //
///////////////////////////////////////////////////////////////////

package JugglerFest;

public class SkillSet {
	private int h;
	private int e;
	private int p;
	
	//-----< Default constructor for an SkillSet>-----
	public SkillSet(int h, int e, int p){
		validateSkills(h, e, p);
		this.h = h;
		this.e = e;
		this.p = p;
	}

	void validateSkills(int h, int e, int p) {
		if(!(h>-1 && h<11 && e>-1 && e<11 && p>-1 && p<11)){
			System.out.println("Skill Values out of range! Please Re-Enter");
		}
	}

	//-----< Setters and Getters for various skills of the Circuit or the Juggler >-----
	public int getH(){
		return h;
	}
	public int getE(){
		return e;
	}
	public int getP(){
		return p;
	}
}
