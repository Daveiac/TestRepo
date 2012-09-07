package awesomePackage;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;

public class DisplayImage {
	
	private JFrame frame;
	
	public static void main(String[] args) {
		new DisplayImage().run();
		
		
	}

	private void run() {
		frame = new JFrame("AWESOMEFRAME!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(100, 100);
		frame.setLayout(new FlowLayout());
		
		ImageManager im = new ImageManager();
		BufferedImage coinImg = im.newImage("res/Spritesheet.png");
		int w = coinImg.getWidth();
		int h = coinImg.getHeight();
		BufferedImage bar1 = im.crop(coinImg, 0, 0, w/3, h);
		BufferedImage bar2 = im.crop(coinImg, w/3, 0, w/3, h);
		BufferedImage bar3 = im.crop(coinImg, 2*(w/3), 0, w/3, h);
		
		
		JLabel coin = new JLabel(new ImageIcon(bar1));
		JLabel coin2 = new JLabel(new ImageIcon(bar2));
		JLabel coin3 = new JLabel(new ImageIcon(bar3));
		
		frame.add(coin);
		frame.add(coin2);
		frame.add(coin3);
		frame.setVisible(true);
	}

}
