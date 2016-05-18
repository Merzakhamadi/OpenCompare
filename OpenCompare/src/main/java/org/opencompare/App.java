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
		if (args.length == 2) {
			//Input File
			String filePath = args[0];
			File f = new File(filePath);
			
			if (f.exists() && f.canRead()) 
			{
				// MATRICE
				Matrice maMatrice = new Matrice();
				maMatrice.importFile(filePath);
				if(maMatrice.getNbrOfProperties() >=1)
				{
					maMatrice.setTitre(f.getName());
					maMatrice.setMatrice(getAxisProperties(maMatrice));
					maMatrice.writePropertiesAxisFile();

					//NVD
					if(args[1].equals("-nvd3")) {
						generateNvd(maMatrice);
					}
					//PLOTLY
					else if (args[1].equals("-plotly")) {
						generatePlotly(maMatrice);
					} 
					else {
						System.out.println("Ajouter le parametre -nvd3 ou -plotly");
					}
				}
				else{
					System.out.println("Aucune caractéristique comparable !");
				}
			}
			else { System.out.println("Le fichier indiqué n'existe pas ou n'est pas lisible !"); }
			
		}
		else {
			System.out.println("java -jar OpenCompare.jar inputfilepath -nvd3");
			System.out.println("java -jar OpenCompare.jar inputfilepath -plotly");
		}
	}

	
	public static void generatePlotly(Matrice maMatrice){
		//PLOTLY
		GraphPLOTLY graph = new GraphPLOTLY();
		graph.getGraph(maMatrice);
		graph.toJson("www/json/plotly.json");

		HtmlGenerator htmlGenerator = new HtmlGenerator(false, true);
		htmlGenerator.writeAll(); 
	}
	
	public static void generateNvd(Matrice maMatrice){
		//NVD
		RootNVD root = new RootNVD();
		root.createMap(maMatrice);
		root.toJson("www/json/nvd.json");
	
		HtmlGenerator htmlGenerator = new HtmlGenerator(true, false);
		htmlGenerator.writeAll();
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
		boolean valid = false;
		while (!valid)
			try {
				System.out.print("Saisir au clavier le nombre de paramètre : ");
				nbrProperties = Integer.parseInt(sc.nextLine());
				if (nbrProperties > 0 && nbrProperties <= 4) {
					valid = true;
				} else {
					System.out
							.println("Erreur : Paramètre d'entrée doit être compris entre 1 et 4 ");
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Erreur : Paramètre d'entrée non numérique");
			}
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
					.print("Saisir au clavier la caractéristique en Axe X : ");
			maMatrice.setPropertyAxisX(listProperties.get(sc.nextInt()));
			System.out
					.println("-------------------------------------------------");
			break;

		case 2:
			System.out
					.print("Saisir au clavier la caractéristique en Axe X : ");
			maMatrice.setPropertyAxisX(listProperties.get(sc.nextInt()));
			System.out
					.println("-------------------------------------------------");
			System.out
					.print("Saisir au clavier la caractéristique en Axe Y : ");
			maMatrice.setPropertyAxisY(listProperties.get(sc.nextInt()));
			System.out
					.println("-------------------------------------------------");
			break;

		case 3:
			System.out
					.print("Saisir au clavier la caractéristique en Axe X : ");
			maMatrice.setPropertyAxisX(listProperties.get(sc.nextInt()));
			System.out
					.println("-------------------------------------------------");
			System.out
					.print("Saisir au clavier la caractéristique en Axe Y : ");
			maMatrice.setPropertyAxisY(listProperties.get(sc.nextInt()));
			System.out
					.println("-------------------------------------------------");
			System.out.print("Saisir au clavier la caractéristique size : ");
			maMatrice.setPropertyAxisSize(listProperties.get(sc.nextInt()));
			System.out
					.println("-------------------------------------------------");
			break;

		case 4:
			System.out
					.print("Saisir au clavier la caractéristique en Axe X : ");
			maMatrice.setPropertyAxisX(listProperties.get(sc.nextInt()));
			System.out
					.println("-------------------------------------------------");
			System.out
					.print("Saisir au clavier la caractéristique en Axe Y : ");
			maMatrice.setPropertyAxisY(listProperties.get(sc.nextInt()));
			System.out
					.println("-------------------------------------------------");
			System.out.print("Saisir au clavier la caractéristique size : ");
			maMatrice.setPropertyAxisSize(listProperties.get(sc.nextInt()));
			System.out
					.println("-------------------------------------------------");
			System.out.print("Saisir au clavier la caractéristique color : ");
			maMatrice.setPropertyAxisColor(listProperties.get(sc.nextInt()));
			System.out
					.println("-------------------------------------------------");
			break;

		}
		return nbrNeededNbrProperties;
	}
}
