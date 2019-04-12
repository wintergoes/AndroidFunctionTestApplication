package bcoder.com.androidfunctiontestapplication.unittest;

public class HeapTest {

    public static void main(String args[]){
        String str1 = "Hello";
        String str2 = "Hello";

        //栈的共享，大部分情况下即使str1和str2的值相等，str1==str2也不一定返回true
        //但是这种情况下是相等的

        System.out.print(str1 == str2);
    }
}
