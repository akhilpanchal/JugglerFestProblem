///////////////////////////////////////////////////////////////////
// Aspect.java - 		Represents various skills required for	 //
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

public class Aspect {
	public int H;
	public int E;
	public int P;
	
	//-----< Default constructor for an Aspect>-----
	public Aspect(int h, int e, int p){
		if(h>-1 && h<11 && e>-1 && e<11 && p>-1 && p<11){
			H=h;
			E=e;
			P=p;
		}
		else
			System.out.println("Skill Values out of range! Please Re-Enter");
	}
	
	//-----< Setters and Getters for various skills of the Circuit or the Juggler >-----
	public int getH(){
		return H;
	}
	public int getE(){
		return E;
	}
	public int getP(){
		return P;
	}
}
