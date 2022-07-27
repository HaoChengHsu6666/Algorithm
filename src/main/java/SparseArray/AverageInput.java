package SparseArray;

import java.util.Scanner;

public class AverageInput {

    public static void main(String[] args) {
        System.out.println("算出全部人的平均分數如下");
        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入XX的人數:\n");
        int length = scanner.nextInt();
        float[] score = new float[length];

        for (int i = 0; i < score.length; i++) {
            System.out.println("請輸入第"+ (i+1) +"個分數:");
            float input = scanner.nextFloat();
            score[i] = input;
        }

        System.out.println("個別分數為: ");
        float total = 0;
        for (int i = 0; i < score.length; i++) {
           total = total + score[i];
           System.out.print(score[i] + ",");
        }
        System.out.printf("\n總分平均為 : %.2f", total / score.length);

    }
}
