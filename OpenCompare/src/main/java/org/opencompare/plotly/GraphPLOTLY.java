package org.opencompare.plotly;

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

public class GraphPLOTLY {

	private List<String> text;
	private List<Object> x;
	private List<Object> y;
	private String mode;
	private Marker marker;

	public GraphPLOTLY() {
		super();
		this.marker = new Marker();
		this.text = new ArrayList<String>();
		this.x = new ArrayList<Object>();
		this.y = new ArrayList<Object>();
		this.mode = "markers";
	}

	public List<String> getText() {
		return text;
	}

	public void setText(List<String> text) {
		this.text = text;
	}

	public List<Object> getX() {
		return x;
	}

	public void setX(List<Object> x) {
		this.x = x;
	}

	public List<Object> getY() {
		return y;
	}

	public void setY(List<Object> y) {
		this.y = y;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public void getGraph(Matrice matrice) {

		for (Item item : matrice.getListItems()) {
			String key = item.getName();
			String x = getXFromPropertyAsisX(item, matrice.getPropertyAxisX());
			String y = getYFromPropertyAsisY(item, matrice.getPropertyAxisY());
			String size = getSizeFromPropertyAsisSize(item,
					matrice.getPropertyAxisSize());
			String color = getColorFromPropertyAsisColor(item,
					matrice.getPropertyAxisColor());

			this.text.add(key);
			this.x.add(x);
			this.y.add(y);

			this.marker.getcolor().add(color);
			this.marker.getsize().add(size);

		}
	}

	private String getColorFromPropertyAsisColor(Item item,
			String propertyAxisColor) {
		String color = null;
		for (Property property : item.getListProperties()) {
			if (property.getName().equals(propertyAxisColor)) {
				color = property.getValue();
			}
		}

		return color;
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
			String jsonInString = "[";
			jsonInString += mapper.writeValueAsString(this);
			jsonInString += "]";

			FileUtils.writeStringToFile(new File(path), jsonInString);

			// Test
			System.out.println(jsonInString);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static class Marker {
		private List<Object> size;
		private List<Object> color;

		public Marker() {
			super();
			this.color = new ArrayList<Object>();
			this.size = new ArrayList<Object>();
		}

		public List<Object> getcolor() {
			return color;
		}

		public void setcolor(List<Object> color) {
			this.color = color;
		}

		public List<Object> getsize() {
			return size;
		}

		public void setsize(List<Object> size) {
			this.size = size;
		}

	}

}
