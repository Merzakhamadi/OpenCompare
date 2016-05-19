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
	@Test
	public void testStructureJsonNvd(){
		String str = "{ \"key\": \"D5500\",}";
		String str1 = "{ \"values\": [{ \"x\": \"26600\",\"y\": \"25600\",\"size\": \"1000\",\"shape\": \"circle\" }] }";

		JSONObject obj = new JSONObject(str);
		String n = obj.getString("key");
		assertEquals("D5500", n);
		//System.err.println(n);
		
		JSONObject obj1 = new JSONObject(str1);
		JSONArray n1 = obj1.getJSONArray("values");
		//System.out.println(n1);
		JSONObject vls = n1.getJSONObject(0);		
		//System.out.println(vls);
		String x = vls.getString("x");
		//System.err.println("x=" + x);
		//assertNotNull(vls);
		//assertNotNull(x);
		assertEquals("26600", x);
		String y = vls.getString("y");
		//System.err.println("y= " + y);
		assertEquals("25600", y);
		String size = vls.getString("size");
		//System.err.println("size= " + size);
		assertEquals("1000", size);
		String shape = vls.getString("shape");
		//System.err.println(shape);
		assertEquals("circle", shape);
		
		int ix = vls.getInt("x");
		assertEquals(26600, ix);
	}
	
	@Test
	public void testStructureJsonPlot(){
		String str = "{\"text\": [\"toto\", \"tata\",\"titi\",\"tutu\"],\"y\":[\"10\",\"11\",\"12\",\"13\"],\"x\":[\"1\",\"2\",\"3\",\"4\"],\"mode\":\"markers\",\"marker\":{\"color\":[1,4,9,20],\"opacity\": [1,0.8,0.6,0.4],\"size\":[40,60,80,100]}}";
		JSONObject obj = new JSONObject(str);
		JSONArray arr = obj.getJSONArray("text");
		//System.err.println(arr);
		for (int i = 0; i < arr.length(); i++){
			if (i==0){
				assertEquals("toto", arr.getString(i));
				//System.out.println(arr.getString(i));
			}
			if (i==1){
				assertEquals("tata", arr.getString(i));
				//System.out.println(arr.getString(i));
			}
			if (i==2){
				assertEquals("titi", arr.getString(i));
				//System.out.println(arr.getString(i));
			}
			if (i==3){
				assertEquals("tutu", arr.getString(i));
				//System.out.println(arr.getString(i));
			}
			else{
			}
		}
		
		JSONArray arr1 = obj.getJSONArray("y");
		//System.err.println(arr1);
		for (int i = 0; i < arr.length(); i++){
			if (i==0){
				assertEquals("10", arr1.getString(i));
				//System.out.println(arr.getString(i));
			}
			if (i==1){
				assertEquals("11", arr1.getString(i));
				//System.out.println(arr.getString(i));
			}
			if (i==2){
				assertEquals("12", arr1.getString(i));
				//System.out.println(arr.getString(i));
			}
			if (i==3){
				assertEquals("13", arr1.getString(i));
				//System.out.println(arr.getString(i));
			}
			else{
			}
		}
		
		JSONArray arr2 = obj.getJSONArray("x");
		//System.err.println(arr2);
		for (int i = 0; i < arr.length(); i++){
			if (i==0){
				assertEquals("1", arr2.getString(i));
				//System.out.println(arr.getString(i));
			}
			if (i==1){
				assertEquals("2", arr2.getString(i));
				//System.out.println(arr.getString(i));
			}
			if (i==2){
				assertEquals("3", arr2.getString(i));
				//System.out.println(arr.getInt(i));
			}
			if (i==3){
				assertEquals("4", arr2.getString(i));
				//System.out.println(arr.getString(i));
			}
			else{
			}
		}
		
		String n = obj.getString("mode");
		//System.err.println(n);
		assertEquals("markers", n);
		
		JSONObject nMarker = obj.getJSONObject("marker");
		//System.err.println(nMarker);
		
		JSONArray nColor = (JSONArray) nMarker.get("color");
		//System.err.println(n2);
		for (int i = 0; i<n.length(); i++){
			if (i == 0){
				assertEquals(1, nColor.getInt(i));
				//System.err.println(n2.get(i));
			}
			if (i == 1){
				assertEquals(4, nColor.getInt(i));
				//System.err.println(n2.get(i));
			}
			if (i == 2){
				assertEquals(9, nColor.getInt(i));
				//System.err.println(n2.get(i));
			}
			if (i == 3){
				assertEquals(20, nColor.getInt(i));
				//System.err.println(n2.get(i));
			}
		}
		
		JSONArray nSize = (JSONArray) nMarker.get("size");
		//System.err.println(n3);
		for (int i = 0; i<n.length(); i++){
			if (i == 0){
				assertEquals(40, nSize.getInt(i));
				//System.err.println(nSize.get(i));
			}
			if (i == 1){
				assertEquals(60, nSize.getInt(i));
				//System.err.println(nSize.get(i));
			}
			if (i == 2){
				assertEquals(80, nSize.getInt(i));
				//System.err.println(nSize.get(i));
			}
			if (i == 3){
				assertEquals(100, nSize.getInt(i));
				//System.err.println(nSize.get(i));
			}
		}
		
		JSONArray nOpa = (JSONArray) nMarker.get("opacity");
		//System.err.println(n3);
		for (int i = 0; i<n.length(); i++){
			if (i == 0){
				assertEquals(1, nOpa.getInt(i));
				//System.err.println(nOpa.get(i));
			}
			if (i == 1){
				assertEquals(0.8, nOpa.getDouble(i));
				//System.err.println(nOpa.get(i));
			}
			if (i == 2){
				assertEquals(0.6, nOpa.getDouble(i));
				//System.err.println(nOpa.get(i));
			}
			if (i == 3){
				assertEquals(0.4, nOpa.getDouble(i));
				//System.err.println(nOpa.get(i));
			}
		}
		
		//n1.getJSONObject("color");
		//JSONObject vls = a.get		
		//String x = vls.getString("x");
		
	}

}
