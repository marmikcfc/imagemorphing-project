import java.util.*;

public class SourceModel extends Observable
{

    public SourceModel()
    {
        elementList = new LinkedList();
    }

    public boolean remove(Element element)
    {
        boolean flag = elementList.remove(element);
        if(flag)
        {
            setChanged();
            notifyObservers(element.getBounds());
        }
        return flag;
    }

    public void add(Element element)
    {
        elementList.add(element);
        setChanged();
        notifyObservers(element.getBounds());
    }

    public Iterator getIterator()
    {
        return elementList.listIterator();
    }

    protected LinkedList elementList;
}