import java.util.*;

public class RadixOrder{

	public static int[] order(int[] arr,int digitIndex){
		final int MAX=10;
		int[] sum = new int[MAX];
		int[] digitCount = new int[MAX];
		int[] result = new int[arr.length];
		//表示一个数的倒数第几位
		//int digitIndex = 3;

		for (int i=1; i<arr.length; i++) {
			sum[i] = arr[i]+sum[i-1];
		}

		int digit = getMaxDigit(arr);

		for (int i=0; i<arr.length;i++ ) {

			char[] charArr = getNumCharArray(arr[i],digit);
			++digitCount[charArr[charArr.length-digitIndex]-'0'];

		}

		sum[0] = digitCount[0];
		for (int i=1; i<MAX; i++) {
			sum[i] = sum[i-1] + digitCount[i];
		}

		for (int i=arr.length-1; i>=0;i-- ) {
			char[] charArr = getNumCharArray(arr[i],digit);
			result[sum[charArr[charArr.length-digitIndex]-'0']-1] = arr[i];
			--sum[charArr[charArr.length-digitIndex]-'0'];
		}

		return result;


		// printArray(arr,"原始数组=");
		// printArray(digitCount,"个位累加数组=");
		// printArray(sum,"前缀和数组=");
		// printArray(result,"结果数组=");
 	}

 	public static void printArray(int[] arr,String desc){
 		System.out.println(desc);
 		for (int i=0; i<arr.length; i++) {
 			System.out.print(arr[i]+",");
 		}
 		System.out.println();
 	}

 	public static char[] getNumCharArray(int i,int digit){
 		String s = i+"";
		while(s.length() < digit){
			s = "0"+s;
		}
		return s.toCharArray();
 	}

 	public static int getMaxDigit(int[] arr){
 		int[] _arr=copyArr(arr);
 		Arrays.sort(_arr);
 		int v = _arr[_arr.length-1];
 		int digit = 0;
 		while(v>0){
 			v = v/10;
 			digit++;
 		}
 		return digit;
 	}

 	public static int getMin(int[] arr){
 		int min = Integer.MAX_VALUE;
 		for (int i=0; i<arr.length;i++) {
 			if(arr[i]<min) min = arr[i];
 		}
 		return min;
 	}

 	public static void negativeNumber(int[] arr,int min){
 		for (int i=0; i<arr.length;i++ ) {
 			arr[i]-=min;
 		}
 	}

 	public static void restoreArray(int[] arr,int min){
 		for (int i=0; i<arr.length;i++ ) {
 			arr[i]+=min;
 		}
 	}

 	public static int[] getArr(int length,int max){
		int[] arr= new int[length];
		for (int i=0; i<length; i++) {
			arr[i] = (int)(Math.random()*max);
		}
		return arr;
	}

	public static Boolean compar(int[] arr,int[] _arr){

		Arrays.sort(_arr);

		for (int i=0; i<_arr.length;i++ ) {
			System.out.print(arr[i]+","+_arr[i]+"|");
			if(arr[i] != _arr[i]){
				return false;
			}
		}
		return true;
	}

	public static int[] copyArr(int[] arr){
		int[] _arr= new int[arr.length];
		for (int i=0; i<arr.length; i++) {
			_arr[i] = arr[i];
		}
		return _arr;
	}

	public static void radixOrder(int[] arr){
		int min=getMin(arr);
		if(min<0){
			negativeNumber(arr,min);
		}
		int digit = getMaxDigit(arr);
// System.out.println("digit= "+digit);
		int loop = 1;
		while(digit>=loop){
			printArray(arr,"原始数组=");
			arr = order(arr,loop++);

		}
		if(min<0){
			restoreArray(arr,min);
		}

	}

	public static Boolean isTrue(){
		int max = 1;
		for (int i=0; i<max;i++ ) {

			int[] arr=getArr(5,9999);
			int[] _arr=copyArr(arr);
//printArray(arr,"原始数组=");
			//基数排序
			radixOrder(arr);
//printArray(arr,"XX数组=");
			if(!compar(arr,_arr)) return false;
			
		}
		return true;

	}


	public static void main(String[] args){
		
		System.out.println("isTrue = "+isTrue());
	}
}
