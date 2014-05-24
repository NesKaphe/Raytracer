package com.nes.raytracer.utils.geometrics;

/**
 * The interface for the object geometry 
 * @author Alain Dias
 * @version 1.0
 */
public interface Geometry {
	
	/**
	 * Compute the intersection between the object and a ray
	 * @param ray an instance of {@link Ray}
	 * @return hit an instance of {@link Hit} which represents the hit result of the ray and the object
	 * @since 1.0
	 */
	public Hit intersection(Ray ray);
	
	/**
	 * Finds the normal vector to the object 
	 * @param point the {@link Point3D} to find the normal vector
	 * @return vector the normal {@link Vector3D} for the given point
	 * @since 1.0
	 */
	public Vector3D getNormal(Point3D point);
	
}
