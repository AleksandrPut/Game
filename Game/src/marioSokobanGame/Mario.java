package marioSokobanGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Mario {
	
	int x, y;
	String MarioDir = "BAS";
	Image Mario;
	ImageIcon iMarioBas = new ImageIcon("Images/mario_before.gif");
	ImageIcon iMarioDroite = new ImageIcon("Images/mario_right.gif");
	ImageIcon iMarioGauche = new ImageIcon("Images/mario_left.gif");
	ImageIcon iMarioHaut = new ImageIcon("Images/mario_ago.gif");
	
	
	public Mario(int Startx, int Starty){
		x = Startx;
		y = Starty;
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
	
	public String getDir(){
		return MarioDir;
	}
	
	public void setX(int newX){
		this.x = newX;
	}
	
	public void setY(int newY){
		this.y = newY;
	}
	
	public void setDir(String newDir){
		this.MarioDir = newDir;
	}
	
	public Image getImage(){
		if (MarioDir == "BAS"){
			Mario = iMarioBas.getImage();
		}
		
		else if (MarioDir == "DROITE"){
			Mario = iMarioDroite.getImage();
		}
		
		else if (MarioDir == "GAUCHE"){
			Mario = iMarioGauche.getImage();
		}
		
		else if (MarioDir == "HAUT"){
			Mario = iMarioHaut.getImage();
		}
		return Mario;
	}
	public void Move(){
		if (MarioDir == "BAS"){
			this.y += 34;
		}
		
		else if (MarioDir == "DROITE"){
			this.x += 34;
		}
		
		else if (MarioDir == "GAUCHE"){
			this.x -= 34;
		}
		
		else if (MarioDir == "HAUT"){
			this.y -= 34;
		}
	}
	
	
	
}
