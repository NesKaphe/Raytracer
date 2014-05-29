package com.nes.raytracer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.nes.raytracer.objects.SceneObject;
import com.nes.raytracer.utils.colors.ColorProperties;
import com.nes.raytracer.utils.colors.RayColor;
import com.nes.raytracer.utils.geometrics.Hit;
import com.nes.raytracer.utils.geometrics.Point3D;
import com.nes.raytracer.utils.geometrics.Ray;
import com.nes.raytracer.utils.geometrics.Vector3D;
import com.nes.raytracer.view.Camera;
import com.nes.raytracer.view.Scene;

public class Raytracer {

	private Scene scene;
	private Camera camera;
	
	
	public Raytracer(Scene scene, Camera camera) {
		this.scene = scene;
		this.camera = camera;
	}
	
	
	public BufferedImage rayTrace() {
		BufferedImage buf = new BufferedImage(this.camera.getWidth(), this.camera.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) buf.getGraphics();
		
		//eye rays help : http://www.unknownroad.com/rtfm/graphics/rt_eyerays.html
		double fovx = Math.PI/4;
		double fovy = ((double)this.camera.getHeight()/this.camera.getWidth()) * fovx;
		
		double xAxis = Math.tan(fovx);
		double yAxis = Math.tan(fovy);
		
		for(int i = 0; i < this.camera.getWidth(); i++) {
			for (int j = 0; j < this.camera.getHeight(); j++) {
				double x = ((double)(2*i - this.camera.getWidth()) / this.camera.getWidth()) * xAxis;
				double y = ((double)(2*j - this.camera.getHeight()) / this.camera.getHeight()) * yAxis;

				Point3D point = new Point3D(x, y, 0);

				point.translate(this.camera.getTarget());

				Ray ray = new Ray(point, new Vector3D(this.camera.getPosition(), point));
				Color pixel = this.compute(ray);
				
				g.setColor(pixel);
				g.drawRect(i, this.camera.getHeight() - j, 1, 1);
			}
		}
		return buf;
	}
	
	
	private Color compute(Ray ray) {		
		return this.compute(ray, Ray.MAX_BOUNCES);
	}
	
	
	private Color compute(Ray ray, int bounces) {
		RayColor rayColor = new RayColor();
		
		if(bounces > 0) {
		
			SceneObject closest = this.getClosest(ray);
			
			if(closest != null) { //If we found an object
				
				Hit hit = closest.intersection(ray);

				hit.getIntersection().translate(closest.getNormalTo(hit.getIntersection()).scale(0.000000000001));

				
				rayColor.add(this.computeAmbientColor(closest));
				rayColor.add(this.computeDiffuseColor(closest, hit.getIntersection()));
				rayColor.add(this.computeSpecularColor(closest, ray, hit.getIntersection(), bounces));
			}
		}
		
		return rayColor.toColor();
	}
	
	
	private Color computeAmbientColor(SceneObject object) {
		ColorProperties ambient = object.getMaterial().getAmbient();
		
		return ambient.getColorFrom(this.scene.getAmbientColor());
	}
	
	
	private Color computeDiffuseColor(SceneObject object, Point3D intersectionPoint) {
		
		RayColor rayColor = new RayColor();
		
				
		for(SceneObject nextObject : this.scene.getSceneObjects()) {
			
			if(nextObject.isEmittingLight() && !nextObject.equals(object)) {
				if(this.isLightVisible(intersectionPoint)) {
					Vector3D direction = new Vector3D(intersectionPoint, nextObject.getPosition());
					double distance = direction.getLength();
										
					double cos = direction.normalize().copy().dot(object.getNormalTo(intersectionPoint));
					
					if(cos > 0) {
						ColorProperties diffuseProperty = object.getMaterial().getDiffuse();
						ColorProperties emissivityProperty = nextObject.getMaterial().getEmissive();
						
						Color emissiveColor = emissivityProperty.getColorFrom(new Color(255,255,255));
						
						Color diffuseColor = diffuseProperty.getColorFrom(emissiveColor);
						
						int red = (int) ((cos *diffuseColor.getRed()));// / (distance/5));
						int green = (int) ((cos * diffuseColor.getGreen()));// / (distance/5));
						int blue = (int) ((cos * diffuseColor.getBlue()));// / (distance/5));
						
						
						
						rayColor.add(red, green, blue);
					}
				}
			}
		}
		
		return rayColor.toColor();
	}
	
	
	private Color computeSpecularColor(SceneObject object, Ray ray, Point3D intersectionPoint, int bounces) {
		ColorProperties specularProperty = object.getMaterial().getSpecular();
		RayColor rayColor = new RayColor();
		
		if (specularProperty.getRedProperty() > 0 || specularProperty.getGreenProperty() > 0 || specularProperty.getBlueProperty() > 0) {
			Ray reflectedRay = object.getReflectedRay(ray, intersectionPoint);
			rayColor.add(specularProperty.getColorFrom(this.compute(reflectedRay, bounces - 1)));
			rayColor.attenuate(Ray.MAX_BOUNCES - bounces + 1);
		}
		return rayColor.toColor();
	}

	private SceneObject getClosest(Ray ray) {
		ArrayList<SceneObject> listObjects = this.scene.getSceneObjects();
		
		double distance = -1;
		double curDistance = -1;
		
		SceneObject closest = null;
		
		for(SceneObject so : listObjects) {
			Hit hit = so.intersection(ray);
			
			if(hit.hasHit()) {
				curDistance = hit.getDistanceFrom(ray.getOrigin());
				
				if(distance < 0 || distance > curDistance) {
					distance = curDistance;
					
					closest = so;
					
				}
			}
		}
		return closest;
	}
	
	
	private boolean isLightVisible(Point3D hitPoint) {
		ArrayList<SceneObject> listObjects = this.scene.getSceneObjects();
		
		for(SceneObject so : listObjects) {
			if(so.isEmittingLight()) {
				//We'll construct a ray from the point to the center of the object
				Vector3D direction = new Vector3D(hitPoint, so.getPosition());
				
				direction.normalize();
				
				Ray ray = new Ray(hitPoint, direction);
				
				if(so.equals(this.getClosest(ray))) {
					return true;
				}
			}
		}
		
		return false;
	}
}
