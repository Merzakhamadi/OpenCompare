package org.opencompare;

import java.util.ArrayList;

import org.junit.Test;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.objects.Item;
import org.opencompare.objects.Matrice;
import org.opencompare.objects.Property;

import junit.framework.TestCase;

public class testMatrice extends TestCase{
	
//	private Matrice matrice;
//	
//	public testMatrice(String m){
//		super(m);
//	}
//	
//	protected void setUp() throws Exception {
//		super.setUp();
//		matrice = new Matrice();
//		}
//	
//	protected void tearDown() throws Exception {
//		super.tearDown();
//		matrice = null;
//		}
//
//	@Test
//	public void testGetTitre(){
//		System.out.println("Test get titre");
//		matrice.setName("nom1");
//		assertEquals("La récupération du titre n'a pas été faite", "nom1", matrice.getTitre());
//		System.out.println(matrice.getTitre());
//	}
//	
//	@Test
//	public void testSetName(){
//		System.out.println("Test set name");
//		matrice.setName("toto");
//		assertEquals("l'écriture du titre n'est pas bonne", "toto", matrice.getTitre());
//		System.out.println(matrice.getTitre());
//	}
//	
//	@Test
//	public void testGetItems(){
//		System.out.println("Test get item");
//		Item item = new Item("a");
//		Item item1 = new Item("b");
//		matrice.addItemToList(item);
//		matrice.addItemToList(item1);
//		assertEquals("la liste est incomplete", 2, matrice.getListItems().size());
//		System.out.println(matrice.getListItems());
//	}
///*	
//	// A retester
//	@Test
//	public void testSetListItems(){
//		System.out.println("Test set list item");
//		Item item = new Item("aaa");
//		Item item1 = new Item("bbb");
//		ArrayList<Item> list = new ArrayList<Item>();
//		list.set(0, item);
//		list.set(1, item1);
//		assertEquals("la liste ne contient pas deux items", 1, list.get(0));
//	}
//*/
//	@Test
//	public void testAddItemToList(){
//		System.out.println("Test add item to list");
//		Item item = new Item("aaa");
//		Item item1 = new Item("bbb");
//		ArrayList<Item> list = new ArrayList<Item>();
//		list.add(item);
//		list.add(item1);
//		assertEquals("la liste ne contient pas deux items", 2, list.size());
//		System.out.println(list.size());
//	}
//	
//	@Test
//	public void testGetPropoertyAxisX(){
//		matrice.setPropertyAxisX("1234");
//		matrice.getPropertyAxisX();
//		assertEquals("l'écriture de l'axe des x n'est pas bon", "1234", matrice.getPropertyAxisX());
//		System.out.println(matrice.getPropertyAxisX());
//	}
//	
//	@Test
//	public void testGetPropertyAxisY(){
//		matrice.setPropertyAxisY("123");
//		assertEquals("l'écriture de l'axe des y n'est pas bon", "123", matrice.getPropertyAxisY());
//		System.out.println(matrice.getPropertyAxisY());
//	}
//	
//	@Test
//	public void testGetPropertyAxisColor() {
//		matrice.setPropertyAxisColor("bleu");
//		assertEquals("la couleur sélectionner est fausse", "bleu", matrice.getPropertyAxisColor());
//		System.out.println(matrice.getPropertyAxisColor());
//	}
//	
//	@Test
//	public void testGetPropertyAxisSize(){
//		matrice.setPropertyAxisSize("12");
//		assertEquals("la taille sélectionnée n'est pas bonne", "12", matrice.getPropertyAxisSize());
//		System.out.println(matrice.getPropertyAxisSize());
//	}
//	
//	@Test
//	public void testGetNbrOfProperties(){
//		
//	}
}
