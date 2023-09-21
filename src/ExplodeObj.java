import java.awt.*;

public class ExplodeObj extends GameObj{
    static Image[] pic=new Image[16];
    int explodecount=0;
    static{
        for(int i=0;i<pic.length;i++){
            pic[i]=Toolkit.getDefaultToolkit().getImage("C:\\javaspace\\planeGame\\imgs\\explode\\e"+(i+1)+".gif");
        }
    }
    public ExplodeObj(int x, int y) {
        super(x, y);
    }

    @Override
    public void paintself(Graphics gImage) {
        if(explodecount<16){
            img=pic[explodecount];
            explodecount++;
            super.paintself(gImage);
        }

    }
}
