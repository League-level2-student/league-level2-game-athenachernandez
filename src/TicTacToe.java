import java.awt.Dimension;

import javax.swing.JFrame;

public class TicTacToe {
	JFrame frame;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	TicTacToeGamePanel panel;
	
	public TicTacToe() {
		frame = new JFrame();
		panel = new TicTacToeGamePanel();
	}
	
	void setup() {
		frame.add(panel);
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addKeyListener(panel);
		frame.addMouseListener(panel);
		frame.pack();
	}
	
	public static void main(String[] args) {
		TicTacToe ttt = new TicTacToe();
		ttt.setup();
	}
}
