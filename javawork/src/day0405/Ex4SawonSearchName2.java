package day0405;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ex4SawonSearchName2 {
    static final String MYSQL_DRIVER="com.mysql.cj.jdbc.Driver";
    static final String MYSQL_URL="jdbc:mysql://localhost:3306/bit701?serverTimezone=Asia/Seoul";
    static final String USERNAME="root";
    static final String PASSWORD="1234";

    public Ex4SawonSearchName2() {
        // TODO Auto-generated constructor stub
        try {
            Class.forName(MYSQL_DRIVER);
            System.out.println("Mysql8 드라이버 성공!");
        } catch (ClassNotFoundException e) {
            System.out.println("Mysql8 드라이버 오류:"+e.getMessage());
        }
    }

    public void searchSawon(String searchName)
    {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="select * from sawon where name like ?";

        boolean find=false;

        try {
            conn=DriverManager.getConnection(MYSQL_URL, USERNAME, PASSWORD);
            pstmt=conn.prepareStatement(sql);
            //? 갯수만큼 바인딩
            pstmt.setString(1, "%"+searchName+"%");
            //?에 값을 다 넣은후 실행
            rs=pstmt.executeQuery();

            System.out.println("** "+searchName+" 검색결과 **");
            System.out.println("=".repeat(40));
            System.out.println("시퀀스\t이름\t성별\t부서\t점수");
            System.out.println("=".repeat(40));

            while(rs.next())
            {
                int num=rs.getInt("num");
                String name=rs.getString("name");
                String gender=rs.getString("gender");
                String buseo=rs.getString("buseo");
                int score=rs.getInt("score");
                System.out.println(num+"\t"+name+"\t"+gender+"\t"+buseo+"\t"+score);

                find=true;
            }

            if(!find)
                System.out.println(searchName+" 을 포함하는 사원은 없습니다");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                rs.close();
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
        Ex4SawonSearchName2 ex3=new Ex4SawonSearchName2();
        Scanner sc = new Scanner(System.in);
        String searchName="";
        while(true)
        {
            System.out.println("검색할 이름을 입력해주세요(종료:x)");
            searchName = sc.nextLine();
            if(searchName.equalsIgnoreCase("x"))
            {
                System.out.println("종료합니다");
                break;
            }

            ex3.searchSawon(searchName);
            System.out.println();
        }
    }

}