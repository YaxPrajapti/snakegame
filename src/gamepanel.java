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
	static final int DELAY = 75;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int initial_bodyparts = 6;
	int ratsEaten;
	int ratX;
	int ratY;
	char directioin = 'U';
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

	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		Draw(g);
	}

	public void Draw(Graphics g) {
		for (int i = 0; i < SCREEN_HIGHT / UNIT_SIZE; i++) {
			g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HIGHT);
			g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
		}
		g.setColor(Color.MAGENTA);
		g.fillOval(ratX, ratY, UNIT_SIZE, UNIT_SIZE);
	}

	public void newRat() {
		ratX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE)) * UNIT_SIZE;
		ratX = random.nextInt((int)(SCREEN_HIGHT/UNIT_SIZE)) * UNIT_SIZE;
	}

	public void move() {

	}

	public void checkrats() {

	}

	public void checkcollisions() {

	}

	public void gameOver(Graphics g) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public class MykeyAdapter extends KeyAdapter {
		public void keyPress(KeyEvent e) {

		}
	}
}
