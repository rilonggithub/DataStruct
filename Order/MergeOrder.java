import java.util.*;

public class MergeOrder{



	public void process2(int[] arr){
		int size = 1;
		int length = arr.length;

		while(size < length){
			int left = 0;
			while(left < length){
				int mid = left + size -1;
				if(mid > length) break;
				int right = Math.min(mid+size,length-1);
				merge(arr,left,mid,right);
				left = right+1;
			}
			size=size<<1;
		}
	}




	public void process(int[] arr,int left, int right){

		if(left == right) return;

		int mid = left +((right - left) >> 1);
		process(arr,left,mid);
		process(arr,mid+1,right);
		merge(arr,left,mid,right);
	}

	public void merge(int[] arr, int left,int mid,int right){
		int[] help = new int[right - left +1 ];
		int helpi = 0;

		int p1 = left;
		int p2 = mid+1;

		while(p1<= mid && p2<= right){
			help[helpi++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}

		while(p1<=mid){
			help[helpi++] = arr[p1++];
		}

		while(p2<=right){
			help[helpi++] = arr[p2++];
		}

		for (int i=0; i<help.length;i++ ) {
			arr[left+i] = help[i];
		}
	}

	public static void main(String[] args){
		int[] arr={20000,3,2,5,4,8,99,600,1,99,188,230,9100,10000,9999};
		MergeOrder merge = new MergeOrder();
		//merge.process(arr,0,arr.length - 1);
		merge.process2(arr);
		for (int i=0; i<arr.length;i++ ) {
			System.out.print(arr[i]+",");
		}
	}

}
