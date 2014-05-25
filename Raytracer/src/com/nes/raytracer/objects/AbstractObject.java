package com.nes.raytracer.objects;

import com.nes.raytracer.objects.materials.Material;
import com.nes.raytracer.utils.geometrics.Geometry;
import com.nes.raytracer.utils.geometrics.Point3D;

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
