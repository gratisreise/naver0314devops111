package day0329;

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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Ex6_SwingTableShop extends JFrame{
    JTable table;
    DefaultTableModel tableModel;
    JButton btnAdd,btnDelete;
    JTextField tfSang,tfSu,tfDan;

    final static String FILENAME="d:/naver0314/myshop.txt";

    public Ex6_SwingTableShop(String title) {
        // TODO Auto-generated constructor stub
        super(title);
        this.setLocation(100, 100);//시작위치
        this.setSize(400, 400);//창크기
        //this.getContentPane().setBackground(new Color(93, 199, 187));//배경색 변경
        //this.getContentPane().setBackground(Color.yellow);//배경색 변경
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//프레임 종료

        //윈도우 종료시 테이블의 내용을 파일에 저장후 종료하자
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
                super.windowClosing(e);

                FileWriter fw=null;
                try {
                    fw=new FileWriter(FILENAME);
                    //테이블의 행갯수만큼 반복해서 데이타를 읽는다
                    for(int i=0;i<table.getRowCount();i++)
                    {
                        String sang=table.getValueAt(i, 0).toString();
                        String su=table.getValueAt(i, 1).toString();
                        String dan=table.getValueAt(i, 2).toString();
                        String tot=table.getValueAt(i, 3).toString();

                        String s=sang+","+su+","+dan+","+tot+"\n";
                        //파일에 저장
                        fw.write(s);
                    }
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }finally {
                    try {
                        fw.close();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }

                System.exit(0);
            }
        });

        this.initDesign();//각종 컴포넌트 생성
        //파일의 내용을 불러오려면 테이블이 생성이 된 후라야 한다
        this.shopFileRead();

        this.setVisible(true);
    }

    //파일 불러와서 테이블에 출력하는 메서드
    public void shopFileRead()
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
                //, 로 분리를 해서 배열로 만든다
                String []data=s.split(",");
                //테이블에 추가
                tableModel.addRow(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("파일없음:"+e.getMessage());
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
    public void initDesign()
    {
        //상단에는 추가,삭제 버튼
        JPanel pTop=new JPanel();
        btnAdd=new JButton("상품정보추가");
        btnDelete=new JButton("상품정보삭제");
        pTop.add(btnAdd);
        pTop.add(btnDelete);
        this.add("North",pTop);

        //중간에는 테이블 추가
        String []title= {"상품명","수량","단가","총금액"};
        tableModel=new DefaultTableModel(title, 0);
        table=new JTable(tableModel);
        this.add("Center",new JScrollPane(table));

        //하단에는 입력 컴포넌트들
        tfSang=new JTextField(7);
        tfSu=new JTextField(4);
        tfDan=new JTextField(7);

        JPanel pBottom=new JPanel();
        pBottom.add(new JLabel("상품명"));
        pBottom.add(tfSang);
        pBottom.add(new JLabel("수량"));
        pBottom.add(tfSu);
        pBottom.add(new JLabel("단가"));
        pBottom.add(tfDan);

        this.add("South",pBottom);

        //추가버튼 이벤트
        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //입력 안했을경우 종료
                if(tfSang.getText().length()==0) {
                    JOptionPane.showMessageDialog(Ex6_SwingTableShop.this, "상품명을 입력해주세요");
                    return;
                }
                if(tfSu.getText().length()==0) {
                    JOptionPane.showMessageDialog(Ex6_SwingTableShop.this, "수량을 입력해주세요");
                    return;
                }
                if(tfDan.getText().length()==0) {
                    JOptionPane.showMessageDialog(Ex6_SwingTableShop.this, "단가를 입력해주세요");
                    return;
                }
                String sang=tfSang.getText();
                int su=0,dan=0;
                try {
                    su=Integer.parseInt(tfSu.getText());
                    dan=Integer.parseInt(tfDan.getText());
                }catch (NumberFormatException e1) {
                    // TODO: handle exception
                    System.out.println("수량,단가 문자입력오류");
                    JOptionPane.showMessageDialog(Ex6_SwingTableShop.this, "수량,단가는 숫자로만 입력해주세요");
                    return;//추가 메서드만 종료
                }
                int total=su*dan;

                //Vector 로 데이타를 담는다(data)
                Vector<String> data=new Vector<String>();
                data.add(sang);
                data.add(String.valueOf(su));
                data.add(String.valueOf(dan));
                data.add(String.valueOf(total));

                //tableModel 로 추가한다(addRow)
                tableModel.addRow(data);

                //입력박스의 데이타는 지운다(setText)
                tfSang.setText("");
                tfSu.setText("");
                tfDan.setText("");

            }
        });

        //삭제이벤트 : 행을 선택시 행의 데이타 삭제(선택안했을경우 경고메세지)
        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //행선택번호
                int row=table.getSelectedRow();
                if(row==-1) {
                    JOptionPane.showMessageDialog(Ex6_SwingTableShop.this, "삭제할 행을 선택해주세요");
                }else {
                    tableModel.removeRow(row);
                }
            }
        });
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Ex6_SwingTableShop a=new Ex6_SwingTableShop("상품등록및삭제");
    }

}