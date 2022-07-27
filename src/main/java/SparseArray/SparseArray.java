package SparseArray;

public class SparseArray {

    public static void main(String[] args) {
        //創建一個原始的二維陣列 11 * 11
        //0: 表示沒有棋子，1表示黑子 2表示藍子
        int chessArr1[][] = new int[11][21];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[3][5] = 3;
        //輸出原始的二維陣列
        System.out.println("原始的二微陣列");
        //二維陣列中取出一維陣列(Row)，再從一維陣列(Row)中取出個數(Column)的值
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //1: 先遍歷二維陣列 得到非0數據的個數
        int sum = 0;
        //(範圍為"列"(Row)的長度)
        for (int i = 0; i < chessArr1.length; i++) {
            //(範圍為"欄"(Column)的長度)
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("========================");
        System.out.println("非零個數的sum為: " + sum);

        //2.創建對應的稀疏陣列(sparseArr)
        int sparseArr[][] = new int[sum + 1][3];
        //給稀疏陣列賦值
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;

        //遍歷二維陣列，將非0的值存放到稀疏陣列(sparseArr)中
        int count = 0; // count 用於紀錄是第幾個非0數據
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1.length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        // 輸出稀疏陣列的形式
        System.out.println("========================");
        System.out.println("得到的稀疏陣列為???");
        for (int i=0;i <sparseArr.length; i++){
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        System.out.println();






        //將稀疏陣列 恢復成 原始的二維陣列
        //1.先讀取稀疏陣列的第一行，根據第一行數據，創建原始的二維陣列

        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        //2. 在讀取稀疏陣列後幾行的數據(從第二行開始(i=1))，並賦予 原始的二微陣列 即可

        for(int i=1; i<sparseArr.length; i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        System.out.println("========================");
        System.out.println("恢復後的二維陣列");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
