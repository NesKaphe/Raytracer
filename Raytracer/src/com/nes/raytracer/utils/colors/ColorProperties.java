package com.nes.raytracer.utils.colors;

import java.awt.Color;

public class ColorProperties {
	
	private static final double MIN_RANGE = 0;
	private static final double MAX_RANGE = 1;
	
	
	private final double redProperty;
	private final double greenProperty;
	private final double blueProperty;
	
	
	public ColorProperties(double redProperty, double greenProperty, double blueProperty) {
		this.redProperty = setInRange(redProperty);
		this.greenProperty = setInRange(greenProperty);
		this.blueProperty = setInRange(blueProperty);
	}
	
	
	public ColorProperties(ColorProperties cp) {
		this(cp.redProperty, cp.greenProperty, cp.blueProperty);
	}
	
	
	public ColorProperties copy() {
		return new ColorProperties(this);
	}
	
	
	public double getRedProperty() {
		return this.redProperty;
	}
	
	
	public double getGreenProperty() {
		return this.greenProperty;
	}
	
	
	public double getBlueProperty() {
		return this.blueProperty;
	}
	
	
	public Color getColorFrom(Color c) {
		int red = (int) (c.getRed() * this.redProperty);
		int green = (int) (c.getGreen() * this.greenProperty);
		int blue = (int) (c.getBlue() * this.blueProperty);
		
		return new Color(red < 0 ? 0 : red,
						 green < 0 ? 0 : green,
						 blue < 0 ? 0 : blue);
	}
	
	
	private double setInRange(double property) {
		if(property < ColorProperties.MIN_RANGE) {
			return 0;
		}
		if(property > ColorProperties.MAX_RANGE) {
			return 1;
		}
		
		return property;
	}
	
	
	@Override
	public boolean equals(Object object) {
		if(this == object) {
			return true;
		}
		
		if(!(object instanceof ColorProperties)) {
			return false;
		}
		
		ColorProperties cp = (ColorProperties) object;
		
		return 	this.redProperty == cp.redProperty
				&& this.greenProperty == cp.greenProperty
				&& this.blueProperty == cp.blueProperty;
	}
}
