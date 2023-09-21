import javazoom.jl.player.Player;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class EnemyObj extends GameObj{
    public EnemyObj() {
        super();
    }

    public EnemyObj(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    public EnemyObj(Image img, int x, int y, int hight, int width, double speed, GameWin frame) {
        super(img, x, y, hight, width, speed, frame);
    }

    @Override
    public void paintself(Graphics gImage) {
        super.paintself(gImage);
        y+=speed;
        //敌我飞机碰撞检测
        if(this.getRec().intersects(this.frame.planeobj.getRec())){
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
        //敌机的越界条件 y>600,改变后的坐标（-200，-200）
       if(y>600){
            this.x=-200;
            this.y=-200;
            GameUtils.removeList.add(this);
        }
        //遍历shellObList,让当前敌机的对象与每一个shellObj(对象)进行碰撞检测
        //敌机消失前移动到（-200，-200）  我方子弹（-100，100）
        for(ShellObj shellobj:GameUtils.shellObjList){
            if(this.getRec().intersects(shellobj.getRec())){
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
                //碰撞后改变一下敌机和我方子弹的位置，不然会留坑
                shellobj.setX(-100);
                shellobj.setY(100);
                //改变当前敌机的坐标
                this.x=-200;
                this.y=200;
                GameUtils.removeList.add(shellobj);
                GameUtils.removeList.add(this);
                GameWin.score++;
            }
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
