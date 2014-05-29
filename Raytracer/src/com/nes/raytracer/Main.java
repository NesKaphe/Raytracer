package com.nes.raytracer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import com.nes.raytracer.objects.InfinitePlane;
import com.nes.raytracer.objects.Light;
import com.nes.raytracer.objects.Sphere;
import com.nes.raytracer.objects.materials.Material;
import com.nes.raytracer.ui.Window;
import com.nes.raytracer.utils.colors.ColorProperties;
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
		
		Window window = new Window();
		Camera cam = new Camera(new Dimension(1600,900), new Point3D(0,0,0), new Vector3D(0,-0.2,1));
		
		Scene scene = scene1();
		
		Raytracer r = new Raytracer(scene, cam);
		BufferedImage buf = r.rayTrace();
		window.setNewDrawingBuffer(buf);
		window.refresh();
		System.out.println("fin !");
	}
	
	
	private static Scene scene1() {
		Scene scene = new Scene();
		
		ColorProperties cp = new ColorProperties(0.1, 0.15, 0.25);
		ColorProperties cp2 = new ColorProperties(0.2, 0.5, 0.3);
		ColorProperties cp3 = new ColorProperties(0.5, 1, 0);
		ColorProperties cp4 = new ColorProperties(1,1,1);
		ColorProperties cp5 = new ColorProperties(0,0,0);
		ColorProperties cp6 = new ColorProperties(0.3,0,0);
		ColorProperties cp7 = new ColorProperties(0.2,0.2,0.2);
		
		Material mat = new Material(cp, cp2, cp4);
		Material mat2 = new Material(cp, cp3, cp4);
		Material mat3 = new Material(cp, cp3, cp4);
		Material mat4 = new Material(cp6, cp4, cp5);
		Material mat5 = new Material(cp6, cp7, cp7);
		
		Sphere s = new Sphere(new Point3D(-3, -1, 5), 1, mat);
		Sphere s2 = new Sphere(new Point3D(3, 1, 5), 1, mat3);
		Sphere s3 = new Sphere(new Point3D(-1.5,1,3.7),0.5,mat2);
		Sphere s4 = new Sphere(new Point3D(1,-0.5,3),0.2,mat3);
		
		InfinitePlane ip = new InfinitePlane(new Point3D(0, -3, 0), new Vector3D(0,1,0), mat4);
		InfinitePlane ip2 = new InfinitePlane(new Point3D(0,-2,0), new Vector3D(0,1,0), mat5);
		
		Light light = new Light(new Point3D(0,3,0),0.01, new Color(255,255,255));
		
		scene.add(s);
		scene.add(s2);
		scene.add(s3);
		scene.add(s4);
		scene.add(light);
		//scene.add(ip);
		scene.add(ip2);
		return scene;
	}
	
	
	private static Scene scene2() {
		Scene scene = new Scene();
		
		ColorProperties ambient1 = new ColorProperties(0.2, 0, 0);
		ColorProperties ambient2 = new ColorProperties(0, 0.15, 0);
		ColorProperties ambient3 = new ColorProperties(0.2,0,0.1);
		
		ColorProperties diffuse1 = new ColorProperties(0.35, 0, 0);
		ColorProperties diffuse2 = new ColorProperties(0, 0.2, 0);
		ColorProperties diffuse3 = new ColorProperties(0.1,0.1,0.1);
		
		ColorProperties specular1 = new ColorProperties(1, 1, 1);
		ColorProperties specular2 = new ColorProperties(1, 1, 1);
		ColorProperties specular3 = new ColorProperties(0.3,0.3,0.3);
		
		Material mat1 = new Material(ambient1, diffuse1, specular1);
		Material mat2 = new Material(ambient2, diffuse2, specular2);
		Material mat3 = new Material(ambient3, diffuse3, specular3);
		
		
		Sphere s1 = new Sphere(new Point3D(-2, 0.4, 8), 2, mat1);
		Sphere s2 = new Sphere(new Point3D(2.2, 0, 5), 2, mat2);
		
		Light light = new Light(new Point3D(0, -3, 1), 2, new Color(255,255,255));
		Light light2 = new Light(new Point3D(3, 1, 1), 1, new Color(255,255,255));
		
		InfinitePlane ip2 = new InfinitePlane(new Point3D(0,-2,0), new Vector3D(0,1,0.2), mat3);
		
		scene.add(s1);
		scene.add(s2);
		//scene.add(light);
		scene.add(light2);
		scene.add(ip2);
		return scene;
	}
	
	
	private static Scene scene3() {
		Scene scene = new Scene();
		
		ColorProperties ambient1 = new ColorProperties(0.2, 0, 0);
		ColorProperties diffuse1 = new ColorProperties(0.35, 0, 0);
		ColorProperties specular1 = new ColorProperties(1, 1, 1);
		
		ColorProperties ambient = new ColorProperties(0, 1, 0);
		ColorProperties diffuse = new ColorProperties(0.1,0.1,0.1);
		ColorProperties specular = new ColorProperties(0.3,0.3,0.3);
		
		ColorProperties ambient3 = new ColorProperties(0.2,0,0.1);
		ColorProperties diffuse3 = new ColorProperties(0.1,0.1,0.1);
		ColorProperties specular3 = new ColorProperties(1,1,1);
		
		Material mat1 = new Material(ambient1, diffuse1, specular1);
		Material mat = new Material(ambient, diffuse, specular);
		Material mat3 = new Material(ambient3, diffuse3, specular3);
		
		Sphere s1 = new Sphere(new Point3D(-2, 0.4, 1), 0.5, mat1);
		
		InfinitePlane ip = new InfinitePlane(new Point3D(0,0,-2), new Vector3D(0,0,1), mat);
		InfinitePlane ip2 = new InfinitePlane(new Point3D(0,0,2), new Vector3D(0,0,1), mat3);
		Light light2 = new Light(new Point3D(0, 2, 0), 0.5, new Color(255,255,255));
		
		scene.add(light2);
		scene.add(ip);
		scene.add(ip2);
		scene.add(s1);
		
		
		return scene;
	}
}