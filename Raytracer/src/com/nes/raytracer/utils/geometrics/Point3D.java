package com.nes.raytracer.utils.geometrics;

/**
 * Point in 3 dimensions
 * @author Alain Dias
 * @version 1.0
 */
public final class Point3D {

	private double x;
	private double y;
	private double z;
	
	
	/**
	 * Create a point with the origin coordinate
	 */
	public Point3D() {
		this(0, 0, 0);
	}
	
	
	/**
	 * Create a point with the given coordinate
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param z the Z coordinate
	 */
	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	
	/**
	 * Create a point by copy
	 * @param point the point to be copied
	 */
	public Point3D(Point3D point) {
		this(point.x, point.y, point.z);
	}
	
	
	/**
	 * returns a cloned instance of the Point3D
	 * @return point a cloned instance of this point
	 * @since 1.0
	 */
	public Point3D copy() {
		return new Point3D(this);
	}
	
	
	/**
	 * Translation of the point by the given vector
	 * @param vect
	 * @since 1.0
	 */
	public Point3D translate(Vector3D vect) {
		return this.translate(vect.getX(), vect.getY(), vect.getZ());
	}
	
	
	/**
	 * Translation of the point by the given translation coordinates
	 * @param x the X translation
	 * @param y the Y translation
	 * @param z the Z translation
	 * @since 1.0
	 */
	public Point3D translate(double x, double y, double z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}
	
	
	/**
	 * Translate the point to the origin point
	 * @since 1.0
	 */
	public Point3D zero() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		return this;
	}
	
	
	/**
	 * getter for the x field
	 * @return x field
	 * @since 1.0
	 */
	public double getX() {
		return this.x;
	}
	
	
	/**
	 * getter for the y field
	 * @return y field
	 * @since 1.0
	 */
	public double getY() {
		return this.y;
	}
	
	
	/**
	 * getter for the z field
	 * @return z field
	 * @since 1.0
	 */
	public double getZ() {
		return this.z;
	}
	
	
	@Override
	public boolean equals(Object object) {
		if(this == object) {
			return true;
		}
		
		if ( !(object instanceof Vector3D) ) {
			return false;
		}
		
		Point3D point = (Point3D) object;
		
		return (this.x == point.x && this.y == point.y && this.z == point.z );
	}
}