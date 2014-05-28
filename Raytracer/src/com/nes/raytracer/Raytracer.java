package com.nes.raytracer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.nes.raytracer.objects.SceneObject;
import com.nes.raytracer.objects.materials.ColorProperties;
import com.nes.raytracer.utils.exceptions.NotOnSurfaceException;
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
		Color finalColor = Color.black;
		SceneObject closest = this.getClosest(ray);
		
		if(closest != null) { //If we found an object
			ColorProperties ambient = closest.getMaterial().getAmbient();
			Color ambientColor = ambient.getColorFrom(this.scene.getAmbientColor());
			Color diffuseColor = Color.black;
			Color specularColor = Color.black;
			//Now we're going to look for the diffuse color
			Hit hit = closest.intersection(ray);

			hit.getIntersection().translate(closest.getNormalTo(hit.getIntersection()).scale(0.000000000001));
			
			for(SceneObject nextObject : this.scene.getSceneObjects()) {
				
				if(nextObject.isEmittingLight() && !nextObject.equals(closest)) {
					if(this.isLightVisible(hit.getIntersection())) {
						Vector3D direction = new Vector3D(hit.getIntersection(), nextObject.getPosition());
						double distance = direction.getLength();
											
						double cos = direction.normalize().copy().dot(closest.getNormalTo(hit.getIntersection()));
						
						if(cos > 0) {
							ColorProperties diffuseProperty = closest.getMaterial().getDiffuse();
							ColorProperties emissivityProperty = nextObject.getMaterial().getEmissive();
							
							Color emissiveColor = emissivityProperty.getColorFrom(new Color(255,255,255));
							
							diffuseColor = diffuseProperty.getColorFrom(emissiveColor);
							
							int red = (int) ((cos *diffuseColor.getRed()) / (distance/5));
							int green = (int) ((cos * diffuseColor.getGreen()) / (distance/5));
							int blue = (int) ((cos * diffuseColor.getBlue()) / (distance/5));
							
							red = red > 255 ? 255 : red;
							green = green > 255 ? 255 : green;
							blue = blue > 255 ? 255 : blue;
							
							diffuseColor = new Color(red, green, blue);
						}
					}
				}
			}
			
			//And now the specular
			ColorProperties specularProperty = closest.getMaterial().getSpecular();
			if (specularProperty.getRedProperty() > 0 || specularProperty.getGreenProperty() > 0 || specularProperty.getBlueProperty() > 0) {
				Ray reflectedRay = closest.getReflectedRay(ray, hit.getIntersection());
				specularColor = specularProperty.getColorFrom(this.compute(reflectedRay, bounces - 1));
			}
			
			finalColor = this.combineColors(ambientColor, diffuseColor, specularColor);
			//System.out.println(ambientColor+" ; "+finalColor);
		}
		
		return finalColor;
	}
	
	
	private Color combineColors(Color ambientColor, Color diffuseColor, Color specularColor) {
		
		int red = ambientColor.getRed()+diffuseColor.getRed()+specularColor.getRed();
		int green = ambientColor.getGreen()+diffuseColor.getGreen()+specularColor.getGreen();
		int blue = ambientColor.getBlue()+diffuseColor.getBlue()+specularColor.getBlue();
		
		red = red > 255 ? 255 : red;
		green = green > 255 ? 255 : green;
		blue = blue > 255 ? 255 : blue;
		
		return new Color(red, green, blue);
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
