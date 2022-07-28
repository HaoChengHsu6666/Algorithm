package HashTable;

import java.util.Scanner;

public class HashTableDemo {

    public static void main(String[] args) {

        //創建哈希表
        HashTable hashTable = new HashTable(7);

        //寫一個簡單的菜單
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加員工");
            System.out.println("list: 顯示員工");
            System.out.println("find: 查找員工");
            System.out.println("exit: 退出系統");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("輸入id");
                    int id = scanner.nextInt();
                    System.out.println("輸入名字");
                    String name = scanner.next();
                    //創建員工
                    Employee employee = new Employee(id, name);
                    hashTable.add(employee);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.println("請輸入要查找的id");
                    id = scanner.nextInt();
                    hashTable.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }

        }

    }
}

class Employee {

    public int id;
    public String name;
    public Employee next; //next 預設為 null
    public Employee(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

//創建HashTable 管理多條鏈表
class HashTable {
    private EmployeeLinkedList[] employeeLinkedListArray;
    private int size; //表示共有多少條鏈表

    //構造器
    public HashTable(int size) {
        this.size = size;
        //初始化employeeLinkedListArray
        employeeLinkedListArray = new EmployeeLinkedList[size];
        //可能會遇到空指針的問題，這時候則需(利用遍歷)初始化每個鏈表
        for (int i=0; i<size; i++){
            employeeLinkedListArray[i] = new EmployeeLinkedList();
        }
    }

    //添加員工
    public void add(Employee employee) {
        //根據員工的id，得到該員工應當添加到哪條鏈表
        int empLinkedListNo = hashFun(employee.id);
        //將emp 添加到對應的鏈表中
        employeeLinkedListArray[empLinkedListNo].add(employee);
    }

    //遍歷所有的鏈表，遍歷HashTable
    public void list() {
        for (int i = 0; i < size; i++) {
            employeeLinkedListArray[i].list(i);
        }
    }

    //根據輸入的id，查找員工
    public void findEmpById(int id){
        //使用散列函數確定到哪條鏈表查找
        int empLinkedListNo = hashFun(id);
        Employee employee = employeeLinkedListArray[empLinkedListNo].findEmpById(id);
        if(employee != null){
            System.out.printf("在第%d條鏈表中找到 員工id為：%d\n", (empLinkedListNo + 1), id);

        }else {
            System.out.println("在哈希表中，沒有找到該員工");
        }
    }


    //編寫散列函數，使用一個簡單的取 % 法
    public int hashFun(int id) {
        return id % size;
    }
}

    //創建EmployeeLinkedList，表示鏈表
    class EmployeeLinkedList {
        //頭指針，執行第一個Emp，因此這個列表的頭，是直接指向第一個Emp
        private Employee head;

        //添加員工到鏈表
        //1. 假設，當添加員工時，id 是自增長，即id的分配總是由小到大
        // 因此將該員工直接加入到本鏈表的最後即可
        public void add(Employee employee) {
            //如果是添加第一個員工
            if (head == null) {
                head = employee;
                return;
            }
            //如果不是第一個員工，則使用一個遍歷輔助指針(curEmp)幫助定位到最後
            Employee curEmp = head;
            while (true) {
                if (curEmp.next == null) { //直到鏈表最後跳出
                    break;
                }
                curEmp = curEmp.next; //往後移繼續找
            }
            // 退出時直接將employee加入鏈表
            curEmp.next = employee;
        }

        // 遍歷鏈表的員工信息
        public void list(int no) {
            if (head == null) { //說明鏈表為空
                System.out.println("第 "+ (no+1) +" 鏈表為空");
                return;
            }
            System.out.print("第 "+ (no+1) +" 鏈表信息為：");
            Employee curEmp = head; //輔助指針
            while (true) {
                System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
                if (curEmp.next == null) { //說明curEmp已經是最後節點
                    break;
                }
                curEmp = curEmp.next; //後移。遍歷
            }
            System.out.println();
        }

        //根據id查找員工
        //如果查找到，就返回Emp、如果沒找到，則返回null
        public Employee findEmpById(int id){
            //判斷鏈表是否為空
            if(head == null){
                System.out.println("鏈表為空");
                return null;
            }
            //輔助指針
            Employee curEmp = head;
            while (true){
                if(curEmp.id == id){ // 找到
                    break; // 這時curEmp就指向要查找的雇員
                }
                // 退出
                if(curEmp.next == null){ // 說明遍歷當前鏈表沒有找到該雇員
                    curEmp = null;
                    break;
                }
                curEmp = curEmp.next;// 往後繼續遍歷
            }
            return curEmp;
        }


    }


