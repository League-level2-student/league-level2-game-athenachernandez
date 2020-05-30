import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TicTacToeGamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font subTitleFont;
	Timer frameDraw;
	int[][] boxes;
	String user1Pick;
	TTTX xPlayer;
	TTTO oPlayer;
	String user2Pick;
	ArrayList<TTTO> oList = new ArrayList<TTTO>();
	ArrayList<TTTX> xList = new ArrayList<TTTX>();
	boolean xTurn;
	int ans1[] = new int[] { 0, 1, 2 };
	int ans2[] = new int[] { 3, 4, 5 };
	int ans3[] = new int[] { 6, 7, 8 };
	int ans4[] = new int[] { 0, 3, 6 };
	int ans5[] = new int[] { 1, 4, 7 };
	int ans6[] = new int[] { 2, 5, 8 };
	int ans7[] = new int[] { 0, 4, 8 };
	int ans8[] = new int[] { 2, 4, 6 };
	boolean winnerFound;

	TicTacToeGamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 48);
		subTitleFont = new Font("Arial", Font.PLAIN, 29);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		boxes = new int[3][3];
	}

	void updateMenuState() {

	}

	void updateGameState() {

	}

	void updateEndState() {

	}

	void getUserPick() {
		user1Pick = JOptionPane.showInputDialog("Do you want Xs or Os? X starts first.");
		if (user1Pick.equalsIgnoreCase("x")) {
			xTurn = true;
		} else {
			xTurn = false;
		}

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, TicTacToe.WIDTH, TicTacToe.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("TIC-TAC-TOE", 105, 100);
		g.setFont(subTitleFont);
		g.setColor(Color.WHITE);
		g.drawString("Press ENTER to start.", 120, 300);
		g.setFont(subTitleFont);
		g.setColor(Color.WHITE);
		g.drawString("Press SPACE for instructions.", 70, 500);
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.PINK);
		g.fillRect(0, 0, TicTacToe.WIDTH, TicTacToe.HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(188, 213, 2, 375);
		g.setColor(Color.BLACK);
		g.fillRect(313, 213, 2, 375);

		g.setColor(Color.BLACK);
		g.fillRect(63, 338, 375, 2);
		g.setColor(Color.BLACK);
		g.fillRect(63, 463, 375, 2);

		for (TTTO o : oList) {
			o.draw(g);
		}
		for (TTTX x : xList) {
			x.draw(g);
		}
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, TicTacToe.WIDTH, TicTacToe.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("GAME OVER", 105, 100);
		g.setFont(subTitleFont);
		g.setColor(Color.WHITE);
		g.drawString("Press ENTER to play again.", 75, 300);
		g.setFont(subTitleFont);
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END) {
				xList = new ArrayList<TTTX>();
				oList = new ArrayList<TTTO>();
				TicTacToe ticTacToe = new TicTacToe();
				ticTacToe.panel = new TicTacToeGamePanel();
				ticTacToe.frame.add(ticTacToe.panel);
				winnerFound = false;
				currentState = MENU;
			} else {
				currentState++;
			}
			if (currentState == GAME) {
				getUserPick();
			}
			
		}
		if (currentState == MENU) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				JOptionPane.showMessageDialog(null, "There are 2 players. One is X, the other is O. "
						+ "First person to get 3 Xs or Os in a row wins!");
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	void displayTurn(int x, int y, int position) {
		if (xTurn) {
			xList.add(new TTTX(x, y, 100, 100, position));
			xTurn = false;
		} else if (!xTurn) {
			oList.add(new TTTO(x, y, 100, 100, position));
			xTurn = true;
		}
		checkWinner(xTurn, xList, oList);
	}

	void checkWinner(boolean xTurn, ArrayList<TTTX> xList, ArrayList<TTTO> oList) {
		ArrayList<Integer> currentPlayerPositions = new ArrayList<Integer>();
		if (!xTurn) {
			for (TTTX x : xList) {
				currentPlayerPositions.add(x.position);
			}
		} else {
			for (TTTO o : oList) {
				currentPlayerPositions.add(o.position);
			}
		}

		if (!winnerFound) {
			checkWinScenario(ans1, currentPlayerPositions);
		}

		if (!winnerFound) {
			checkWinScenario(ans2, currentPlayerPositions);
		}

		if (!winnerFound) {
			checkWinScenario(ans3, currentPlayerPositions);
		}

		if (!winnerFound) {
			checkWinScenario(ans4, currentPlayerPositions);
		}

		if (!winnerFound) {
			checkWinScenario(ans5, currentPlayerPositions);
		}

		if (!winnerFound) {
			checkWinScenario(ans6, currentPlayerPositions);
		}

		if (!winnerFound) {
			checkWinScenario(ans7, currentPlayerPositions);
		}

		if (!winnerFound) {
			checkWinScenario(ans8, currentPlayerPositions);
		}

		if (winnerFound) {
			if (xTurn) {
				JOptionPane.showMessageDialog(null, "O won the game!");
			} else {
				JOptionPane.showMessageDialog(null, "X won the game!");
			}
			currentState = END;

		}
		repaint();
	}

	boolean checkWinScenario(int[] ans, ArrayList<Integer> currentPlayerPositions) {

		winnerFound = true;

		for (int i = 0; i < ans.length; i++) {
			if (!currentPlayerPositions.contains(ans[i])) {
				winnerFound = false;
				break;
			}
		}
		return winnerFound;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		int position = 0;
		if (x > 63 && x < 188 && y > 238 && y < 363) {
			position = 0;
			displayTurn(75, 220, position);
		} else if (x >= 188 && x < 314 && y > 238 && y < 363) {
			position = 1;
			displayTurn(200, 220, position);
		} else if (x >= 314 && x < 437 && y > 238 && y < 363) {
			position = 2;
			displayTurn(325, 220, position);
		} else if (x > 63 && x < 188 && y >= 363 && y < 488) {
			position = 3;
			displayTurn(75, 350, position);
		} else if (x >= 188 && x < 314 && y >= 363 && y < 488) {
			position = 4;
			displayTurn(200, 350, position);
		} else if (x >= 314 && x < 437 && y >= 363 && y < 488) {
			position = 5;
			displayTurn(325, 350, position);
		} else if (x > 63 && x < 188 && y >= 488 && y < 612) {
			position = 6;
			displayTurn(75, 480, position);
		} else if (x >= 188 && x < 314 && y >= 488 && y < 612) {
			position = 7;
			displayTurn(200, 480, position);
		} else if (x >= 314 && x < 437 && y >= 488 && y < 612) {
			position = 8;
			displayTurn(325, 480, position);
		} else {
			JOptionPane.showMessageDialog(null, "Please click one of the squares.");
		}
		System.out.println(position);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
