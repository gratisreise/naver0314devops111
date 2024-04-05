package day0329;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Ex5_SwingTableModel extends JFrame{
    JLabel lblTitle;
    JTable table;
    DefaultTableModel tableModel;
    JButton btnDelete;

    public Ex5_SwingTableModel(String title) {
        // TODO Auto-generated constructor stub
        super(title);
        this.setLocation(100, 100);//시작위치
        this.setSize(500, 300);//창크기
        //this.getContentPane().setBackground(new Color(93, 199, 187));//배경색 변경
        //this.getContentPane().setBackground(Color.yellow);//배경색 변경
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//프레임 종료
        this.initDesign();//각종 컴포넌트 생성
        this.setVisible(true);
    }

    public void initDesign()
    {
        //상단에는 제목 넣기
        lblTitle=new JLabel("강남고 학생 정보", JLabel.CENTER);
        lblTitle.setFont(new Font("", Font.BOLD, 30));
        this.add("North",lblTitle);

        //테이블을 행갯수 0, 제목만 넣어서 생성후 Center 에 추가
        String []title= {"이름","나이","Java","Spring","HTML","총점","평균"};
        tableModel=new DefaultTableModel(title, 0);
        table=new JTable(tableModel);
        this.add("Center",new JScrollPane(table));
        this.addStudentFile();
        this.process();

        //삭제버튼 생성및 이벤트
        btnDelete=new JButton("학생정보 삭제하기");
        this.add("South",btnDelete);

        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //선택한 행
                int row=table.getSelectedRow();
                System.out.println(row);//선택을 안했을경우 -1 발생

                if(row==-1)
                    JOptionPane.showMessageDialog(Ex5_SwingTableModel.this, "먼저 삭제할 행을 선택해주세요");
                else
                    tableModel.removeRow(row);
            }
        });
    }

    //총점,평균 계산해주는 메서드
    public void process()
    {
        for(int i=0;i<tableModel.getRowCount();i++)
        {
            //각 행마다 3과목의 점수를 읽어서 int 로 변환
            int java=Integer.parseInt(table.getValueAt(i, 2).toString());
            int spring=Integer.parseInt(table.getValueAt(i, 3).toString());
            int html=Integer.parseInt(table.getValueAt(i, 4).toString());

            //총점구하기
            int tot=java+spring+html;
            //5번열에 출력
            table.setValueAt(tot, i, 5);
            //평균 구하기
            double avg=tot/3.0;
            //6번열에 출력
            //평균은 소숫점 이하 2자리로
            NumberFormat nf=NumberFormat.getInstance();
            nf.setMaximumFractionDigits(2);
            table.setValueAt(nf.format(avg), i, 6);
        }
    }
    public void addStudentFile()
    {
        FileReader fr=null;
        BufferedReader br=null;
        try {
            fr=new FileReader("d:/naver0314/student.txt");
            br=new BufferedReader(fr);
            while(true)
            {
                String s=br.readLine();
                if(s==null)
                    break;
                String []data=s.split(",");
                //테이블모델에 추가
                tableModel.addRow(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("파일을 못찾았어요"+e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                br.close();
                fr.close();
            }catch (IOException|NullPointerException e) {
                // TODO: handle exception
            }
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Ex5_SwingTableModel a=new Ex5_SwingTableModel("student파일 읽기");
    }

}