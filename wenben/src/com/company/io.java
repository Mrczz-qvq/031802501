package com.company;

import java.io.*;

public class io {
    public static String read(File file) throws IOException{
            BufferedReader f1 = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            String s = "";
            String Str1 = "";
            while ((s = f1.readLine()) != null) {//将文本转化为String
                Str1 += s + "\r\n";
            }
            return Str1;
    }
    public static void  write (double ans,File file3)
    {
        String ans1 = String.format("%.2f", ans);
        System.out.println(ans1);
        try (FileWriter writer = new FileWriter(file3)) {
            writer.write(ans1);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
