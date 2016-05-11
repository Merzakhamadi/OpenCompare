package org.opencompare.objects;

import java.util.ArrayList;
import java.util.List;

public class Item {

	private String name;
	private List<Property> listProperties;

	public Item() {
		super();
	}

	public Item(String name) {
		super();
		this.name = name;
		this.listProperties = new ArrayList<Property>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Property> getListFeatures() {
		return listProperties;
	}

	public void setListProperty(List<Property> listProperties) {
		this.listProperties = listProperties;
	}

	public void addFeatureToList(Property propertyToAdd) {
		this.listProperties.add(propertyToAdd);
	}

}
