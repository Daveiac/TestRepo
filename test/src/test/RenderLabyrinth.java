package test;

import java.awt.event.KeyEvent;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class RenderLabyrinth extends GraphicsProgram {
	private GCompound gc = new GCompound();
	
	private int width = 40;
	private int height = 30;
	
	LabyrinthGenerator lab = new LabyrinthGenerator(width, height);
	
	public void init() {
		addKeyListeners();
	}
	public void run() {
		this.getGCanvas().requestFocus();
		setSize(lab.getWidth() * 16, lab.getHeight() * 16);
		render();
	}
	
	private void render() {
		remove(gc);
		gc.removeAll();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (lab.getWalkways()[i][j]) {
					gc.add(new GImage("blank.png", i*16, j*16));
				} else {
					gc.add(new GImage("wall.png", i*16, j*16));
				}
			}
		}
		add(gc);
	}
	
	public void keyPressed(KeyEvent event) {
//		println("You pressed: " + event.getKeyCode() + ", " + event.getKeyChar());
		if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		} else if (event.getKeyCode() == KeyEvent.VK_ENTER) {
			lab.genNewLab();
			render();
		}
	}
}
