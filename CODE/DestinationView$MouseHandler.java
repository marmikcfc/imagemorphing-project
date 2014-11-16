import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

class DestinationView$MouseHandler extends MouseInputAdapter
{

    public void mousePressed(MouseEvent mouseevent)
    {
        start = mouseevent.getPoint();
        int i = mouseevent.getModifiers();
        MouseEvent mouseevent1 = mouseevent;
        if((i & 0x10) != 0)
        {
            g2D = (Graphics2D)getGraphics();
            g2D.setXORMode(getBackground());
            g2D.setPaint(DestinationView.access$000(DestinationView.this).getDestinationWindow().getElementColor());
            if(algo == 2)
            {
                prev1 = new Point();
                next1 = new Point();
                prev2 = new Point();
                next2 = new Point();
                prev3 = new Point();
                next3 = new Point();
                prev4 = new Point();
                next4 = new Point();
                prev1.setLocation(start.x - 2, start.y - 2);
                next1.setLocation(start.x + 2, start.y + 2);
                prev2.setLocation(start.x - 2, start.y + 2);
                next2.setLocation(start.x + 2, start.y - 2);
                prev3.setLocation(start.x - 3, start.y - 2);
                next3.setLocation(start.x + 1, start.y + 2);
                prev4.setLocation(start.x - 3, start.y + 2);
                next4.setLocation(start.x + 1, start.y - 2);
                if(tempElement1 == null || tempElement2 == null)
                {
                    tempElement1 = createElement(prev1, next1);
                    tempElement2 = createElement(prev2, next2);
                    tempElement3 = createElement(prev3, next3);
                    tempElement4 = createElement(prev4, next4);
                } else
                {
                    g2D.draw(tempElement1.getShape());
                    tempElement1.modify(prev1, next1);
                    g2D.draw(tempElement2.getShape());
                    tempElement2.modify(prev2, next2);
                    g2D.draw(tempElement3.getShape());
                    tempElement3.modify(prev3, next3);
                    g2D.draw(tempElement4.getShape());
                    tempElement4.modify(prev4, next4);
                }
                g2D.draw(tempElement1.getShape());
                g2D.draw(tempElement2.getShape());
                g2D.draw(tempElement3.getShape());
                g2D.draw(tempElement4.getShape());
                dCounter++;
            }
        }
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
        int i = mouseevent.getModifiers();
        MouseEvent mouseevent1 = mouseevent;
        if((i & 0x10) != 0)
        {
            if((tempElement1 != null || tempElement2 != null) && algo == 2)
            {
                DestinationView.access$000(DestinationView.this).getDestinationModel().add(tempElement1);
                tempElement1 = null;
                DestinationView.access$000(DestinationView.this).getDestinationModel().add(tempElement2);
                tempElement2 = null;
                DestinationView.access$000(DestinationView.this).getDestinationModel().add(tempElement3);
                tempElement3 = null;
                DestinationView.access$000(DestinationView.this).getDestinationModel().add(tempElement4);
                tempElement4 = null;
            }
            if(g2D != null)
            {
                g2D.dispose();
                g2D = null;
            }
            if(algo == 2)
                objD.addDestinationPoint(start.x, start.y);
            start = last = null;
        }
    }

    private Element createElement(Point point, Point point1)
    {
        return new last(point, point1, colors[dCounter]);
    }

    private Graphics2D g2D;
    private Point start;
    private Point prev1;
    private Point prev2;
    private Point prev3;
    private Point prev4;
    private Point last;
    private Point next1;
    private Point next2;
    private Point next3;
    private Point next4;
    private Element tempElement3;
    private Element tempElement4;
    private Element tempElement;
    private Element tempElement1;
    private Element tempElement2;

    DestinationView$MouseHandler()
    {
    }
}