package marioSokobanEditor;

import javax.swing.JFrame;
//Gui ��� ��������� ����
public class EditorFrame extends JFrame {
	public EditorFrame(){
		this.setTitle("�������� ����");
		this.setSize(765, 780);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new EditorBoard(this));
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
