import java.util.*;
public class TwoStackQueue{

	Stack<Integer> pushStack = new Stack<Integer>();
	Stack<Integer> popStack = new Stack<Integer>();

	Queue<Integer> queue = new LinkedList<Integer>();

	public void push(int v){
		pushStack.push(v);
		queue.offer(v);
	}

	public int pop(){
		if(popStack.isEmpty()){
			while(!pushStack.isEmpty()){
				popStack.push(pushStack.pop());
			}
			if(popStack.isEmpty()) return -1;
			queue.poll();
			return popStack.pop();
		}else{
			queue.poll();
			return popStack.pop();
		}
	}

	public Queue<Integer> getQueue(){
		return queue;
	}

	public int size(){
		return popStack.size() + pushStack.size();
	}

	public void RandomOptions(TwoStackQueue queue){
		int max = 10000;
		for (int j=0;j<max ;j++ ) {
			for (int i=0;i<max ;i++ ) {
				int r = (int)(Math.random()*99999);
				if(r%4==0){
					queue.push(r);
				}else{
					queue.pop();
				}
			}
		}
		

	}

	public Boolean isTrue(TwoStackQueue queue){
		
		Queue<Integer> _queue=queue.getQueue();

		if(_queue.size() != queue.size()){
			System.out.println("_queue.size = "+_queue.size() +"queue.size"+queue.size());
			return false;
		}

		while(!_queue.isEmpty()){
			int v = _queue.poll();
			int v1 = queue.pop();

			if(v!=v1) return false;
		}
		return true;

	}

	public static void main(String[] args){
		TwoStackQueue queue=new TwoStackQueue();
		queue.RandomOptions(queue);
		System.out.print("result = "+queue.isTrue(queue));
	}
}
