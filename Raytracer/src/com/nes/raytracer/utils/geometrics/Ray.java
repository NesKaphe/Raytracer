package com.nes.raytracer.utils.geometrics;

/**
 * A ray that will be shot from the camera 
 * @author Alain Dias
 * @version 1.0
 */
public class Ray {

	private final Point3D origin;
	private final Vector3D direction;
	
	
	/**
	 * Create a ray with origin the point (0,0,0) and with no direction (0,0,0)
	 */
	public Ray() {
		this(new Point3D());
	}
	
	
	/**
	 * Create a ray with the given origin and with no direction (0,0,0)
	 * @param origin the point from where the ray will be shot
	 */
	public Ray(Point3D origin) {
		this(origin, new Vector3D());
	}
	
	
	/**
	 * Create a ray with the given origin and direction
	 * @param origin the point from where the ray will be shot
	 * @param direction a vector to represent the direction of the ray
	 */
	public Ray(Point3D origin, Vector3D direction) {
		this.origin = origin.copy();
		this.direction = direction.copy();
	}
	
	
	/**
	 * getter for the origin field
	 * @return origin a cloned instance of the ray origin
	 * @since 1.0
	 */
	public Point3D getOrigin() {
		return this.origin.copy();
	}
	
	
	/**
	 * getter for the direction field
	 * @return direction a cloned instance of the ray direction
	 * @since 1.0
	 */
	public Vector3D getDirection() {
		return this.direction.copy();
	}
}