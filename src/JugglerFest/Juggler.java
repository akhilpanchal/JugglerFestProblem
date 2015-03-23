///////////////////////////////////////////////////////////////////
// Juggler.java - 		Represents a Juggler in the 			 //
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
import java.util.*;

public class Juggler {

	private String name;
	private Map<Circuit, Integer> cir_pref;
	private static int count;
	private Aspect skill;

	//-----< Default constructor for a Juggler >-----
	public Juggler(){
		cir_pref = new LinkedHashMap<Circuit,Integer>();
		count++;
	}
	
	//-----< Getter for number of Jugglers in the problem >-----
	public static int getCount(){
		return count;
	}
	
	//-----< Setters and Getters for Name of the Juggler >-----
	public void setName(String s){
		name = s;
	}	
	public String getName(){
		return name;
	}

	//-----< Setters and Getters for preference circuits of the Juggler >-----
	public Map<Circuit,Integer> getPreferences(){
		return cir_pref;
	}
	public void addCircuitPreference(Circuit pref, int x){
		cir_pref.put(pref,x);
	}
	
	//-----< Setters and Getters for the Aspect of the Circuit >-----
	public void setAspect(int h, int e, int p){
		skill = new Aspect(h, e, p);
	}	
	public Aspect getAspect(){
		return skill;
	}
}
