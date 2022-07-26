package queue;

import java.util.Scanner;

public class ArrayQueueDemo {

    public static void main(String[] args) {

        //創建一個佇列
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';// 接收用戶輸入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        // 輸入一個菜單
        while (loop) {
            System.out.println("s(show): 顯示佇列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加數據佇列");
            System.out.println("g(get): 從佇列取出數據");
            System.out.println("h(head): 查看佇列頭的數據");
            key = scanner.next().charAt(0); //接受一個字符
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("輸出一個數字");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的數據是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("佇列頭的數據是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;

            }
        }
        System.out.println("程序退出!!!");
    }


    //使用數組模擬佇列-編寫一個ArrayQueue類
    static class ArrayQueue{
        private int maxSize; // 表示陣列的最大容量
        private int front; // 佇列頭
        private int rear; // 佇列尾
        private int[] arr; // 該數據用存放數據，模擬佇列

        //創建佇列的構造器
        public ArrayQueue(int arrMaxSize){
            maxSize = arrMaxSize;
            arr = new int[maxSize];
            front = -1; //指向佇列頭部，分析出front是指向佇列頭的前一個位置
            rear = -1; //指向佇列尾部，指向佇列尾的數據(即就是佇列最後一個數據)
        }

        //判斷佇列是否滿
        public boolean isFull(){
            return rear == maxSize - 1;
        }
        //判斷佇界是否為空
        public boolean isEmpty(){
            return rear == front;
        }

        //添加數據到佇列
        public void addQueue(int n){
            //判斷佇列是否滿了
            if (isFull()){
                System.out.println("佇列已滿，不能添加數據!");
                return;
            }
            rear++; //讓rear 往後移動
            arr[rear] = n;
        }

        //獲取佇列的數據，出佇列
        public int getQueue(){
            //判斷佇列使否為空
            if(isEmpty()){
                //通過拋出異常
                throw new RuntimeException("佇列為空，不能取數據");
            }
            front++; //front先往後移以便回傳值能取到值
            return arr[front];
        }
        //顯示佇列內的所有數據
        public void showQueue(){
            //遍歷
            if(isEmpty()){
                System.out.println("佇列為空，沒有數據");
                return;
            }
            for (int i=0; i<arr.length; i++){
                System.out.printf("arr[%d]=%d\n",i,arr[i]);
            }
        }

        //顯示佇列的頭數據 (注意!非取出數據)
        public int headQueue(){
            //判斷
            if(isEmpty()){
                throw new RuntimeException("佇列為空，沒有數據");
            }
            return arr[front + 1];
        }
    }

}
