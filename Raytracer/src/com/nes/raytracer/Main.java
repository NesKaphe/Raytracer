package com.nes.raytracer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import com.nes.raytracer.objects.Light;
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
		Ray ray = new Ray(new Point3D(5,5,5), new Vector3D(0,0,1));
		
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
		Camera cam = new Camera(new Dimension(800,600), new Point3D(0,0,0), new Vector3D(0,0,1));
		Scene scene = new Scene();
		
		ColorProperties cp = new ColorProperties(0.1, 0.15, 0.25);
		ColorProperties cp2 = new ColorProperties(0.2, 0.5, 0.3);
		ColorProperties cp3 = new ColorProperties(0.5, 1, 0);
		ColorProperties cp4 = new ColorProperties(1,1,1);
		ColorProperties cp5 = new ColorProperties(0,0,0);
		
		Material mat = new Material(cp, cp2, cp);
		Material mat2 = new Material(cp, cp3, cp2);
		Material mat3 = new Material(cp2, cp3, cp3);
		Material matLight = new Material(cp4);
		
		Sphere s = new Sphere(new Point3D(-3, -1, 5), 1, mat);
		Sphere s2 = new Sphere(new Point3D(3, 1, 5), 1, mat3);
		Sphere s3 = new Sphere(new Point3D(-0.7,-1,4),0.5,mat2);
		Sphere s4 = new Sphere(new Point3D(1,-0.5,3),0.2,mat3);
		
		Light light = new Light(new Point3D(0,5,2),1, Color.white);
		
		scene.add(s);
		scene.add(s2);
		scene.add(s3);
		scene.add(s4);
		scene.add(light);
		Window window = new Window();
		
		
		Raytracer r = new Raytracer(scene, cam);
		long debut = System.currentTimeMillis();
		BufferedImage buf = r.rayTrace();
		long fin = System.currentTimeMillis() - debut;
		System.out.println(fin);
		window.setNewDrawingBuffer(buf);
		window.refresh();
		System.out.println("fin !");
	}
}