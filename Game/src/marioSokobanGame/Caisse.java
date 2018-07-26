package marioSokobanGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Caisse {
	int x,y;
	String Etat = "NORMALE";
	Image Caisse;
	ImageIcon iCaisseNormale = new ImageIcon("Images/box.jpg");
	ImageIcon iCaisseObjectif = new ImageIcon("Images/box_ok.jpg");
	boolean caissejuste = false;
	
	
	public Caisse(int Startx, int Starty){
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
	public void setX(int newX){
		this.x = newX;
	}
	
	public void setY(int newY){
		this.y = newY;
	}
	
	public boolean getJuste(){
		return caissejuste;
		
	}
	
	public void setJuste(boolean newJuste){
		this.caissejuste = newJuste;
	}
	
	public void setEtat(String newEtat){
		this.Etat = newEtat;
	}
	public Image getImage(){
		if (Etat == "NORMALE"){
			Caisse = iCaisseNormale.getImage();
		}
		else if(Etat == "OBJECTIF"){
			Caisse = iCaisseObjectif.getImage();
		}
		return Caisse;
	}
	
	
	
	
	
}
