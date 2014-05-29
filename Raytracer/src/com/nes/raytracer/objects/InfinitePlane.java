package com.nes.raytracer.objects;

import com.nes.raytracer.objects.materials.Material;
import com.nes.raytracer.utils.geometrics.Hit;
import com.nes.raytracer.utils.geometrics.PlaneGeometry;
import com.nes.raytracer.utils.geometrics.Point3D;
import com.nes.raytracer.utils.geometrics.Ray;
import com.nes.raytracer.utils.geometrics.Vector3D;

public class InfinitePlane extends AbstractObject implements SceneObject {
	
	public InfinitePlane(Point3D position, Vector3D normalVector, Material material) {
		super(position, material);
		this.geometry = new PlaneGeometry(position, normalVector);
	}

	@Override
	public Hit intersection(Ray ray) {
		return this.geometry.intersection(ray);
	}

	@Override
	public Material getMaterial() {
		return this.material;
	}

	@Override
	public boolean isOnSurface(Point3D point) {
		return this.geometry.isOnSurface(point);
	}
}
