package marioSokoban;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import marioSokobanEditor.EditorFrame;
import marioSokobanGame.GameFrame;

@SuppressWarnings("serial")
public class MenuFrame extends JFrame implements ActionListener {
	
	JButton cmdGame = new JButton(" Играть ");
	JButton cmdEditor = new JButton(" Конструктор ");
	JPanel pan = new JPanel();
	JLabel p1 = new JLabel ("s-сохранить; "	+ "r- начать сначала");
	
	//GUI меню
	public MenuFrame(){
		this.setTitle("Меню");
		this.setSize(765, 780);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		pan.add(cmdEditor);
		pan.add(cmdGame);
		pan.add(p1);
		p1.setHorizontalAlignment(JLabel.CENTER);
		
		cmdGame.addActionListener(this);
		cmdEditor.addActionListener(this);
		
		this.setContentPane(pan);
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == cmdGame){
			GameFrame frm = new GameFrame();
			dispose();
		}
		else if (arg0.getSource() == cmdEditor){
			EditorFrame frm = new EditorFrame();
			dispose();
		}
		
	}

}
