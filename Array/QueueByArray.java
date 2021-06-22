import java.util.*;

public class QueueByArray{
	
	Queue<Integer> queue = new LinkedList<Integer>();
	int[] arr = new int[5];
	int size=0;
	int pushi = 0;
	int popi = 0;
	
	public void push_queue(int v){
		queue.offer(v);
	}

	public void push(int v){
		if(size<arr.length){
			arr[pushi] = v;
			pushi = getIndex(pushi);
			size++;
		}else{
			
			System.out.println("队列已满");
		}
	}

	public int pop_queue(){
		if(!queue.isEmpty()){
			return queue.poll();
		}
		return -1;
	}

	public int pop(){
		
		if(size>0){
			int v = arr[popi];
			arr[popi] = -9999;
			popi = getIndex(popi);
			size--;
			return v;
		}else{
			return -1;
		}
	}

	public int getIndex(int index){
		return (index+1) >= arr.length ? 0 : index+1;
	}

	public void printpushi(){
		System.out.println("pushi = "+pushi+"popi = "+popi);
	}

	public int getPopIndex(){
		return popi;
	}

	public int[] getArray(){
		return arr;
	}

	public Queue<Integer> getQueue(){
		return queue;
	}


	


	public void RandomOption(int pushCount, int popCount,int push_count,BecomeQueueByArray queue){

		while(true){
			int r = (int)(Math.random()*9999);
			if(r%2 == 0){
				if(pushCount >0 ){
					queue.push(r);
					queue.push_queue(r);
					pushCount--; 
				}
			}else{
				if(popCount >0){
					queue.pop();
					queue.pop_queue();
					popCount--; 
				}
			}
			
			if(pushCount == 0 && popCount ==0) {
				break;
			}
		}

		
		for (int i=0;i<push_count;i++ ) {
			int r = (int)(Math.random()*9999);
			queue.push(r);
			queue.push_queue(r);
		}

	}

	public Boolean isTrue(BecomeQueueByArray queue){
		Queue<Integer> _queue = queue.getQueue();
		int[] arr= queue.getArray();
		int _index=queue.getPopIndex();
		int index= (_index == 0) ? arr.length-1: (_index);

		System.out.println("");
		for (int num : arr ) {
			System.out.print(num+",");
		}
		System.out.println();
		while(!_queue.isEmpty()){
			int x = _queue.poll();
			System.out.print(x+",");
			if(arr[index] != x){
				return false;
			}
			index = ((index + 1) >= arr.length) ? 0 : index+1;
		}
		return true;
	}


	public static void main(String args[]){
		int count = 100;
		while(count-- > 0){
			BecomeQueueByArray queue = new BecomeQueueByArray();
			queue.RandomOption(4,3,1,queue);

			if(!queue.isTrue(queue)){
				int[] arr= queue.getArray();
				Queue<Integer> _queue = queue.getQueue();

				System.out.println();
				System.out.println("begin-----------");
				for (int num:arr ) {
					System.out.print(num+",");
				}
				System.out.println();
				queue.printpushi();

				System.out.println();
				while(!_queue.isEmpty()){
					System.out.print(_queue.poll()+",");
				}
				System.out.println();
				System.out.println("end-----------");
				break;
			}
			

		}
		if(count > 0){
			System.out.println("出错了...");
		}else{
			System.out.println("成功了...");
		}
	}
}
