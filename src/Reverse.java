public class Reverse {
    public static void main(String args[]){
        char [] a = {'A','B','C','1','2','3','4','5','6','7'};

        Stack abc = new Stack();
        for(int i=0;i<10;i++){
            SNode PRE=new SNode(a[i]);
            abc.EnStack(PRE);
        }
        for(int i=0;i<10;i++){
            System.out.println(abc.DeStack());
        }
    }


}
class Stack{
    SNode base =new SNode('0');
    SNode top=base;


    public int EnStack(SNode e){//构造链栈，栈顶栈底的指针都不动，只更改指针的后继关系
        top.next=e;
        top=top.next;
        return 1;
    }

    public char DeStack(){
        if(top==base) return 0;
        SNode pre=base;
        while(pre.next!=top){
            pre=pre.next;
        }
        char e=top.number;
        top=pre;
        return e;
    }
}
class SNode{
    char number;
    SNode next;
    public SNode(char e){
        number=e;
        next=null;
    }
}