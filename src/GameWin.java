//import javazoo.jl.player.Player;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.AttributedCharacterIterator;

public class GameWin extends JFrame implements KeyListener,ActionListener {
    static GameWin gameWin = null;
    //游戏状态 0：未开始 1：游戏中 2：暂停 3：通关失败 4：通关成功  5.重新游戏
    static int state = 0;
    //游戏得分
    public static int score = 0;
    int width = 600;
    int hight = 600;
    //游戏的重绘次数
    int count = 0;
    //敌机出现的架数
    int enemycount = 0;
    //双缓存的图片
    Image offScreenImage = null;
    //在窗口类中获取背景类的对象
    BgObj obj = new BgObj(GameUtils.bgImg, 0, -2000, 3);
    //我方飞机的对象
    PlaneObj planeobj = new PlaneObj(GameUtils.planeImag, 350, 550, 20, 30, 0, this);
    ShellObj shellobj = new ShellObj(GameUtils.shellImag, planeobj.getX() + 3, planeobj.getY() - 16, 14, 29, 8, this);
    BossObj bossobj = null;



    public void launch() throws InterruptedException {
        //设置标题
        this.setTitle("飞机大战 By 隔壁老王");
        //设置界面的大小
        this.setSize(width, hight);
        //设置页面置顶
        //this.setAlwaysOnTop(true);
        //设置页面居中
        this.setLocationRelativeTo(null);
        //设置页面关闭
        this.setDefaultCloseOperation(3);
        //让界面显现出来
        this.setVisible(true);
        GameUtils.gameObjList.add(obj);
        GameUtils.gameObjList.add(planeobj);
            //用repaint来重新调用paint方法
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (e.getButton() == 1 && state == 0) {
                        state = 1;
                        //用repaint来重新调用paint方法
                        repaint();
                    } else if (e.getButton() == 1 && (state == 3 || state == 4)) {
                        state = 5;
                        repaint();
                    }

                }
            });
            //添加键盘的监听事件来实现暂停功能
            this.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    //空格的ASCLL值为32
                    if (e.getKeyCode() == 32) {
                        switch (state) {
                            case 1:
                                    cg = new ClickGame();
                                    cg.addMouseListener(cg);
                                    gameWin.add(cg);
                                    gameWin.setVisible(true);
                                state = 2;
                                break;
                            case 2:
                                if (cg != null) {
                                    gameWin.remove(cg);
                                    gameWin.setVisible(true);
                                }
                                state = 1;
                                break;

                            case 6:
                                if (cg != null) {
                                    cg = null;
                                    //gameWin.remove(cg);
                                    gameWin.setVisible(true);
                                }
                                state = 1;
                            default:
                        }
                    }
                }
            });


            while (true) {
                //System.out.println(state);
                if (state == 1) {
                    createObj();
                    repaint();
                }
                if (state == 2 ) {

                }
                if (state == 4) {
                    if (cg != null) {
                        gameWin.remove(cg);
                        repaint();
                    }
                }
                if (state == 5) {
                    if (cg != null) {
                        gameWin.remove(cg);
                        repaint();
                    }

                }
                if (state == 6) {
                    if (cg != null) {
                        gameWin.remove(cg);
                        repaint();
                    }
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        public void paint (Graphics g){
            //System.out.println("state = " + state);
            if (offScreenImage == null) {
                offScreenImage = createImage(width, hight);
            }
            Graphics gImage = offScreenImage.getGraphics();
            gImage.fillRect(0, 0, width, hight);
            if(state==6){
                gImage.drawImage(GameUtils.ewmImag, 0, 0, this);
            }
            else {
                if (state == 0) {
                    //未开始
                    gImage.drawImage(GameUtils.bgImg, 0, 0, null);
                    gImage.drawImage(GameUtils.bossImg, 220, 180, null);
                    //gImage.drawImage(GameUtils.explodeImag, 270, 400, null);
                    GameUtils.drawWord(gImage, "点击开始游戏", Color.CYAN, 40, 200, 370);
                    gImage.drawImage(GameUtils.planeImag, 280, 400, null);
                    repaint();
                }
                if (state == 1) {
                    //gameWin.remove(cg);
                    GameUtils.gameObjList.addAll(GameUtils.explodeObjList);
                    for (int i = 0; i < GameUtils.gameObjList.size(); i++) {
                        GameUtils.gameObjList.get(i).paintself(gImage);
                    }
                    //在下一次绘制之前，将要删除的元素从gameobjList中删除
                    GameUtils.gameObjList.removeAll(GameUtils.removeList);
                }
                if (state == 2) {
                }
                if (state == 3) {
                    //游戏失败
                    gImage.drawImage(GameUtils.explodeImag, planeobj.getX() - 35, planeobj.getY() - 50, null);
                    gImage.drawImage(GameUtils.explodebgImag, 0, 0, this);
                    GameUtils.drawWord(gImage, "GAME OVER!", Color.CYAN, 50, 165, 300);
                    GameUtils.drawWord(gImage, "再来一局", Color.black, 50, 200, 450);

                }
                if (state == 4) {
                    //游戏通关
                    //
                    // gImage.drawImage(GameUtils.winbgImag,0,0,null);
                    gImage.drawImage(GameUtils.winbgImag, 0, 0, this);
                    GameUtils.drawWord(gImage, "Victory!", Color.red, 50, 200, 150);
                    GameUtils.drawWord(gImage, "再来一局", Color.black, 50, 200, 450);
                }
                if (state == 5) {
                    //重新游戏
                    count = 1;
                    enemycount = 1;
                    score = 0;
                    GameUtils.removeList.clear();
                    GameUtils.gameObjList.clear();
                    GameUtils.enemyObjList.clear();
                    GameUtils.shellObjList.clear();
                    GameUtils.explodeObjList.clear();
                    GameUtils.bulletObjList.clear();
                    GameUtils.gameObjList.add(obj);
                    GameUtils.gameObjList.add(planeobj);
                    bossobj = null;
                    // bossobj.life=10;
                    if (bossobj != null) {
                        bossobj.life = 10;
                    }
                    planeobj.ownlife = 3;
                    GameUtils.drawWord(gImage, "生命值：" + planeobj.ownlife, Color.CYAN, 40, 30, 100);
//                    cg = null;
                    if (cg != null) {
                        gameWin.remove(cg);
                        cg = null;
                        gameWin.setVisible(true);
                    }
                    repaint();
                    state = 1;
                }
                GameUtils.drawWord(gImage, "生命值：" + planeobj.ownlife, Color.CYAN, 40, 30, 100);
                //将记分面板绘制到窗口上
                GameUtils.drawWord(gImage, score + "分", Color.CYAN, 40, 60, 150);

            }
            //把新图片一次性绘制到窗口中
            g.drawImage(offScreenImage, 0, 0, this);
            count++;
            }


        void createObj () {
            if (count % 15 == 0) {
                //我方子弹
                GameUtils.shellObjList.add(new ShellObj(GameUtils.shellImag, planeobj.getX() + 35, planeobj.getY() - 16, 14, 29, 5, this));
                //通过索引获得shellObList的最后一个元素，并添加到GameObList中
                GameUtils.gameObjList.add(GameUtils.shellObjList.get(GameUtils.shellObjList.size() - 1));
            }
            if (count % 15 == 0) {
                GameUtils.enemyObjList.add(new EnemyObj(GameUtils.enemyImag, (int) (Math.random() * 12) * 50, 0, 49, 36, 5, this));
                //把enemyObList中的最后一个元素，添加到gameOblist中
                GameUtils.gameObjList.add(GameUtils.enemyObjList.get(GameUtils.enemyObjList.size() - 1));
                enemycount++;
            }
            if (count % 15 == 0 && bossobj != null) {
                GameUtils.bulletObjList.add(new BulletObj(GameUtils.bulletImag, bossobj.getX() + 76, bossobj.getY() + 150, 15, 25, 10, this));
                GameUtils.gameObjList.add(GameUtils.bulletObjList.get(GameUtils.bulletObjList.size() - 1));
            }
            if (enemycount > 20 && bossobj == null) {
                bossobj = new BossObj(GameUtils.bossImg, 250, 35, 155, 100, 5, this);
                GameUtils.gameObjList.add(bossobj);
            }
        }
    public void actionPerformed (ActionEvent e){

        }
    ClickGame cg=null;

    public static void main(String[] args) throws InterruptedException {
        gameWin = new GameWin();
        //多线程运行游戏
        new Thread(()->{while(true){
            try {
                gameWin.launch();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        }).start();
        //背景音乐
        try {
            //加载音频文件
            Clip bgm = AudioSystem.getClip();
            InputStream is = Music.class.getClassLoader().getResourceAsStream("music.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(is);
            bgm.open(ais);
            bgm.start();
            //无限循环播放音乐
            bgm.loop(Clip.LOOP_CONTINUOUSLY);
           /* do {
            } while (true);*/
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
    }


        @Override
        public void keyTyped (KeyEvent e){

        }

        @Override
        public void keyPressed (KeyEvent e){

        }

        @Override
        public void keyReleased (KeyEvent e){

        }
    }

