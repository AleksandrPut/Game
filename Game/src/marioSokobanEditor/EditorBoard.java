package marioSokobanEditor;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import marioSokoban.MenuFrame;

public class EditorBoard extends JPanel implements MouseListener, MouseMotionListener,MouseWheelListener, KeyListener{
	String Editeur[][] = new String[24][24];
	String ImageSelect[] = {"WALL","BOX","MARIO","EXIT"};
	String ImageCourante = "WALL";
	int Mx, My;
	int indexInc = 0;
	Image Mario;
	Image Mur;
	Image Caisse;
	Image Objectif;
	FileWriter fw;
	FileReader fr;
	Frame Eframe;
	
	public EditorBoard(Frame ef){
		ImageIcon iMario = new ImageIcon("Images/mario_before.gif");
		Mario = iMario.getImage();
		
		ImageIcon iMur = new ImageIcon("Images/wall.jpg");
		Mur = iMur.getImage();
		
		ImageIcon iCaisse = new ImageIcon("Images/box.jpg");
		Caisse = iCaisse.getImage();
		
		ImageIcon iObjectif = new ImageIcon("Images/exit.png");
		Objectif = iObjectif.getImage();
		
		Eframe = ef;
		
		setFocusable(true);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		addKeyListener(this);
		
		
	}
	public void paint (Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		for (int i= 0; i <= 23; i++){
			for (int j = 0; j <= 23; j++){
				if (Editeur[i][j]== "WALL"){
					g2d.drawImage(Mur, i * 34, j * 34, null);
				}
				if (Editeur[i][j]== "MARIO"){
					g2d.drawImage(Mario, i * 34, j * 34, null);	
				}	
					
				if (Editeur[i][j]== "BOX"){
					g2d.drawImage(Caisse, i * 34, j * 34, null);	
				}	
				if (Editeur[i][j]== "EXIT"){
					g2d.drawImage(Objectif, i * 34, j * 34, null);	
					
					
				}
			}
		}
		if (ImageCourante == "WALL"){
			g2d.drawImage(Mur, Mx, My, null);
		}
		else if (ImageCourante == "BOX"){
			g2d.drawImage(Caisse, Mx, My, null);
		}
		else if (ImageCourante == "MARIO"){
			g2d.drawImage(Mario, Mx, My, null);
		}
		else if (ImageCourante == "EXIT"){
			g2d.drawImage(Objectif, Mx, My, null);
		}
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		char key =arg0.getKeyChar();
		//редакто сохранение уровня
		if (key =='s'){
			try{
				fw = new FileWriter (JOptionPane.showInputDialog(null, "Введите путь резервного копирования:", "Редактор карт", JOptionPane.QUESTION_MESSAGE));
				for (int i = 0; i < 24; i++){
					for (int j = 0; j < 24; j++){
						if (Editeur[j][i] == "WALL"){
							fw.write("0");
						}
						else if (Editeur[j][i] == "MARIO"){
							fw.write("1");
						}
						else if (Editeur[j][i] == "BOX"){
							fw.write("2");
						}
						else if (Editeur[j][i] == "EXIT"){
							fw.write("3");
						}
						else if (Editeur[j][i] == null){
							fw.write(" ");
						}
					}
					fw.write("\r\n");
				}
				fw.close();
			}
			catch (Exception ex){}
		}
		//редактор созданных уровней
		else if (key == 'l'){
			try{
				fr = new FileReader (JOptionPane.showInputDialog(null, "Введите путь резервного копирования:", "Редактор карт", JOptionPane.QUESTION_MESSAGE));
				int i = 0;
				int x = 0, y = 0;
				
				while ((i = fr.read()) != -1){
					char strImg = (char) i;
					
					if (strImg == '0'){
						Editeur[x][y] = "WALL";
					}
					else if (strImg =='1'){
						Editeur[x][y] = "MARIO";
					}
					else if (strImg =='2'){
						Editeur[x][y] = "BOX";
					}
					
					else if (strImg =='3'){
						Editeur[x][y] = "EXIT";
					}
					else if (strImg ==' '){
						Editeur[x][y] = null;
					}
					else if (strImg =='\r' || strImg =='\n'){
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
		}
		else if (key == KeyEvent.VK_ESCAPE){
		MenuFrame frm = new MenuFrame();
		Eframe.dispose();
	}
	}
	


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		int rot = arg0.getWheelRotation();
		
		if (rot < 0){
			if (indexInc > 0){
				indexInc--;
			}
		}
		else if (rot > 0){
			if (indexInc < 3){
				indexInc++;
			}
		}
		
		ImageCourante = ImageSelect[indexInc];
		
		repaint();
	}


	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	
	public void mouseMoved(MouseEvent arg0) {
		Mx = arg0.getX() - 17;
		My = arg0.getY() - 17;
		
		repaint();
		
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	
	public void mouseReleased(MouseEvent arg0) {
		int x = arg0.getX() / 34;
		int y = arg0.getY() / 34;
		
		if (arg0.getButton()== MouseEvent.BUTTON1){
			Editeur[x][y] = ImageCourante;
		}
		else if (arg0.getButton() == MouseEvent.BUTTON3){
			Editeur[x][y] = null;
		}
		
	}
}
