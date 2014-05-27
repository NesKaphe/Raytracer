package com.nes.raytracer.objects;

import com.nes.raytracer.objects.materials.Material;
import com.nes.raytracer.utils.exceptions.NotOnSurfaceException;
import com.nes.raytracer.utils.geometrics.Hit;
import com.nes.raytracer.utils.geometrics.Point3D;
import com.nes.raytracer.utils.geometrics.Ray;
import com.nes.raytracer.utils.geometrics.Vector3D;

public interface SceneObject {

	public Hit intersection(Ray ray);
	
	
	public Material getMaterial();
	
	
	public boolean isOnSurface(Point3D point);
	
	
	public Point3D getPosition();


	public boolean isEmittingLight();
	
	
	public Ray getReflectedRay(Ray ray, Point3D intersection);


	public Vector3D getNormalTo(Point3D intersection);
	
	
	/*
	 * TODO: maybe remove this 
	public Color getColor(Point3D point) throws NotOnSurfaceException;
	*/
}
