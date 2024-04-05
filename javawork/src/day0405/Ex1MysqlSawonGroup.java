package day0405;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex1MysqlSawonGroup {
    static final String MYSQL_DRIVER="com.mysql.cj.jdbc.Driver";
    static final String MYSQL_URL="jdbc:mysql://localhost:3306/bit701?serverTimezone=Asia/Seoul";
    static final String USERNAME="root";
    static final String PASSWORD="1234";


    public Ex1MysqlSawonGroup() {
        // TODO Auto-generated constructor stub
        try {
            Class.forName(MYSQL_DRIVER);
            System.out.println("Mysql8 드라이버 성공!");
        } catch (ClassNotFoundException e) {
            System.out.println("Mysql8 드라이버 오류:"+e.getMessage());
        }
    }

    public void writeSawonGroup()
    {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        String sql="""
				select buseo,count(*) inwon,max(score) maxscore,
				ceiling(avg(score)) avgscore
				from sawon
				group by buseo
				""";

        try {
            conn=DriverManager.getConnection(MYSQL_URL, USERNAME, PASSWORD);
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);

            System.out.println("\t** 부서별 현황**\n");
            System.out.println("부서명\t인원수\t최고점수\t평균점수");
            System.out.println("=".repeat(30));
            while(rs.next())
            {
                String buseo=rs.getString("buseo");
                int inwon=rs.getInt("inwon");
                int max=rs.getInt("maxscore");
                int avg=rs.getInt("avgscore");

                System.out.println(buseo+"\t"+inwon+"명\t"+max+"점\t"+avg+"점");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException|NullPointerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Ex1MysqlSawonGroup ex1=new Ex1MysqlSawonGroup();
        ex1.writeSawonGroup();
    }

}