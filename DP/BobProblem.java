import java.text.DecimalFormat;
import java.util.*;
public class BobProblem{


	public static double bob2(int[][] arr,int i,int j,int K){


		int N = arr.length;
		int M = arr[0].length;

		if(i>=N || i<0 || j>=M || j<0){
			return 1.0;
		}

		int[][][] dp = new int[N][M][K+1];
		for (int r=0; r<N; r++) {
			for (int c=0; c<M;c++ ) {
				for (int s=0; s<=K; s++) {
					dp[r][c][s] =-1;
				}
			}
		}
		return process2(arr,i,j,K,dp)/Math.pow(4,K);
	}

	public static int process2(int[][] arr, int curR,int curC,int rest,int[][][] dp){


		
		if(curR == arr.length || curR <0 || curC == arr[0].length || curC<0){
			return 0;
		}
		if(dp[curR][curC][rest]!=-1 && rest>=0){
			return dp[curR][curC][rest];
		}
		if(rest == 0){
			return 1;
		}

		int ans=
		process2(arr,curR+1,curC,rest-1,dp)+
		process2(arr,curR-1,curC,rest-1,dp)+
		process2(arr,curR,curC+1,rest-1,dp)+
		process2(arr,curR,curC-1,rest-1,dp);

		dp[curR][curC][rest]=ans;
		return ans;
	}

	public static void main(String[] args){
		int N = 15;
		int[][] arr= new int[N][N];
		DecimalFormat df = new DecimalFormat("0.000000000");
		System.out.println("P = "+df.format(bob2(arr,4,4,15)));
	}
}
