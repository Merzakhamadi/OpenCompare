package org.opencompare;

import org.junit.Test;
import org.opencompare.objects.Item;
import org.opencompare.objects.Property;

import junit.framework.TestCase;

public class testProperty extends TestCase{
	
	private Property prop;
	
	public testProperty(String p) {
		super(p);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		prop = new Property("nom", "value");
		}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		prop = null;
		}
	
	@Test
	public void testProperty(){
		assertNotNull("l'instance n'est pas nul", prop);
	}
	
	@Test
	public void testGetName(){
		prop.setName("n");
		assertEquals("Erreur dans la fonction get name de la classe property", "n", prop.getName());
	}
	
	@Test
	public void testSetName(){
		prop.setName("nom1");
		assertEquals("la fonction set name ne fonctionne pas", "nom1", prop.getName());
	}
	
	@Test
	public void testGetValue(){
		prop.setValue("100");
		assertEquals("Erreur dans la fonction get value de la classe property", "100", prop.getValue());
	}
	
	@Test
	public void testSetValue(){
		prop.setValue("10");
		assertEquals("la fonction set name ne fonctionne pas", "10", prop.getValue());
	}
	
/*


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
 */
}
