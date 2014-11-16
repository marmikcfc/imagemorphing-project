import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;

class morph
{

    morph()
    {
    }

    public Image[] mainMorph(Image image, Image image1, point apoint[], point apoint1[], int i, int j, String s)
    {
        Image aimage[] = new Image[j + 2];
        bimg = new BufferedImage[j + 2];
        fimage = s;
        siw = image.getWidth(null);
        sih = image.getHeight(null);
        pixelsS = new int[siw * sih];
        try
        {
            PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, siw, sih, pixelsS, 0, siw);
            pixelgrabber.grabPixels();
        }
        catch(InterruptedException interruptedexception) { }
        if(image1 != null)
        {
            diw = image1.getWidth(null);
            dih = image1.getHeight(null);
            pixelsD = new int[diw * dih];
            aimage[j + 1] = image1;
            try
            {
                PixelGrabber pixelgrabber1 = new PixelGrabber(image1, 0, 0, diw, dih, pixelsD, 0, diw);
                pixelgrabber1.grabPixels();
            }
            catch(InterruptedException interruptedexception1) { }
            apoint1[i - 4] = new point(0, 0, i - 4);
            apoint1[i - 3] = new point(diw - 1, 0, i - 3);
            apoint1[i - 2] = new point(diw - 1, dih - 1, i - 2);
            apoint1[i - 1] = new point(0, dih - 1, i - 1);
        } else
        {
            apoint1[i - 4] = new point(0, 0, i - 4);
            apoint1[i - 3] = new point(siw - 1, 0, i - 3);
            apoint1[i - 2] = new point(siw - 1, sih - 1, i - 2);
            apoint1[i - 1] = new point(0, sih - 1, i - 1);
        }
        aimage[0] = image;
        if(fimage != null)
        {
            for(int k = 0; k < j + 2; k++)
                bimg[k] = new BufferedImage(siw, sih, 1);

            for(int l = 0; l < siw * sih; l++)
            {
                bimg[0].setRGB(l % siw, l / siw, pixelsS[l]);
                if(image1 != null)
                    bimg[j + 1].setRGB(l % siw, l / siw, pixelsD[l]);
            }

            try
            {
                ImageIO.write(bimg[0], "JPG", new File(fimage + 0 + ".jpg"));
            }
            catch(IOException ioexception) { }
            if(image1 != null)
                try
                {
                    ImageIO.write(bimg[j + 1], "JPG", new File(fimage + (j + 1) + ".jpg"));
                }
                catch(IOException ioexception1) { }
        }
        apoint[i - 4] = new point(0, 0, i - 4);
        apoint[i - 3] = new point(siw - 1, 0, i - 3);
        apoint[i - 2] = new point(siw - 1, sih - 1, i - 2);
        apoint[i - 1] = new point(0, sih - 1, i - 1);
        point_warper point_warper1 = new point_warper();
        mshS = point_warper1.mesh_formation(apoint, i - 4, siw, sih);
        mshS = calc_m4(mshS);
        mshD = destMesh(apoint1, mshS);
        calcIncr(apoint, apoint1, i, j);
        for(int i1 = 1; i1 <= j; i1++)
        {
            destinationImageWeight = 2D * (double)i1 * (1.0D / (double)(j + 1));
            sourceImageWeight = 2D - destinationImageWeight;
            Vector vector = interpolateMesh(i1, mshS);
            aimage[i1] = interImage(image, image1, siw, sih, mshS, mshD, vector, i1);
        }

