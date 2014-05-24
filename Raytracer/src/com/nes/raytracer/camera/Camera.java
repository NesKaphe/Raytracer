package com.nes.raytracer.camera;

import java.awt.Dimension;

import com.nes.raytracer.utils.geometrics.Point3D;
import com.nes.raytracer.utils.geometrics.Vector3D;

/**
 * The point of view of the raytracer
 * @author Alain Dias
 * @version 1.0
 */
public class Camera {
	
	//The eye position
	private Point3D position;
	
	//The center of the target window where the ray should pass
	private Vector3D target;
	
	//The direction of the target window
	private Vector3D direction;
	
	//The dimension of the target window
	private Dimension screenDimension;
	
	public Camera(Dimension screenDimension) {
		this(screenDimension, new Point3D());
	}
	
	
	public Camera(Dimension screenDimension, Point3D position) {
		this(screenDimension, position, new Vector3D());
	}
	
	
	public Camera(Dimension screenDimension, Point3D position, Vector3D target) {
		this(screenDimension, position, target, new Vector3D());
	}
	
	
	public Camera(Dimension screenDimension, Point3D position, Vector3D target, Vector3D direction) {
		this.position = position.copy();
		this.target = target.copy();
		this.direction = direction.copy();
		this.screenDimension = screenDimension;
	}
	
	
	public Point3D getPosition() {
		return this.position.copy();
	}
	
	
	public Vector3D getTarget() {
		return this.target.copy();
	}
	
	
	public Vector3D getDirection() {
		return this.direction.copy();
	}
	
	
	public int getWidth() {
		return this.screenDimension.width;
	}
	
	
	public int getHeight() {
		return this.screenDimension.height;
	}
}
