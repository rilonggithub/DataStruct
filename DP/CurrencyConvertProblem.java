public class CurrencyConvertProblem{

	public static int currencyConvert1(int[] arr,int aim){
		return process(arr,0,aim);
	}

	public static int process(int[] arr,int index,int rest){

		

		if(rest == 0){
			return 1;
		}
		if(rest < 0 ){
			return 0;
		}

		int sum = 0;

		for (int i=index; i<arr.length;i++ ) {
			sum+= process(arr,i+1,rest-arr[i]);
		}

		return sum;
	}

	public static int currencyConvert2(int[] arr,int aim){
		return process2(arr,0,aim);
	}

	public static int process2(int[] arr,int index,int rest){

		

		if(rest == 0){
			return 1;
		}
		if(rest < 0 || index==arr.length){
			return 0;
		}

		return process2(arr,index+1,rest) + process2(arr,index+1,rest-arr[index]);

		
	}











	public static int currencyConvert3(int[] arr,int aim){
		int N = arr.length;
		int[][] dp=new int[N+1][aim+1];

		for (int i=0; i<=N; i++) {
			for (int j=0; j<=aim; j++) {
				dp[i][j] = -1;
			}
		}
		return process3(arr,0,aim,dp);
	}

	public static int process3(int[] arr,int index,int rest,int[][] dp){

		if(rest == 0){
			return 1;
		}
		else 
		if(rest < 0 ){
			return 0;
		}

		
		if(dp[index][rest] != -1){
			return dp[index][rest];
		}


		int sum = 0;

		for (int i=index; i<arr.length;i++ ) {
			sum+= process3(arr,i+1,rest-arr[i],dp);
		}
		dp[index][rest] = sum;
		return sum;
	}

















	public static int currencyConvert4(int[] arr,int aim){
		int N = arr.length;
		int[][] dp=new int[N+1][aim+1];

		for (int i=0; i<=N; i++) {
			dp[i][0] = 1;
		}

		for (int index=N-1; index>=0; index--) {
			for (int rest=0; rest<=aim; rest++) {
					dp[index][rest] = dp[index+1][rest]+ 
					((rest-arr[index] >=0)?dp[index+1][rest-arr[index]]:0);
			}
		}
		return dp[0][aim];
	}

	















	public static int currencyConvert5(int[] arr,int aim){
		return process5(arr,0,aim);
	}

	public static int process5(int[] arr,int index,int rest){

		if(rest == 0){
			return 1;
		}

		int sum = 0;

		for (int i=index; i<arr.length&&rest-arr[i]>=0;i++ ) {
			sum+= process5(arr,i,rest-arr[i]);
		}

		return sum;
	}


















	public static int currencyConvert6(int[] arr,int aim){
		return process6(arr,0,aim);
	}
	public static int process6(int[] arr,int index,int rest){

		if(index == arr.length){
			return rest==0?1:0;
		}

		int sum = 0;
		for (int i=0; i*arr[index]<=rest;i++ ) {
			sum+= process6(arr,index+1,rest-(i*arr[index]));
		}

		return sum;
	}















	public static int currencyConvert7(int[] arr,int aim){
		int N = arr.length;
		int[][] dp=new int[N+1][aim+1];

		for (int i=0;i<=N ; i++) {
			dp[i][0] = 1;
		}
		

		for (int index=N-1; index>=0; index--) {
			for (int rest=0; rest<=aim; rest++) {

				int sum = 0;

				for (int i=index; i<arr.length&&rest-arr[i]>=0;i++ ) {
					sum+= dp[i][rest-arr[i]];
				}

				dp[index][rest] = sum;
			}
		}

		for (int i=0; i<=N; i++) {
			System.out.println();
			for (int j=0; j<=aim; j++) {
				System.out.print(dp[i][j]+",");
			}
		}

		return dp[0][aim];
	}


	public static void main(String[] args){
		int[] arr= new int[]{2,4,5,10};
		int aim = 20;
		//System.out.println("count = "+currencyConvert1(arr,aim));
		//System.out.println("count = "+currencyConvert2(arr,aim));
		System.out.println("count = "+currencyConvert3(arr,aim));
		System.out.println("count = "+currencyConvert4(arr,aim));
		System.out.println("count = "+currencyConvert5(arr,aim));
		System.out.println("count = "+currencyConvert6(arr,aim));
    //currencyConvert7错误
		System.out.println("count = "+currencyConvert7(arr,aim));
	}
}

