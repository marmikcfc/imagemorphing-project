import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

static class user_intf$1 extends WindowAdapter
{

    public void windowClosing(WindowEvent windowevent)
    {
        System.exit(0);
    }

    user_intf$1()
    {
    }
}