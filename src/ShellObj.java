import javazoom.jl.player.Player;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class ShellObj extends GameObj{
    public ShellObj() {
    }

    public ShellObj(Image img, int x, int y, int hight, int width, double speed, GameWin frame) {
        super(img, x, y, hight, width, speed, frame);
        new Thread("ATTACK"){
            public void run(){
                String filename="C:\\javaspace\\planeGame\\imgs\\让炸弹(get_bomb)_爱给网_aigei_com.mp3";
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
    }

    @Override
   public void paintself(Graphics gImage) {
        super.paintself(gImage);
        y-=speed;
        //我方子弹的越界消失条件 y<0，改变后的坐标（-100，100）
        if(y<0){
            this.x=-100;
            this.y=100;
            GameUtils.removeList.add(this);
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
