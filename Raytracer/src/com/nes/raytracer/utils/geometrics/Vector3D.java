package com.nes.raytracer.utils.geometrics;

/**
 * Vector in 3 dimensions
 * @author Alain Dias
 * @version 1.0
 */
public final class Vector3D {

	private double x;
	private double y;
	private double z;
	
	
	/**
	 * Will create a null vector (0,0,0)
	 */
	public Vector3D () {
		this(0, 0, 0);
	}
	
	
	/**
	 * Will create a vector with the given parameters 
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param z the Z coordinate
	 */
	public Vector3D (double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	
	/**
	 * Will create a vector by copy
	 * @param vect
	 */
	public Vector3D (Vector3D vect) {
		this(vect.x, vect.y, vect.z);
	}
	
	
	/**
	 * Will create a vector from a point to an other
	 * @param p0 the first point
	 * @param p1 the second point
	 */
	public Vector3D (Point3D p0, Point3D p1) {
		this(p1.getX() - p0.getX(), p1.getY() - p0.getY(), p1.getZ() - p0.getZ());
		
	}
	
	
	/**
	 * returns a cloned instance of the Vector3D
	 * @return vector a cloned instance of the Vector3D
	 * @since 1.0
	 */
	public Vector3D copy() {
		return new Vector3D(this);
	}
	
	
	/**
	 * Will calculate the sum with the given vector
	 * @param vect a Vector3D
	 * @since 1.0
	 */
	public Vector3D add(Vector3D vect) {
		return this.add(vect.x, vect.y, vect.z);
	}
	
	
	/**
	 * Will sum with the given x,y,z
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param z the Z coordinate
	 * @since 1.0
	 */
	public Vector3D add(double x, double y, double z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}
	
	
	/**
	 * Will calculate the difference with the given vector
	 * @param vect a Vector3D
	 * @since 1.0
	 */
	public Vector3D difference(Vector3D vect) {
		return this.difference(vect.x, vect.y, vect.z);
	}
	
	
	/**
	 * Will do the difference with the given x,y,z
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @param z the Z coordinate
	 * @since 1.0
	 */
	public Vector3D difference(double x, double y, double z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}
	
	
	/**
	 * Will multiply the vector by a scale
	 * @param scale coefficient
	 * @since 1.0
	 */
	public Vector3D scale(double scale) {
		this.x *= scale;
		this.y *= scale;
		this.z *= scale;
		return this;
	}
	
	
	/**
	 * The vector will me normalized, ie. the vector will be of length 1
	 * @since 1.0
	 */
	public Vector3D normalize() {
		this.scale(1 / this.getLength());
		return this;
	}
	
	
	/**
	 * The dot product between this vector and the given vector
	 * @return dotProduct the result of the dot product
	 * @since 1.0
	 */
	public double dot(Vector3D vect) {
		return this.x * vect.x + this.y * vect.y + this.z * vect.z;
	}
	
	
	/**
	 * Transform the vector to a null vector
	 * @since 1.0
	 */
	public Vector3D zero() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		return this;
	}
	
	
	/**
	 * Transform the vector to the opposite vector
	 * @since 1.0
	 */
	public Vector3D opposite() {
		this.x *= -1;
		this.y *= -1;
		this.z *= -1;
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
	
	
	/**
	 * Calculate the vector length
	 * @return length the length of the vector
	 * @since 1.0
	 */
	public double getLength() {
		double lengthSquare = Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2);
		
		return Math.sqrt(lengthSquare);
	}
	
	
	@Override
	public boolean equals(Object object) {
		if(this == object) {
			return true;
		}
		
		if ( !(object instanceof Vector3D) ) {
			return false;
		}
		
		Vector3D vect = (Vector3D) object;
		
		return (this.x == vect.x && this.y == vect.y && this.z == vect.z );
	}
}