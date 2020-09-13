package com.company;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main<Length1> {


    public static void main(String[] args) throws IOException {//io异常抛出
        // write your code here
     //   ArrayList list = new ArrayList();
    //    args = new String[3]; 
        String filepath = args[0];
        File file = new File(filepath);
        File file2 = new File(args[1]);
        File file3 = new File(args[2]);
        System.out.println("文件的绝对路径：" + file.getAbsolutePath());
        System.out.println("文件的绝对路径：" + file2.getAbsolutePath());
        System.out.println("文件的绝对路径：" + file3.getAbsolutePath());

      /*  try {                                 文本是否读入
            Reader fr = new FileReader(filepath);
            char[] data = new char[1000];
            int length = 0;
            while ((length = fr.read(data)) > 0) {
                String str = new String(data, 0, length);
                System.out.println(str);
            }
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        BufferedReader f1 = new BufferedReader(new InputStreamReader(new  FileInputStream(file)));
        BufferedReader f2 = new BufferedReader(new InputStreamReader(new  FileInputStream(file2)));
        String  s = new String();
        String Str1 ="";
        String Str2 ="";
        while((s = f1.readLine()) != null){//将文本转化为String
            Str1 += s +"\r\n" ;
        }
        while ((s = f2.readLine())!=null){
            Str2 += s +"\r\n" ;
        }
      //  System.out.println(Str1.toString());
      //  System.out.println(Str2.toString());
        //length
        int Length1 = Str1.length();
        int Length2 = Str2.length();//编辑操作包括将一个字符替换成另一个字符，插入一个字符，删除一个字符
          int ed = getEd(Str1,Str2,Length1,Length2);//获得最小编辑距离（动态规划）
         double ans=1-1.0*ed/Math.max(Length1,Length2);//求得相似度
       // double bb = getSimilarity(Str_1,Str_2);余弦定理方法
        String ans1 =String.format("%.2f",ans);
        System.out.println(ans1);
        try (FileWriter writer = new FileWriter(file3)) {
            writer.write(ans1);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static int getEd(String str1,String str2,int l1, int l2){//动态规划求最小编辑距离
        int Distance=0;
        int ed = 0;
        if(l1!=0&&l2!=0){
            int[][] Distance_shuzu=new int[l1+1][l2+1];
            //编号
            int Bianhao=0;
            for (int i = 0; i <= l1; i++) {//初始化，给每个字符编号
                Distance_shuzu[i][0]=Bianhao;
                Bianhao++;
            }
            Bianhao=0;
            for (int i = 0; i <=l2; i++) {
                Distance_shuzu[0][i]=Bianhao;
                Bianhao++;
            }


            char[] Str1_CharArray=str1.toCharArray();
            char[] Str2_CharArray=str2.toCharArray();
            for (int i = 1; i <= l1; i++) {
                for(int j=1;j<=l2;j++){
                    if(Str1_CharArray[i-1]==Str2_CharArray[j-1]){
                        Distance=0;
                    }
                    else{
                        Distance=1;
                    }

                    int Temp1=Distance_shuzu[i-1][j]+1;//增
                    int Temp2=Distance_shuzu[i][j-1]+1;//减
                    int Temp3=Distance_shuzu[i-1][j-1]+Distance;//改

                    Distance_shuzu[i][j]=Math.min(Temp1,Temp2);//找最小的一步
                    Distance_shuzu[i][j]=Math.min(Temp3,Distance_shuzu[i][j]);

                }

            }

            ed=Distance_shuzu[l1][l2];
        }
        return ed;
    }
   /*public static int  getEd2(String str1,String str2,int l1, int l2){
 //StackOverflowError堆栈溢出异常，递归不能用
        int op1 = getEd2(str1,str2,l1-1,l2)+1;
        int op2 = getEd2(str1,str2,l1,l2-1)+1;
        int op3 = getEd2(str1,str2,l1-1,l2-1);
        if(str1.charAt(l1-1)!=str2.charAt(l2-1)){
            op3 += 1;
        }
        return Math.min(Math.min(op1,op2),op3);
    }*/
    /*
    //利用余弦相似度算法
   public static doguble getSimilarity(String s1, String s2) {
           Map<Character, int[]> Mymap = new HashMap<>();//将两个字符串中的中文字符以及出现的总数封装到map中；
           for (int i = 1; i < s1.length()-2; i++) //将文本转化String时多出一些空格，减去
           {
               char d1 = s1.charAt(i); //  d1=第i个汉字，每一个汉字，标点不比较
               if(isHanZi(d1)) {
                   int[] num = Mymap.get(d1);
                   if (num != null && num.length == 2) {
                       num[0]++; //已有该字符，加1,原文的字符数量提取
                   } else {
                       num = new int[2];
                       num[0] = 1;//原文
                       num[1] = 0;//比较文
                       Mymap.put(d1, num);//将字符加入mymap
                   }
                   System.out.println(d1 + Arrays.toString(num));
               }
           }
           System.out.println(" ");
           for (int i = 1; i < s2.length()-2; i++) {//将文本转化String时多出一些空格，减去
               char d2 = s2.charAt(i);
               if(isHanZi(d2)) {
                   int[] num = Mymap.get(d2);
                   if (num != null && num.length == 2) {
                       num[1]++;
                   } else {
                       num = new int[2];
                       num[0] = 0;
                       num[1] = 1;
                       Mymap.put(d2, num);
                   }
                   System.out.println(d2 + Arrays.toString(num));
               }
           }
           double sq1 = 0;
           double sq2 = 0;
           double denuminator = 0;
           for (Map.Entry entry : Mymap.entrySet()) //用map的entryset遍历所有key-value；
           {
               int[] c = (int[]) entry.getValue();//章节1中出现的字为：Z1c1,Z1c2,Z1c3,Z1c4……Z1cn；它们在章节中的个数为：Z1n1,Z1n2,Z1n3……Z1nm；
               //章节2中出现的字为：Z2c1,Z2c2,Z2c3,Z2c4……Z2cn；它们在章节中的个数为：Z2n1,Z2n2,Z2n3……Z2nm；
               denuminator += c[0] * c[1];     //z1n1*z2n1+z1n2*z2n2...
               sq1 += c[0] * c[0];      //z1n1^2+z1n2^2+..
               sq2 += c[1] * c[1];      //Z2n1^2+z2n2^2+..
           }
           return denuminator / (Math.sqrt(sq1) * Math.sqrt(sq2));//余弦定理
       }

    public static boolean isHanZi(char ch) {
        // 判断是否汉字
        return (ch >= 0x4E00 && ch <= 0x9FA5);
    }*/
}
