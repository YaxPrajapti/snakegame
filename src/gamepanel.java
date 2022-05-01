import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

public class gamepanel extends JPanel implements ActionListener {
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_HIGHT * SCREEN_WIDTH) / UNIT_SIZE;
	static final int DELAY = 70;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int initial_bodyparts = 6;
	int ratsEaten;
	int ratX;
	int ratY;
	char directioin = 'R';
	boolean running = false;
	Timer timer;
	Random random;

	public gamepanel() {
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_HIGHT, SCREEN_WIDTH));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MykeyAdapter());
		StartGame();
	}

	public void StartGame() {
		newRat();
		running = true;
		timer = new Timer(DELAY, this);
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Draw(g);
	}

	public void Draw(Graphics g) {
		if (running) {
//			for (int i = 0; i < SCREEN_HIGHT / UNIT_SIZE; i++) {
//				g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HIGHT);
//				g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
//			}
			g.setColor(Color.orange);
			g.fillOval(ratX, ratY, UNIT_SIZE, UNIT_SIZE);

			for (int i = 0; i < initial_bodyparts; i++) {
				if (i == 0) {
					g.setColor(Color.BLUE);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				} else {
//					g.setColor(new Color(52, 159, 235));
					g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			g.setColor(Color.white);
			g.setFont(new Font("Monospace",Font.BOLD,25));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: " + ratsEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + ratsEaten))/2,g.getFont().getSize());
		}
		else {
			gameOver(g);
		}

	}

	public void newRat() {
		ratX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
		ratX = random.nextInt((int) (SCREEN_HIGHT / UNIT_SIZE)) * UNIT_SIZE;
	}

	public void move() {
		for (int i = initial_bodyparts; i > 0; i--) {
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}
		switch (directioin) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
	}

	public void checkrats() {
		if ((x[0] == ratX) && (y[0] == ratY)) {
			initial_bodyparts++;
			ratsEaten++;
			newRat();
		}
	}

	public void checkcollisions() {
		for (int i = initial_bodyparts; i > 0; i--) {
			if ((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
			}
		}
		// head touches left border.
		if (x[0] < 0) {
			running = false;
		}
		// head touches right border.
		if (x[0] > SCREEN_WIDTH) {
			running = false;
		}
		// head touches top border.
		if (y[0] > SCREEN_HIGHT) {
			running = false;
		}
		// head touches bottom.
		if (y[0] < 0) {
			running = false;
		}
		if (running == false) {
			timer.stop();
		}
	}

	public void gameOver(Graphics g) {
		//score
		g.setColor(Color.white);
		g.setFont(new Font("Monospace",Font.BOLD,25));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: " + ratsEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + ratsEaten))/2,g.getFont().getSize());
		//game over
		g.setColor(Color.white);
		g.setFont(new Font("Monospace",Font.BOLD,50));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over! Try again.", (SCREEN_WIDTH - metrics2 .stringWidth("Game Over! Try again."))/2,SCREEN_HIGHT / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (running) {
			move();
			checkrats();
			checkcollisions();
		}
		repaint();

	}

	public class MykeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if (directioin != 'R') {
					directioin = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (directioin != 'L') {
					directioin = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if (directioin != 'D') {
					directioin = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if (directioin != 'U') {
					directioin = 'D';
				}
				break;
			}
		}
	}
}
