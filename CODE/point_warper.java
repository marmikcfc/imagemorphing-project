import java.util.Vector;

class point_warper
{

    point_warper()
    {
    }

    public Vector mesh_formation(point apoint[], int i, int j, int k)
    {
        point apoint1[] = new point[i];
        point apoint2[] = new point[i];
        for(int l = 0; l < i; l++)
        {
            apoint1[l] = apoint[l];
            apoint2[l] = apoint[l];
        }

        for(int i1 = 0; i1 < i; i1++)
        {
            for(int j1 = i1 + 1; j1 < i; j1++)
            {
                if(apoint1[i1].y > apoint1[j1].y)
                {
                    point point1 = new point();
                    point1 = apoint1[i1];
                    apoint1[i1] = apoint1[j1];
                    apoint1[j1] = point1;
                }
                if(apoint2[i1].x > apoint2[j1].x)
                {
                    point point2 = new point();
                    point2 = apoint2[i1];
                    apoint2[i1] = apoint2[j1];
                    apoint2[j1] = point2;
                }
            }

        }

        Vector vector = new Vector(10, 2);
        int k1 = 0;
        int l1 = 0;
        int ai[] = new int[i];
        for(int i2 = 0; i2 < i; i2++)
            ai[i2] = 0;

        boolean flag = false;
        point point3 = new point();
        boolean flag1 = false;
        int j2 = 0;
        int k2 = 0;
        int l2 = i - 1;
        int i3 = i - 1;
        pt = new point[3];
        int j3 = 0;
        do
        {
            if(k1 >= i)
                break;
            if(!flag)
            {
                switch(l1)
                {
                default:
                    break;

                case 0: // '\0'
                    if(j2 < i)
                    {
                        point3 = apoint1[j2++];
                        int k3 = point3.id;
                        if(point3.y < j / 2 && ai[k3] == 0)
                        {
                            flag = true;
                            ai[k3] = 1;
                            k1++;
                        }
                    }
                    break;

                case 1: // '\001'
                    if(i3 < 0)
                        break;
                    point3 = apoint2[i3--];
                    int l3 = point3.id;
                    if(point3.x > k / 2 && ai[l3] == 0)
                    {
                        flag = true;
                        ai[l3] = 1;
                        k1++;
                    }
                    break;

                case 2: // '\002'
                    if(l2 < 0)
                        break;
                    point3 = apoint1[l2--];
                    int j4 = point3.id;
                    if(point3.y > j / 2 && ai[j4] == 0)
                    {
                        flag = true;
                        ai[j4] = 1;
                        k1++;
                    }
                    break;

                case 3: // '\003'
                    if(k2 >= i)
                        break;
                    point3 = apoint2[k2++];
                    int l4 = point3.id;
                    if(point3.x < k / 2 && ai[l4] == 0)
                    {
                        flag = true;
                        ai[l4] = 1;
                        k1++;
                    }
                    break;
                }
                l1 = (l1 + 1) % 4;
            }
            if(flag)
            {
                if(j3 < 3)
                {
                    pt[j3] = point3;
                    if(++j3 == 3)
                        vector.addElement(new mesh(pt[0], pt[1], pt[2], vector.size()));
                } else
                if(is_outside_to_all_mesh(point3, vector, vector.size()))
                {
                    vector.addElement(new mesh(point3, pt[0], pt[1], vector.size()));
                } else
                {
                    mesh mesh1 = (mesh)vector.elementAt(mesh_index);
                    point point4 = mesh1.m1;
                    point point5 = mesh1.m2;
                    point point7 = mesh1.m3;
                    vector.setElementAt(new mesh(point4, point5, point3, mesh_index), mesh_index);
                    vector.addElement(new mesh(point5, point7, point3, vector.size()));
                    vector.addElement(new mesh(point7, point4, point3, vector.size()));
                }
                flag = false;
            }
        } while(true);
        int i4 = vector.size();
        for(int k4 = 0; k4 < i4; k4++)
        {
            point point6 = new point();
            point point8 = new point();
            point point10 = new point();
            mesh mesh2 = (mesh)vector.elementAt(k4);
            if(count_meshes_with_link(mesh2.m1, mesh2.m2, vector, i4) == 1)
            {
                point point12 = mesh2.m1;
                point point16 = mesh2.m2;
                point point19 = mesh2.m3;
                point point22 = nearest_point(point12, point16, point19, apoint, i);
                vector.addElement(new mesh(point12, point16, point22, 1000));
            }
            if(count_meshes_with_link(mesh2.m2, mesh2.m3, vector, i4) == 1)
            {
                point point13 = mesh2.m2;
                point point17 = mesh2.m3;
                point point20 = mesh2.m1;
                point point23 = nearest_point(point13, point17, point20, apoint, i);
                vector.addElement(new mesh(point13, point17, point23, 1000));
            }
            if(count_meshes_with_link(mesh2.m3, mesh2.m1, vector, i4) == 1)
            {
                point point14 = mesh2.m3;
                point point18 = mesh2.m1;
                point point21 = mesh2.m2;
                point point24 = nearest_point(point14, point18, point21, apoint, i);
                vector.addElement(new mesh(point14, point18, point24, 1000));
            }
        }

        for(int i5 = 0; i5 < 4; i5++)
        {
            point point9 = apoint[i + i5];
            point point11 = apoint[i + (i5 + 1) % 4];
            int j5 = 0;
            do
            {
                if(j5 >= i)
                    break;
                point point15 = apoint[j5];
                if(count_meshes_with_link(point15, point9, vector, vector.size()) == 1 && count_meshes_with_link(point15, point11, vector, vector.size()) == 1)
                {
                    vector.addElement(new mesh(point9, point11, point15, 1000));
                    break;
                }
                j5++;
            } while(true);
        }

        return vector;
    }

