import java.awt.*;
import java.awt.geom.Line2D;

public static class Element$Line extends Element
{

    public Shape getShape()
    {
        return line;
    }

    public Rectangle getBounds()
    {
        return line.getBounds();
    }

    public void modify(Point point, Point point1)
    {
        line.x2 = point1.x;
        line.y2 = point1.y;
    }

    private java.awt.geom. line;

    public Element$Line(Point point, Point point1, Color color)
    {
        super(color);
        line = new java.awt.geom.(point, point1);
    }
}