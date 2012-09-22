package labGen2;

public interface LabGen {
	public Node2[][] getNetwork();
	public int getWidth();
	public int getHeight();
	public void genNewLab(int startX, int startY);
}
