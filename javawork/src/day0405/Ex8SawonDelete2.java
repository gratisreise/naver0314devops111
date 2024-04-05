package day0405;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import day0404db.Ex1MysqlSawon;

public class Ex8SawonDelete2 {
    static final String MYSQL_DRIVER="com.mysql.cj.jdbc.Driver";
    static final String MYSQL_URL="jdbc:mysql://localhost:3306/bit701?serverTimezone=Asia/Seoul";
    static final String USERNAME="root";
    static final String PASSWORD="1234";

    public Ex8SawonDelete2() {
        // TODO Auto-generated constructor stub
        try {
            Class.forName(MYSQL_DRIVER);
            System.out.println("Mysql8 드라이버 성공!");
        } catch (ClassNotFoundException e) {
            System.out.println("Mysql8 드라이버 오류:"+e.getMessage());
        }
    }

    public void deleteSawon()
    {
        Scanner sc=new Scanner(System.in);
        String delName="";
        System.out.println("삭제할 이름을 입력하세요");
        delName=sc.nextLine();

        String sql="delete from sawon where name=?";

        Connection conn=null;
        PreparedStatement pstmt=null;

        try {
            conn=DriverManager.getConnection(MYSQL_URL, USERNAME, PASSWORD);
            pstmt=conn.prepareStatement(sql);
            //바인딩
            pstmt.setString(1, delName);
            //executeUpdate : sql문을 실행후 실행한 레코드의 갯수를 반환
            //n 이 0이면 삭제할 데이타가 없다는 의미
            int n=pstmt.executeUpdate();
            if(n==0)
                System.out.println(delName+"님은 명단에 없습니다");
            else
                System.out.println(n+"명의 "+delName+" 님을 삭제했습니다");

            //매번 워크벤치가서 확인하기 싫으면
            Ex1MysqlSawon sawon=new Ex1MysqlSawon();
            sawon.writeSawon();

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
        Ex8SawonDelete2 ex7=new Ex8SawonDelete2();
        ex7.deleteSawon();

    }

}