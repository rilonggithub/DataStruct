import java.util.*;

public class HeapGreate{

	private int heapSize = 0;
	int[] arr=new int[1000000];

	public void heapInsert(int v){

		int index = heapSize;
		arr[index] = v;
		heapSize++;

		while(arr[index]<arr[(index - 1)/2]){
			swap(arr,index,(index - 1)/2);
			index = (index - 1)/2;
		}

	}

	public void heapify(int[] arr,int index){

		int left = index * 2+1;

		while(left < heapSize){
			int max = left + 1 < heapSize && arr[left+1] < arr[left] ? left+1:left;
			if(arr[max] >= arr[index]) break;
			swap(arr,index,max);
			index = max;
			left = index * 2+1;
		}
	}

	

	public void swap(int[] arr,int l,int r){
		int t = arr[l];
		arr[l] = arr[r];
		arr[r] = t;
	}

	public Boolean isEmpty(){
		return heapSize == 0;
	}


	public int pop(){
		if(heapSize >0){
			int ans = arr[0];
			swap(arr,0,--heapSize);
			heapify(arr,0);
			return ans;
		}
		return -999999;
	}

	public int peek(){
		return arr[0];
	}

	public int size(){
		return heapSize;
	}


	public static int[] getArray(int length){
		int[] arr=  new int[length];
		for (int i=0;i<length ;i++ ) {
			arr[i] = (int)(Math.random()*9999);
		}
		return arr;
	}

	public static Boolean campore(PriorityQueue<Integer> queue,HeapGreate heap){
		//System.out.println(queue.size()+","+heap.size());
		if(queue.size() != heap.size()) return false;
		while(!queue.isEmpty()){
			int ans = queue.poll() ;
			int _ans = heap.pop();
			//System.out.println(ans+","+_ans);
			if(ans != _ans) {
				System.out.println(ans+","+_ans);
				return false;
			}
		}
		return true;
	}

	public static Boolean isTrue(){

		
		int max = 1000000;
		int opCount = 500;

		for (int i=0; i<max; i++) {
			PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
			HeapGreate heap = new HeapGreate();
			int[] arr=getArray(opCount);
			
			for (int j=0; j<opCount; j++) {
				if((int)(Math.random()*9999) % 7 >0){
					queue.offer(arr[j]);
					heap.heapInsert(arr[j]);
				}else{
					queue.poll();
					heap.pop();
				}
				
			}
			if(!campore(queue,heap)){
				return false;
			}
		}
		System.out.println("----------");
		return true;
	}

	public static void main(String[] args){
		
		System.out.println("Result = "+ isTrue());
		
	}
}
