package com.nes.raytracer.objects;

import com.nes.raytracer.objects.materials.Material;
import com.nes.raytracer.utils.exceptions.NotOnSurfaceException;
import com.nes.raytracer.utils.geometrics.Hit;
import com.nes.raytracer.utils.geometrics.Point3D;
import com.nes.raytracer.utils.geometrics.Ray;
import com.nes.raytracer.utils.geometrics.SphereGeometry;
import com.nes.raytracer.utils.geometrics.Vector3D;

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
	public Material getMaterial() {
		return this.material;
	}
	
	
	public boolean isOnSurface(Point3D point) {
		return this.geometry.isOnSurface(point);
	}

	
	public Ray getReflectedRay(Ray ray, Point3D intersection) {
		
		Vector3D normal = this.geometry.getNormal(intersection);
		Vector3D direction = ray.getDirection();
		
		Vector3D substract = normal.copy().scale(2*normal.dot(direction));
		
		return new Ray(intersection, direction.difference(substract));
	}
}
