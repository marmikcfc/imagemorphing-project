import java.awt.*;

class layout
    implements LayoutManager
{

    public layout()
    {
    }

    public void addLayoutComponent(String s, Component component)
    {
    }

    public void removeLayoutComponent(Component component)
    {
    }

    public Dimension preferredLayoutSize(Container container)
    {
        Dimension dimension = new Dimension(0, 0);
        Insets insets = container.getInsets();
        dimension.width = 200;
        dimension.height = 240;
        return dimension;
    }

    public Dimension minimumLayoutSize(Container container)
    {
        Dimension dimension = new Dimension(0, 0);
        return dimension;
    }

    public void layoutContainer(Container container)
    {
        Insets insets = container.getInsets();
        Component component = container.getComponent(0);
        if(component.isVisible())
            component.setBounds(insets.left + 0, insets.top + 0, 220, 200);
        component = container.getComponent(1);
        if(component.isVisible())
            component.setBounds(insets.left + 0, insets.top + 200, 220, 80);
    }
}