package marioSokobanGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Mur {
	
	int x,y;
	Image Mur;
	
	
	public Mur(int Startx, int Starty){
		x = Startx;
		y = Starty;
		
		ImageIcon iMur = new ImageIcon ("Images/wall.jpg");
		Mur = iMur.getImage();
	}
	
	public Rectangle getBounds(){
		Rectangle Box = new Rectangle (x, y, 34, 34);
		return Box;
	}
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	public Image getImage(){
		return Mur;
	}
	
}
