//无序数组中找出最大的 Top K
//构建一个 K 节点的 小根堆
//如果后续新加入的元素比堆顶大，则和第一个元素交换
//同时使用heapify(arr,0)进行调整堆
//直到最后


//-------------------------------------------------------
//如果是找出最小的 Top K
//构建一个 K 节点的 大根堆
//如果后续新加入的元素比堆顶小，则和第一个元素交换
//同时使用heapify(arr,0)进行调整堆
//直到最后
import java.util.*;
public class HeapStruct{

	private int heapSize = 0;

	public void heapInsert(int[] arr, int index){
		heapSize++;

		while(arr[index] < arr[(index -1)/2]){
			swap(arr,index,(index-1)/2);
			index = (index - 1)/2;
		}
	}

	public void heapify(int[] arr,int index){
		int left = index*2 +1;

		while(left < heapSize){

			int min = ((left+1 < heapSize) && (arr[left+1] < arr[left])) ? left+1 :left;
			if(arr[min] > arr[index]) break;

			swap(arr,index,min);

			index = min;
			left = index*2+1;
		}
	}

	public int pop(int[] arr){
		if(heapSize>0){
			int ans = arr[0];
			swap(arr,0,--heapSize);
			heapify(arr,0);
			return ans;
		}
		return -999999;
	}

	public int peek(int[] arr){
		return arr[0];
	}

	public Boolean isEmpty(){
		return heapSize == 0;
	}


	public void swap(int[] arr,int l,int r){
		int t = arr[l];
		arr[l] = arr[r];
		arr[r] = t;
	}

	public static void main(String[] args){
		HeapStruct heap = new HeapStruct();

		int[] arr=new int[]{91,8,2,7,4,90,12,45,-1,-9,0,10,3,24,-6,83,2,8,19,40};

		int K = 5;

		for (int i=0; i<K; i++) {
			heap.heapInsert(arr,i);
		}

		for (int i=K; i<arr.length; i++) {
			int top = heap.peek(arr);
			if(arr[i]>top){
				heap.swap(arr,0,i);
				heap.heapify(arr,0);
			}
		}

		

		while(!heap.isEmpty()){
			System.out.print(heap.pop(arr)+",");
		}

		System.out.println("----------------------");

		Arrays.sort(arr);
		for (int i=0; i<arr.length; i++) {
			System.out.print(arr[i]+",");
		}
	}
}
