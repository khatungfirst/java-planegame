import javazoom.jl.player.Player;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class BulletObj extends GameObj{
    public BulletObj(Image img, int x, int y, int hight, int width, double speed, GameWin frame) {
        super(img, x, y, hight, width, speed, frame);
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
    @Override
    public void paintself(Graphics gImage) {
        super.paintself(gImage);
        y += speed;
        //第一个this代表的是当前敌方子弹
        if (this.getRec().intersects(this.frame.planeobj.getRec())) {
            new Thread("BOOM") {
                public void run() {
                    String filename = "C:\\javaspace\\planeGame\\imgs\\meteorit 爆炸(meteorit_explode)_爱给网_aigei_com (1).mp3";
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
            ExplodeObj explodeObj = new ExplodeObj(x, y);
            GameUtils.explodeObjList.add(explodeObj);
            GameUtils.removeList.add(explodeObj);
            //改变当前敌机的坐标
            this.x = -200;
            this.y = 200;
            GameUtils.removeList.add(this);
            PlaneObj.ownlife--;
            if (PlaneObj.ownlife == 0) {
                GameWin.state = 3;
            }
            //敌方子弹越界消失 y>600,改变后的坐标（-300，300）
            if (y > 600) {
                this.x = -300;
                this.y = 300;
                GameUtils.removeList.add(this);
            }
        }

    /*@Override
    public Rectangle getRec() {
        return super.getRec();
    }*/

    }
}