    public boolean is_outside_to_all_mesh(point point1, Vector vector, int i)
    {
        boolean flag = true;
        int j = point1.x;
        int k = point1.y;
        int l = 0;
        int i1 = 0;
        double d = 20000D;
        for(int j1 = 0; j1 < i; j1++)
        {
            mesh mesh1 = (mesh)vector.elementAt(j1);
            if(isInside3(mesh1.m1, mesh1.m2, mesh1.m3, point1))
            {
                flag = false;
                mesh_index = j1;
                break;
            }
            if(!isJustifies(mesh1.m1, mesh1.m2, mesh1.m3, point1))
            {
                int k1 = (mesh1.m1.x + mesh1.m2.x) / 2;
                int j2 = (mesh1.m1.y + mesh1.m2.y) / 2;
                double d1 = Math.sqrt((k1 - j) * (k1 - j) + (j2 - k) * (j2 - k));
                if(d1 < d && count_meshes_with_link(mesh1.m1, mesh1.m2, vector, vector.size()) == 1)
                {
                    d = d1;
                    l = j1;
                    i1 = 12;
                }
            }
            if(!isJustifies(mesh1.m3, mesh1.m1, mesh1.m2, point1))
            {
                int l1 = (mesh1.m1.x + mesh1.m3.x) / 2;
                int k2 = (mesh1.m1.y + mesh1.m3.y) / 2;
                double d2 = Math.sqrt((l1 - j) * (l1 - j) + (k2 - k) * (k2 - k));
                if(d2 < d && count_meshes_with_link(mesh1.m3, mesh1.m1, vector, vector.size()) == 1)
                {
                    d = d2;
                    l = j1;
                    i1 = 13;
                }
            }
            if(!isJustifies(mesh1.m2, mesh1.m3, mesh1.m1, point1))
            {
                int i2 = (mesh1.m2.x + mesh1.m3.x) / 2;
                int l2 = (mesh1.m2.y + mesh1.m3.y) / 2;
                double d3 = Math.sqrt((i2 - j) * (i2 - j) + (l2 - k) * (l2 - k));
                if(d3 < d && count_meshes_with_link(mesh1.m2, mesh1.m3, vector, vector.size()) == 1)
                {
                    d = d3;
                    l = j1;
                    i1 = 23;
                }
            }
        }

        if(flag)
        {
            mesh mesh2 = (mesh)vector.elementAt(l);
            if(i1 == 12)
            {
                pt[0] = mesh2.m1;
                pt[1] = mesh2.m2;
            } else
            if(i1 == 13)
            {
                pt[0] = mesh2.m1;
                pt[1] = mesh2.m3;
            } else
            {
                pt[0] = mesh2.m2;
                pt[1] = mesh2.m3;
            }
        }
        return flag;
    }

