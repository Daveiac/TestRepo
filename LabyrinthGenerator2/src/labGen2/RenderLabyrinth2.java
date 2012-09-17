package labGen2;


import java.awt.event.KeyEvent;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class RenderLabyrinth2 extends GraphicsProgram {
	private static final int IMG_SIZE = 16;
	private GCompound gc = new GCompound();
	private int width = 20;
	private int height = 10;
	LabyrinthGenerator2 lab;

	public static void main(String[] args) {
		new RenderLabyrinth2().start();
	}
	
	public void setSize(int x, int y) {
		super.setSize(x, y);
		super.setSize(x*2-getWidth(), y*2-getHeight());
	}
	
	public void init() {
		lab = new LabyrinthGenerator2(width, height);
		addKeyListeners();
	}
	public void run() {
		this.getGCanvas().requestFocus();
		setSize(lab.getWidth() * IMG_SIZE * 2 + IMG_SIZE, lab.getHeight() * IMG_SIZE * 2 + IMG_SIZE);
		render();
	}
	
	private void render() {
		remove(gc);
		gc.removeAll();
		Node[][] network = lab.getNetwork();
		renderBackground();
		renderNodes();
		renderNetwork(network);
		add(gc);
	}

	private void renderBackground() {
		for (int i = 0; i < width * 2 + 1; i++) {
			for (int j = 0; j < height * 2 +1 ; j++) {
				gc.add(new GImage("wall.png", i * IMG_SIZE, j * IMG_SIZE));
			}
		}
	}

	private void renderNodes() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				gc.add(new GImage("blank.png", (i*2+1)*IMG_SIZE, (j*2+1)*IMG_SIZE));
			}
		}
	}

	private void renderNetwork(Node[][] network) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Node parent = network[i][j];
				for (Node child : parent.getChildren()) {
					if (parent.getX() > child.getX()) {
						gc.add(new GImage("blank.png", (child.getX()*2+2)*IMG_SIZE, (child.getY()*2+1)*IMG_SIZE));
					} else if (parent.getY() > child.getY()) {
						gc.add(new GImage("blank.png", (child.getX()*2+1)*IMG_SIZE, (child.getY()*2+2)*IMG_SIZE));
					} else if (parent.getX() < child.getX()) {
						gc.add(new GImage("blank.png", (child.getX()*2)*IMG_SIZE, (child.getY()*2+1)*IMG_SIZE));
					} else if (parent.getY() < child.getY()) {
						gc.add(new GImage("blank.png", (child.getX()*2+1)*IMG_SIZE, (child.getY()*2)*IMG_SIZE));
					}
//					gc.add(new GImage("blank.png", (child.getX()*2+1)*IMG_SIZE, (child.getY()*2+1)*IMG_SIZE));
				}
			}
		}
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
