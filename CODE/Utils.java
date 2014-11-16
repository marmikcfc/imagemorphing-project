import java.io.File;
import java.io.PrintStream;
import javax.swing.ImageIcon;

public class Utils
{

    public Utils()
    {
    }

    public static String getExtension(File file)
    {
        String s = null;
        String s1 = file.getName();
        int i = s1.lastIndexOf('.');
        if(i > 0 && i < s1.length() - 1)
            s = s1.substring(i + 1).toLowerCase();
        return s;
    }

    protected static ImageIcon createImageIcon(String s)
    {
        java.net.URL url = (Utils.class).getResource(s);
        if(url != null)
        {
            return new ImageIcon(url);
        } else
        {
            System.err.println("Couldn't find file: " + s);
            return null;
        }
    }

    public static final String jpeg = "jpeg";
    public static final String jpg = "jpg";
}