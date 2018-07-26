package marioSokobanGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Objectif {
	int x, y;
	Image Objectif;
	boolean caisseDessus = false;
	
	
	public Objectif(int Startx, int Starty){
		x = Startx;
		y = Starty;
		
		ImageIcon iObjectif = new ImageIcon("Images/exit.png");
		Objectif = iObjectif.getImage();
	}
	public Rectangle getBounds(){
		Rectangle Box = new Rectangle(x, y, 34, 34);
		return Box;
	}
	
	public boolean getDessus(){
		return caisseDessus;
	}
	
	public void setDessus(boolean newDessus){
		this.caisseDessus = newDessus;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Image getImage(){
		return Objectif;
	}
	
	
}
