package com.nes.raytracer.utils.exceptions;

@SuppressWarnings("serial")
public class NotOnSurfaceException extends Exception {
	
	public NotOnSurfaceException() {
		super();
	}
	
	public NotOnSurfaceException(String message) {
		super(message);
	}
	
	public NotOnSurfaceException(Throwable cause) {
		super(cause);
	}
	
	public NotOnSurfaceException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
