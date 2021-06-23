import java.util.*;
//两个栈组成一个队列
//
//核心思想
//1、准备两个栈，
//   一个用于正常入栈pushStack
//   一个用于出栈popStack
//2、入栈操作全部压入pushStack栈
//3、出栈的时候，需要注意如下两点：
//   3.1 如果popStack为空，就需要一次性将pushStack栈的数据全部出栈，并压入popStack
//   3.2 否则，直接从popStack出栈
//
public class TwoStackForQueue{
	Stack<Integer> pushStack = new Stack<Integer>();
	Stack<Integer> popStack = new Stack<Integer>();

	//对数器正常的队列
	Queue<Integer> queue = new LinkedList<Integer>();

	public void push(int v){
		pushStack.push(v);
	}

	public void pushQueue(int v){
		queue.offer(v);
	}

	public void popQueue(){
		queue.poll();
	}

	public int pop(){
		if(!popStack.isEmpty()){
			return popStack.pop();
		}else{
			while(!pushStack.isEmpty()){
				popStack.push(pushStack.pop());
			}
			if(popStack.isEmpty()) return -1;
			return popStack.pop();
		}
	}

	public Boolean isEmpty(){
		return popStack.isEmpty() && pushStack.isEmpty();
	}

	public Queue<Integer> getQueue(){
		return queue;
	}

	public Boolean isTrue(){
		int max = 100000;
		int optionsCount = 10000;
		for (int j=0;j< max;j++ ) {
			TwoStackForQueue queue = new TwoStackForQueue();
			for (int i=0; i<optionsCount; i++) {
				int r = (int)(Math.random()*99999);
				if(r % 8 == 0){
					queue.pop();
					queue.popQueue();
				}else{
					queue.push(r);
					queue.pushQueue(r);
				}
			}

			Queue<Integer> _queue = queue.getQueue();
			while(!_queue.isEmpty()){
				int v = _queue.poll();
				int v1 = queue.pop();
				if(v!=v1) return false;
			}
			if(!queue.isEmpty()) return false;
		}
		return true;
	}


	public static void main(String[] args){
		TwoStackForQueue queue = new TwoStackForQueue();
		System.out.print("测试结果："+queue.isTrue());
	}
}
