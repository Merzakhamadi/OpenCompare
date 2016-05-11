package org.opencompare.objects;

import java.util.List;

public class Matrice {

	private String name;
	private List<Product> listProducts;

	public Matrice() {
		super();
	}

	public Matrice(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getListProducts() {
		return listProducts;
	}

	public void setListProducts(List<Product> listProducts) {
		this.listProducts = listProducts;
	}

	public void addProductToList(Product productToAdd) {
		this.listProducts.add(productToAdd);
	}

}
