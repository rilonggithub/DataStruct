import java.util.*;

public class RadixOrder{

	//digitIndex 表示对数字的倒数第几位进行排序
	public static int[] order(int[] arr,int digitIndex){
		final int MAX=10;
		//统计该位上0-9各自出现的次数
		int[] digitCount = new int[MAX];
		//基于digitCount数组的前缀和数组
		int[] sum = new int[MAX];
		//结果返回数组
		int[] result = new int[arr.length];


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
			arr[i] = (int)(Math.random()*max)-1000;
		}
		return arr;
	}

	public static Boolean compar(int[] arr,int[] _arr){

		Arrays.sort(_arr);

		for (int i=0; i<_arr.length;i++ ) {
			//System.out.print(arr[i]+","+_arr[i]+"|");
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

	public static int[] radixOrder(int[] arr){
		int min=getMin(arr);
		if(min<0){
			negativeNumber(arr,min);
		}
		int digit = getMaxDigit(arr);
		int loop = 1;
		while(digit>=loop){
			arr = order(arr,loop++);

		}
		if(min<0){
			restoreArray(arr,min);
		}
		return arr;
	}

	public static Boolean isTrue(){
		int max = 1000000;
		for (int i=0; i<max;i++ ) {

			int[] arr=getArr(50,9999);
			int[] _arr=copyArr(arr);
			arr = radixOrder(arr);
			if(!compar(arr,_arr)) return false;
			
		}
		return true;

	}


	public static void main(String[] args){
		System.out.println("ans = "+isTrue());
	}
}