        return aimage;
    }

    public Image interImage(Image image, Image image1, int i, int j, Vector vector, Vector vector1, Vector vector2, 
            int k)
    {
        uv uv1 = new uv();
        boolean flag = false;
        int l = 0;
        int ai[] = new int[i * j];
        for(int i1 = 0; i1 < j; i1++)
        {
            for(int j1 = 0; j1 < i; j1++)
            {
                point point1 = new point(j1, i1);
                int k1 = j1 + i * i1;
                int l1 = identify_mesh(point1, vector2);
                int i2;
                if(l1 != -1)
                {
                    mesh mesh1 = (mesh)vector2.elementAt(l1);
                    mesh mesh2 = (mesh)vector.elementAt(l1);
                    mesh mesh3 = (mesh)vector1.elementAt(l1);
                    uv uv2 = uv1.calculate_uv(point1, mesh1.m1, mesh1.m2, mesh1.m3, mesh1.m4);
                    point point2 = uv1.estimate_point(uv2, mesh2.m1, mesh2.m2, mesh2.m3, mesh2.m4);
                    point point3 = uv1.estimate_point(uv2, mesh3.m1, mesh3.m2, mesh3.m3, mesh3.m4);
                    if(point2.x + i * point2.y < j * i && point2.x + i * point2.y > 0)
                        i2 = pixelsS[point2.x + i * point2.y];
                    else
                        i2 = pixelsS[k1];
                    if(image1 != null)
                        if(point3.x + i * point3.y < j * i && point3.x + i * point3.y > 0)
                            l = pixelsD[point3.x + i * point3.y];
                        else
                            l = pixelsD[k1];
                } else
                {
                    i2 = pixelsS[k1];
                    if(image1 != null)
                        l = pixelsD[k1];
                }
                if(image1 != null)
                {
                    int j2 = i2;
                    int k2 = 0xff & j2 >> 24;
                    int l2 = 0xff & j2 >> 16;
                    int i3 = 0xff & j2 >> 8;
                    int j3 = 0xff & j2;
                    j2 = l;
                    int k3 = 0xff & j2 >> 24;
                    int l3 = 0xff & j2 >> 16;
                    int i4 = 0xff & j2 >> 8;
                    int j4 = 0xff & j2;
                    int k4 = (int)((double)k2 * sourceImageWeight + (double)k3 * destinationImageWeight) / 2;
                    int l4 = (int)((double)l2 * sourceImageWeight + (double)l3 * destinationImageWeight) / 2;
                    int i5 = (int)((double)i3 * sourceImageWeight + (double)i4 * destinationImageWeight) / 2;
                    int j5 = (int)((double)j3 * sourceImageWeight + (double)j4 * destinationImageWeight) / 2;
                    ai[k1] = k4 << 24 | l4 << 16 | i5 << 8 | j5;
                } else
                {
                    ai[k1] = i2;
                }
                if(fimage != null)
                    bimg[k].setRGB(k1 % i, k1 / i, ai[k1]);
            }

        }

        Image image2 = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(i, j, ai, 0, i));
        if(fimage != null)
            try
            {
                ImageIO.write(bimg[k], "JPG", new File(fimage + k + ".jpg"));
            }
            catch(IOException ioexception) { }
        return image2;
    }

    public Vector interpolateMesh(int i, Vector vector)
    {
        Vector vector1 = new Vector(vector.size(), 2);
        for(int j = 0; j < vector.size(); j++)
        {
            mesh mesh1 = new mesh();
            mesh mesh2 = (mesh)vector.elementAt(j);
            mesh1.m1.x = mesh2.m1.x + (int)(incrx[mesh2.m1.id] * (double)i);
            mesh1.m1.y = mesh2.m1.y + (int)(incry[mesh2.m1.id] * (double)i);
            mesh1.m2.x = mesh2.m2.x + (int)(incrx[mesh2.m2.id] * (double)i);
            mesh1.m2.y = mesh2.m2.y + (int)(incry[mesh2.m2.id] * (double)i);
            mesh1.m3.x = mesh2.m3.x + (int)(incrx[mesh2.m3.id] * (double)i);
            mesh1.m3.y = mesh2.m3.y + (int)(incry[mesh2.m3.id] * (double)i);
            mesh1.m4.x = (mesh1.m1.x + mesh1.m3.x) / 2;
            mesh1.m4.y = (mesh1.m1.y + mesh1.m3.y) / 2;
            mesh1.id = mesh2.id;
            vector1.addElement(mesh1);
        }

        return vector1;
    }

    public Vector calc_m4(Vector vector)
    {
        for(int i = 0; i < vector.size(); i++)
        {
            mesh mesh1 = (mesh)vector.elementAt(i);
            mesh1.m4.x = (mesh1.m1.x + mesh1.m3.x) / 2;
            mesh1.m4.y = (mesh1.m1.y + mesh1.m3.y) / 2;
            vector.setElementAt(mesh1, i);
        }

        return vector;
    }

    public Vector destMesh(point apoint[], Vector vector)
    {
        Vector vector1 = new Vector(vector.size(), 2);
        for(int i = 0; i < vector.size(); i++)
        {
            mesh mesh1 = new mesh();
            mesh mesh2 = (mesh)vector.elementAt(i);
            mesh1.m1 = apoint[mesh2.m1.id];
            mesh1.m2 = apoint[mesh2.m2.id];
            mesh1.m3 = apoint[mesh2.m3.id];
            mesh1.m4.x = (mesh1.m1.x + mesh1.m3.x) / 2;
            mesh1.m4.y = (mesh1.m1.y + mesh1.m3.y) / 2;
            vector1.addElement(mesh1);
        }

        return vector1;
    }

    public int identify_mesh(point point1, Vector vector)
    {
        int i = -1;
        point_warper point_warper1 = new point_warper();
        for(int j = 0; j < vector.size(); j++)
        {
            mesh mesh1 = (mesh)vector.elementAt(j);
            if(point_warper1.isInside3(mesh1.m1, mesh1.m2, mesh1.m3, point1))
                if(i == -1)
                    i = j;
                else
                if(mesh1.id != 1000)
                    i = j;
        }

        return i;
    }

    public void calcIncr(point apoint[], point apoint1[], int i, int j)
    {
        incrx = new double[i];
        incry = new double[i];
        for(int k = 0; k < i; k++)
        {
            incrx[k] = (double)(apoint1[k].x - apoint[k].x) * (1.0D / (double)(j + 1));
            incry[k] = (double)(apoint1[k].y - apoint[k].y) * (1.0D / (double)(j + 1));
        }

    }

    double incrx[];
    double incry[];
    double sourceImageWeight;
    double destinationImageWeight;
    Vector mshS;
    Vector mshD;
    int sih;
    int siw;
    int pixelsS[];
    int dih;
    int diw;
    int pixelsD[];
    String fimage;
    BufferedImage bimg[];
}