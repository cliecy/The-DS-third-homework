import java.util.Arrays;
import java.util.Scanner;
import java.util.Collections;
public class Migong {

    public static void main(String args[]) {//假设迷宫是一个int[9][9]二维数组，有障碍为"1"，无障碍为"0"
        int[][] e = initmigong();//e表示迷宫二维数组

        for (int i = 0; i < 9; i++) {
            for (int a = 0; a < 9; a++) {
                System.out.print(e[i][a]);
                System.out.print(" ");

            }
            System.out.println("");
            //用于生成迷宫给使用者
        }

        //要求使用者输入起点和终点的坐标，用数组保存
        Scanner input = new Scanner(System.in);
        System.out.println("请输入起点的坐标");
        int[] begin = {input.nextInt() - 1, input.nextInt() - 1};
        System.out.println("请输入终点的坐标");
        int[] last = {input.nextInt() - 1, input.nextInt() - 1};

        int[] now = new int[2];//用now来保存当前的坐标
        now[0] = begin[0];
        now[1] = begin[1];
        SQStack S = new SQStack();

        while (!Arrays.equals(now, last)) {//以2表示已经走过且存在栈中的路径,用3标记已经退回了的方块
            //先判断当前已经到了的位置的情况
            //if (e[now[0]][now[1]] == 0/*&&!Arrays.equals(now, begin)*/) {
                PushIntoStack(S, now, e);
                move(S, now, e);
            /*} else {
                e[now[0]][now[1]] = 3;
                now = S.get();
                S.member[S.top - 1].di++;
                e[now[0]][now[1]] = 2;
                move(S, now, e);
            }*/
        }

        SQStack Spre=new SQStack();
        while (!S.isEmpty()) {
            Elemtype pre =new Elemtype(S.get(),1);
            Spre.join(pre);
        }
        while (!Spre.isEmpty()) {

            int[] pre = Spre.get();
            System.out.println((pre[0] + 1) + "," + (pre[1] + 1));
        }


        /*for(int i=0;i<9;i++){
            for(int a=0;a<9;a++){
                System.out.print(e[i][a]);
                System.out.print(" ");

            }
            System.out.println("");
            用于测试生成迷宫的系统
        }*/


    }

    public static void move(SQStack e, int[] now, int[][] map) {
        a:
        while (1 > 0) {
            switch (e.member[e.top - 1].di) {//将移动分为四种情况，使得可以做到依次尝试东南西北的移动,并且要求该循环尝试所有的情况，直到成功移动为止
                case 1:
                    if (map[now[0]][now[1]+1] != 0) {
                        e.member[e.top - 1].di++;
                        break;
                    }
                    map[now[0]][now[1]] = 2;
                    now[1]++;
                    break a;
                case 2:
                    if (map[now[0]+1][now[1]] != 0) {
                        e.member[e.top - 1].di++;
                        break;
                    }
                    map[now[0]][now[1]] = 2;
                    now[0]++;
                    break a;
                case 3:
                    if (map[now[0]][now[1]-1] != 0) {
                        e.member[e.top - 1].di++;
                        break;
                    }
                    map[now[0]][now[1]] = 2;
                    now[1]--;
                    break a;
                case 4:
                    if (map[now[0]-1][now[1]] != 0) {
                        e.member[e.top - 1].di++;
                        break;
                    }
                    map[now[0]][now[1]] = 2;
                    now[0]--;
                    break a;
                default:
                    map[now[0]][now[1]] = 3;
                    e.get();
                    now=Arrays.copyOf(e.get(),2);//回到上一个位置
            }
        }
    }

    public static void PushIntoStack(SQStack e, int[] now, int[][] map) {
        int[] pre = Arrays.copyOf(now, 2);
        Elemtype stack = new Elemtype(pre, 1);
        e.join(stack);
    }


    public static int[][] initmigong() {
        int[][] a = new int[9][9];
        for (int i = 0; i < 9; i++) {
            a[0][i] = 1;
            a[i][0] = 1;
            a[8][i] = 1;
            a[i][8] = 1;
        }
        for (int i = 0; i < 7; i++) {
            System.out.println("请输入第" + (i + 1) + "行迷宫的情况，如有障碍则打1，无障碍打0以空格隔开");
            Scanner input = new Scanner(System.in);

            String pre = input.nextLine();
            for (int zff = 0; zff < 7; zff++) {

                a[i + 1][zff + 1] = Integer.parseInt(pre.split(" ")[zff]);//用一行空格隔开的数字生成一行迷宫
            }
        }

        return a;

    }//initmigong

}//migong



class Elemtype{//分别存储先后顺序、迷宫坐标，已经移动的方向
    //int ord;
    int[] a;
    int di;//定义1为东 2为西 3为南 4为北
    public Elemtype(/*int d,*/int[]e,int f){
        //ord=d;
        a=e;
        di=f;
    }
}

class SQStack{
    Elemtype[] member=new Elemtype[100];
    int top=0;
    int base=0;

    public int join(Elemtype a){
        if(top==99) return 0;
        member[top]=a;
        top++;
        return 1;
    }

    public int[] get(){
        if(top==0) System.exit(1);
        int[] pre=member[top-1].a;
        member[top-1]=null;
        top--;
        return pre;

    }

    public boolean isEmpty(){
        return top == base;
    }

}
