package awesomePackage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DisplayImage {
	
	private JFrame frame;
	
	public static void main(String[] args) {
		new DisplayImage().run();
		
		
	}

	private void run() {
		frame = new JFrame("AWESOMEFRAME!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(100, 100);
		ImageManager im = new ImageManager();
		JLabel coin = new JLabel(new ImageIcon(im.newImage("res/coin_00.png")));
		frame.add(coin);
		frame.setVisible(true);
	}

}
