public class SteelCuttingProblem{

	public static int steelCutting1(int[] w,int[] v,int length){
		return process1(w,v,length);
	}

	public static int process1(int[] w,int[] v,int rest){

		if(rest == 0 ){
			return 0;
		}
		int max=0;
		for (int i=0;i<w.length ;i++ ) {
			int r = rest-w[i];
			if(r>=0){
				int p = v[i]+process1(w,v,r);
				max = Math.max(max,p);
			}
		}
		return max;
	}


	public static int steelCutting2(int[] w,int[] v,int length){
		int[] dp = new int[length+1];
		for (int i=0; i<dp.length;i++ ) {
			dp[i]=-1;
		}
		int max = process2(w,v,length,dp);
		return max;
	}

	public static int process2(int[] w,int[] v,int rest,int[] dp){

		if(dp[rest] != -1) return dp[rest];
		if(rest == 0 ){
			return 0;
		}
		int max=0;
		for (int i=0;i<w.length ;i++ ) {
			int r = rest-w[i];
			if(r>=0){
				int p = v[i]+process2(w,v,r,dp);
				max = Math.max(max,p);
			}
		}
		dp[rest]=max;

		return max;
	}

	public static void main(String[] args){
		int[] w=new int[]{1,2,3,5,6,7};
		int[] v=new int[]{2,5,7,10,14,19};
		int length=6000000;

		//System.out.println("max1 = "+steelCutting1(w,v,length));
		System.out.println("max2 = "+steelCutting2(w,v,length));
	}
}
