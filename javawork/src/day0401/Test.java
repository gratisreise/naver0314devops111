package day0401;

public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String s="aaa|bbbb|ccc|ddd";
        String []a=s.split("\\|");
        for(String ss:a)
            System.out.println(ss);
    }

}