package day0405;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ex6SawonInsert2 {
    static final String MYSQL_DRIVER="com.mysql.cj.jdbc.Driver";
    static final String MYSQL_URL="jdbc:mysql://localhost:3306/bit701?serverTimezone=Asia/Seoul";
    static final String USERNAME="root";
    static final String PASSWORD="1234";

    public Ex6SawonInsert2() {
        // TODO Auto-generated constructor stub
        try {
            Class.forName(MYSQL_DRIVER);
            System.out.println("Mysql8 드라이버 성공!");
        } catch (ClassNotFoundException e) {
            System.out.println("Mysql8 드라이버 오류:"+e.getMessage());
        }
    }

    public void insertSawon()
    {
        Scanner sc=new Scanner(System.in);
        String name,gender,buseo;
        int score,gnum,bnum;
        String sql="";

        System.out.println("이름은?");
        name=sc.nextLine();
        System.out.println("점수는?");
        score=Integer.parseInt(sc.nextLine());
        System.out.println("성별은 선택 1.남자  2.여자");
        gnum=Integer.parseInt(sc.nextLine());
        gender=gnum==1?"남자":"여자";

        System.out.println("부서는 1.홍보부  2.인사부  3.교육부");
        bnum=Integer.parseInt(sc.nextLine());
        buseo=bnum==1?"홍보부":bnum==2?"인사부":"교육부";

        sql="insert into sawon (name,score,gender,buseo) values (?,?,?,?)";

        Connection conn=null;
        PreparedStatement pstmt=null;

        try {
            conn=DriverManager.getConnection(MYSQL_URL, USERNAME, PASSWORD);
            pstmt=conn.prepareStatement(sql);
            //4개의 ? 에 차례대로 값을 바인딩
            pstmt.setString(1, name);
            pstmt.setInt(2, score);
            pstmt.setString(3, gender);
            pstmt.setString(4, buseo);

            //바인딩이 모두 끝난후 execute
            pstmt.execute();

            System.out.println("데이타 추가 성공!");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException|NullPointerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Ex6SawonInsert2 ex5=new Ex6SawonInsert2();
        ex5.insertSawon();
    }

}