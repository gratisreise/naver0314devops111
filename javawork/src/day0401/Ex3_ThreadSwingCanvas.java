package day0401;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ex3_ThreadSwingCanvas extends JFrame implements ActionListener{
    JButton btnStart,btnStop;
    MyDraw draw=new MyDraw();
    boolean loop=false;//true일경우 원이 무한으로 그려지도록 할 예정
    Color drawColor=new Color(180, 190, 200);
    int xpos,ypos;

    public Ex3_ThreadSwingCanvas(String title) {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object ob=e.getSource();
        if(ob==btnStart) {
            //System.out.println("Start");
            loop=true;
        }else {
            //System.out.println("Stop");
            loop=false;
        }

        //Thread 의 run 메소드 호출
        //인터페이스 Runnable 을 구현했으므로 Thread 생성자에 현재클래스의 인스턴스를 보낸다
        Thread th=new Thread(draw);
        th.start();//run 메소드 호출
    }



    //캔바스 내부클래스
    class MyDraw extends Canvas implements Runnable
    {
        @Override
        public void paint(Graphics g) {
            // TODO Auto-generated method stub
            //super.paint(g);
            //System.out.println("paint");
            if(loop) {
                g.setColor(drawColor);
                g.fillOval(xpos, ypos, 60, 60);
            }
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            System.out.println("원그리기");
            if(draw==null)
                return;
            while(loop)
            {
                //랜덤 색상 구하기
                int r=(int)(Math.random()*256);//0~255
                int g=(int)(Math.random()*256);//0~255
                int b=(int)(Math.random()*256);//0~255

                drawColor=new Color(r, g, b);

                //랜덤하게 x좌표,y좌표도 구한다
                xpos=(int)(Math.random()*450);
                ypos=(int)(Math.random()*450);

                //캔바스의 paint 메소드 호출
                this.paint(getGraphics());

                try {
                    Thread.sleep(300);//1000 이 1초
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public void initDesign()
    {
        //캔바스를 센타에 배치
        this.add("Center",draw);

        JPanel p=new JPanel();
        btnStart=new JButton("스레드 시작");
        btnStop=new JButton("스레드 멈춤");

        p.add(btnStart);
        p.add(btnStop);

        this.add("North",p);
        this.add("Center",draw);

        //버튼 이벤트
        btnStart.addActionListener(this);
        btnStop.addActionListener(this);//this:actionPerformed 메서드를 구현한 클래스의 인스턴스를 보낸다
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Ex3_ThreadSwingCanvas a=new Ex3_ThreadSwingCanvas("스레드예제");
//		Thread th=new Thread(a);
//		th.start();
    }

}