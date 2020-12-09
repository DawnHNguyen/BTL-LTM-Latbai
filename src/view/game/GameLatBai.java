

package view.game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.Border;
import game.subForm;
public final class GameLatBai extends JFrame implements ActionListener {
    int count = 0;
    int id;
    int preX;
    int preY;
    int X;
    int Y;
    int level = 0;
    int hit = 0;
    int h;
    int[] sizeX = new int[]{2, 2, 2, 3, 4, 4, 4, 4, 5, 5};
    int[] sizeY = new int[]{3, 4, 6, 6, 6, 7, 8, 10, 10, 12};
    int[] TIME = new int[]{10, 20, 30, 50, 65, 80, 100, 120, 140, 150};
    int maxTime = 30;
    int time = 0;
    boolean loss = false;
    int BOM;
    int dem = 0;
    int maxXY = 100;
    int m = 2;
    int n = 3;
    private JProgressBar progressTime;
    private JButton[][] bt;
    private boolean[][] tick;
    private int[][] a;
    private int xFood;
    private int yFood;
    private JButton score_bt;
    private JButton out_bt;
    private JPanel pn;
    private JPanel pn2;
    private subForm form;
    Container cn;
    Timer timer;
    Timer timer2;

    public GameLatBai(int k, int score) {
        this.bt = new JButton[this.maxXY][this.maxXY];
        this.tick = new boolean[this.maxXY][this.maxXY];
        this.a = new int[this.maxXY][this.maxXY];
        this.setTitle(" Nhóm 5 - Game Lật Bài");
        this.level = k;
        this.cn = this.init(k, score);
        this.timer = new Timer(400, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameLatBai.this.open();
                GameLatBai.this.timer.stop();
            }
        });
        this.timer2 = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ++GameLatBai.this.time;
                GameLatBai.this.progressTime.setValue(GameLatBai.this.maxTime - GameLatBai.this.time);
                if (GameLatBai.this.maxTime == GameLatBai.this.time) {
                    GameLatBai.this.timer2.stop();
                    GameLatBai.this.showDialogNewGame("Hết thời gian.\nĐiểm: " + GameLatBai.this.score_bt.getText() + "\n" + "Bạn có muốn chơi lại không?", "Thông báo");
                }

            }
        });
    }

    public void open() {
        if (this.id == this.a[this.X][this.Y]) {
            this.bt[this.preX][this.preY].setIcon(this.getIcon(-1));
            this.a[this.X][this.Y] = this.a[this.preX][this.preY] = 0;
            this.tick[this.X][this.Y] = this.tick[this.preX][this.preY] = false;
            this.bt[this.X][this.Y].setBorder((Border)null);
            this.bt[this.preX][this.preY].setBorder((Border)null);
            this.showMatrix();
            this.bt[this.X][this.Y].setIcon(this.getIcon(-1));
            this.score_bt.setText(String.valueOf(Integer.parseInt(this.score_bt.getText()) + 100));
            ++this.hit;
            if (this.hit == this.m * this.n / 2) {
                this.timer.stop();
                this.timer2.stop();
                this.nextGame();
            }
        } else {
            this.bt[this.preX][this.preY].setIcon(this.getIcon(0));
            this.bt[this.X][this.Y].setIcon(this.getIcon(0));
            this.score_bt.setText(String.valueOf(Integer.parseInt(this.score_bt.getText()) - 10));
        }

    }

    public Container init(int k, int score) {
        this.m = this.sizeX[k];
        this.n = this.sizeY[k];
        this.maxTime = this.TIME[k] * 10;
        this.time = 0;
        Container cn = this.getContentPane();
        this.pn = new JPanel();
        this.pn.setLayout(new GridLayout(this.m, this.n));

        for(int i = 0; i < this.m; ++i) {
            for(int j = 0; j < this.n; ++j) {
                this.bt[i][j] = new JButton();
                this.pn.add(this.bt[i][j]);
                this.bt[i][j].setActionCommand(i + " " + j);
                this.bt[i][j].addActionListener(this);
                this.bt[i][j].setBackground(Color.black);
                this.a[i][j] = (int)(Math.random() * 2.0D + 1.0D);
                this.bt[i][j].setIcon(this.getIcon(0));
                this.tick[i][j] = true;
            }
        }

        this.pn2 = new JPanel();
        this.pn2.setLayout(new FlowLayout());
        this.score_bt = new JButton(String.valueOf(score));
        this.score_bt.setFont(new Font("UTM Nokia", 1, 20));
        this.score_bt.setBackground(Color.white);
        this.out_bt = new JButton("QUIT");
        JLabel score_lb = new JLabel("Điểm: ");
        score_lb.setFont(new Font("UTM Nokia", 1, 20));
        this.pn2.add(score_lb);
        this.pn2.add(this.score_bt);
        this.pn2.add(this.out_bt);
        out_bt.addActionListener(this);
        this.progressTime = new JProgressBar(0, this.maxTime);
        this.progressTime.setValue(this.maxTime);
        this.progressTime.setForeground(Color.orange);
        this.createMatrix();
        this.showMatrix();
        cn.add(this.pn);
        cn.add(this.progressTime, "North");
        cn.add(this.pn2, "South");
        form = new subForm();
        this.setVisible(true);
        this.setSize(this.n * 120, this.m * 170 + 90);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo((Component)null);
        this.setResizable(false);
        return cn;
    }

    public void createMatrix() {
        int N = this.m * this.n;
        int[] b = new int[this.m * this.n];
        int[] c = new int[this.m * this.n];

        int i;
        for(i = 0; i < this.m * this.n; ++i) {
            b[i] = i + 1;
            if (b[i] > N / 2) {
                b[i] -= N / 2;
            }

            c[i] = (int)(Math.random() * 1000000.0D);
        }

        int j;
        for(i = 0; i < N - 1; ++i) {
            for(j = i + 1; j < N; ++j) {
                if (c[i] > c[j]) {
                    int tmp = b[i];
                    b[i] = b[j];
                    b[j] = tmp;
                    tmp = c[i];
                    c[i] = c[j];
                    c[j] = tmp;
                }
            }
        }

        N = 0;

        for(i = 0; i < this.m; ++i) {
            for(j = 0; j < this.n; ++j) {
                this.a[i][j] = b[N++];
            }
        }

    }

    public void showMatrix() {
        for(int i = 0; i < this.m; ++i) {
            for(int j = 0; j < this.n; ++j) {
                System.out.printf("%3d", this.a[i][j]);
            }

            System.out.println();
        }

        System.out.println("-----------------");
        System.out.println();
    }

    private Icon getIcon(int index) {
        int width = 120;
        int height = 170;
//        Image image = (new ImageIcon(this.getClass().getResource("/icon/icon" + index + ".jpg"))).getImage();
        Image image = (new ImageIcon(this.getClass().getResource("/images/icon" + index + ".jpg"))).getImage();
        Icon icon = new ImageIcon(image.getScaledInstance(width, height, 4));
        return icon;
    }

    public void newGame() {
        this.dispose();
        new GameLatBai(0, 100);
    }

    public void nextGame() {
        this.dispose();
        new GameLatBai(this.level + 1, Integer.parseInt(this.score_bt.getText()) + (this.maxTime - this.time) / 50);
    }

    public void showDialogNewGame(String message, String title) {
        int select = JOptionPane.showOptionDialog((Component)null, message, title, 0, 3, (Icon)null, (Object[])null, (Object)null);
        if (select == 0) {
            this.newGame();
        } else {
            System.exit(0);
        }

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==out_bt){
			form.setVisible(true);
		}
        this.timer2.start();
        String s = e.getActionCommand();
        int k = s.indexOf(32);
        int i = Integer.parseInt(s.substring(0, k));
        int j = Integer.parseInt(s.substring(k + 1, s.length()));
        if (this.tick[i][j]) {
            if (this.count == 0) {
                this.bt[i][j].setIcon(this.getIcon(this.a[i][j]));
                this.id = this.a[i][j];
                this.preX = i;
                this.preY = j;
            } else {
                this.bt[i][j].setIcon(this.getIcon(this.a[i][j]));
                this.X = i;
                this.Y = j;
                this.timer.start();
            }

            this.count = 1 - this.count;
        }
        
    
        
    }

    public static void main(String[] args) {
        new GameLatBai(0, 0);
    }
}