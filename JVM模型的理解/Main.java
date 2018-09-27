package com.company;
public class Main {

    public static void main(String[] args) {
	// write your code here
//        String s1 = new String("这他妈是什么鬼");
//        String s2 = new String("这他妈是什么鬼");
//        String intern1 = s1.intern();
//        String intern2 = s2.intern();
//        System.out.println(intern1==s1);
//        System.out.println(intern2==s2)；

          String s11 = new StringBuffer("这他妈").append("是什么鬼").toString();
         //String s11 = new String("这他妈是什么鬼");
          System.out.println(s11==s11.intern());//true,但是利用第二行的s11就会false;
                                    //按道理常量池中应该存在的是堆的引用，不存在复制实例，但是直接使用new String的BUG
          String s1 = new String("这他妈是什么鬼");

            System.out.println(s1.intern()==s11);//true证明了常量池中保存的是堆中实例对象字符串首次出现的引用


    }

}
