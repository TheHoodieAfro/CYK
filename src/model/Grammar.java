package model;

import java.util.*;
import java.io.*;

public class Grammar {
	
	//----------------------------- Atributes -----------------------------
	public String start;
	public ArrayList<String> terminals;
	public ArrayList<String> variables;
	public TreeMap<String,ArrayList<String>> grammar;
	
	//----------------------------- Constructors -----------------------------
	public Grammar(String start, ArrayList<String> terminals, ArrayList<String> variables, TreeMap<String, ArrayList<String>> grammar) {
		super();
		this.start = start;
		this.terminals = terminals;
		this.variables = variables;
		this.grammar = grammar;
	}
	
	public Grammar(String startingSymbol, String terminals, String variables, String grammar) {
		super();
		this.start = startingSymbol;
		
		String[] terminal = terminals.split(" ");
		this.terminals = new ArrayList<String>();
		for(int i=0; i<terminal.length; i++) this.terminals.add(terminal[i]);
		
		String[] variable = variables.split(" ");
		this.variables = new ArrayList<String>();
		for(int i=0; i<variable.length; i++) this.variables.add(variable[i]);
		
		this.grammar = new TreeMap<>();
		productions(grammar);
	}
	
	//----------------------------- Getters and Setters -----------------------------
	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}

	/**
	 * @return the terminals
	 */
	public ArrayList<String> getTerminals() {
		return terminals;
	}

	/**
	 * @return the variables
	 */
	public ArrayList<String> getVariables() {
		return variables;
	}

	/**
	 * @return the grammar
	 */
	public TreeMap<String, ArrayList<String>> getGrammar() {
		return grammar;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * @param terminals the terminals to set
	 */
	public void setTerminals(ArrayList<String> terminals) {
		this.terminals = terminals;
	}
	public void setTerminals(String terminals) {
		String[] terminal = terminals.split(" ");
		this.terminals = new ArrayList<String>();
		for(int i=0; i<terminal.length; i++) this.terminals.add(terminal[i]);
	}

	/**
	 * @param variables the variables to set
	 */
	public void setVariables(ArrayList<String> variables) {
		this.variables = variables;
	}
	public void setVariables(String variables) {
		String[] variable = variables.split(" ");
		this.variables = new ArrayList<String>();
		for(int i=0; i<variable.length; i++) this.variables.add(variable[i]);
	}

	/**
	 * @param grammar the grammar to set
	 */
	public void setGrammar(TreeMap<String, ArrayList<String>> grammar) {
		this.grammar = grammar;
	}
	public void setGrammar(String grammar) {
		productions(grammar);
	}
	
	//----------------------------- Methods -----------------------------
	public void productions(String prodsTxt) {
		
		Scanner sc = null;
		try {
			sc = new Scanner(new File(prodsTxt));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		ArrayList<String> prods = new ArrayList<>();
		
		while(sc.hasNextLine()) {
			String s = sc.nextLine();
			System.out.println(s);
			prods.addAll(Arrays.<String>asList(toArray(s)));
            String left = prods.get(0);
            prods.remove(0);
            grammar.put(left, new ArrayList<String>());
            grammar.get(left).addAll(prods);
            prods.clear();
		}
		
	}
	
	public String CYK(String w) {
		
		String[][] table = table(w);
		String[][] tableCYK = algorithm(table, w);
		
		if(tableCYK[tableCYK.length-1][tableCYK[tableCYK.length-1].length-1].contains(start)) {
			return ("La cadena "+ w +" es generada por la gramatica");
		}else {
			return ("La cadena "+ w +" NO es generada por la gramatica");
		}
		
	}
	
	public String[][] table(String w) {
		
		int l = w.length();
		String[][] table = new String[l + 1][];
        table[0] = new String[l];
        
        for(int i = 1; i < table.length; i++){
        	table[i] = new String[l - (i - 1)];
        }
        
        for(int i = 1; i < table.length; i++){
        	for(int j = 0; j < table[i].length; j++){
            	table[i][j] = "";
            }
        }
        
        return table;
		
	}
	
	public String[][] algorithm(String[][] table, String w) {
		
		for(int i=0; i<table[0].length; i++) {
			table[0][i] = word(w, i);
		}
		
		for(int i=0; i<table[1].length; i++) {
			String[] combs = check(new String[] {table[0][i]});
			table[1][i] = toString(combs);
		}
		if(w.length() <= 1) {
			return table;
		}
		
		for(int i = 0; i < table[2].length; i++){
			String[] down = toArray(table[1][i]);
			String[] diagonal = toArray(table[1][i+1]);
			
			String[] combs = check(combinations(down, diagonal));
			
			table[2][i] = toString(combs);
		}
		if(w.length() <= 2){
			return table;
		}
		
		TreeSet<String> v = new TreeSet<String>();

        for(int i = 3; i < table.length; i++){
            for(int j = 0; j < table[i].length; j++){
                for(int c = 1; c < i; c++){
                    String[] down = table[c][j].split("\\s");
                    String[] diagonal = table[i-c][j+c].split("\\s");
                    String[] combs = combinations(down, diagonal);
                    String[] ValidCombs = check(combs);
                    
                    if(table[i][j].isEmpty()){
                    	
                    	table[i][j] = toString(ValidCombs);
                    	
                    }else{
                    	
                        String[] oldV = toArray(table[i][j]);
                        ArrayList<String> newV = new ArrayList<String>(Arrays.asList(oldV));
                        newV.addAll(Arrays.asList(ValidCombs));
                        v.addAll(newV);
                        table[i][j] = toString(v.toArray(new String[v.size()]));
                        
                    }
                    
                }
                
                v.clear();
            }
        }
        
        return table;
	}
	
	public String toString(String[] input) {
		return Arrays.toString(input).replaceAll("[\\[\\]\\,]", "");
	}
	
	public String[] combinations(String[] a, String[] b) {
		
		int l = a.length * b.length;
		int c = 0;
		String[] combs = new String[l];
		
		if(l == 0) {
			return combs;
		}
		
		for(int i=0; i<a.length; i++) {
			for(int j=0; j<b.length; j++) {
				combs[c] = a[i] + b[j];
				c++;
			}
		}
		
		return combs;
	}
	
	public String[] check(String[] check) {
		
		ArrayList<String> save = new ArrayList<>();
		
		for(String s : grammar.keySet()) {
			for(String c : check) {
				if(grammar.get(s).contains(c)) {
					save.add(s);
				}
			}
		}
		
		if(save.size() == 0) {
			return new String[] {};
		}
		
		return save.toArray(new String[save.size()]);
	}
	
	public String word(String w, int p) {
		//return toArray(w)[p];
		return Character.toString(w.charAt(p));
	}
	
	public String[] toArray(String input) {
		return input.split("\\s");
	}
	
}
