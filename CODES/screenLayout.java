import java.awt.*;

class screenLayout
    implements LayoutManager
{

    public screenLayout()
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
        dimension.width = 799 + insets.left + insets.right;
        dimension.height = 549 + insets.top + insets.bottom;
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
            component.setBounds(insets.left + 200, insets.top + 200, 400, 60);
        component = container.getComponent(1);
        if(component.isVisible())
            component.setBounds(insets.left + 200, insets.top + 250, 400, 60);
    }
}
