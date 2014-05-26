package com.nes.raytracer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.nes.raytracer.objects.SceneObject;
import com.nes.raytracer.objects.materials.ColorProperties;
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
		
		double fovx = Math.PI / 4;
		double fovy = ((double)this.camera.getHeight()/this.camera.getWidth()) * fovx;
		
		double xAxis = Math.tan(fovx);
		double yAxis = Math.tan(fovy);
		System.out.println("="+yAxis);
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
		ArrayList<SceneObject> listObjects = this.scene.getSceneObjects();
		
		for(SceneObject so : listObjects) {
			Hit hit = so.intersection(ray);
			
			if(hit.hasHit()) {
				//Getting the ambient color from the ambientLigt of the scene
				ColorProperties ambient = so.getMaterial().getAmbient();
				Color colorObject = ambient.getColorFrom(this.scene.getAmbientColor());
				
				//System.out.println(so.isOnSurface(hit.getIntersection())+" but "+hit.hasHit());
				//System.out.println(ray.getOrigin()+" ; "+ray.getDirection()+" : "+hit.hasHit()+" ; "+so.isOnSurface(hit.getIntersection()));
				return colorObject;
			}
		}
		
		return Color.black;
	}
}
