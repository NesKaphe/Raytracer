package com.nes.raytracer.utils.colors;

import java.awt.Color;

/**
 * Data structure to represent the color of a ray. 
 * @author Nes
 *
 */
public class RayColor {

	private static final int RED_ATTRIBUTE = 0;
	private static final int GREEN_ATTRIBUTE = 1;
	private static final int BLUE_ATTRIBUTE = 2;
	private static final int ALL_ATTRIBUTE = 3;
	
	private int red;
	private int green;
	private int blue;
	
	
	public RayColor() {
		this(0, 0, 0);
	}

	
	public RayColor(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		
		this.setToInterval();
	}
	
	
	public void add(RayColor rayColor) {
		this.red += rayColor.red;
		this.green += rayColor.green;
		this.blue += rayColor.blue;
		
		this.setToInterval();
	}
	
	
	public void add(Color color) {
		this.red += color.getRed();
		this.green += color.getGreen();
		this.blue += color.getBlue();
		
		this.setToInterval();
	}
	
	
	public void add(int red, int green, int blue) {
		this.red += red;
		this.green += green;
		this.blue += blue;
		
		this.setToInterval();
	}
	
	
	public void addRed(int red) {
		this.red += red;
	
		this.setToInterval(RayColor.RED_ATTRIBUTE);
	}
	
	
	public void addGreen(int green) {
		this.green += green;
		
		this.setToInterval(RayColor.GREEN_ATTRIBUTE);
	}
	
	
	public void addBlue(int blue) {
		this.blue += blue;
		
		this.setToInterval(RayColor.BLUE_ATTRIBUTE);
	}
	
	
	public void attenuate(int coef) {
		this.red = this.red / coef;
		this.green = this.green / coef;
		this.blue = this.blue / coef;
		
		this.setToInterval();
	}
	
	
	public void setRed(int red) {
		this.red = red;
		
		this.setToInterval(RayColor.RED_ATTRIBUTE);
	}
	
	
	public void setGreen(int green) {
		this.green = green;
		
		this.setToInterval(RayColor.GREEN_ATTRIBUTE);
	}
	
	
	public void setBlue(int blue) {
		this.blue = blue;
		
		this.setToInterval(RayColor.BLUE_ATTRIBUTE);
	}
	
	
	public int getRed() {
		return this.red;
	}
	
	
	public int getGreen() {
		return this.green;
	}
	
	
	public int getBlue() {
		return this.blue;
	}
	
	public Color toColor() {
		return new Color(this.red, this.green, this.blue);
	}
	
	
	private void setToInterval() {
		this.setToInterval(RayColor.ALL_ATTRIBUTE);
	}
	
	
	private void setToInterval(int attributeType) {
		switch(attributeType) {
		case RayColor.RED_ATTRIBUTE:
			
			this.red = this.red < 0 ? 0 : this.red > 255 ? 255 : this.red;
			break;
			
		case RayColor.GREEN_ATTRIBUTE:
			
			this.green = this.green < 0 ? 0 : this.green > 255 ? 255 : this.green;
			break;
			
		case RayColor.BLUE_ATTRIBUTE:
			
			this.blue = this.blue < 0 ? 0 : this.blue > 255 ? 255 : this.blue;
			break;
			
		case RayColor.ALL_ATTRIBUTE:
			
			this.red = this.red < 0 ? 0 : this.red > 255 ? 255 : this.red;
			this.green = this.green < 0 ? 0 : this.green > 255 ? 255 : this.green;
			this.blue = this.blue < 0 ? 0 : this.blue > 255 ? 255 : this.blue;
			break;
			
		}
	}
}
