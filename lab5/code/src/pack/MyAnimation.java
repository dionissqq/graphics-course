package pack;

import java.awt.*;
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


    private JFrame mainFrame;

    private float rot_angle = 0.f;
    private float sign=1.0f;
    private float zoom=0.5f;
    private float xloc=0.3f;
    private float yloc=0.3f;
    private float zloc=0.0f;
    private int moveType=1;
    private Timer timer;

    public MyAnimation(TransformGroup wholePlane, Transform3D trans,JFrame frame){
        this.wholePlane=wholePlane;
        this.translateTransform=trans;
        this.mainFrame=frame;

        rotateTransformX= new Transform3D();
        rotateTransformY= new Transform3D();
        rotateTransformZ= new Transform3D();

        timer = new Timer(100, this);

        timer.start();
    }

    private void initialPlaneState(){
        xloc=0.0f;
        yloc=0.0f;
        zloc=0.0f;
        zoom=0.2f;
        moveType=1;
        sign=1.0f;
        rotateTransformY.rotY(-Math.PI/2.8);
        translateTransform.mul(rotateTransformY);
        if(timer.isRunning()){timer.stop();}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // start timer when button is pressed

        Move(moveType);
        translateTransform.setScale(new Vector3d(zoom,zoom,zoom));
        translateTransform.setRotation(new AxisAngle4d(0,yloc,0,rot_angle ));
        translateTransform.setTranslation(new Vector3f(xloc,yloc,zloc));
        wholePlane.setTransform(translateTransform);

    }

    private void Move(int mType) {
        xloc = (float) Math.sin(rot_angle);
        yloc = 0.6f *  (float) (Math.cos(rot_angle)-1);
        zoom = ((float) (-Math.cos(rot_angle)+1))/4f+0.5f;
        rot_angle += 0.1;
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
