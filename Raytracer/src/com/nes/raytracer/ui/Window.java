package com.nes.raytracer.ui;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.JFrame;

public class Window extends JFrame {
	
	private DrawSurface surface;
	
	public Window() {
		super("Raytracer by Alain DIAS");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.surface = new DrawSurface();
		this.getContentPane().setLayout(new BorderLayout());
		this.add(this.surface, BorderLayout.CENTER);
		
		this.pack();
		
		this.setVisible(true);
	}
	
	
	public void setNewDrawingBuffer(Image image) {
		this.surface.setBuffer(image);
	}
	
	
	public void refresh() {
		this.surface.repaint();
	}
}
