import java.util.*;

public class RecursionDemo{

/**************************************** SubSequence  **************************************/

	public static HashSet<String> getSubSequence(String str){
		HashSet<String> arr=new HashSet<String>();
		process(str.toCharArray(),0,"",arr);
		return arr;
	}

	public static void process(char[] str,int length,String path,HashSet<String> list){
		if(str.length ==length){
			list.add(path);
			return ;
		}

		char ch = str[length];
		process(str,length+1,path,list);
		process(str,length+1,path+String.valueOf(ch),list);
	}






/**************************************** Permutation  *************************************/

	public static HashSet<String> getPermutation(String str){
		HashSet<String> arr=new HashSet<String>();
		ArrayList<Character> strArr=new ArrayList<Character>();

		for (int i=0; i<str.toCharArray().length; i++) {
			strArr.add(str.toCharArray()[i]);
		}
		process1(strArr,"",arr);
		return arr;
	}

	public static void process1(ArrayList<Character> str,String path,HashSet<String> list){

		if(str.isEmpty()){
			list.add(path);
		}else{
			int length = str.size();
			for (int i=0; i<length;i++ ) {

				char ch = str.get(i);
				str.remove(i);
				process1(str,path+ch,list);
				str.add(i,ch);
			}
		}

	}






/**************************************** reboot1  *************************************/

	public static int reboot(int size,int start,int aim,int rest){
		
		return process3(size,start,aim,rest);
	}

	public static int process3(int size,int start,int aim,int rest){

		
		int ans;

		if(rest == 0){
			ans= start==aim?1:0;
		}

		else if(start == 1){
			ans= process3(size,start+1,aim,rest-1);
		}

		else if(start == size){
			ans= process3(size,size-1,aim,rest-1);
		}
		else{
			ans= process3(size,start-1,aim,rest-1) + process3(size,start+1,aim,rest-1);
		}
		
		return ans;
	}



/**************************************** reboot2  *************************************/

	public static long reboot2(int size,int start,int aim,int rest){
		long[][] dp = new long[size+1][rest+1];
		for (int i=0; i<=size; i++) {
			for (int j=0; j<=rest; j++) {
				dp[i][j]=-1;
			}
		}
		return process2(size,start,aim,rest,dp);
	}

	public static long process2(int size,int start,int aim,int rest,long[][] dp){

		if(dp[start][rest]!=-1)
			return dp[start][rest];

		long ans;
		if(rest == 0){
			ans= start==aim?1:0;
		}else if(start == 1){
			ans= process2(size,start+1,aim,rest-1,dp);
		}else if(start == size){
			ans= process2(size,size-1,aim,rest-1,dp);
		}
		else{
			ans= process2(size,start-1,aim,rest-1,dp) + process2(size,start+1,aim,rest-1,dp);
		}

		dp[start][rest]=ans;
		return ans;
	}








	public static void main(String[] args){
		// HashSet<String> arr = getSubSequence("acccddeeffccc");
		// for (String str : arr ) {
		// 	System.out.println("\""+str+"\"");
		// }



		// HashSet<String> arr = getPermutation("1114");
		// for (String str : arr ) {
		// 	System.out.println("\""+str+"\"");
		// }

		System.out.println("count = "+reboot2(5,2,4,10));
		
	}
}
