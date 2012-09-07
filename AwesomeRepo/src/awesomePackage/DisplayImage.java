package awesomePackage;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
		frame.setLocationRelativeTo(null);
		
		BufferedImage img = ImageManager.newImage("res/Spritesheet.png");
		ArrayList<JLabel> imgList = new ArrayList<JLabel>();
		for (int i = 0; i < 3; i++) {
			imgList.add(new JLabel(new ImageIcon(ImageManager.getAnimationFrame(img, i, 0, 240, 34))));
			frame.add(imgList.get(i));
		}
		frame.setVisible(true);
	}

}