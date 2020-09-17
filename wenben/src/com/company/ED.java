package com.company;

public class ED {
    public static int getEd(String str1, String str2, int l1, int l2) {//动态规划求最小编辑距离
        int Distance = 0;
        int ed = 0;
        if (l1 != 0 && l2 != 0) {
            int[][] Distance_shuzu = new int[l1 + 1][l2 + 1];
            //编号
            int Bianhao = 0;
            for (int i = 0; i <= l1; i++) {//初始化，给每个字符编号
                Distance_shuzu[i][0] = Bianhao;
                Bianhao++;
            }
            Bianhao = 0;
            for (int i = 0; i <= l2; i++) {
                Distance_shuzu[0][i] = Bianhao;
                Bianhao++;
            }


            char[] Str1_CharArray = str1.toCharArray();
            char[] Str2_CharArray = str2.toCharArray();
            for (int i = 1; i <= l1; i++) {
                for (int j = 1; j <= l2; j++) {
                    if (Str1_CharArray[i - 1] == Str2_CharArray[j - 1]) {//相同不变
                        Distance = 0;
                    } else {
                        Distance = 1;
                    }

                    int Temp1 = Distance_shuzu[i - 1][j] + 1;//增
                    int Temp2 = Distance_shuzu[i][j - 1] + 1;//减
                    int Temp3 = Distance_shuzu[i - 1][j - 1] + Distance;//改

                    Distance_shuzu[i][j] = Math.min(Temp1, Temp2);//找最小的一步
                    Distance_shuzu[i][j] = Math.min(Temp3, Distance_shuzu[i][j]);

                }

            }

            ed = Distance_shuzu[l1][l2];
        }
        return ed;
    }
}
