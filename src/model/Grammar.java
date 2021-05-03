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
			prods.addAll(Arrays.<String>asList(sc.nextLine().split("\\s")));
            String left = prods.get(0);
            prods.remove(0);
            grammar.put(left, new ArrayList<String>());
            grammar.get(left).addAll(prods);
            prods.clear();
		}
		
	}
	
}
