import java.awt.*;
import java.util.*;
import java.util.List;

public class GameUtils {
    //背景图片
    public static Image bgImg=Toolkit.getDefaultToolkit().createImage("C:\\javaspace\\planeGame\\imgs\\bg.jpg");
    //Boss图片
    public static Image bossImg=Toolkit.getDefaultToolkit().createImage("C:\\javaspace\\planeGame\\imgs\\Enemy14.png");
    //爆炸图片
    public static Image explodeImag=Toolkit.getDefaultToolkit().createImage("C:\\javaspace\\planeGame\\imgs\\explode\\e6.gif");
    //己方图片
    public static Image planeImag=Toolkit.getDefaultToolkit().createImage("C:\\javaspace\\planeGame\\Plane02.png");
    //我方子弹的图片
    public static Image shellImag=Toolkit.getDefaultToolkit().createImage("C:\\javaspace\\planeGame\\imgs\\shell.png");
    //敌方子弹的图片
    public static Image bulletImag=Toolkit.getDefaultToolkit().createImage("C:\\javaspace\\planeGame\\imgs\\bullet.png");
    //敌机的图片
    public static Image enemyImag=Toolkit.getDefaultToolkit().createImage("C:\\javaspace\\planeGame\\imgs\\Enemy.png");
    //失败时的背景图
    public static Image explodebgImag=Toolkit.getDefaultToolkit().createImage("C:\\javaspace\\planeGame\\imgs\\explodebg.jpg");
    //游戏胜利时的背景图
    public static Image winbgImag=Toolkit.getDefaultToolkit().createImage("C:\\javaspace\\planeGame\\imgs\\winbg.jpg");
    //暂停时的图片
    public static Image tcbgImag=Toolkit.getDefaultToolkit().createImage("C:\\javaspace\\planeGame\\imgs\\menu.jpg");
    //菜单弹窗的图片
    public static Image tanchuangbgImag=Toolkit.getDefaultToolkit().createImage("C:\\javaspace\\planeGame\\imgs\\tanchuangbg.jpg");
    //二维码图片
    public static Image ewmImag=Toolkit.getDefaultToolkit().createImage("C:\\javaspace\\planeGame\\imgs\\ewm.jpg");
    //所有游戏物体的集合
    public static List<GameObj> gameObjList=new ArrayList<>();
    //要删除元素的集合
    public static List<GameObj> removeList=new ArrayList<>();
    //我方子弹的集合
    public static List<ShellObj> shellObjList=new ArrayList<>();
    //敌方子弹的集合
    public static List<BulletObj> bulletObjList=new ArrayList<>();
    //敌机的集合
    public static List<EnemyObj> enemyObjList=new ArrayList<>();
    //爆炸的集合
    public static List<ExplodeObj> explodeObjList=new ArrayList<ExplodeObj>();
    //绘制字符串的工具类
    public static void drawWord(Graphics gImage,String str,Color color,int size,int x,int y){
        gImage.setColor(color);
        gImage.setFont(new Font("仿宋",Font.BOLD,size));
        gImage.drawString(str, x, y);

    }
}

