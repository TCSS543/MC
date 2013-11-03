package tcss543;

public class DeviceGraph {
	private int[] R;
	private int[] H;
	private int[][] neighbors;
	
	public DeviceGraph(int[] R, int[] H, int[][] neighbors) {
		this.R = R;
		this.H = H;
		this.neighbors = neighbors;
	}
	
	public int[] getR() {
		return R;
	}
	
	public int[] getH() {
		return H;
	}
	
	public int[][] getNeighbors() {
		return neighbors;
	}
}
