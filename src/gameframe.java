import javax.swing.JFrame;

public class gameframe extends JFrame {
	gameframe()
	{
		gamepanel panel = new gamepanel();
		this.add(panel);
		this.setTitle("Snake Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
	