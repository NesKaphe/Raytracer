package com.nes.raytracer.view;

import java.awt.Color;
import java.util.ArrayList;

import com.nes.raytracer.objects.SceneObject;
import com.nes.raytracer.objects.materials.ColorProperties;

public class Scene {

	private ArrayList<SceneObject> sceneObjects;
	
	private Color ambientLight;
	private ColorProperties lightIntensity;
	
	public Scene() {
		this(new Color(255,255,255));
	}
	
	
	public Scene(Color ambientLight) {
		this(ambientLight, new ColorProperties(1,1,1));
	}
	
	
	public Scene(Color ambientLight, ColorProperties lightIntensity) {
		this.sceneObjects = new ArrayList<SceneObject>();
		this.ambientLight = ambientLight;
		this.lightIntensity = lightIntensity;
	}
	
	
	public void add(SceneObject object) {
		this.sceneObjects.add(object);
	}
	
	
	public ArrayList<SceneObject> getSceneObjects() {
		return this.sceneObjects;
	}
	
	
	public Color getAmbientColor() {
		int red = (int) (this.ambientLight.getRed() * this.lightIntensity.getRedProperty());
		int green = (int) (this.ambientLight.getGreen() * this.lightIntensity.getGreenProperty());
		int blue = (int) (this.ambientLight.getBlue() * this.lightIntensity.getBlueProperty());
		
		return new Color(red, green, blue);
	}
}
