package com.nes.raytracer;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import com.nes.raytracer.objects.Sphere;
import com.nes.raytracer.objects.materials.ColorProperties;
import com.nes.raytracer.objects.materials.Material;
import com.nes.raytracer.ui.Window;
import com.nes.raytracer.utils.geometrics.Hit;
import com.nes.raytracer.utils.geometrics.Point3D;
import com.nes.raytracer.utils.geometrics.Ray;
import com.nes.raytracer.utils.geometrics.SphereGeometry;
import com.nes.raytracer.utils.geometrics.Vector3D;
import com.nes.raytracer.view.Camera;
import com.nes.raytracer.view.Scene;

public class Main {

	public static void main(String[] args) {
		SphereGeometry sphere = new SphereGeometry(new Point3D(5,5,15), 10);
		Ray ray = new Ray(new Point3D(5,5,0), new Vector3D(0,0,1));
		
		Hit hit = sphere.intersection(ray);
		
		if(hit.hasHit()) {
			Point3D point = hit.getIntersection();
			
			System.out.println("le rayon a touché au point "+point+" a une distance de "+hit.getDistanceFrom(ray.getOrigin()));
			System.out.println("Le vecteur normal est "+sphere.getNormal(point));
		}
		else {
			System.out.println("Le rayon n'a pas touché la sphere");
		}
		
		Number n = new Double(5);
		int i = 0;
		System.out.println(i++);
		System.out.println(i);
		
		//-----------------------------TEST---------------------------------------
		Camera cam = new Camera(new Dimension(800,600), new Point3D(0,0,0), new Vector3D(0,0,5));
		Scene scene = new Scene();
		
		ColorProperties cp = new ColorProperties(0.1, 0.5, 1);
		
		Material mat = new Material(cp, cp, cp);
		Sphere s = new Sphere(new Point3D(0,0,5), 1, mat);
		
		scene.add(s);
		
		Raytracer r = new Raytracer(scene, cam);
		
		BufferedImage buf = r.rayTrace();
		
		Window window = new Window();
		
		window.setNewDrawingBuffer(buf);
		
		window.refresh();
	}
}