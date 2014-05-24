package com.nes.raytracer.utils.geometrics;

/**
 * A data structure to store the hit result of the ray
 * @author Alain Dias
 * @version 1.0
 */
public final class Hit {

	private final boolean intersection;
	private final Point3D point;
	
		
	public Hit(boolean intersection, Point3D point) {
		this.intersection = intersection;
		this.point = intersection ? point.copy() : null;
	}
	
	
	public boolean hasHit() {
		return this.intersection;
	}
	
	
	public Point3D getIntersection() {
		return this.point;
	}
	
	
	public double getDistanceFrom(Point3D point) {
		return new Vector3D(this.point.getX() - point.getX(), this.point.getY() - point.getY(), this.point.getZ() - point.getZ()).getLength();
	}
	
	
	public static Hit HitMissed() {
		return new Hit(false, null);
	}
}
