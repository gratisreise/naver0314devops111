package day0329;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.crypto.interfaces.PBEKey;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Ex7_SwingFileMunje extends JFrame{
    JButton btnSawonAdd,btnSawonDel;
    JTable table;
    DefaultTableModel tableModel;
    JTextField txtName;
    JComboBox<String> cbBlood,cbCity,cbGrade;

    final static String FILENAME="d:/naver0314/sawon.txt";

    public Ex7_SwingFileMunje(String title) {
        // TODO Auto-generated constructor stub
        super(title);
        this.setLocation(100, 100);//시작위치
        this.setSize(500, 400);//창크기
        this.getContentPane().setBackground(new Color(93, 199, 187));//배경색 변경
        //this.getContentPane().setBackground(Color.yellow);//배경색 변경
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//프레임 종료

        //종료시 발생하는 이	벤트
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
                super.windowClosing(e);
                //자동저장해주는 메서드 호출
                sawonDataWrite();
                //시스템 종료
                System.exit(0);
            }
        });
        this.initDesign();//각종 컴포넌트 생성
        this.sawonDataRead();
        this.setVisible(true);
    }

    //처음 생성시 파일 	불러오기
    public void sawonDataRead()
    {
        FileReader fr=null;
        BufferedReader br=null;
        try {
            fr=new FileReader(FILENAME);
            br=new BufferedReader(fr);
            while(true)
            {
                String s=br.readLine();
                if(s==null)
                    break;
                //한줄 읽어온 자료는 배열로 변환
                String []data=s.split(",");
                //테이블에 추가
                tableModel.addRow(data);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "읽어올 사원정보가 없습니다");
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

    //종료시 파일 저장
    public void sawonDataWrite()
    {
        FileWriter fw=null;
        try {
            fw=new FileWriter(FILENAME);
            //행갯수만큼 반복해서 데이타를 읽어서 파일에 저장(컴마로 연결)
            for(int i=0;i<table.getRowCount();i++)
            {
                String s=table.getValueAt(i, 0)+","+table.getValueAt(i, 1)+","+
                        table.getValueAt(i, 2)+","+table.getValueAt(i, 3)+"\n";
                fw.write(s);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                fw.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void initDesign()
    {
        //상단
        JPanel pTop=new JPanel();
        btnSawonAdd=new JButton("사원정보 추가");
        btnSawonDel=new JButton("사원정보 삭제");
        pTop.add(btnSawonAdd);
        pTop.add(btnSawonDel);
        this.add("North",pTop);

        //중간
        String []title= {"이름","혈액형","지역","직급"};
        tableModel=new DefaultTableModel(title, 0);
        table=new JTable(tableModel);
        this.add("Center",new JScrollPane(table));

        //하단
        JPanel pBottom=new JPanel();
        pBottom.add(new JLabel("이름"));
        txtName=new JTextField(4);
        pBottom.add(txtName);

        pBottom.add(new JLabel("혈액형"));
        String []b= {"A형","B형","O형","AB형"};
        cbBlood=new JComboBox<String>(b);
        pBottom.add(cbBlood);

        pBottom.add(new JLabel("지역"));
        String []c= {"서울","경기김포","경기용인","부산","제주도"};
        cbCity=new JComboBox<String>(c);
        pBottom.add(cbCity);

        pBottom.add(new JLabel("직급"));
        String []j= {"사원","대리","차장","과장","부장"};
        cbGrade=new JComboBox<String>(j);
        pBottom.add(cbGrade);

        this.add("South",pBottom);

        //추가 버튼 이벤트
        btnSawonAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(txtName.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(Ex7_SwingFileMunje.this, "이름을 입력해주세요");
                    return;
                }

                Vector<String> data=new Vector<String>();
                data.add(txtName.getText());
                data.add(cbBlood.getSelectedItem().toString());
                data.add(cbCity.getSelectedItem().toString());
                data.add(cbGrade.getSelectedItem().toString());

                //테이블모델에 추가
                tableModel.addRow(data);
                //초기화
                txtName.setText("");
                cbBlood.setSelectedIndex(0);
                cbCity.setSelectedIndex(0);
                cbGrade.setSelectedIndex(0);

            }
        });

        //사원 삭제 이벤트
        btnSawonDel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //선택한 행번호 얻기
                int row=table.getSelectedRow();
                if(row==-1) {
                    JOptionPane.showMessageDialog(Ex7_SwingFileMunje.this, "삭제할 행을 선택해주세요");
                    return;
                }

                //선택한 행 삭제
                tableModel.removeRow(row);
            }
        });
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Ex7_SwingFileMunje a=new Ex7_SwingFileMunje("사원관리");
    }

}