package ThirdWeek;

public class Joe {
    public static void main (String[] args){
        Queue<Integer> q = new QueueArray<Integer>();

        for (int i = 1; i <=4;i++){
            q.enqueue(i);
        }
        System.out.println("Show queue:");
        while(! q.isEmpty()){
            for (int i = 0; i < 3; i++){
                q.enqueue(q.dequeue());
            }
            System.out.print(q.dequeue() + " ");
        }
    }
}
