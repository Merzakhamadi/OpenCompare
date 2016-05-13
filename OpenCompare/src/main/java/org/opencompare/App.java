package org.opencompare;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.opencompare.nvd.RootNVD;
import org.opencompare.objects.Matrice;

public class App {

	public static void main(String[] args) throws IOException {

		Matrice maMatrice = new Matrice();
		if (args.length == 1) {
			File f = new File(args[0]);
			if (f.exists() && f.canRead()) {
				maMatrice.importFile(args[0]);
				defineAxisProperties(maMatrice);
				maMatrice.setMatrice();
				
				RootNVD root = new RootNVD();
				root.createMap(maMatrice);
				root.toJson("www/json/matrice.json");
			}
			else
			{
				System.out.println("input file not exit or not readable");
			}
		}
		else{
			
			System.out.println("java -jar OpenCompare.jar inputfilepath");
		}

	}

	public static void defineAxisProperties(Matrice maMatrice) {
		List<String> listProperties = new ArrayList<String>();
		listProperties = maMatrice.getListPropertiesIntegerValue();

		System.out.println("-----------------OPENCOMPARE-------------------");
		System.out.println("Caractéristiques :");
		int count = 0;
		for (String property : listProperties) {
			System.out.println(count + " : " + property);
			count++;
		}

		System.out.println();
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisir au clavier la caractéristique en Axe X : ");
		maMatrice.setPropertyAxisX(listProperties.get(sc.nextInt()));
		System.out.println("-------------------------------------------------");
		System.out.println("Saisir au clavier la caractéristique en Axe Y : ");
		maMatrice.setPropertyAxisY(listProperties.get(sc.nextInt()));
		System.out.println("-------------------------------------------------");
		System.out
				.println("Saisir au clavier la caractéristique en Axe Taille : ");
		maMatrice.setPropertyAxisSize(listProperties.get(sc.nextInt()));
		System.out.println("-------------------------------------------------");
	}
}
