package com.company;

import org.junit.Assert;

import static org.junit.Assert.*;

class MainTest {



    @org.junit.Test
    public static void testEd(){
        String [] testNames = new String[]{
                "src/sim_0.8/orig_0.8_add.txt",
                "src/sim_0.8/orig_0.8_del.txt",
                "src/sim_0.8/orig_0.8_dis_1.txt",
                "src/sim_0.8/orig_0.8_dis_3.txt",
                "src/sim_0.8/orig_0.8_dis_7.txt",
                "src/sim_0.8/orig_0.8_dis_10.txt",
                "src/sim_0.8/orig_0.8_dis_15.txt",
                "src/sim_0.8/orig_0.8_mix.txt",
                "src/sim_0.8/orig_0.8_rep.txt"
        };
        for (int i=0;i<testNames.length;i++) {
            System.out.println("测试"+i+':');
            double ans = Test.test(testNames[i]);
            Assert.assertEquals(0.8,ans,0.2);
        }
    }

}