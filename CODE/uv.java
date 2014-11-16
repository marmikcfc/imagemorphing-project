class uv
{

    uv()
    {
        u = 0.0D;
        v = 0.0D;
        u = 0.0D;
        v = 0.0D;
    }

    uv(double d, double d1)
    {
        u = 0.0D;
        v = 0.0D;
        u = d;
        v = d1;
    }

    public uv calculate_uv(point point1, point point2, point point3, point point4, point point5)
    {
        double d = point1.x - point2.x;
        double d1 = -point2.x + point3.x;
        double d2 = -point2.x + point5.x;
        double d3 = ((point2.x - point3.x) + point4.x) - point5.x;
        double d4 = point1.y - point2.y;
        double d5 = -point2.y + point3.y;
        double d6 = -point2.y + point5.y;
        double d7 = ((point2.y - point3.y) + point4.y) - point5.y;
        double d8 = d;
        double d9 = d1;
        double d10 = d2;
        d8 = d - (d3 / d7) * d4;
        d9 = d1 - (d3 / d7) * d5;
        d10 = d2 - (d3 / d7) * d6;
        double d11 = 0.0D;
        double d12 = d5;
        double d13 = -d4;
        if(d10 != 0.0D);
        d11 = (-d9 * d7) / d10;
        d12 = (d5 - (d9 * d6) / d10) + (d8 * d7) / d10;
        d13 = (d8 * d6) / d10 - d4;
        double d14 = 0.0D;
        double d15 = 0.0D;
        double d16 = 0.0D;
        double d17 = 0.0D;
        double d18 = Math.sqrt(d12 * d12 - 4D * d11 * d13);
        if(d11 == 0.0D)
        {
            u = -d13 / d12;
            v = (d8 - u * d9) / d10;
        } else
        {
            double d19 = (-d12 + d18) / (2D * d11);
            double d20 = (-d12 - d18) / (2D * d11);
            if(d10 != 0.0D)
            {
                d16 = (d8 - d19 * d9) / d10;
                d17 = (d8 - d20 * d9) / d10;
            }
            if(d19 >= 0.0D && d20 >= 0.0D)
            {
                if(d16 <= 1.0D)
                {
                    u = d19;
                    v = d16;
                } else
                if(d17 <= 1.0D)
                {
                    u = d20;
                    v = d17;
                }
            } else
            if(d19 >= 0.0D)
            {
                u = d19;
                v = d16;
            } else
            {
                u = d20;
                v = d17;
            }
        }
        uv uv1 = new uv(u, v);
        return uv1;
    }

    public point estimate_point(uv uv1, point point1, point point2, point point3, point point4)
    {
        u = uv1.u;
        v = uv1.v;
        point point5 = new point();
        point5.x = (int)((1.0D - u) * (1.0D - v) * (double)point1.x + u * (1.0D - v) * (double)point2.x + u * v * (double)point3.x + (1.0D - u) * v * (double)point4.x);
        point5.y = (int)((1.0D - u) * (1.0D - v) * (double)point1.y + u * (1.0D - v) * (double)point2.y + u * v * (double)point3.y + (1.0D - u) * v * (double)point4.y);
        return point5;
    }

    double u;
    double v;
}