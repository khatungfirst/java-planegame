import javazoom.jl.player.Player;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class PlaneObj extends GameObj{
    public static int ownlife=3;
    public PlaneObj() {
        super();
    }

    @Override
    public Image getImg() {
        return super.getImg();
    }

    public PlaneObj(Image img, int x, int y, int hight, int width, double speed, GameWin frame) {
        super(img, x, y, hight, width, speed, frame);
        //鼠标控制
        this.frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
               //让飞机的横坐标等于鼠标光标的横坐标
                //11是飞机宽度的一半
                //16是飞机高度的一半
               PlaneObj.super.x=e.getX()-35;
               PlaneObj.super.y=e.getY()-16;
            }
        });
    }

    @Override
    public void paintself(Graphics gImage) {

        super.paintself(gImage);
            if (this.frame.bossobj != null && this.getRec().intersects(this.frame.bossobj.getRec())) {
                new Thread("BOOM"){
                    public void run(){
                        String filename="C:\\javaspace\\planeGame\\imgs\\meteorit 爆炸(meteorit_explode)_爱给网_aigei_com (1).mp3";
                        try {
                            sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(filename));
                            Player player = new Player(buffer);
                            player.play();
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }.start();
                //爆炸图的位置就是飞机被撞的位置
                ExplodeObj explodeObj=new ExplodeObj(x,y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);
                //改变当前敌机的坐标
                this.x=-200;
                this.y=200;
                GameUtils.removeList.add(this);
                PlaneObj.ownlife--;
                if (PlaneObj.ownlife <= 0) {
                    GameWin.state = 3;
                }
            }
        }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
