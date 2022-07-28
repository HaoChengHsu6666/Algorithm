package Queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {

    public static void main(String[] args) {

        System.out.println("測試陣列模擬環形佇列的案例");

        //創建一個佇列
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(4); // 說明設置4，其佇列的有效數據最大是3
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
                    circleArrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("輸出一個數字");
                    int value = scanner.nextInt();
                    circleArrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = circleArrayQueue.getQueue();
                        System.out.printf("取出的數據是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = circleArrayQueue.headQueue();
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
}


class CircleArrayQueue {
    private int maxSize; // 表示陣列的最大容量
    //front變數的含意做一個調整，front就指向佇列的第一個元素，也就是arr[front]就是佇列的第一個元素，初始值為0
    private int front; // 佇列頭 (用來取出值)
    //rear變數的含意做一個調整，rear指向佇列的最後一個元素的後一個位置，因為希望空出一個空間作為約定，初始值為0
    private int rear; // 佇列尾 (用來插入值)
    private int[] arr; // 該數據用存放數據，模擬佇列

    //創建佇列的構造器
    public CircleArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    //判斷佇列是否滿
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判斷佇列是否為空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加數據到佇列
    public void addQueue(int n) {
        //判斷佇列是否滿了
        if (isFull()) {
            System.out.println("佇列已滿，不能添加數據!");
            return;
        }
        //直接將數據加入
        arr[rear] = n;
        //將 rear 後移，這裡必須考慮取%(讓rear指針若可能到最後的位置時，讓其能返回到index 0 的位置，避免陣列超出範圍)
        rear = (rear + 1) % maxSize;
    }

    //獲取佇列的數據，出佇列
    public int getQueue() {
        //判斷佇列使否為空
        if (isEmpty()) {
            //通過拋出異常
            throw new RuntimeException("佇列為空，不能取數據");
        }
        //這裡需要分析出front是指向佇列的第一個元素
        // 1. 先把front 對應的值保留到一個臨時變數 (讓front指針能往後移作準備)
        // 2. 將 front 後移，考慮取%(讓front指針若可能到最後的位置時，讓其能返回到index 0 的位置，避免陣列超出範圍)
        // 3. 將臨時保存的變數返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //顯示佇列內的所有數據
    public void showQueue() {
        //遍歷
        if (isEmpty()) {
            System.out.println("佇列為空，沒有數據");
            return;
        }
        // 思考點： 須從front開始遍歷至當前佇列中有效的數據個數
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // 求出當前佇列有效的數據個數
    public int size() {
        //EX:
        //rear = 2，front = 1，maxSize = 3
        return (rear + maxSize - front) % maxSize;
    }

    //顯示佇列的頭數據 (注意!非取出數據)
    public int headQueue() {
        //判斷
        if (isEmpty()) {
            throw new RuntimeException("佇列為空，沒有數據");
        }
        return arr[front];
    }
}