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

public class Juggler extends Festival{

	private String name;
	private SkillSet skill;
	private Map<Circuit, Integer> cir_pref;

	//-----< Default constructor for a Juggler >-----
	public Juggler(){
		cir_pref = new LinkedHashMap<Circuit,Integer>();
	}
	
	//-----< Setters and Getters for Name of the Juggler >-----
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

	//-----< Setters and Getters for preference circuits of the Juggler >-----
	public Map<Circuit,Integer> getPreferences(){
		return cir_pref;
	}
	public void addCircuitPreference(Circuit pref, int x){
		cir_pref.put(pref,x);
	}

}
