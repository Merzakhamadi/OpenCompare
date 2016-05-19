package org.opencompare;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonParser;
import org.junit.Test;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;
import org.opencompare.nvd.RootNVD;
import org.opencompare.objects.Item;
import org.opencompare.objects.Matrice;
import org.opencompare.objects.Property;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.AsExternalTypeDeserializer;

import org.json.*;

import junit.framework.TestCase;

public class testMatrice extends TestCase{
	
	private Matrice matrice;
	
	public testMatrice(String m){
		super(m);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		matrice = new Matrice();
		}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		matrice = null;
		}
	
	@Test
	public void testGetItems(){
		Item item = new Item("a");
		Item item1 = new Item("b");
		matrice.addItemToList(item);
		matrice.addItemToList(item1);
		assertEquals("la liste est incomplete", 2, matrice.getListItems().size());
	}
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
	@Test
	public void testAddItemToList(){
		Item item = new Item("aaa");
		Item item1 = new Item("bbb");
		ArrayList<Item> list = new ArrayList<Item>();
		list.add(item);
		list.add(item1);
		assertEquals("la liste ne contient pas deux items", 2, list.size());
	}
	
	@Test
	public void testGetPropoertyAxisX(){
		matrice.setPropertyAxisX("1234");
		matrice.getPropertyAxisX();
		assertEquals("l'écriture de l'axe des x n'est pas bon", "1234", matrice.getPropertyAxisX());
	}
	
	@Test
	public void testGetPropertyAxisY(){
		matrice.setPropertyAxisY("123");
		assertEquals("l'écriture de l'axe des y n'est pas bon", "123", matrice.getPropertyAxisY());
	}
	

	
	@Test
	public void testGetPropertyAxisSize(){
		matrice.setPropertyAxisSize("12");
		assertEquals("la taille sélectionnée n'est pas bonne", "12", matrice.getPropertyAxisSize());
	}
/* A réécrire	
	@Test
	public void testImportPcmFile(){
		String path = "p";
		File filetest = new File(path);
		PCMLoader loader = new KMFJSONLoader();
		//List<PCMContainer> pcmC = loader.load(filetest);
	}
*/

}
