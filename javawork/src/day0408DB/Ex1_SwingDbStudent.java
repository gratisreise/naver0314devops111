//package day0408;
//
//import java.awt.Color;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.text.NumberFormat;
//import java.util.Vector;
//
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//import javax.swing.table.DefaultTableModel;
//
//import db.common.DbConnect;
//import oracle.jdbc.proxy.annotation.Pre;
//
//public class Ex1_SwingDbStudent extends JFrame{
//    DbConnect db=new DbConnect();
//    JTable table;
//    DefaultTableModel tableModel;
//    JTextField tfName,tfJava,tfHtml,tfSpring;
//    JButton btnAdd,btnDel;
//    JComboBox<String> cbResult;
//    int order=1;
//
//    public Ex1_SwingDbStudent(String title) {
//        // TODO Auto-generated constructor stub
//        super(title);
//        this.setLocation(100, 100);//시작위치
//        this.setSize(500, 400);//창크기
//        this.getContentPane().setBackground(new Color(93, 199, 187));//배경색 변경
//        //this.getContentPane().setBackground(Color.yellow);//배경색 변경
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//프레임 종료
//        this.initDesign();//각종 컴포넌트 생성
//        this.setVisible(true);
//    }
//
//    public void initDesign()
//    {
//        JPanel p1=new JPanel();
//        p1.add(new JLabel("이름"));
//        tfName=new JTextField(5);
//        p1.add(tfName);
//
//        p1.add(new JLabel("Java"));
//        tfJava=new JTextField(5);
//        p1.add(tfJava);
//
//        p1.add(new JLabel("HTML"));
//        tfHtml=new JTextField(5);
//        p1.add(tfHtml);
//
//        p1.add(new JLabel("Spring"));
//        tfSpring=new JTextField(5);
//        p1.add(tfSpring);
//
//        this.add("North",p1);
//        //////////////////////////////
//        String []title= {"시퀀스","이름","Java","HTML","Spring","총점","평균"};
//        tableModel=new DefaultTableModel(title, 0);
//        table=new JTable(tableModel);
//        this.add("Center",new JScrollPane(table));
//
//        /////////////////////////////
//        JPanel p2=new JPanel();
//        btnAdd=new JButton("추가");
//        btnDel=new JButton("삭제");
//        p2.add(btnAdd);
//        p2.add(btnDel);
//
//        String []cb= {"추가순","이름순","총점높은순","총점낮은순"};
//        cbResult=new JComboBox<String>(cb);
//        p2.add(new JLabel("출력순서"));
//        p2.add(cbResult);
//        this.add("South",p2);
//
//        //콤보박스 이벤트
//        cbResult.addItemListener(new ItemListener() {
//
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                // TODO Auto-generated method stub
//                order=cbResult.getSelectedIndex()+1;
//                //다시 출력
//                writeStudent();
//            }
//        });
//
//        //추가버튼 이벤트
//        btnAdd.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // TODO Auto-generated method stub
//                //입력안했을경우 이벤트 종료
//                if(tfName.getText().length()==0)
//                    return;
//                if(tfJava.getText().length()==0)
//                    return;
//                if(tfHtml.getText().length()==0)
//                    return;
//                if(tfSpring.getText().length()==0)
//                    return;
//
//                //입력값을 db 에 insert
//                Connection conn=db.getConnection();
//                PreparedStatement pstmt=null;
//                String sql="insert into student values (null,?,?,?,?,now())";
//
//                try {
//                    pstmt=conn.prepareStatement(sql);
//                    //? 갯수만큼 바인딩
//                    pstmt.setString(1, tfName.getText());
//                    pstmt.setInt(2, Integer.parseInt(tfJava.getText()));
//                    pstmt.setInt(3, Integer.parseInt(tfHtml.getText()));
//                    pstmt.setInt(4, Integer.parseInt(tfSpring.getText()));
//
//                    //실행
//                    pstmt.execute();
//                    //입력값 초기화
//                    tfName.setText("");
//                    tfJava.setText("");
//                    tfHtml.setText("");
//                    tfSpring.setText("");
//
//                    //다시 출력
//                    writeStudent();
//
//                } catch (SQLException e1) {
//                    // TODO Auto-generated catch block
//                    e1.printStackTrace();
//                }finally {
//                    db.dbClose(pstmt, conn);
//                }
//            }
//        });
//
//        //삭제 이벤트
//        btnDel.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // TODO Auto-generated method stub
//                //테이블의 선택한 행번호 row를 얻는다
//                int row=table.getSelectedRow();
//                //row가 -1이면 경고메세지후 이벤트 종료
//                if(row==-1) {
//                    JOptionPane.showMessageDialog(Ex1_SwingDbStudent.this, "삭제할 행을 선택해주세요");
//                    return;
//                }
//                //row행의 0번열에 있는 시퀀스값을 얻는다 getValueAt
//                String num=table.getValueAt(row, 0).toString();
//
//                //그 시퀀스에 해당하는 db데이타를 삭제하는 sql문 작성
//                String sql="delete from student where num=?";
//
//                //Connection,PreparedStatement 생성후 sql 문 실행
//                Connection conn=db.getConnection();
//                PreparedStatement pstmt=null;
//
//                try {
//                    pstmt=conn.prepareStatement(sql);
//                    pstmt.setString(1, num);
//                    pstmt.execute();//db 에서 삭제
//
//                    //db 다시 불러오기
//                    writeStudent();
//
//                } catch (SQLException e1) {
//                    // TODO Auto-generated catch block
//                    e1.printStackTrace();
//                }finally {
//                    db.dbClose(pstmt, conn);
//                }
//            }
//        });
//
//        //출력 메서드 호출
//        writeStudent();
//    }
//
//    //출력 메서드
//    public void writeStudent()
//    {
//        Connection conn=db.getConnection();
//        PreparedStatement pstmt=null;
//        ResultSet rs=null;
//        String sql="";
//        if(order==1)
//            sql="select *,java+html+spring tot from student order by num asc";
//        else if(order==2)
//            sql="select *,java+html+spring tot from student order by name asc";
//        else if(order==3)
//            sql="select *,java+html+spring tot from student order by tot desc";
//        else if(order==4)
//            sql="select *,java+html+spring tot from student order by tot asc";
//
//        try {
//            pstmt=conn.prepareStatement(sql);
//            rs=pstmt.executeQuery();
//
//            //기존 테이블의 데이타 지운후 추가하기
//            tableModel.setRowCount(0);
//
//            while(rs.next())
//            {
//                Vector<String> data=new Vector<String>();
//                data.add(rs.getString("num"));
//                data.add(rs.getString("name"));
//                data.add(rs.getString("java"));
//                data.add(rs.getString("html"));
//                data.add(rs.getString("spring"));
//                data.add(rs.getString("tot"));
//
//                //평균 구하기
//                NumberFormat nf=NumberFormat.getInstance();
//                nf.setMaximumFractionDigits(2);//소숫점2자리
//                double avg=rs.getInt("tot")/3.0;
//
//                //문자열로 변환후 추가
//                data.add(String.valueOf(nf.format(avg)));
//
//                //table에 추가
//                tableModel.addRow(data);
//            }
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }finally {
//            db.dbClose(pstmt, conn, rs);
//        }
//
//    }
//
//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        Ex1_SwingDbStudent a=new Ex1_SwingDbStudent("학생관리DB");
//    }
//
//}