import javazoom.jl.player.Player;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class BossObj extends GameObj{
    //定义boss的生命值
    int life=10;
    public BossObj(Image img, int x, int y, int hight, int width, double speed, GameWin frame) {
        super(img, x, y, hight, width, speed, frame);
    }

    @Override
    public void paintself(Graphics gImage) {
        super.paintself(gImage);
        if(x>550||x<-50){
            speed=-speed;
        }
        x+=speed;
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
           // GameUtils.removeList.add(this);
            PlaneObj.ownlife=0;
           //life--;
            if (PlaneObj.ownlife == 0) {
                GameWin.state = 3;
            }
        }
        for(ShellObj shellObj:GameUtils.shellObjList){
            if(this.getRec().intersects(shellObj.getRec())){
                //改变子弹的位置

                shellObj.setX(-100);
                shellObj.setY(100);
                GameUtils.removeList.add(shellObj);
                life--;
            }
            if(life<=0&&GameWin.score>=30){
                new Thread("BOOMboss"){
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
                //打败boss，游戏通关
                GameWin.state=4;
            }
        }
        //血条的白色背景
        gImage.setColor(Color.white);
        gImage.fillRect(480,60,100,20);
        //血条的绘制
        gImage.setColor(Color.RED);
        gImage.fillRect(480,60,life*100/10,20);
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
