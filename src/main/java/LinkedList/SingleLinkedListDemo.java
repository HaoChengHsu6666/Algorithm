package LinkedList;

public class SingleLinkedListDemo {

    public static void main(String[] args) {

        //先創建節點
        HeroNode hero1 = new HeroNode(1, "竈門炭志郎", "火之神神樂~碧羅天");
        HeroNode hero2 = new HeroNode(2, "竈門禰豆子", "血鬼術~爆血");
        HeroNode hero3 = new HeroNode(3, "我妻善逸", "雷之呼吸第一式~霹靂一閃~六連!");
        HeroNode hero4 = new HeroNode(4, "嘴平伊之助", "獸之呼吸~第八牙!徹底撕碎");
        HeroNode hero5 = new HeroNode(5, "煉獄杏壽郎", "火之呼吸~奧義~煉獄!!!");

        //創建需要給鏈表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

//        無序加入
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero5);

//      加入後自動排序
        singleLinkedList.addByOrder(hero5);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero4);

        System.out.println("修改資料前~~~~~~~~~");
        singleLinkedList.list();

        //測試修改節點
        HeroNode newHeroNode1 = new HeroNode(5, "富岡義勇", "水之呼吸~第十一式~靜如止水......");
        HeroNode newHeroNode2 = new HeroNode(6, "蝴蝶忍", "蟲之呼吸~第一舞~嬉弄~");
        HeroNode newHeroNode3 = new HeroNode(7, "宇髓天元", "音之呼吸~第五式~華麗爆炸!!!");
        System.out.println("修改資料後.........");
        singleLinkedList.update(newHeroNode1);
        singleLinkedList.update(newHeroNode2);
        singleLinkedList.update(newHeroNode3);


        singleLinkedList.list();
    }

}

//定義SingleLinkedList管理英雄
class SingleLinkedList {
    //先初始化一個頭節點，頭節點固定不動，也不存放任何具體數據
    private HeroNode head = new HeroNode(0, "", "");

    //添加節點到單向鏈表
    //第一種方式：...當不考慮編號順序時
    //1. 找到當前鏈表的最後節點
    //2. 將最後這個節點的next 指向 新的節點
    public void add(HeroNode heroNode) {
        //因頭節點不能動，因此需要一個輔助變數temp
        HeroNode temp = head;
        //遍歷練表，找到最後
        while (true) {
            //找到鏈表的最後
            if (temp.next == null) {
                break;
            }
            //若沒找到最後，就將temp後移
            temp = temp.next;
        }
        //當退出while循環時，temp就指向了鏈表的最後
        //將最後這個節點的next 指向 新的節點
        temp.next = heroNode;
    }

    //第二種方式：在添加英雄時，根據排名將英雄插入到指定位置
    //(如果有這個排名，則添加失敗，並給出提示)
    public void addByOrder(HeroNode heroNode){
        //因為頭節點不能動，因此我們仍然通過一個輔助指針(變數)來幫助找到添加的位置
        //因為單鏈表，因此我們找的temp是位於添加位置的前一個節點，否則插入不了
        HeroNode temp = head;
        boolean flag = false; // flag標誌添加的編號是否存在，默認為false
        while(true){
            if(temp.next == null){ // 說明temp已經在鏈表的最後
                break;
            }
            if(temp.next.no > heroNode.no){ //位置找到，就在原temp和原temp.next之間做插入
                break;
            }else if(temp.next.no == heroNode.no){ //說明希望添加的heroNode的號已經存在
                flag = true; // 說明編號已存在
                break;
            }
            temp = temp.next; // 後移，繼續遍歷當前鏈表
        }
        //判斷flag的值
        if(flag){
            System.out.printf("準備插入的英雄編號%d已經存在了，無法加入\n", heroNode.no);
        }else {
            //插入到鏈表中，temp的後面
            // (EX：原為1->3 若插入2 則將改為 2->3；白話說法，原1的下一個是指向3，若插入2，則讓2的下一個指向3)
            heroNode.next = temp.next;
            // (EX：原為1->3 若插入2 則將改為 1->2；白話說法，原1的下一個是指向3，若插入2，則讓1的下一個指向2，最後形成1->2->3)
            temp.next = heroNode;
        }

    }

    //修改節點的信息，根據no編號來修改，即no編號不能改
    //說明
    //1. 根據 newHeroNode 的 no 來修改即可
    public void update(HeroNode newHeroNode){
        //判斷是否為空
        if(head.next == null){
            System.out.println("鏈表為空 !");
            return;
        }
        //找到需要修改的節點，根據no編號
        //定義一個輔助變數
        HeroNode temp = head.next;
        boolean flag = false; // 表示是否找到該節點
        while (true){
            if(temp == null){
               break; //已經完成遍歷
            }
            if(temp.no == newHeroNode.no){
                //找到了 !
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //最後，再根據flag判斷是否找到要修改的節點
        if(flag){
            temp.name = newHeroNode.name;
            temp.skill = newHeroNode.skill;
        }else { // 遍歷後仍無找到，回傳錯誤提示
            System.out.printf("沒有找到遍號 %d 的節點，不能修改\n", newHeroNode.no);
        }

    }



    //顯示鏈表(遍歷)
    public void list() {
        //判斷鏈表是否為空
        if ((head.next == null)) {
            System.out.println("鏈表為空");
            return;
        }
        //因頭節點不能動，因此需要一個輔助變數temp
        HeroNode temp = head.next;
        while (true) {
            //判斷是否到鏈表最後
            if (temp == null) {
                break;
            }
            //輸出節點的信息
            System.out.println(temp);
            //然後需將temp後移，否則無窮迴圈
            temp = temp.next;
        }
    }

}

//定義HeroNode，每個HeroNode物件就是一個節點
class HeroNode {
    public int no;
    public String name;
    public String skill;
    public HeroNode next; //指向下一個節點

    //構造器
    public HeroNode(int no, String name, String skill) {
        this.no = no;
        this.name = name;
        this.skill = skill;
    }

    //為了顯示方法，重寫一下toString方法
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", skill='" + skill +
                '}';
    }


}
