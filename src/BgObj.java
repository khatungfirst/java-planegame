import java.awt.*;

public class BgObj extends GameObj{


    public BgObj() {
        super();
    }

    public BgObj(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    public BgObj(Image img, int x, int y, int hight, int width, double speed, GameWin frame) {
        super(img, x, y, hight, width, speed, frame);
    }

    @Override
    public void paintself(Graphics gImage) {

        super.paintself(gImage);
        y+=speed;
        if(y>=0){
            y=-2000;
        }
    }
}
