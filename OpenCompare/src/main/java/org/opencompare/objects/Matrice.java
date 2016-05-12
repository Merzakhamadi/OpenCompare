package org.opencompare.objects;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonView;
import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.Product;
import org.opencompare.api.java.Value;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;

public class Matrice {

	private String titre;
	private List<Item> listItems;
	private PCM pcm;

	private String propertyAxisX;
	private String propertyAxisY;
	private String propertyAxisColor;
	private String propertyAxisSize;

	public Matrice(String titre) {
		super();
		this.titre = titre;
		this.listItems = new ArrayList<Item>();
	}

	public String getTitre() {
		return titre;
	}

	public void setName(String titre) {
		this.titre = titre;
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

	public String getPropertyAxisColor() {
		return propertyAxisColor;
	}

	public void setPropertyAxisColor(String propertyAxisColor) {
		this.propertyAxisColor = propertyAxisColor;
	}

	public String getPropertyAxisSize() {
		return propertyAxisSize;
	}

	public void setPropertyAxisSize(String propertyAxisSize) {
		this.propertyAxisSize = propertyAxisSize;
	}

	public void importPcmFile(String pathPcmFile) throws IOException {

		File pcmFile = new File(pathPcmFile);
		PCMLoader loader = new KMFJSONLoader();
		List<PCMContainer> pcmContainers = loader.load(pcmFile);
		for (PCMContainer pcmContainer : pcmContainers) {
			this.pcm = pcmContainer.getPcm();
		}
	}

	@JsonIgnore
	public int getNbrOfProperties() {
		int NbrOfProperties = 0;
		List<Feature> listFeatures = pcm.getConcreteFeatures();
		NbrOfProperties = listFeatures.size();

		return NbrOfProperties;
	}

	@JsonIgnore
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

	public void setMatrice() {
		for (Product product : pcm.getProducts()) {
			Item i = new Item(product.getKeyContent());

			for (Feature feature : pcm.getConcreteFeatures()) {

				// Find the cell corresponding to the current feature and
				// product
				Cell cell = product.findCell(feature);

				// Get information contained in the cell
				String content = cell.getContent();

				if(propertyAxisX == feature.getName() 
					|| propertyAxisY == feature.getName() 
					|| propertyAxisColor == feature.getName() 
					|| propertyAxisSize == feature.getName()){	
					Property p = new Property(feature.getName(), content);
					i.addPropertyToList(p);
				}
			}
			this.addItemToList(i);
		}
	}

	public void toJson(String path) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			// Convert object to JSON string and save into file directly
			mapper.writeValue(new File(path), this);

			// Convert object to JSON string
			String jsonInString = mapper.writeValueAsString(this);
			// System.out.println(jsonInString);

			// Convert object to JSON string and pretty print
			jsonInString = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(this);
			System.out.println(jsonInString);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isNumeric(String str) {
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c))
				return false;
		}
		return true;
	}

}