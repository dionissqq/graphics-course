package code;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.media.j3d.*;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.vecmath.*;

public class MyAnimation  implements ActionListener, KeyListener{

    private TransformGroup wholePlane;
    private Transform3D translateTransform;
    private Transform3D rotateTransformX;
    private Transform3D rotateTransformY;
    private Transform3D rotateTransformZ;
    private Transform3D scaleTransform;
    private TransformGroup left_hand;
    private Transform3D left_trans;
    private TransformGroup right_hand;
    private Transform3D right_trans;
    private TransformGroup tail;
    private Transform3D tail_trans;

    private JFrame mainFrame;

    private float rot_angle = 0.f;
    private float sign=1.0f;
    private float zoom=0.5f;
    private float xloc=0.3f;
    private float yloc=0.3f;
    private float zloc=0.0f;
    private int moveType=1;
    private Timer timer;

    public MyAnimation(TransformGroup wholePlane,Transform3D trans,
                       TransformGroup left_hand,Transform3D left_trans,
                       TransformGroup right_hand, Transform3D right_trans,
                       TransformGroup tail, Transform3D tail_trans,
                       JFrame frame){
        this.tail = tail;
        this.tail_trans = tail_trans;
        this.left_hand = left_hand;
        this.left_trans = left_trans;
        this.right_hand = right_hand;
        this.right_trans = right_trans;
        this.wholePlane=wholePlane;
        this.translateTransform=trans;
        this.mainFrame=frame;

        rotateTransformX= new Transform3D();
        rotateTransformY= new Transform3D();
        rotateTransformZ= new Transform3D();

        timer = new Timer(100, this);

        timer.start();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        // start timer when button is pressed

        Move(moveType);
        translateTransform.setScale(new Vector3d(zoom,zoom,zoom));
        translateTransform.setRotation(new AxisAngle4d(0,yloc,0,rot_angle ));
        translateTransform.setTranslation(new Vector3f(xloc,yloc,zloc));
        wholePlane.setTransform(translateTransform);

        float newangle = rot_angle*3;
        while(newangle<-2*Math.PI){
            newangle += 2*Math.PI;
        }
        System.out.print(newangle);
        System.out.print("\n new angle");
        while(Math.abs(newangle)>Math.PI/3){
            if (newangle>Math.PI/6){
                newangle = (float) (Math.PI/6 - newangle);
            }
            if (newangle< - Math.PI/6){
                newangle = (float) (-Math.PI/6 - newangle);
            }
        }
        System.out.print(newangle);
        System.out.print("\n");
        left_trans.rotX(newangle);
        left_hand.setTransform(left_trans);
        right_trans.rotX(-newangle);
        right_hand.setTransform(right_trans);
        tail_trans.rotY(newangle);
        tail.setTransform(tail_trans);
    }

    private void Move(int mType) {
        xloc = (float) Math.sin(rot_angle);
        yloc = 0.6f *  (float) (Math.cos(rot_angle)-1);
        zoom = ((float) (-Math.cos(rot_angle)+1))/4f+0.5f;
        rot_angle -= 0.1;
        if (rot_angle>2*Math.PI){
            rot_angle = 0f;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Invoked when a key has been typed.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Invoked when a key has been pressed.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Invoked when a key has been released.
    }
}
