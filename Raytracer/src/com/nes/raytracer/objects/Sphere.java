package com.nes.raytracer.objects;

import java.awt.Color;

import com.nes.raytracer.objects.materials.Material;
import com.nes.raytracer.utils.exceptions.NotOnSurfaceException;
import com.nes.raytracer.utils.geometrics.Hit;
import com.nes.raytracer.utils.geometrics.Point3D;
import com.nes.raytracer.utils.geometrics.Ray;
import com.nes.raytracer.utils.geometrics.SphereGeometry;

public class Sphere extends AbstractObject implements SceneObject {

	public Sphere(Point3D position, double radius, Material material) {
		super(position, material);
		this.geometry = new SphereGeometry(position, radius);
	}
	
	
	@Override
	public Hit intersection(Ray ray) {
		return this.geometry.intersection(ray);
	}

	
	@Override
	public Color getColor(Point3D point) throws NotOnSurfaceException {
		
		return null;
	}
}
