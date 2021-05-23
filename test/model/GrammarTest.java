package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GrammarTest {

	private Grammar g;
	
	private void setUp() {
		g = new Grammar("S", "a b", "S A B E C X Y Z", "C:\\Users\\Usuario\\Documents\\JavaProyects\\CYK\\prods.txt");
	}
	
	private void setUp2() {
		g = new Grammar("S", "a b", "S A B", "C:\\Users\\Usuario\\Documents\\JavaProyects\\CYK\\prods.txt");
	}
	
	/*@Test
	void CYK1() {
		
		setUp();
		assertEquals("La cadena abbbabaa es generada por la gramatica", g.CYK("abbbabaa"), "why");
		
	}*/
	
	@Test
	void CYK2() {
		
		setUp();
		assertEquals("La cadena aab es generada por la gramatica", g.CYK("aab"), "why");
		
	}
	
	/*@Test
	void prods() {
		
		setUp();
		g.productions("C:\\Users\\Usuario\\Documents\\JavaProyects\\CYK\\prods.txt");
		
	}*/

}
