package marioSokobanGame;

import javax.swing.JFrame;

import marioSokobanEditor.EditorBoard;
//GUI для игры
public class GameFrame extends JFrame{
	public GameFrame(){
		this.setTitle("Игра");
		this.setSize(765, 780);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new GameBoard(this));
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
}
