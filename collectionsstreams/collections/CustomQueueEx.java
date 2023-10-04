package collectionsstreams.collections;
//FIFO
//queue impl
class QueueImpl {
	private static int front, rear, capacity;   
    private static int queue[];
    
    QueueImpl(int size) {   
        front = rear = 0;   
        capacity = size;   
        queue = new int[capacity];   
    }   
     
    // insert an element into queue  
    static void queueEnqueue(int item) {   
        // check full  
        if (capacity == rear) {   
            System.out.println("full");   
            return;
        } else { // insert at rear
            queue[rear] = item;   
            rear++;   
        }   
        return;   
    }   
     
    //remove an element from the queue  
    static void queueDequeue() {   
        // check if queue is empty   
        if (front == rear) {   
            System.out.println("empty");   
            return;   
        } else {   
            for (int i = 0; i < rear - 1; i++) {   
                queue[i] = queue[i + 1];   
            }
            if (rear < capacity)   
                queue[rear] = 0;   
     
            // decrement   
            rear--;   
        }   
        return;   
    }   
     
    // print queue elements   
    static void queueDisplay() {   
        int i;   
        if (front == rear) {   
            System.out.println("empty");   
            return;   
        }   
     
        // traverse front to rear and print elements   
        for (i = front; i < rear; i++) {   
            System.out.print(queue[i] + " ,");   
        }   
        System.out.println();
        return;   
    }   
     
    // print front of queue   
    static void queueFront()   
    {   
        if (front == rear) {   
            System.out.println("empty");   
            return;   
        }   
        System.out.println("Front Element of the queue: " + queue[front]);   
        return;   
    }   
}
//linkedlistqueue impl
class LinkedListQueue {
	private class Node {  
		 int data;  
		 Node next;  
	}  	
	
	private Node front, rear;
	private int queueSize;

	public LinkedListQueue() {  
		front = null;  
		rear = null;  
		queueSize = 0;  
	}  

	public boolean isEmpty() {  
		return (queueSize == 0);  
	}    
	
	public int dequeue() {  
		int data = front.data;  
		front = front.next;  
		if (isEmpty()) {  
			rear = null;  
		}  
		queueSize--;  
		System.out.println("Element " + data + " removed from the queue");  
		return data;  
	}  
	
	public void enqueue(int data) {  
		Node oldRear = rear;  
		rear = new Node();  
		rear.data = data;  
		rear.next = null;  
		if (isEmpty()) {  
			front = rear;  
		} else {  
			oldRear.next = rear;  
		}  
		 queueSize++;  
		 System.out.println("Element " + data+ " added to the queue");  
	 }  
	 public void printFrontAndRear() {  
	     System.out.println("Front of the queue:" + front.data  
	     + " Rear of the queue:" + rear.data);  
	 }  
}

public class CustomQueueEx {
	//queue impl example
	public static void example1() {
		QueueImpl q = new QueueImpl(4);   
	     
        System.out.println("Initial Queue:");  
        q.queueDisplay();   
     
        //insert
        q.queueEnqueue(10);   
        q.queueEnqueue(30);   
        q.queueEnqueue(50);   
        q.queueEnqueue(70);   
     
        //print  
        System.out.println("after Enqueue Operation:");  
        q.queueDisplay();   
     
        //print front  
        q.queueFront();   
           
        //insert 90 el
        q.queueEnqueue(90);   
     
        //print
        q.queueDisplay();   
     
        q.queueDequeue();  
        q.queueDequeue();  
        System.out.printf("after two dequeue operations: ");   
     
        // print
        q.queueDisplay();   
     
        // print front  
        q.queueFront();   
	}
	//linkedlistqueue impl example
	public static void example2() {
		LinkedListQueue queue = new LinkedListQueue();  
		queue.enqueue(6); queue.enqueue(3);  
		queue.printFrontAndRear();  
		queue.enqueue(12); queue.enqueue(24);  
		queue.dequeue(); queue.dequeue();  
		queue.enqueue(9);   
		queue.printFrontAndRear();
	}
	public static void main(String args[]) {
		example2();
	}
}


