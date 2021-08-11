import java.util.*;
public class WindowMax{

	private LinkedList<Integer> list;
	private int L;
	private int R;
	private int winMax;
	private int[] array;

	public  WindowMax(int[] arr,int _winMax){
		list=new  LinkedList<Integer> ();
		L=0;
		R=0;
		winMax = _winMax;
		array = arr;

		for (int i=0; i<_winMax && i<arr.length; i++) {
			toRight();
		}

	}

	public  int getMax(){
		if(!list.isEmpty()){
			return array[list.peekFirst()];
		}
		return Integer.MIN_VALUE;
	}

	public  WindowMax toLeft(){
		while(!list.isEmpty()){
			int first = list.peekFirst();
			if(first > L){
				break;
			}
			list.removeFirst();
		}
		L++;
		return this;
	}

	public  WindowMax toRight(){
		if(list.isEmpty()){
			list.offerLast(R);
		}
		else{
			while(!list.isEmpty()){
				if(array[list.peekLast()] > array[R]){
					break;
				}
				list.removeLast();
			}
			list.offerLast(R);
		}
		R++;
		return this;
	}


	public static void main(String[] args){

		List<Integer> result = new ArrayList<>();
		int[] arr=new int[]{4,3,5,4,3,3,6,7,8,5,9};
		int win_max=24;

		WindowMax win=new WindowMax(arr,win_max);
		result.add(win.getMax());

		for (int i=win_max; i<arr.length; i++) {
			win.toLeft().toRight();
			result.add(win.getMax());
		}

		for (int v:result ) {
			System.out.print(v+",");
		}
		System.out.println();
	}

}



