package org.opencompare.nvd;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.opencompare.objects.Item;
import org.opencompare.objects.Matrice;
import org.opencompare.objects.Property;

public class RootNVD {
	List<ObjectNVD> listObjectNVD;

	public RootNVD() {
		super();
		listObjectNVD = new ArrayList<ObjectNVD>();
	}

	public void createMap(Matrice matrice) {

		for (Item item : matrice.getListItems()) {
			String key = item.getName();
			String x = getXFromPropertyAsisX(item, matrice.getPropertyAxisX());
			String y = getYFromPropertyAsisY(item, matrice.getPropertyAxisY());
			String size = getSizeFromPropertyAsisSize(item,
					matrice.getPropertyAxisSize());
			String color = getColorFromPropertyAsisColor(item,
					matrice.getPropertyAxisColor());

			ObjectNVD object = new ObjectNVD(key, x, y, size, "circle", color);
			this.listObjectNVD.add(object);
		}

	}

	private String getColorFromPropertyAsisColor(Item item,
			String propertyAxisColor) {
		String c = null;
		for (Property property : item.getListProperties()) {
			if (property.getName().equals(propertyAxisColor)) {
				c = property.getValue();
			}
		}

		return c;
	}

	public List<ObjectNVD> getListObjectNVD() {
		return listObjectNVD;
	}

	public void setListObjectNVD(List<ObjectNVD> listObjectNVD) {
		this.listObjectNVD = listObjectNVD;
	}

	public void addObjecttoListObjectNVD(ObjectNVD objectToAdd) {
		this.listObjectNVD.add(objectToAdd);
	}

	public String getXFromPropertyAsisX(Item item, String propertyAxisX) {
		String x = null;
		for (Property property : item.getListProperties()) {
			if (property.getName().equals(propertyAxisX)) {
				x = property.getValue();
			}
		}

		return x;
	}

	public String getYFromPropertyAsisY(Item item, String propertyAxisY) {
		String y = null;
		for (Property property : item.getListProperties()) {
			if (property.getName().equals(propertyAxisY)) {
				y = property.getValue();
			}
		}

		return y;
	}

	public String getSizeFromPropertyAsisSize(Item item, String propertyAxisSize) {
		String size = null;
		for (Property property : item.getListProperties()) {
			if (property.getName().equals(propertyAxisSize)) {
				size = property.getValue();
			}
		}

		return size;
	}

	public void toJson(String path) {
		ObjectMapper mapper = new ObjectMapper();

		try {

			// Convert object to JSON string
			String jsonInString = mapper.writeValueAsString(this);

			// DELETE { } and "listObjectNVD : " for respect JSON Format
			jsonInString = jsonInString.substring(17, jsonInString.length() - 1);

			FileUtils.writeStringToFile(new File(path), jsonInString);

			// Test
			System.out.println(jsonInString);

			// Convert object to JSON string and pretty print
			// jsonInString = mapper.writerWithDefaultPrettyPrinter()
			// .writeValueAsString(this);
			//
			// System.out.println(jsonInString);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
