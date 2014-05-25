package com.nes.raytracer.objects;

import java.awt.Color;

import com.nes.raytracer.utils.exceptions.NotOnSurfaceException;
import com.nes.raytracer.utils.geometrics.Hit;
import com.nes.raytracer.utils.geometrics.Point3D;
import com.nes.raytracer.utils.geometrics.Ray;

public interface SceneObject {

	public Hit intersection(Ray ray);
	
	public Color getColor(Point3D point) throws NotOnSurfaceException;
	
}
