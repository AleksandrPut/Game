package marioSokobanGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JPanel;

import marioSokoban.MenuFrame;

public class GameBoard extends JPanel implements KeyListener{
	
	
	
	String Game[][] = new String [24][24];
	int Level = 1;
	private static ArrayList<Mur> Murs;
	private static ArrayList<Objectif> Objectifs;
	private static ArrayList<Caisse> Caisses;
	Mur mur;
	Mario mario;
	Caisse caisse;
	Objectif objectif;
	
	Font levelFont = new Font("SansSerif", Font.BOLD, 20);
	FileReader fr;
	
	
	public GameBoard(Frame gf){
		ChargerLevel();
		
		
		
		setFocusable(true);
		addKeyListener(this);
	}
	
	public void ChargerLevel(){
		try{
			fr = new FileReader("Maps/level" + Level + ".level");
			int x = 0, y = 0, i= 0;
			
			Murs = new ArrayList<Mur>();
			Caisses = new ArrayList<Caisse>();
			Objectifs = new ArrayList<Objectif>();
			
			while ((i = fr.read()) != -1){
				char strImg = (char) i;
				
				if (strImg == '0'){
					Game[x][y] = "MUR";
					mur = new Mur(x * 34, y * 34);
					Murs.add(mur);
				}
				else if (strImg =='1'){
					Game[x][y] = "MARIO";
					mario = new Mario(x * 34, y * 34);
				}
				else if (strImg =='2'){
					Game[x][y] = "CAISSE";
					caisse = new Caisse(x * 34, y * 34);
					Caisses.add(caisse);
				}
				else if (strImg =='3'){
					Game[x][y] = "OBJECTIOF";
					objectif = new Objectif(x * 34, y * 34);
					Objectifs.add(objectif);
				}
				else if (strImg ==' '){
					Game[x][y] = null;
				}
				else if (strImg =='\r'|| strImg == '\n'){
					x--;
				}
				if (x == 23){
					y++;
					x = 0;
				}
				else{
					x++;
				}
				
			}	
				
		}
		catch (Exception ex){}
		repaint();
	}
	
	
	public void paint (Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		for ( int i = 0; i < Murs.size(); i++){
			mur = (Mur) Murs.get(i);
			g2d.drawImage(mur.getImage(), mur.getX(), mur.getY(), null);
		}
		for ( int i = 0; i < Objectifs.size(); i++){
			objectif = (Objectif) Objectifs.get(i);
			g2d.drawImage(objectif.getImage(), objectif.getX(), objectif.getY(), null);
		}
		for ( int i = 0; i < Caisses.size(); i++){
			caisse = (Caisse) Caisses.get(i);
			g2d.drawImage(caisse.getImage(), caisse.getX(), caisse.getY(), null);
		}
		try{
			g2d.drawImage(mario.getImage(), mario.getX(), mario.getY(), null);
		}
		catch(Exception ex){}
		
		g.setColor(Color.BLACK);
		g.setFont(levelFont);
		g.drawString("LEVEL : " + Level, 10, 25);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		int Touche = arg0.getKeyCode();
		
		if (Touche == KeyEvent.VK_DOWN){
			mario.setDir("BAS");
			mario.Move();
			CheckCollision();
		}
		else if (Touche == KeyEvent.VK_UP){
			mario.setDir("HAUT");
			mario.Move();
			CheckCollision();
		}
		else if (Touche == KeyEvent.VK_RIGHT){
			mario.setDir("DROITE");
			mario.Move();
			CheckCollision();
		}
		else if (Touche == KeyEvent.VK_LEFT){
			mario.setDir("GAUCHE");
			mario.Move();
			CheckCollision();
		}
		else if (Touche == KeyEvent.VK_R){
			ChargerLevel();
		}
		
		
		
		repaint();
		VerifierLevelFini();
	}


	

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	//проверка уровня завершонности
	public void VerifierLevelFini(){
		int nbCaisseJuste = 0;
		
		for (int i = 0; i < Caisses.size(); i++){
			caisse = (Caisse) Caisses.get(i);
			
			if (caisse.Etat == "OBJECTIF"){
				nbCaisseJuste++;				
			}
			
			if (nbCaisseJuste == Caisses.size()){
				Level++;
				ChargerLevel();
			}
		}
	}
	
	//проверка столкновения
	public void CheckCollision() {
		Rectangle marioRec;
		marioRec = mario.getBounds();
		
		for (int i = 0; i < Murs.size(); i++){
			mur = (Mur) Murs.get(i);
			Rectangle murRec = mur.getBounds();
			
			if (marioRec.intersects(murRec)){
				if (mario.getDir() == "BAS"){
					mario.setY(mario.getY() - 34);
				}
				else if(mario.getDir() == "HAUT"){
					mario.setY(mario.getY() + 34);
				}
				
				else if(mario.getDir() == "GAUCHE"){
					mario.setX(mario.getX() + 34);
				}
				
				else if(mario.getDir() == "DROITE"){
					mario.setX(mario.getX() - 34);
				}
			}
		}
		
		for (int i = 0; i < Caisses.size(); i++){
			caisse = (Caisse) Caisses.get(i);
			Rectangle caisseRec = caisse.getBounds();
			
			if (marioRec.intersects(caisseRec)){
				if (mario.getDir() == "BAS"){
					if (Game[caisseRec.x / 34][(caisseRec.y + 34) / 34] !="MUR"){
						caisse.setY(caisse.getY() + 34);
					}
					else if(Game[caisseRec.x / 34][(caisseRec.y + 34) / 34] == "MUR"){
						mario.setY(mario.getY() - 34);
					}
				}
				
				else if(mario.getDir() == "HAUT"){
					if(Game[caisseRec.x / 34][(caisseRec.y - 34) / 34] != "MUR"){
					caisse.setY(caisse.getY() - 34);
				}
					else if (Game[caisseRec.x / 34][(caisseRec.y - 34) / 34] == "MUR")
					{
						mario.setY(mario.getY() + 34);
					}
				}
				else if(mario.getDir() == "GAUCHE"){
					if(Game[(caisseRec.x - 34) / 34][caisseRec.y / 34] != "MUR"){
						caisse.setX(caisse.getX() - 34);
					}
					else if (Game[(caisseRec.x - 34) / 34][caisseRec.y / 34] == "MUR")
					{
						mario.setX(mario.getX() + 34);
					}
				}
				
				else if(mario.getDir() == "DROITE"){
					if(Game[(caisseRec.x + 34) / 34][caisseRec.y / 34] != "MUR"){
						caisse.setX(caisse.getX() + 34);
					}
					else if(Game[(caisseRec.x + 34) / 34][caisseRec.y / 34] == "MUR")
					{
						mario.setX(mario.getX() - 34);
					}
				}
			}
		}
		for (int i = 0; i < Objectifs.size(); i++){
			objectif = (Objectif) Objectifs.get(i);
			Rectangle objectifRec = objectif.getBounds();
			for (int j = 0; j < Caisses.size(); j++){
				caisse = (Caisse) Caisses.get(i);
				Rectangle caisseRec = caisse.getBounds();
				
				if(caisseRec.intersects(objectifRec) && !caisse.getJuste() && !objectif.getDessus()){
					caisse.setEtat("OBJECTIF");
					caisse.setJuste(true);
					objectif.setDessus(true);
				}
				else if (!caisseRec.intersects(objectifRec) && caisse.getJuste() && objectif.getDessus()){
					caisse.setEtat("NORMALE");
					caisse.setJuste(false);
					objectif.setDessus(false);
				}
				
			}
		}
	}
}
