package test;

/**
 * Created by 王俊 on 2019/8/28.
 */
public class Test1 {
    public static void main(String []args){
       /* String a="111";
        String b=new String("111");
        String c=new String ("111");
        String x="111";
        StringBuilder stringBuilder=new StringBuilder("1"+"11");
        System.out.println(b==c);
        System.out.println(b==stringBuilder.toString());
        System.out.println(b==a);
        System.out.println(x==a);*/
        String s1 = "a";
        String s2 = s1 + "b";
        String s3 = "a" + "b";
        System.out.println(s2 == "ab");
        System.out.println(s3 == "ab");
    }
}
