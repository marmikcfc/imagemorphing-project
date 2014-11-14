class point
{

    point()
    {
        x = 0;
        y = 0;
    }

    point(int i, int j)
    {
        x = i;
        y = j;
    }

    point(int i, int j, int k)
    {
        x = i;
        y = j;
        id = k;
    }

    int x;
    int y;
    int id;
}
