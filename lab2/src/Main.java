import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Main extends JPanel implements ActionListener{

    Timer timer;

    private static int maxWidth;
    private static int maxHeight;

    int rectStartPoints[] = {600,100};
    static final int rectWidth = 300;
    static final int rectHeight = 300;

    private double angle = 0;

    private int ovalR = 50;
    private int dx = 0;
    private int tx = 0;
    private int dy = 1;
    private int ty = 1;

    public Main() {
        // Таймер генеруватиме подію що 10 мс
        timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.red);
        g2d.clearRect(0, 0, maxWidth, maxHeight);

        Double[] big_star_points = new Double[10];
        Double[] small_star_points = new Double[10];;

        double big_star_r = 200;
        double small_star_r = 75;

        double center_x = 325;
        double center_y = 300;

        for (int i=0; i<5; i++) {
            big_star_points[i*2] = center_x + big_star_r*Math.cos(Math.PI*(1.0/2.0 + 2.0*i/5.0));
            big_star_points[i*2+1] = center_y - big_star_r*Math.sin(Math.PI*(1.0/2.0 + 2.0*i/5.0));
            small_star_points[i*2] = center_x + small_star_r*Math.cos(Math.PI*(-1.0/2.0 + 2.0*(i+2.0)/5.0));
            small_star_points[i*2+1] = center_y - small_star_r*Math.sin(Math.PI*(-1.0/2.0 + 2.0*(i+2.0)/5.0));
        }
        BasicStroke bs3 = new BasicStroke(16, BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_BEVEL);
        g2d.setStroke(bs3);
        g2d.setColor(new Color(0,150,150));
        g2d.drawLine(
                small_star_points[6].intValue(),
                small_star_points[7].intValue(),
                small_star_points[6].intValue(),
                small_star_points[7].intValue()+200
        );
        g2d.setStroke(new BasicStroke(0));
        GradientPaint gp = new GradientPaint(
                5, 25,
                new Color(255,255,0),
                20, 2,
                new Color(0,0,255), true);
        g2d.setPaint(gp);

        GeneralPath star = new GeneralPath();
//        g2d.translate(-70, -12);
        star.moveTo(small_star_points[0], small_star_points[1]);
        for (int k = 1; k < 5; k++)
            star.lineTo(small_star_points[2*k], small_star_points[2*k+1]);
        star.closePath();
        g2d.fill(star);
        g2d.draw(star);
        for (int i=0; i<5;i++){
            GeneralPath star_part = new GeneralPath();
            star_part.moveTo(small_star_points[2*i], small_star_points[2*i+1]);
            star_part.lineTo(big_star_points[2*i], big_star_points[2*i+1]);
            star_part.lineTo(small_star_points[((i+1)*2) % 10], small_star_points[((i+1)*2+1) % 10]);
            star_part.closePath();
            g2d.fill(star_part);
            g2d.draw(star_part);
        }
        BasicStroke bs4 = new BasicStroke(4, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND);
        g2d.setStroke(bs4);

        g2d.drawRect(rectStartPoints[0], rectStartPoints[1], rectWidth, rectHeight);
        g2d.translate(tx-ovalR/2, ty-ovalR/2);
        g2d.fillOval(rectStartPoints[0], rectStartPoints[1], ovalR, ovalR);
        g2d.translate(-tx+ovalR/2, -ty+ovalR/2);
        g2d.rotate(angle, rectStartPoints[0],
                rectStartPoints[1]);
        g2d.fillOval(rectStartPoints[0]+36, rectStartPoints[1], ovalR, ovalR);
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("hi there");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new Main());
        frame.setVisible(true);
        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;
    }

    public void actionPerformed(ActionEvent e) {
        if (tx >= rectWidth && ty <= 0){
            dx=-1;
            dy=0;
        }
        if (ty>=rectHeight && tx >= rectWidth){
            dx=0;
            dy=-1;
        }
        if (tx<=0 && ty >=rectHeight){
            dx=1;
            dy=0;
        }
        if (ty<=0 && tx <=0){
            dx=0;
            dy=1;
        }
        ty+=dy;
        tx+=dx;
        repaint();
        angle+=0.01;
    }
}
