class mesh
{

    mesh()
    {
        m1 = new point();
        m2 = new point();
        m3 = new point();
        m4 = new point();
    }

    mesh(point point1, point point2, point point3)
    {
        m1 = point1;
        m2 = point2;
        m3 = point3;
        m4 = new point();
    }

    mesh(point point1, point point2, point point3, int i)
    {
        m1 = point1;
        m2 = point2;
        m3 = point3;
        id = i;
        m4 = new point();
    }

    point m1;
    point m2;
    point m3;
    point m4;
    int id;
}