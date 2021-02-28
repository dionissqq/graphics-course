package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{



        Group gr = new Group();
        primaryStage.setScene(new Scene(gr, 1000, 600));

        Ellipse ellipse = new Ellipse(350,170,140, 115);
        ellipse.setFill(Color.YELLOW);
        gr.getChildren().add(ellipse);

        Ellipse ellipse1 = new Ellipse(385,130,12, 25);
        ellipse1.setFill(Color.BLACK);
        ellipse1.getTransforms().add(new Rotate(-20, 385,130));
        gr.getChildren().add(ellipse1);

        Ellipse ellipse2 = new Ellipse(388,123,5, 8);
        ellipse2.setFill(Color.WHITE);
        ellipse2.getTransforms().add(new Rotate(-20, 385,130));
        gr.getChildren().add(ellipse2);

        Rectangle  rect = new Rectangle(
            80,270,
            450,230
        );
        rect.setArcWidth(300);
        rect.setArcHeight(200);
        rect.setFill(Color.YELLOW);
        gr.getChildren().add(rect);

        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[]{
                100.0, 330.0,
                122.0, 222.0,
                145.0, 208.0,
                205.0, 280.0});
        polygon.setFill(Color.YELLOW);
        polygon.setStroke(Color.YELLOW);
        polygon.setStrokeWidth(10);
        polygon.setStrokeLineCap(StrokeLineCap.ROUND);
        polygon.setStrokeLineJoin(StrokeLineJoin.ROUND);
        gr.getChildren().add(polygon);

        Polygon polygon1 = new Polygon();
        polygon1.getPoints().addAll(new Double[]{
                390.0, 218.0,
                433.0, 182.0,
                472.0, 144.0,
                493.0, 152.0,
                533.0, 148.0,
                506.0, 196.0,
                498.0, 237.0,
                494.0, 240.0});
        polygon1.setFill(Color.ORANGE);
        polygon1.setStroke(Color.ORANGE);
        polygon1.setStrokeWidth(10);
        polygon1.setStrokeLineCap(StrokeLineCap.ROUND);
        polygon1.setStrokeLineJoin(StrokeLineJoin.ROUND);
        gr.getChildren().add(polygon1);

        Polygon polygon2 = new Polygon();
        polygon2.getPoints().addAll(new Double[]{
                439.0, 218.0,
                505.0, 192.0,
                503.0, 219.0});
        polygon2.setFill(Color.RED);
        polygon2.setStroke(Color.RED);
        polygon2.setStrokeWidth(10);
        polygon2.setStrokeLineCap(StrokeLineCap.ROUND);
        polygon2.setStrokeLineJoin(StrokeLineJoin.ROUND);
        gr.getChildren().add(polygon2);

        Path path = new Path();

        MoveTo moveTo = new MoveTo();
        moveTo.setX(144);
        moveTo.setY(330);

        QuadCurveTo quadTo = new QuadCurveTo();
        quadTo.setControlX(99);
        quadTo.setControlY(414);
        quadTo.setX(161);
        quadTo.setY(480);

        path.getElements().add(moveTo);
        path.getElements().add(quadTo);
        path.setStroke(Color.ORANGE);
        path.setStrokeWidth(4);
        gr.getChildren().add(path);

        FadeTransition ft = new FadeTransition(Duration.millis(3000), gr);
        ft.setFromValue(1.0);
        ft.setToValue(0.1);
        ft.setCycleCount(5);
        ft.setAutoReverse(true);
//        ft.play();

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(2000), gr);
        scaleTransition.setToX(320);
        scaleTransition.setToY(320);
        scaleTransition.setFromX(0.2);
        scaleTransition.setFromY(0.2);
        scaleTransition.setToX(0.5);
        scaleTransition.setToY(0.5);
        scaleTransition.setCycleCount(2);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();

        RotateTransition rotForArc1 =
                new RotateTransition(Duration.millis(500), gr);
        rotForArc1.setByAngle(20f);
        rotForArc1.setCycleCount(20);
        rotForArc1.setAutoReverse(true);
        rotForArc1.play();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
