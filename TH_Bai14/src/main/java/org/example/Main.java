package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import java.util.List;

public class BezierAnimation {

    private JFrame frame;
    private JPanel drawingPanel;
    private Graphics2D g2d;
    private BezierCurve bezierCurve;
    private String text;
    private double animationProgress;

    public BezierAnimation() {
        createGUI();
        frame.setVisible(true);
    }

    private void createGUI() {
        frame = new JFrame("Bezier Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g2d = (Graphics2D) g;
                drawBezierCurve();
                drawAnimatedText();
            }
        };
        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (bezierCurve == null) {
                    bezierCurve = new BezierCurve();
                    bezierCurve.addPoint(e.getPoint());
                } else {
                    bezierCurve.addPoint(e.getPoint());
                    drawingPanel.repaint();
                }
            }
        });
        frame.getContentPane().add(drawingPanel);
    }

    private void drawBezierCurve() {
        if (bezierCurve != null) {
            g2d.setStroke(new BasicStroke(2));
            g2d.draw(bezierCurve.getCurve());
        }
    }

    private void drawAnimatedText() {
        if (bezierCurve != null && text != null) {
            double curveLength = bezierCurve.getCurve().getLength();
            double textLength = g2d.getFontMetrics().stringWidth(text);
            double progress = animationProgress * (curveLength - textLength);

            Point2D textPosition = bezierCurve.getPointAt(progress);
            g2d.setFont(new Font("Arial", Font.BOLD, 24));
            g2d.drawString(text, (int) ((float) textPosition.getX() - textLength / 2), (float) textPosition.getY() + 12);

            animationProgress += 0.01;
            if (animationProgress > 1) {
                animationProgress = 0;
            }

            drawingPanel.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setText(String text) {
        this.text = text;
        drawingPanel.repaint();
    }

    public static void main(String[] args) {
        BezierAnimation animation = new BezierAnimation();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        animation.setText(scanner.nextLine());
    }

    private class BezierCurve {
        private List<Point2D> points;
        private QuadraticCurve2D.Double curve;

        public BezierCurve() {
            points = new ArrayList<>();
            curve = null;
        }

        public void addPoint(Point2D point) {
            points.add(point);
            updateCurve();
        }

        public void updateCurve() {
            if (points.size() >= 2) {
                double[] xPoints = new double[points.size()];
                double[] yPoints = new double[points.size()];
                for (int i = 0; i < points.size(); i++) {
                    xPoints[i] = points.get(i).getX();
                    yPoints[i] = points.get(i).getY();
                }
                curve = new QuadraticCurve2D.Double(xPoints, yPoints, 0, points.size());
            } else {
                curve = null;
            }
        }

        public Shape getCurve() {
            return curve;
        }

        public Point2D getPointAt(double t) {
            if (curve != null) {
                return curve.getPointAt(t);
            }
            return null;
        }
    }
