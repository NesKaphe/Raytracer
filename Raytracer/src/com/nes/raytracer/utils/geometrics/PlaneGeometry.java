package com.nes.raytracer.utils.geometrics;

public class PlaneGeometry implements Geometry {

	private final Point3D position;
	private final Vector3D normal;
	
	public PlaneGeometry(Point3D position, Vector3D normalVector) {
		this.position = position;
		this.normal = normalVector;
		System.out.println(this.normal);
	}
	
	
	@Override
	public Hit intersection(Ray ray) {
		
		Vector3D rayOriginToPosition = new Vector3D(ray.getOrigin(), this.position);
		Vector3D rayDirectionNormalized = ray.getDirection().normalize();
		Vector3D normalizedNormal = this.normal.normalize();
		
		double a = normalizedNormal.dot(rayOriginToPosition);
		double b = normalizedNormal.dot(rayDirectionNormalized);
		
		if(b == 0) {
			return Hit.HitMissed();
		}
		
		double t = a / b;

		if(t >= 0) {
			Point3D hitLocation = ray.getOrigin().translate(rayDirectionNormalized.scale(t));
			return new Hit(true, hitLocation);
		}
		
		return Hit.HitMissed();
	}
	
	
	
	@Override
	public Vector3D getNormal(Point3D point) {
		return this.normal.copy();
	}

	
	@Override
	public boolean isOnSurface(Point3D point) {
		return true; //TODO: Don't remember how to do it (as lol on lol)
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		
		if (! (o instanceof PlaneGeometry) ) {
			return false;
		}
		
		PlaneGeometry plane = (PlaneGeometry) o;
			
		return this.position.equals(plane.position) && this.normal.equals(plane.normal);
	}
	
}
