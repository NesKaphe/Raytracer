package com.nes.raytracer;

import com.nes.raytracer.utils.geometrics.Hit;
import com.nes.raytracer.utils.geometrics.Point3D;
import com.nes.raytracer.utils.geometrics.Ray;
import com.nes.raytracer.utils.geometrics.SphereGeometry;
import com.nes.raytracer.utils.geometrics.Vector3D;

public class Main {

	public static void main(String[] args) {
		SphereGeometry sphere = new SphereGeometry(new Point3D(0,0,0), 2);
		Ray ray = new Ray(new Point3D(0,0,0), new Vector3D(0,0,1));
		
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
	}
}