package org.opencompare;

import java.util.List;

import org.junit.After;
import org.junit.Test;

import org.opencompare.objects.Item;
import org.opencompare.objects.Property;

import junit.framework.TestCase;

public class testItem extends TestCase{
	
	private Item item;
	private List<Property> listProperties;
	
	public testItem(String name) {
		super(name);
	}
	
	
	protected void setUp() throws Exception {
		super.setUp();
		item = new Item("nom1");
		}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		item = null;
		}
	
	@Test
	public void testItem(){
		//assertNotNull("l'instance n'est pas nul", item);
	}

	@Test
	public void testSetName(){
		item.setName("nom2");
		assertEquals("le nom est incorrecte", "nom2", item.getName());
	}
	
	@Test
	public void testGetName(){
		assertEquals("le nom est incorrecte", "nom1", item.getName());
	}
	
	@Test
	public void testSetLisProperties(){
		//item.setListProperty(listProperties);
		//assertNull(listProperties);
		Property tot = new Property("nom1", "10");
		item.addPropertyToList(tot);
		assertNotNull(item.getListProperties());
		assertEquals("la taille de la liste n'est pas égale à 1", 1, item.getListProperties().size());
	}
	
	@Test
	public void testGetListProperties(){
		Property toto = new Property("nini", "1");
		Property tata = new Property("nana", "10");
		item.addPropertyToList(tata);
		item.addPropertyToList(toto);
		assertEquals("la taille de la liste n'est pas égale à 1", 2, item.getListProperties().size());
	}
	
	@Test
	public void testAdd(){
		//listProperties.add(0, element);
	}
	
	@After
	@Test
	public void testClearList(){
		if (item.getListProperties() != null){
			item.getListProperties().clear();
		}
		else{
			System.out.println("La liste est vide");
		}
	}

}