    public boolean isInside3(point point1, point point2, point point3, point point4)
    {
        boolean flag = true;
        if(!isJustifies(point1, point2, point3, point4))
            flag = false;
        else
        if(!isJustifies(point2, point3, point1, point4))
            flag = false;
        else
        if(!isJustifies(point3, point1, point2, point4))
            flag = false;
        return flag;
    }

    public boolean isJustifies(point point1, point point2, point point3, point point4)
    {
        boolean flag = true;
        int i = point1.x;
        int j = point1.y;
        int k = point2.x;
        int l = point2.y;
        double d;
        double d1;
        if(j - l == 0)
        {
            int i1 = point3.y;
            d = i1 - j;
            i1 = point4.y;
            d1 = i1 - j;
        } else
        if(i - k == 0)
        {
            int j1 = point3.x;
            d = j1 - i;
            j1 = point4.x;
            d1 = j1 - i;
        } else
        {
            double d2 = (double)(j - l) / (double)(i - k);
            int k1 = point3.x;
            int l1 = point3.y;
            d = (double)(l1 - j) - d2 * (double)(k1 - i);
            k1 = point4.x;
            l1 = point4.y;
            d1 = (double)(l1 - j) - d2 * (double)(k1 - i);
        }
        if(d1 * d < 0.0D)
            flag = false;
        return flag;
    }

    public int count_meshes_with_link(point point1, point point2, Vector vector, int i)
    {
        int j = 0;
        for(int k = 0; k < i && j < 2; k++)
        {
            mesh mesh1 = (mesh)vector.elementAt(k);
            if(mesh1.m1.x == point1.x && mesh1.m1.y == point1.y && mesh1.m2.x == point2.x && mesh1.m2.y == point2.y || mesh1.m2.x == point1.x && mesh1.m2.y == point1.y && mesh1.m1.x == point2.x && mesh1.m1.y == point2.y)
                j++;
            else
            if(mesh1.m2.x == point1.x && mesh1.m2.y == point1.y && mesh1.m3.x == point2.x && mesh1.m3.y == point2.y || mesh1.m3.x == point1.x && mesh1.m3.y == point1.y && mesh1.m2.x == point2.x && mesh1.m2.y == point2.y)
                j++;
            else
            if(mesh1.m1.x == point1.x && mesh1.m1.y == point1.y && mesh1.m3.x == point2.x && mesh1.m3.y == point2.y || mesh1.m3.x == point1.x && mesh1.m3.y == point1.y && mesh1.m1.x == point2.x && mesh1.m1.y == point2.y)
                j++;
        }

        return j;
    }

    public point nearest_point(point point1, point point2, point point3, point apoint[], int i)
    {
        int j = (point1.x + point2.x) / 2;
        int k = (point1.y + point2.y) / 2;
        double d = 20000D;
        point point4 = new point();
        if(!isJustifies(point1, point2, point3, apoint[i]))
        {
            int l = apoint[i].x;
            int l1 = apoint[i].y;
            double d1 = Math.sqrt((l - j) * (l - j) + (l1 - k) * (l1 - k));
            if(d1 < d)
            {
                point4 = apoint[i];
                d = d1;
            }
        }
        if(!isJustifies(point1, point2, point3, apoint[i + 1]))
        {
            int i1 = apoint[i + 1].x;
            int i2 = apoint[i + 1].y;
            double d2 = Math.sqrt((i1 - j) * (i1 - j) + (i2 - k) * (i2 - k));
            if(d2 < d)
            {
                point4 = apoint[i + 1];
                d = d2;
            }
        }
        if(!isJustifies(point1, point2, point3, apoint[i + 2]))
        {
            int j1 = apoint[i + 2].x;
            int j2 = apoint[i + 2].y;
            double d3 = Math.sqrt((j1 - j) * (j1 - j) + (j2 - k) * (j2 - k));
            if(d3 < d)
            {
                point4 = apoint[i + 2];
                d = d3;
            }
        }
        if(!isJustifies(point1, point2, point3, apoint[i + 3]))
        {
            int k1 = apoint[i + 3].x;
            int k2 = apoint[i + 3].y;
            double d4 = Math.sqrt((k1 - j) * (k1 - j) + (k2 - k) * (k2 - k));
            if(d4 < d)
            {
                point4 = apoint[i + 3];
                double d5 = d4;
            }
        }
        return point4;
    }

    point pt[];
    int mesh_index;
}