public class LinkQueue {
    Node front=new Node(1);
    Node rear;
   /*public static void main(String args[]){

        Node a = new Node(4);
        LinkQueue main = new LinkQueue();
        main.InitQueue();
        main.EnQueue(a);
        System.out.println(main.DeQueue());

    }*/
    public int InitQueue(){//初始化队列：头结点为空，使得尾指针和头指针指向头结点
        front.next=null;
        rear=front;
        return 1;
    }

    public int EnQueue(Node e){//加入队列开始排队
        e.next=rear.next;
        rear.next=e;
        rear=rear.next;
        return 1;

    }

    public int DeQueue(){//出队列，并且获取服务
        if(front==rear) return 0;
        front.next.whethergetservice=true;
        Node pre= front.next;
        front.next=front.next.next;
        return pre.number;
    }


}

class Node{
    public Node(int a){
        number=a;
        whethergetservice=false;
    }
    boolean whethergetservice;
    int number;
    Node next;
}