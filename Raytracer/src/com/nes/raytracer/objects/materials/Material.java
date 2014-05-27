package com.nes.raytracer.objects.materials;

public class Material {

	private ColorProperties ambient;
	
	private ColorProperties diffuse;
	
	private ColorProperties emissive;
	
	private ColorProperties specular;
	
	//TODO: implement an opacity property to handle refraction and reflexion properly
	
	
	public Material(ColorProperties emissive) {
		this.emissive = emissive;
	}
	
	
	public Material(ColorProperties ambient, ColorProperties diffuse, ColorProperties specular) {
		this.ambient = ambient;
		this.diffuse = diffuse;
		this.specular = specular;
		this.emissive = new ColorProperties(0, 0, 0);
	}
	
	
	public Material(ColorProperties ambient, ColorProperties diffuse, ColorProperties specular, ColorProperties emissive) {
		this.ambient = ambient;
		this.diffuse = diffuse;
		this.specular = specular;
		this.emissive = emissive;
	}
	
	
	public boolean isReflective() {
		return this.diffuse.getRedProperty() > 0
				|| this.diffuse.getGreenProperty() > 0
				|| this.diffuse.getBlueProperty() > 0;
	}
	
	public boolean isRefractive() {
		return false;
	}
	
	
	public ColorProperties getAmbient() {
		return this.ambient;
	}
	
	
	public ColorProperties getDiffuse() {
		return this.diffuse;
	}
	
	
	public ColorProperties getSpecular() {
		return this.specular;
	}
	
	
	public ColorProperties getEmissive() {
		return this.emissive;
	}
}
