package labGen;


import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class RenderLabyrinth extends GraphicsProgram {
	private GCompound gc = new GCompound();
	private int width = 40;
	private int height = 30;
	LabyrinthGenerator lab;
	
	private Node start;
	private Node target;

	public static void main(String[] args) {
		new RenderLabyrinth().start();
	}
	
	public void setSize(int x, int y) {
		super.setSize(x, y);
		super.setSize(x*2-getWidth(), y*2-getHeight());
	}
	
	public void init() {
		lab = new LabyrinthGenerator(width, height);
		addKeyListeners();
		addMouseListeners();
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
				if (lab.getWalkways()[i][j] == null) {
					gc.add(new GImage("player.png", i*16, j*16));
				} else if (lab.getWalkways()[i][j]) {
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
		} else if (event.getKeyCode() == KeyEvent.VK_P) {
			AStar.findPath(1, 1, 38, 28, lab.getWalkways());
			render();
		} else if (event.getKeyCode() == KeyEvent.VK_R) {
			clearPath();
			render();
		}
	}

	private void clearPath() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if(lab.getWalkways()[i][j] == null) lab.getWalkways()[i][j] = true;
			}
		}
	}
	
	public void mouseReleased(MouseEvent me) {
		Node mouseNode = getMouseNode();
		if(me.getButton() == MouseEvent.BUTTON1) {
			target = mouseNode;
			System.out.println("Target pos set to: x= "+mouseNode.getX()+", y= "+mouseNode.getY());
			if(start != null) {
				clearPath();
				AStar.findPath(start.getX(), start.getY(), target.getX(), target.getY(), lab.getWalkways());
				render();
			}
			
		} else if (me.getButton() == MouseEvent.BUTTON3) {
			start = mouseNode;
			System.out.println("Start pos set to: x= "+mouseNode.getX()+", y= "+mouseNode.getY());
		}
	}
	
	public Node getMouseNode() {
		Point mouse = getMousePosition();
		if (mouse == null) return null;
		return new Node(mouse.x / 16, mouse.y / 16);
	}
}
