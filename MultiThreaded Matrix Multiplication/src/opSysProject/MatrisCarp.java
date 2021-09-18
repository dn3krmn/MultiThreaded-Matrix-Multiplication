package opSysProject;

public class MatrisCarp implements Runnable{
	
	private int[][] A;
	private int[][] B;
	private int[][] S;
	private int startIndex;
	private int endIndex;
	private int sayac;
	
	public MatrisCarp(int[][] A, int[][] B, int[][] S, int startIndex, int endIndex,int sayac) {
		
		this.A = A;
		this.B = B;
		this.S = S;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.sayac = sayac;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("Thread: " + (sayac + 1) + "  çalýþýyor...");
		
		for(int i = startIndex; i < endIndex; i++) {
			for(int j = 0; j < B[0].length; j++) {
				S[i][j] = 0;
				for(int k = 0; k < A[i].length; k++) {
					
					S[i][j] += A[i][k] * B[k][j];
				}
				
			}
		}
	}

}

