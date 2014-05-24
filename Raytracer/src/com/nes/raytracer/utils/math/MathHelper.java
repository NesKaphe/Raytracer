package com.nes.raytracer.utils.math;

import com.nes.raytracer.utils.exceptions.NoQuadraticSolutionException;

public class MathHelper {

	
	private MathHelper() {}
	
	
	public static double discriminant(double a, double b, double c) {
		return Math.pow(b, 2) - 4 * a * c;
	}
	
	
	
	public static QuadraticResult solveQuadratic(double a, double b, double c) throws NoQuadraticSolutionException {
		double discriminant = MathHelper.discriminant(a, b, c);
		
		if (discriminant < 0) {
			throw new NoQuadraticSolutionException("No solution");
		}
		
		if (discriminant == 0) {
			return new ConcreteQuadraticResult(- 0.5 * b / a);
		}
		
		double sqrtDiscriminant = Math.sqrt(discriminant);
		
		return new ConcreteQuadraticResult((-b - sqrtDiscriminant) / (2*a), (-b + sqrtDiscriminant) / (2*a));
	}
	
	
	public interface QuadraticResult {
		
		public double getX1();
		
		public double getX2();
		
		public double getMin();
		
	}
	
	
	private static final class ConcreteQuadraticResult implements QuadraticResult {
		
		private double x1;
		private double x2;
		
		
		public ConcreteQuadraticResult(double x0) {
			this(x0, x0);
		}
		
		public ConcreteQuadraticResult(double x1, double x2) {
			this.x1 = x1;
			this.x2 = x2;
		}
		
		public double getX1() {
			return this.x1;
		}
		
		public double getX2() {
			return this.x2;
		}
		
		public double getMin() {
			return Math.min(this.x1,  this.x2);
		}
	}
}
