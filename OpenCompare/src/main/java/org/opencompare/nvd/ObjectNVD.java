package org.opencompare.nvd;

import java.util.ArrayList;
import java.util.List;

public class ObjectNVD {

	private String key;
	private List<Values> values;

	public ObjectNVD(String key, String x, String y, String size, String shape, String color) {
		super();
		this.key = key;
		this.values = new ArrayList<Values>();
		this.values.add(new Values(x, y, size, shape, color));
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<Values> getValues() {
		return values;
	}

	public static class Values {
		private String x;
		private String y;
		private String size;
		private String shape = "circle";
		private String color;

		public Values(String x, String y, String size, String shape, String color) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
			this.shape = shape;
			this.color = color;
		}

		
		public String getX() {
			return x;
		}

		public void setX(String x) {
			this.x = x;
		}

		public String getY() {
			return y;
		}

		public void setY(String y) {
			this.y = y;
		}

		public String getSize() {
			return size;
		}

		public void setSize(String size) {
			this.size = size;
		}

		public String getShape() {
			return shape;
		}

		public void setShape(String shape) {
			this.shape = shape;
		}


		public String getColor() {
			return color;
		}


		public void setColor(String color) {
			this.color = color;
		}
		
		
	}

}
