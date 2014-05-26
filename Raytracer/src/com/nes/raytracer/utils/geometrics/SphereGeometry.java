package com.nes.raytracer.utils.geometrics;

import com.nes.raytracer.utils.exceptions.NoQuadraticSolutionException;
import com.nes.raytracer.utils.math.MathHelper;
import com.nes.raytracer.utils.math.MathHelper.QuadraticResult;

public class SphereGeometry implements Geometry {

	private Point3D center;
	private double radius;
	
	
	public SphereGeometry(Point3D center, double radius) {
		this.center = center.copy();
		this.radius = radius > 0 ? radius : 1;
	}
	

	@Override
	public Hit intersection(Ray ray) {
		Vector3D rayDirection = ray.getDirection().normalize();
		Point3D rayOrigin = ray.getOrigin();
		Vector3D centerToPOV = new Vector3D(this.center, rayOrigin);		
		
		double a = rayDirection.dot(rayDirection);
		double b = 2 * rayDirection.dot(centerToPOV);
		double c = centerToPOV.dot(centerToPOV) - Math.pow(this.radius, 2);
		
		QuadraticResult result;
		
		try {
			result = MathHelper.solveQuadratic(a, b, c);
		}
		catch (NoQuadraticSolutionException e) {
			return Hit.HitMissed();
		}
		
		double t = result.getMin();
		
		
		if(t <= 0) {
			t = result.getMax();
			if (t <= 0) {
				return Hit.HitMissed();
			}
		}
		
		Point3D intersection = rayOrigin.copy().translate(rayDirection.scale(t));
		
		return new Hit(true, intersection);
	}

	@Override
	public Vector3D getNormal(Point3D point) {
		/*
		 * To get the normal vector we will need to get the vector 
		 * from the center of the sphere and point provided
		 */
		Vector3D normal = new Vector3D(this.center, point);
		
		//Then we have to normalize the vector
		normal.normalize();
		
		return normal;
	}
	
	
	@Override
	public boolean isOnSurface(Point3D point) {
		return (double) Math.round(new Vector3D(this.center, point).getLength()) == this.radius;
	}
	
	
	@Override
	public boolean equals(Object object) {
		if( this == object) {
			return true;
		}
		
		if (!(object instanceof SphereGeometry)) {
			return false;
		}
		
		SphereGeometry geo = (SphereGeometry) object;
		
		return this.center.equals(geo.center) && this.radius == geo.radius;
	}
}
