package day0329;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ex3_ComboboxCanvas extends JFrame{
    JComboBox<String> combo;
    int selectCombo=5;//이미지 기본값
    static final String PATH="D:\\naver0314\\workall\\image\\연예인사진\\";
    Random rand=new Random();

    String []comboLabel= {"선","원","사각형","문자열","이미지"};
    Draw draw=new Draw();

    public Ex3_ComboboxCanvas(String title) {
        // TODO Auto-generated constructor stub
        super(title);
        this.setLocation(100, 100);//시작위치
        this.setSize(500, 500);//창크기
        //this.getContentPane().setBackground(new Color(93, 199, 187));//배경색 변경
        //this.getContentPane().setBackground(Color.yellow);//배경색 변경
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//프레임 종료
        this.initDesign();//각종 컴포넌트 생성
        this.setVisible(true);
    }

    public void initDesign()
    {
        JPanel p=new JPanel();
        combo=new JComboBox<String>(comboLabel);
        p.add(new JLabel("메뉴를 선택하세요"));
        p.add(combo);

        combo.setSelectedIndex(selectCombo-1);//콤보박스 초기값 지정

        //이벤트
        combo.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                // TODO Auto-generated method stub
                System.out.println("itemStateChanged");
                selectCombo=combo.getSelectedIndex()+1;
                //paint 호출
                draw.repaint();
            }
        });

        //프레임에 패널을 추가
        this.add("North",p);

        //중앙에는 캔바스를 배치
        this.add("Center",draw);
    }

    //랜덤한 색상을 반환하는 메서드
    public Color getRandomColor()
    {
        int red=rand.nextInt(256);
        int green=rand.nextInt(256);
        int blue=rand.nextInt(256);
        return new Color(red, green, blue);
    }

    class Draw extends Canvas
    {
        @Override
        public void paint(Graphics g) {
            // TODO Auto-generated method stub
            super.paint(g);

            //선굵기 지정
            Graphics2D g2=(Graphics2D)g;
            g2.setStroke(new BasicStroke(3));

            if(selectCombo==1)
            {
                //랜덤한 색상의 랜덤한 위치에 총 50개의 선을 그려보자
                for(int i=1;i<=50;i++)
                {
                    int x=rand.nextInt(500);
                    int y=rand.nextInt(500);
                    int x2=rand.nextInt(500);
                    int y2=rand.nextInt(500);
                    Color color=getRandomColor();

                    g.setColor(color);
                    g.drawLine(x, y, x2, y2);
                }
            }else if(selectCombo==2)
            {
                //랜덤한 색상의 랜덤한 위치에 총 50개의 채워진원을 그려보자
                for(int i=1;i<=50;i++)
                {
                    int x=rand.nextInt(500);
                    int y=rand.nextInt(500);
                    int w=rand.nextInt(40)+20;
                    Color color=getRandomColor();

                    g.setColor(color);
                    g.fillOval(x, y, w, w);
                }
            }else if(selectCombo==3)
            {
                //랜덤한 색상의 랜덤한 위치에 총 50개의 채워진사각형을 그려보자
                for(int i=1;i<=50;i++)
                {
                    int x=rand.nextInt(500);
                    int y=rand.nextInt(500);
                    int w=rand.nextInt(40)+20;
                    Color color=getRandomColor();

                    g.setColor(color);
                    g.fillRect(x, y, w, w);
                }
            }else if(selectCombo==4)
            {
                //나타낼 문자열
                String msg="Have a Nice Day!";
                //글꼴 변경
                g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
                for(int i=1;i<=30;i++)
                {
                    //랜덤 색상
                    Color color=getRandomColor();
                    g.setColor(color);
                    int x=rand.nextInt(350);
                    int y=rand.nextInt(350);
                    g.drawString(msg, x, y);
                }

            }else if(selectCombo==5)
            {
                //1~20 사이의 난수를 발생시킨후 PATH+난수값.jpg Image 생성후 나타내보세요
                int n=rand.nextInt(19)+1;
                String fileName=PATH+n+".jpg";
                System.out.println(fileName);
                Image image=new ImageIcon(fileName).getImage();
                g.drawImage(image, 100, 10, 300, 400, this);
            }
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Ex3_ComboboxCanvas a=new Ex3_ComboboxCanvas("ComboBox메뉴");
    }

}