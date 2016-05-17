package org.opencompare;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.opencompare.nvd.RootNVD;
import org.opencompare.objects.Matrice;
import org.opencompare.plotly.GraphPLOTLY;

public class App {

	public static void main(String[] args) throws IOException {

		System.out.println("-----------------OPENCOMPARE-------------------");
		if (args.length == 1) {
			File f = new File(args[0]);
			if (f.exists() && f.canRead()) {
				// MATRICE
				Matrice maMatrice = new Matrice();
				maMatrice.importFile(args[0]);
				maMatrice.setTitre(f.getName());
				maMatrice.setMatrice(getAxisProperties(maMatrice));
				maMatrice.writePropertiesAxisFile();

				// NVD
				RootNVD root = new RootNVD();
				root.createMap(maMatrice);
				root.toJson("www/json/nvd.json");

				// PLOTLY
				GraphPLOTLY graph = new GraphPLOTLY();
				graph.getGraph(maMatrice);
				graph.toJson("www/json/plotly.json");

			} else {
				System.out.println("input file not exit or not readable");
			}
		} else {

			System.out.println("java -jar OpenCompare.jar inputfilepath");
		}

	}

	public static int getNeededNbrProperties(Matrice maMatrice) {

		int nbrProperties = 0;
		List<String> listProperties = new ArrayList<String>();
		listProperties = maMatrice.getListPropertiesIntegerValue();

		System.out.println("Caractéristiques :");

		int count = 0;
		for (String property : listProperties) {
			System.out.println(count + " : " + property);
			count++;
		}

		System.out.println();
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisir au clavier le nombre de paramètre :");
		nbrProperties = sc.nextInt();
		System.out.println();

		return nbrProperties;
	}

	public static int getAxisProperties(Matrice maMatrice) {
		List<String> listProperties = new ArrayList<String>();
		listProperties = maMatrice.getListPropertiesIntegerValue();
		Scanner sc = new Scanner(System.in);

		int nbrNeededNbrProperties = getNeededNbrProperties(maMatrice);
		switch (nbrNeededNbrProperties) {
		case 1:
			System.out
					.println("Saisir au clavier la caractéristique en Axe X : ");
			maMatrice.setPropertyAxisX(listProperties.get(sc.nextInt()));
			System.out
					.println("-------------------------------------------------");
			;
			break;

		case 2:
			System.out
					.println("Saisir au clavier la caractéristique en Axe X : ");
			maMatrice.setPropertyAxisX(listProperties.get(sc.nextInt()));
			System.out
					.println("-------------------------------------------------");
			System.out
					.println("Saisir au clavier la caractéristique en Axe Y : ");
			maMatrice.setPropertyAxisY(listProperties.get(sc.nextInt()));
			System.out
					.println("-------------------------------------------------");
			break;

		case 3:
			System.out
					.println("Saisir au clavier la caractéristique en Axe X : ");
			maMatrice.setPropertyAxisX(listProperties.get(sc.nextInt()));
			System.out
					.println("-------------------------------------------------");
			System.out
					.println("Saisir au clavier la caractéristique en Axe Y : ");
			maMatrice.setPropertyAxisY(listProperties.get(sc.nextInt()));
			System.out
					.println("-------------------------------------------------");
			System.out.println("Saisir au clavier la caractéristique size : ");
			maMatrice.setPropertyAxisSize(listProperties.get(sc.nextInt()));
			System.out
					.println("-------------------------------------------------");

			break;

		case 4:
			System.out
					.println("Saisir au clavier la caractéristique en Axe X : ");
			maMatrice.setPropertyAxisX(listProperties.get(sc.nextInt()));
			System.out
					.println("-------------------------------------------------");
			System.out
					.println("Saisir au clavier la caractéristique en Axe Y : ");
			maMatrice.setPropertyAxisY(listProperties.get(sc.nextInt()));
			System.out
					.println("-------------------------------------------------");
			System.out.println("Saisir au clavier la caractéristique size: ");
			maMatrice.setPropertyAxisSize(listProperties.get(sc.nextInt()));
			System.out
					.println("-------------------------------------------------");
			System.out.println("Saisir au clavier la caractéristique color : ");
			maMatrice.setPropertyAxisColor(listProperties.get(sc.nextInt()));
			System.out
					.println("-------------------------------------------------");
			break;

		}
		return nbrNeededNbrProperties;
	}
}
