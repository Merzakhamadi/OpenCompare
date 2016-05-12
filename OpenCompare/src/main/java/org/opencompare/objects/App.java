package org.opencompare.objects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws IOException {
		Matrice maMatrice = new Matrice("MatriceTest");
		maMatrice.importPcmFile("pcms/example.pcm");
		
		List<String> listProperties = new ArrayList<String>();
		listProperties = maMatrice.getListPropertiesIntegerValue();
				
		System.out.println("-----------------OPENCOMPARE-------------------");
		System.out.println("Caractéristiques :");
		int count = 0;
		for(String property : listProperties)
		{
			System.out.println(count + " : " + property);
			count++ ;
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisir au clavier la caractéristique en Axe X : ");
		maMatrice.setPropertyAxisX(listProperties.get(sc.nextInt()));
		System.out.println("-------------------------------------------------");
		System.out.println("Saisir au clavier la caractéristique en Axe Y : ");
		maMatrice.setPropertyAxisY(listProperties.get(sc.nextInt()));
		System.out.println("-------------------------------------------------");
		System.out.println("Saisir au clavier la caractéristique en Axe Color : ");
		maMatrice.setPropertyAxisColor(listProperties.get(sc.nextInt()));
		System.out.println("-------------------------------------------------");
		System.out.println("Saisir au clavier la caractéristique en Axe Taille : ");
		maMatrice.setPropertyAxisSize(listProperties.get(sc.nextInt()));
		System.out.println("-------------------------------------------------");
		
		
		maMatrice.setMatrice();
		maMatrice.toJson("json/matrice.json");
	}
	

}
