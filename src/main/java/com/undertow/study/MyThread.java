package com.undertow.study;

public class MyThread implements Runnable
{
    int i = 0;
    int a = 20;
    static int b = 20;
    static int c = 20;
    static int d = 20;
    final int finalIntNoStatic = a++;
    static int staticInt = b++;
    final int finalInt = c++;
    static final int STATIC_FINAL_INT = d++;

    @Override
    public void run()
    {
        while (i < 10)
        {
            System.out.println("i=" + i + ",finalIntNoStatic=" + finalIntNoStatic + ",a=" + a
                    + ",staticInt=" + staticInt + ",b=" + b + ",finalInt=" + finalInt + ",c=" + c
                    + ",STATIC_FINAL_INT=" + STATIC_FINAL_INT + ",d=" + d);
            i++;
        }
    }
}
