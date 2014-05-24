package com.nes.raytracer.utils.exceptions;

@SuppressWarnings("serial")
public class NoQuadraticSolutionException extends Exception {

	public NoQuadraticSolutionException() {
		super();
	}
	
	public NoQuadraticSolutionException(String message) {
		super(message);
	}
	
	public NoQuadraticSolutionException(Throwable cause) {
		super(cause);
	}
	
	public NoQuadraticSolutionException(String message, Throwable cause) {
		super(message, cause);
	}
}
