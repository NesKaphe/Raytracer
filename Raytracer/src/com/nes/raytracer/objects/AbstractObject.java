package com.nes.raytracer.objects;

import com.nes.raytracer.objects.materials.Material;
import com.nes.raytracer.utils.colors.ColorProperties;
import com.nes.raytracer.utils.geometrics.Geometry;
import com.nes.raytracer.utils.geometrics.Point3D;
import com.nes.raytracer.utils.geometrics.Ray;
import com.nes.raytracer.utils.geometrics.Vector3D;

public abstract class AbstractObject {

	protected Geometry geometry;
	protected Point3D position;
	protected Material material;
	
	
	public AbstractObject(Point3D position, Material material) {
		this.position = position;
		this.material = material;
	}
		
	
	public Point3D getPosition() {
		return this.position;
	}
	
	
	public boolean isEmittingLight() {
		ColorProperties cp = this.material.getEmissive();
		
		return cp.getRedProperty() > 0
				|| cp.getBlueProperty() > 0
				|| cp.getGreenProperty() > 0;
	}
	
	
	public Vector3D getNormalTo(Point3D intersection) {
		return this.geometry.getNormal(intersection);
	}
	
	
	public boolean isVisible() {
		return true;
	}
	
	
	public Ray getReflectedRay(Ray ray, Point3D intersection) {
		
		Vector3D normal = this.geometry.getNormal(intersection);
		Vector3D direction = ray.getDirection();
		
		Vector3D substract = normal.copy().scale(2*normal.dot(direction));

		return new Ray(intersection, direction.difference(substract));
	}
	
	
	@Override
	public boolean equals(Object object) {
		if(this == object) {
			return true;
		}
		
		if ( !(object instanceof AbstractObject) ) {
			return false;
		}
		
		AbstractObject absObject = (AbstractObject) object;
		
		return 	this.geometry.equals(absObject.geometry) 
				&& this.position.equals(absObject.position) 
				&& this.material.equals(absObject.material);
	}
}
