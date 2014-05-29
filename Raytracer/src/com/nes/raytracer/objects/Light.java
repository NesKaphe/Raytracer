package com.nes.raytracer.objects;

import java.awt.Color;

import com.nes.raytracer.objects.materials.Material;
import com.nes.raytracer.utils.colors.ColorProperties;
import com.nes.raytracer.utils.geometrics.Point3D;

public class Light extends Sphere {

	public Light(Point3D position, Color lightColor) {
		this(position, 1, lightColor);
	}
	
	public Light(Point3D position, double radius, Color lightColor) {
		super(position, 
			  radius,
			  new Material(
				new ColorProperties(lightColor.getRed()/255, 
									lightColor.getGreen()/255,
									lightColor.getBlue()/255)
			  ));
	}

	
	public boolean isVisible() {
		return false;
	}
}
