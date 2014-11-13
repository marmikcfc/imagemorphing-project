import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class CrossDissolver
{

    CrossDissolver()
    {
    }

    public Image[] CrossDissolve(Image image, Image image1, int i, String s)
    {
        img = new Image[i + 2];
        bimg = new BufferedImage[i + 2];
        try
        {
            siw = image.getWidth(null);
            sih = image.getHeight(null);
            pixelsS = new int[siw * sih];
            pg = new PixelGrabber(image, 0, 0, siw, sih, pixelsS, 0, siw);
            pg.grabPixels();
        }
        catch(InterruptedException interruptedexception) { }
        try
        {
            diw = image1.getWidth(null);
            dih = image1.getHeight(null);
            pixelsD = new int[diw * dih];
            pg = new PixelGrabber(image1, 0, 0, diw, dih, pixelsD, 0, diw);
            pg.grabPixels();
        }
        catch(InterruptedException interruptedexception1) { }
        if(siw * sih >= diw * dih)
        {
            larger = 0;
            iw = siw;
            ih = sih;
            scaledImage = image1.getScaledInstance(iw, ih, 1);
            try
            {
                newImage = new int[iw * ih];
                pg = new PixelGrabber(scaledImage, 0, 0, iw, ih, newImage, 0, iw);
                pg.grabPixels();
            }
            catch(InterruptedException interruptedexception2) { }
        } else
        {
            larger = 1;
            iw = diw;
            ih = dih;
            scaledImage = image.getScaledInstance(iw, ih, 1);
            try
            {
                newImage = new int[iw * ih];
                pg = new PixelGrabber(scaledImage, 0, 0, iw, ih, newImage, 0, iw);
                pg.grabPixels();
            }
            catch(InterruptedException interruptedexception3) { }
        }
        a = new int[iw * ih];
        r = new int[iw * ih];
        g = new int[iw * ih];
        b = new int[iw * ih];
        img[0] = image;
        temp = new int[i + 1][iw * ih];
        for(int j = 0; j < i + 2; j++)
            bimg[j] = new BufferedImage(iw, ih, 1);

        for(int k = 0; k < i + 1; k++)
        {
            for(int l = 0; l < iw * ih; l++)
            {
                int j1;
                if(larger == 0)
                    j1 = pixelsS[l];
                else
                    j1 = newImage[l];
                int k1 = 0xff & j1 >> 24;
                int l1 = 0xff & j1 >> 16;
                int i2 = 0xff & j1 >> 8;
                int j2 = 0xff & j1;
                if(k == 0)
                    bimg[0].setRGB(l % iw, l / iw, k1 << 24 | l1 << 16 | i2 << 8 | j2);
                if(larger == 1)
                    j1 = pixelsD[l];
                else
                    j1 = newImage[l];
                int k2 = 0xff & j1 >> 24;
                int l2 = 0xff & j1 >> 16;
                int i3 = 0xff & j1 >> 8;
                int j3 = 0xff & j1;
                k1 += ((k + 1) * (k2 - k1)) / (i + 1);
                l1 += ((k + 1) * (l2 - l1)) / (i + 1);
                i2 += ((k + 1) * (i3 - i2)) / (i + 1);
                j2 += ((k + 1) * (j3 - j2)) / (i + 1);
                temp[k][l] = k1 << 24 | l1 << 16 | i2 << 8 | j2;
                bimg[k + 1].setRGB(l % iw, l / iw, temp[k][l]);
            }

        }

        for(int i1 = 0; i1 < i + 1; i1++)
        {
            img[i1 + 1] = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(iw, ih, temp[i1], 0, iw));
            if(s != null)
                try
                {
                    ImageIO.write(bimg[i1], "JPG", new File(s + i1 + ".jpg"));
                }
                catch(IOException ioexception) { }
        }

        if(s != null)
            try
            {
                ImageIO.write(bimg[i + 1], "JPG", new File(s + (i + 1) + ".jpg"));
            }
            catch(IOException ioexception1) { }
        return img;
    }

    Image img[];
    Image scaledImage;
    PixelGrabber pg;
    int siw;
    int sih;
    int diw;
    int dih;
    int iw;
    int ih;
    int pixelsS[];
    int pixelsD[];
    int temp[][];
    int rs;
    int gs;
    int bs;
    int as;
    int rd;
    int gd;
    int bd;
    int ad;
    int r[];
    int g[];
    int b[];
    int a[];
    int newImage[];
    BufferedImage bimg[];
    int larger;
}
