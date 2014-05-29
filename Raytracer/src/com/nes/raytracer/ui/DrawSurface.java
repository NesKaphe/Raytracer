package com.nes.raytracer.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class DrawSurface extends JPanel {

	private Image backBuffer;
	
	public DrawSurface() {
		super();
		this.setPreferredSize(new Dimension(1600,900));
		this.setBackground(Color.white);
	}
	
	
	public void initBuffer() {

		if(this.backBuffer == null) {
			this.backBuffer = this.createImage(this.getWidth(), this.getHeight());
		}

		Graphics2D g = (Graphics2D) this.backBuffer.getGraphics();
		
		g.setColor(this.getBackground());
		g.fillRect(0, 0, this.getSize().width, this.getSize().height);
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage((BufferedImage)this.backBuffer, null, 0, 0);
	}
	
	
	public void setBuffer(Image image) {
		this.backBuffer = image;
	}
}
