package com.nes.raytracer.objects.materials;

public class ColorProperties {
	
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
	
	public double getRedProperty() {
		return this.redProperty;
	}
	
	
	public double getGreenProperty() {
		return this.greenProperty;
	}
	
	
	public double getBlueProperty() {
		return this.blueProperty;
	}
	
	
	private double setInRange(double property) {
		if(property < 0) {
			return 0;
		}
		if(property > 1) {
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
	
	
	public ColorProperties copy() {
		return new ColorProperties(this);
	}
}
