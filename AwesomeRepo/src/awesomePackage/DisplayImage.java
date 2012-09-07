package awesomePackage;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

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
		frame.setSize(300, 300);
		frame.setLayout(new FlowLayout());
		
		BufferedImage coinImg = ImageManager.newImage("res/Spritesheet.png");
		BufferedImage bar1 = ImageManager.getAnimationFrame(coinImg, 0, 0, 240, 34);
		BufferedImage bar2 = ImageManager.getAnimationFrame(coinImg, 1, 0, 240, 34);
		BufferedImage bar3 = ImageManager.getAnimationFrame(coinImg, 2, 0, 240, 34);
		
		
		JLabel coin = new JLabel(new ImageIcon(bar1));
		JLabel coin2 = new JLabel(new ImageIcon(bar2));
		JLabel coin3 = new JLabel(new ImageIcon(bar3));
		
		frame.add(coin);
		frame.add(coin2);
		frame.add(coin3);
		frame.setVisible(true);
//		frame.pack();
	}

}
