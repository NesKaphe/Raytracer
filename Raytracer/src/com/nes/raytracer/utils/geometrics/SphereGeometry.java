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
		
		
		Point3D intersection = rayOrigin.copy().translate(rayDirection.scale(t));
		
		if(intersection.getZ() < rayOrigin.getZ()) {
			return Hit.HitMissed();
		}
		
		return new Hit(true, intersection);
	}

	@Override
	public Vector3D getNormal(Point3D point) {
		/*
		 * To get the normal vector we will need to get the vector 
		 * from the center of the sphere and point provided
		 */
		Vector3D normal = new Vector3D(
									 this.center.getX() - point.getX(), 
									 this.center.getY() - point.getY(), 
									 this.center.getZ() - point.getZ()
									 );
		
		//Then we have to normalize the vector
		normal.normalize();
		
		return normal;
	}	
}
