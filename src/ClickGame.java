import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClickGame extends JPanel implements MouseListener {
    private int x1 = 246; // 点击区域 x 坐标
    private int y1 = 220; // 点击区域 y 坐标
    private int x2 = 246; // 点击区域 x 坐标
    private int y2 = 310; // 点击区域 y 坐标
    private int x3 = 246; // 点击区域 x 坐标
    private int y3 = 410; // 点击区域 y 坐标
    private int length= 60; // 点击区域长度
    private int wideth=40;    //点击区域宽度
    private int x4 = 246; // 点击区域 x 坐标
    private int y4 = 510; // 点击区域 y 坐标

    public void paintComponent(Graphics g) {
            //System.out.println("绘制菜单");
            super.paintComponent(g);
            // Graphics2D g2d = (Graphics2D) g;
            g.drawImage(GameUtils.tcbgImag,0,0,this);
            GameUtils.drawWord(g,"菜  单",Color.WHITE,80,125,83);
            GameUtils.drawWord(g,"重新游戏",Color.WHITE,30,185,216);
            GameUtils.drawWord(g,"退出游戏",Color.WHITE,30,185,306);
            GameUtils.drawWord(g,"一键通关",Color.WHITE,30,185,403);
            GameUtils.drawWord(g,"关于我们",Color.WHITE,30,185,506);
    }

    public void mouseClicked(MouseEvent e) {
       // System.out.println("暂停页面鼠标监听");
        int mouseX = e.getX();
        int mouseY = e.getY();
        double area1=(mouseX-x1);
        double area2=(mouseY-y1);
        double area3=(mouseY-y2);
        double area4=(mouseY-y3);
        double area5=(mouseY-y4);
        if ((area1>=-length&&area1<=length)&&(area2<=wideth&&area2>=-wideth)) {
            // 在点击区域内执行相应的操作
            JOptionPane.showMessageDialog(this, "你点中了重新开始");
            GameWin.state=5;
            repaint();
        }
        else if ((area1>=-length&&area1<=length)&&(area3<=wideth&&area3>=-wideth)) {
            // 在点击区域内执行相应的操作
            JOptionPane.showMessageDialog(this, "你点中了退出游戏");
            System.exit(0);
        }
        else if ((area1>=-length&&area1<=length)&&(area4<=wideth&&area4>=-wideth)) {
            // 在点击区域内执行相应的操作
            JOptionPane.showMessageDialog(this, "你点中了一键通关");
            GameWin.state=4;
        }
        else if ((area1>=-length&&area1<=length)&&(area5<=wideth&&area5>=-wideth)) {
            // 在点击区域内执行相应的操作
            JOptionPane.showMessageDialog(this, "你点中了关于我们");
            GameWin.state=6;
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }


}
