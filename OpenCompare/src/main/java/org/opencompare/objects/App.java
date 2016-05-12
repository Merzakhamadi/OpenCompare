package org.opencompare.objects;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class App {

	public static void main(String[] args) throws IOException {
		Matrice maMatrice = new Matrice("MatriceTest");
		
		maMatrice.importPcmFile("pcms/example.pcm");
//		System.out.println(maMatrice.getNbrOfProperties());
//		System.out.println(maMatrice.getNameOfProperty());
		maMatrice.setMatrice();
//		System.out.println(maMatrice.getListItems().size());
//		for(Item i : maMatrice.getListItems())
//		{
//			System.out.println("-----------------" + i.getName() + "-------------------");
//			for(Property p : i.getListFeatures())
//			{
//				System.out.println(p.getName() + " = " + p.getValue());
//			}
//			
//		}
		
		maMatrice.toJson("json/matrice.json");

	}
	

}
