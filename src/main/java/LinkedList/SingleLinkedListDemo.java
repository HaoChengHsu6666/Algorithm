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
        //加入
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        singleLinkedList.add(hero5);

        singleLinkedList.list();
    }

}

//定義SingleLinkedList管理英雄
class SingleLinkedList {
    //先初始化一個頭節點，頭節點固定不動，也不存放任何具體數據
    private HeroNode head = new HeroNode(0, "", "");

    //添加節點到單向鏈表
    //思考點...當不考慮編號順序時
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
