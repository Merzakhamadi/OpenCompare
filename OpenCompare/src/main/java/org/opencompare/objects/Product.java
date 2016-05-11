package org.opencompare.objects;

import java.util.List;

public class Product {

	private String name;
	private List<Feature> listFeatures;

	public Product() {
		super();
	}

	public Product(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Feature> getListFeatures() {
		return listFeatures;
	}

	public void setListFeatures(List<Feature> listFeatures) {
		this.listFeatures = listFeatures;
	}

	public void addFeatureToList(Feature featureToAdd) {
		this.listFeatures.add(featureToAdd);
	}

}
