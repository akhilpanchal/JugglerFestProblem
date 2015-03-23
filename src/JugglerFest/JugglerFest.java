///////////////////////////////////////////////////////////////////
// JugglerFest.java - 	Solution Class that solves the			 //
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import JugglerFest.Juggler;

public class JugglerFest {
	
	private List<Juggler> jugglers;
	private Map<Circuit, Integer> vacancyCount;
	private List<Circuit> circuits;
	private int teamCount;
	private Map<Circuit,List<Juggler>> assignment;
	
	//-----< Default constructor for the JugglerFest Solution >-----
	public JugglerFest(){
		jugglers = new ArrayList<Juggler>();
		circuits = new ArrayList<Circuit>();
		assignment = new LinkedHashMap<Circuit, List<Juggler>>();
		vacancyCount = new HashMap<Circuit, Integer>();
	}
	
	//-----< Read the Input for the JugglerFest Problem >-----/
	public void readProblem(String filename) throws IOException{
		
		try{
			List<String> lines = Files.readAllLines(Paths.get(filename),Charset.defaultCharset());
			int h=0, e=0, p=0;
			for (String line : lines) {
				String[] tokens = line.split(" ");
				int tok_index = 0;
				if(tokens[tok_index].equals("C")){	// Read Circuit Entry
					tok_index++;
					Circuit cir = new Circuit();
					cir.setName(tokens[tok_index++]);
					while(tok_index<tokens.length){
						int i = 0;
						char c = tokens[tok_index].charAt(i++);
						i++;
						StringBuffer buff = new StringBuffer();
						while(i < tokens[tok_index].length() && tokens[tok_index].charAt(i)!='\0'){
							buff.append(tokens[tok_index].charAt(i++));
						}
						tok_index++;						
						int skill_val = Integer.valueOf(buff.toString());
						switch(c){
						case 'H':
							h=skill_val;
							break;
						case 'E':
							e=skill_val;
							break;
						case 'P':
							p=skill_val;
							break;
					
						}
						cir.setAspect(h, e, p);
					}
					// Store Circuit
					circuits.add(cir);
				}
				else if(tokens[tok_index].equals("J")){	// Read Juggler Entry
					tok_index++;
					Juggler jug = new Juggler();
					jug.setName(tokens[tok_index++]);
				
					int skill_count = 0;
					while(tok_index<tokens.length && skill_count<3){
						int i = 0;
						char c = tokens[tok_index].charAt(i++);
						i++;
						StringBuffer buff = new StringBuffer();
						while(i < tokens[tok_index].length() && tokens[tok_index].charAt(i)!='\0'){
							buff.append(tokens[tok_index].charAt(i++));
						}
											
						int skill_val = Integer.valueOf(buff.toString());
						switch(c){
						case 'H':
							h=skill_val;
							break;
						case 'E':
							e=skill_val;
							break;
						case 'P':
							p=skill_val;
							break;
					
						}
						jug.setAspect(h, e, p);
						skill_count++;
						tok_index++;
					}
				
					// Read preferences
					String[] pref = tokens[tok_index].split(",");
					for(int j=0;j<pref.length;j++){
						for(Circuit c : circuits){
							if(c.getName().equals(pref[j])){
								jug.addCircuitPreference(c,calculateDp(c,jug));
							}
						}
					}
					// Store Juggler
					jugglers.add(jug);
				}
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		//Calculate Jugglers per Team/Circuit
		if(jugglers.size()%circuits.size()!=0){
			System.out.println("Equal sized teams not possible!\n");
		}
		try{
			teamCount = jugglers.size()/circuits.size();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		//Create spotCount Table
		for(Circuit c : circuits){
			vacancyCount.put(c, teamCount);
		}
	}
	
	
	//-----< THE SOLUTION METHOD: Assign the participant Jugglers to Teams/Circuits >-----
	public void assignJugglersToCircuits(){
	
		for(int i = 0; i < jugglers.size(); i++){
		
			Juggler j = jugglers.get(i);
			Map<Circuit, Integer> pref = j.getPreferences();
			int pref_count = 0;
			for(Circuit c : pref.keySet()){
				
				if(addAssignment(c,j)){	//assignment successful
					break;
				}
				else{
					Juggler weak = compareWeakest(c,j);
					if(weak!=j){	//Compare with weakest juggler in the team/circuit
						List<Juggler> temp = assignment.get(c);
						temp.remove(weak);
						temp.add(j);
						assignment.put(c, temp);
						jugglers.add(weak);
						break;
						
					}
					//Not possible in this pref level, check if last pref
					else if(pref_count == pref.size()){
						//if last pref level, add in any circuit that has vacant spot
						for(Map.Entry<Circuit, Integer> v : vacancyCount.entrySet()){
							if(v.getValue() != 0){
								if(addAssignment(v.getKey(), j)){
									System.out.println("Placed in vacant spot!");
									break;
								}									
							}
						}
					}			
					pref_count++;
				}	
			}
		}
	}
	
	//-----< Private Helper for the solution method >-----
	//-----< Try assigning a preferred circuit to the Juggler >-----
	private boolean addAssignment(Circuit s, Juggler jug){
		if(assignment.containsKey(s)){
			if(vacancyCount.get(s)!=0){
				int count = vacancyCount.get(s);
				jug.addCircuitPreference(s, calculateDp(s, jug));
				vacancyCount.put(s, count-1);
				
				List<Juggler> temp = assignment.get(s);
				temp.add(jug);
				assignment.put(s, temp);
				return true;
			}
			else
				return false;
		}
		else{
			List<Juggler> temp = new LinkedList<Juggler>();
			temp.add(jug);
			assignment.put(s, temp);
			vacancyCount.put(s, vacancyCount.get(s)-1);
			return true;
		}
	}
	
	//-----< Private Helper for the solution method >-----
	//-----< Compare the Juggler with the Weakest Juggler currently in the team >-----
	private Juggler compareWeakest(Circuit c, Juggler jug){
		List<Juggler> temp = assignment.get(c);
		Juggler j_weak = findWeakest(c,temp);
		if(calculateDp(c, j_weak) < calculateDp(c, jug))
			return j_weak;
		else
			return jug;
	}
	
	//-----< Private Helper for the solution method >-----
	//-----< Find the weakest Juggler in the Circuit >-----
	private Juggler findWeakest(Circuit s, List<Juggler> jugs){
		
		Juggler weakest = jugs.get(0);
		int weakdp = calculateDp(s, weakest);
		for(Juggler j : jugs){
			if(weakdp > calculateDp(s,j)){
				weakest = j;
			}
		}
		
		return weakest;
	}
	
	//-----< Private Helper for the solution method >-----
	//-----< Calculate the Dot Product of the Circuit and the Juggler >-----
	private int calculateDp(Circuit c, Juggler j){
		
		int h = c.getAspect().H;
		int e = c.getAspect().E;
		int p = c.getAspect().P;
		
		int dp = 0;
		dp += h*j.getAspect().H;
		dp += e*j.getAspect().E;
		dp += p*j.getAspect().P;
		return dp;
		
	}
	
	//-----< Store the assignments in the specified File >-----
	public void saveAssignments(String filepath) throws FileNotFoundException, UnsupportedEncodingException{
		//Save assignments to file		
		FileOutputStream fop = null;
		File file;
		
		try {
 
			file = new File(filepath);
			fop = new FileOutputStream(file);
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			String content = new String();
			//Traverse Assignments and Store to File
			for(Map.Entry<Circuit, List<Juggler>> a : assignment.entrySet()){
				content += a.getKey().getName() + " ";
				for(Juggler j : a.getValue()){
					content += j.getName() + " ";
					for(Map.Entry<Circuit, Integer> p : j.getPreferences().entrySet()){
						content += " " + p.getKey().getName() + ":" + p.getValue();
					}
					if(j!=a.getValue().get(a.getValue().size() - 1))
					content += ",";
				}
				content += "\n";
			}
			
			// get the content in bytes
			byte[] contentInBytes = content.getBytes();
 
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//-----< Display the Assignments on Console >-----
	public void showAssignments(){
		
		for(Map.Entry<Circuit, List<Juggler>> a : assignment.entrySet()){
			System.out.print(a.getKey().getName() + " ");
			for(Juggler j : a.getValue()){
				System.out.print(j.getName() + " ");
			}
			System.out.println();
		}
	}
	
	//-----< Display the problem as read from the Input File >-----
	public void showStore(){
		System.out.println("\n\nStore: \n");
		for(int i = 0; i<circuits.size();i++){
			Circuit c = circuits.get(i);
			System.out.println(c.getName() +" "+c.getAspect().getH()+" "+c.getAspect().getE()+" "+c.getAspect().getP());
		}
		
		for(Juggler j : jugglers){
			
			System.out.print("\n"+j.getName()+" "+j.getAspect().getH()+" "+j.getAspect().getE()+" "+j.getAspect().getP());
			Map<Circuit, Integer> preferences = j.getPreferences();
			System.out.print(" ");
			
			for(Map.Entry<Circuit,Integer> en : preferences.entrySet()){
				System.out.print(en.getKey().getName() + " ");
			}
			System.out.print("\n");
		}
		System.out.println("\n\nTotal circuits = " + Circuit.getCount());
		System.out.println("Total jugglers = " + Juggler.getCount());
		System.out.println("Jugglers per Team = " + teamCount);
	}
}
