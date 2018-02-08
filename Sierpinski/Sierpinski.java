/*
 * CSCI 2336 Programming Fundamentals III Fall 2017
 * Chapter 18 Recursive implementation of the von Koch snowflake
 */
package vonkoch;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

public class VonKoch extends Frame implements ActionListener {
    private class doublePoint {
        public double x,y;
    }
    private TextField lvl, len;
    VonKoch() {
        super("von Koch snowflake");
        Label lvlLbl = new Label("level");
        lvl = new TextField("4",5);
        Label lenLbl = new Label("side");
        len = new TextField("500",3);
        Button draw = new Button("draw");
        lvl.addActionListener(this);
        len.addActionListener(this);
        draw.addActionListener(this);
        setLayout(new FlowLayout());
        add(lvlLbl);
        add(lvl);
        add(lenLbl);
        add(len);
        add(draw);
        setSize(3020,2080);
        setForeground(Color.blue);
        setBackground(Color.white);
        show();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    private double angle;
    private doublePoint currPt, pt = new doublePoint();
    private void right(double x) {
        angle += x;
    }
    private void left (double x) {
        angle -= x;
    }

    private void drawTriangle(double side, int level, Graphics g) {
            // arguments to sin() and cos() must be angles given in radians,
            // thus, the angles given in degrees must be multiplied by PI/180;

            left(60);
            currPt.x += side/2;
            currPt.y += ((Math.sin(angle*Math.PI/180)*side));
            pt.x = currPt.x + side;
            pt.y=currPt.y;
//            g.drawLine(currPt.x, currPt.y, pt.x, pt.y);
            g.drawLine((int)(currPt.x), (int)(currPt.y), (int)(pt.x), (int)(pt.y));
            currPt.x = pt.x;
            right(180);
            pt.x = currPt.x + (Math.cos(angle*Math.PI/180)*side);
            pt.y = ((Math.sin(angle*Math.PI/180)*side)) + currPt.y;
            g.drawLine((int)(currPt.x), (int)(currPt.y), (int)(pt.x), (int)(pt.y));
            currPt.x = pt.x;
            currPt.y = pt.y;
            right(120);
            pt.x = (Math.cos(angle*Math.PI/180)*side) + currPt.x;
            pt.y = (Math.sin(angle*Math.PI/180)*side) + currPt.y;
            g.drawLine((int)(currPt.x), (int)(currPt.y), (int)(pt.x), (int)(pt.y));
            currPt.x -= side;
            left(240);
    }
    private void drawBigTriangle(double side, int level, Graphics g) {
        if (level == 0) {
            drawTriangle(side,level-1,g);
        }
        else {
            drawTriangle(side,level-1,g);
            drawBigTriangle(side/2.0,level-1,g);
            currPt.x += side;
            drawBigTriangle(side/2.0,level-1,g);
            left (60);
            currPt.x -= side/2;
            currPt.y+= (Math.sin(angle*Math.PI/180)*side/2)*2;
            right(60);
            drawBigTriangle(side/2.0,level-1,g);
            right(120);
            currPt.x -= side/2;
            currPt.y += (Math.sin(angle*Math.PI/180)*side/2)*2;
            left (120);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) { // ActionListener
        repaint();
    }
    @Override
    public void paint(Graphics g) {
        int level = Integer.parseInt(lvl.getText().trim());
        double side = Double.parseDouble(len.getText().trim());
        currPt = new doublePoint();
        currPt.x=20;
        currPt.y=(Math.sin(60*Math.PI/180)*side)+100;

        angle = 0;
        left(60);
        pt.x = side/2 + currPt.x;
        pt.y = (Math.sin(angle*Math.PI/180)*side) + currPt.y;
        g.drawLine((int)(currPt.x), (int)(currPt.y), (int)(pt.x), (int)(pt.y));
        currPt.x = pt.x;
        currPt.y = pt.y;
        right(120);
        pt.x = side/2 + currPt.x;
        pt.y = (Math.sin(angle*Math.PI/180)*side) + currPt.y;
        g.drawLine((int)(currPt.x), (int)(currPt.y), (int)(pt.x), (int)(pt.y));
        currPt.x = pt.x;
        currPt.y = pt.y;
        left(60);
        pt.x = currPt.x-side;
        g.drawLine((int)(currPt.x), (int)(currPt.y), (int)(pt.x), (int)(pt.y));
        currPt.x = pt.x;
        currPt.y = pt.y;
        if (level>0)drawBigTriangle(side/2,level-1,g);
    }
    static public void main(String[] a) {
        VonKoch vonKoch = new VonKoch();
    }
}

