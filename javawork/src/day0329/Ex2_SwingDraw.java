
package day0329;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class Ex2_SwingDraw extends JFrame implements MouseMotionListener,MouseListener{
    //초기 선 색상
    Color color=Color.green;
    int x,y,prex,prey;//곡선을 그릴 현재좌표 x,y 와 직전좌표 prex,prey

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        //현재 좌표를 직전좌표로 저장
        prex=x;
        prey=y;

        //현재좌표는 다시 마우스 위치로 저장
        x=e.getX();
        y=e.getY();

        //paint 다시 호출
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("mouseClicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("mousePressed");
        //클릭시 x,y변경
        x=e.getX();
        y=e.getY();

        //mousePressed 발생시 선색을 랜덤하게 바꿔보자
        int red=(int)(Math.random()*256);
        int green=(int)(Math.random()*256);
        int blue=(int)(Math.random()*256);

        color=new Color(red, green, blue);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public Ex2_SwingDraw(String title) {
        // TODO Auto-generated constructor stub
        super(title);
        this.setLocation(100, 100);//시작위치
        this.setSize(500, 500);//창크기
        //this.getContentPane().setBackground(new Color(93, 199, 187));//배경색 변경
        //this.getContentPane().setBackground(Color.yellow);//배경색 변경
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//프레임 종료
        //마우스 이벤트 추가
        this.addMouseMotionListener(this);
        this.addMouseListener(this);

        this.initDesign();//각종 컴포넌트 생성
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        //super.paint(g);

        //선색상
        g.setColor(color);

        //선굵기 지정
        Graphics2D g2=(Graphics2D)g;
        g2.setStroke(new BasicStroke(3));
        //선그리기
        //g.drawLine(10, 100, 400, 100);

        //곡선은 마우스를 드래그시 직전좌표부터 현재좌표까지 선을 그리면 곡선이 된다
        g.drawLine(prex, prey, x, y);
    }

    public void initDesign()
    {

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Ex2_SwingDraw a=new Ex2_SwingDraw("곡선그리기");
    }

}
