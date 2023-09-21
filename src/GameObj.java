import java.awt.*;

public class GameObj {        //游戏元素父类的编写
    Image img;
    //定义物体的坐标
    int x;
    int y;
    //定义物体的宽高
    int hight;
    int width;
    //设置速度
    double speed;
    //窗口的引用
    GameWin frame;
    //无参构造
    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHight() {
        return hight;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public GameWin getFrame() {
        return frame;
    }

    public void setFrame(GameWin frame) {
        this.frame = frame;
    }
    public GameObj(){
    }
    public GameObj(int x,int y){
        this.x=x;
        this.y=y;
    }
    //有参构造(多写几个用起来更加灵活)

    public GameObj(Image img, int x, int y,double speed) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed=speed;
    }
    public GameObj(Image img, int x, int y, int hight, int width, double speed, GameWin frame) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.hight = hight;
        this.width = width;
        this.speed = speed;
        this.frame = frame;
    }
    //绘制自身的方法
    public void paintself(Graphics gImage){
        gImage.drawImage(img,x,y,null);

    }
    //获取自身矩形的方法
    public Rectangle getRec(){
        return new Rectangle(x,y,width,hight);
    }
}
