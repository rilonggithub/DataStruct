import java.util.*;

public class SubArrayProblem{

	private LinkedList<Integer> maxList;
	private LinkedList<Integer> minList;
	private int[] arr;
	private int value;

	public SubArrayProblem(int[] _arr,int num){
		maxList = new LinkedList<Integer>();
		minList = new LinkedList<Integer>();
		arr = _arr;
		value = num;
	}

	//缩小，L++
	public void shrinkWindow(int index){

		while(!maxList.isEmpty()){
			if(maxList.peekFirst() > index){
				break;
			}
			maxList.removeFirst();
		}



		while(!minList.isEmpty()){
			if(minList.peekFirst() > index){
				break;
			}
			minList.removeFirst();
		}

	}

	//扩大，R++
	public void magnifyWindow(int index){
		if(maxList.isEmpty()){
			maxList.offerLast(index);
		}else{
			while(!maxList.isEmpty()){
				if(arr[maxList.peekLast()]>arr[index]){
					break;
				}
				maxList.removeLast();
			}
			maxList.offerLast(index);
		}




		if(minList.isEmpty()){
			minList.offerLast(index);
		}else{
			while(!minList.isEmpty()){
				if(arr[minList.peekLast()]<arr[index]){
					break;
				}
				minList.removeLast();
			}
			minList.offerLast(index);
		}
	}



	public int getMax(){
		return arr[maxList.peekFirst()];
	}




	public int getMin(){
		return arr[minList.peekFirst()];
	}


	public boolean isOK(){
		return (getMax() - getMin()) <= value;
	}

	public void clear(){
		maxList.clear();
		minList.clear();
	}


	public int getSubArrayCount(){
		int ways=0;
		for (int i=0; i<arr.length; i++) {
			clear();
			for (int j=i; j<arr.length; j++) {
				magnifyWindow(j);
				ways+= isOK()?1:0;
			}
		}

		return ways;
	}


	public static void main(String[] args){
		int[] arr= new int[]{1,3,6,2};
		int num = 4;
		SubArrayProblem subArray=new SubArrayProblem(arr,num);
		System.out.println("ways = "+subArray.getSubArrayCount());
	}


}


