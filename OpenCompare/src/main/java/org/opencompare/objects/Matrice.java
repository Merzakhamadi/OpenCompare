package org.opencompare.objects;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.Product;
import org.opencompare.api.java.extractor.CellContentInterpreter;
import org.opencompare.api.java.impl.PCMFactoryImpl;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.CSVLoader;
import org.opencompare.api.java.io.PCMLoader;

public class Matrice {
	private List<Item> listItems;
	private PCM pcm;

	private String propertyAxisX;
	private String propertyAxisY;
	private String propertyAxisSize;
	private String propertyAxisColor;

	private String titre;

	public Matrice() {
		super();
		this.listItems = new ArrayList<Item>();
	}

	public List<Item> getListItems() {
		return listItems;
	}

	public void setListItems(List<Item> listItems) {
		this.listItems = listItems;
	}

	public void addItemToList(Item itemToAdd) {
		this.listItems.add(itemToAdd);
	}

	public String getPropertyAxisX() {
		return propertyAxisX;
	}

	public void setPropertyAxisX(String propertyAxisX) {
		this.propertyAxisX = propertyAxisX;
	}

	public String getPropertyAxisY() {
		return propertyAxisY;
	}

	public void setPropertyAxisY(String propertyAxisY) {
		this.propertyAxisY = propertyAxisY;
	}

	public String getPropertyAxisSize() {
		return propertyAxisSize;
	}

	public void setPropertyAxisSize(String propertyAxisSize) {
		this.propertyAxisSize = propertyAxisSize;
	}

	public String getPropertyAxisColor() {
		return propertyAxisColor;
	}

	public void setPropertyAxisColor(String propertyAxisColor) {
		this.propertyAxisColor = propertyAxisColor;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void importPcmFile(String pathPcmFile) throws IOException {

		File pcmFile = new File(pathPcmFile);
		PCMLoader loader = new KMFJSONLoader();
		List<PCMContainer> pcmContainers = loader.load(pcmFile);
		for (PCMContainer pcmContainer : pcmContainers) {
			this.pcm = pcmContainer.getPcm();
		}
	}

	public void importCsvFileToPcm(String pathCsvFile) throws IOException {
		CSVLoader csvL = new CSVLoader(new PCMFactoryImpl(),
				new CellContentInterpreter(new PCMFactoryImpl()));
		List<PCMContainer> pcms = csvL.load(new File(pathCsvFile));
		for (PCMContainer pcmContainer : pcms) {
			this.pcm = pcmContainer.getPcm();
		}

	}

	public void importFile(String pathFile) throws IOException {
		String ext = FilenameUtils.getExtension(pathFile);

		if (ext.equals("pcm")) {
			this.importPcmFile(pathFile);
		} else if (ext.equals("csv")) {
			this.importCsvFileToPcm(pathFile);
		}
	}

	public int getNbrOfProperties() {
		int NbrOfProperties = 0;
		List<Feature> listFeatures = pcm.getConcreteFeatures();
		NbrOfProperties = listFeatures.size();

		return NbrOfProperties;
	}

	public List<String> getListPropertiesIntegerValue() {
		List<String> listProperties = new ArrayList<String>();
		Product product = pcm.getProducts().get(0);
		for (Feature feature : pcm.getConcreteFeatures()) {
			Cell cell = product.findCell(feature);
			String content = cell.getContent();
			if (isNumeric(content)) {
				listProperties.add(feature.getName());
			}
		}
		Collections.sort(listProperties);
		return listProperties;
	}

	public void setMatrice(int nbrProperty) {
		for (Product product : pcm.getProducts()) {
			Item i = new Item(product.getKeyContent());

			for (Feature feature : pcm.getConcreteFeatures()) {

				// Find the cell corresponding to the current feature and
				// product
				Cell cell = product.findCell(feature);

				// Get information contained in the cell
				String content = cell.getContent();

				switch (nbrProperty) {

				case 1:
					if (propertyAxisX == feature.getName()) {
						Property p = new Property(feature.getName(), content);
						i.addPropertyToList(p);
					}
					break;

				case 2:
					if (propertyAxisX == feature.getName()
							|| propertyAxisY == feature.getName()) {
						Property p = new Property(feature.getName(), content);
						i.addPropertyToList(p);
					}
					break;

				case 3:
					if (propertyAxisX == feature.getName()
							|| propertyAxisY == feature.getName()
							|| propertyAxisSize == feature.getName()) {
						Property p = new Property(feature.getName(), content);
						i.addPropertyToList(p);
					}
					break;

				case 4:
					if (propertyAxisX == feature.getName()
							|| propertyAxisY == feature.getName()
							|| propertyAxisSize == feature.getName()
							|| propertyAxisColor == feature.getName()) {
						Property p = new Property(feature.getName(), content);
						i.addPropertyToList(p);
					}
					break;
				}
			}
			this.addItemToList(i);
		}
	}

	public static boolean isNumeric(String str) {
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c))
				return false;
		}
		return true;
	}

	public void writePropertiesAxisFile() throws IOException {
		String json = "{\"titre\":\"" + titre + "\",\"labelx\":\""
				+ propertyAxisX + "\",\"labely\":\"" + propertyAxisY
				+ "\",\"labelsize\":\"" + propertyAxisSize
				+ "\",\"labelcolor\":\"" + propertyAxisColor + "\"}";

		FileUtils.writeStringToFile(new File("www/json/param.json"), json);
	}

}
