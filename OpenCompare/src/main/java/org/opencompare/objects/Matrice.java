package org.opencompare.objects;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.Product;
import org.opencompare.api.java.Value;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;

public class Matrice {

	private String name;
	private List<Item> listItems;
	private PCM pcm;

	public Matrice(String name) {
		super();
		this.name = name;
		this.listItems = new ArrayList<Item>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void importPcmFile(String pathPcmFile) throws IOException {

		File pcmFile = new File(pathPcmFile);
		PCMLoader loader = new KMFJSONLoader();
		List<PCMContainer> pcmContainers = loader.load(pcmFile);
		for (PCMContainer pcmContainer : pcmContainers) {
			this.pcm = pcmContainer.getPcm();
		}
	}

	public int getNbrOfProperties() {
		int NbrOfProperties = 0;
		List<Feature> listFeatures = pcm.getConcreteFeatures();
		NbrOfProperties = listFeatures.size();

		return NbrOfProperties;
	}

	public List<String> getNameOfProperty() {
		List<String> listToReturn = new ArrayList<String>();
		List<Feature> listFeatures = pcm.getConcreteFeatures();

		for (Feature temp : listFeatures) {
			listToReturn.add(temp.getName());
		}

		return listToReturn;
	}

	public void setMatrice() {
		for (Product product : pcm.getProducts()) {
			Item i = new Item(product.getKeyContent());

			for (Feature feature : pcm.getConcreteFeatures()) {

				// Find the cell corresponding to the current feature and
				// product
				Cell cell = product.findCell(feature);

				// Get information contained in the cell
				String content = cell.getContent();

				Property p = new Property(feature.getName(), content);
				i.addFeatureToList(p);
			}
			this.addItemToList(i);
		}
	}

}
