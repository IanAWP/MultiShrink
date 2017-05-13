package io.github.ianawp.multishrink.store.magick;

import android.net.Uri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;
import javax.inject.Named;



import io.github.ianawp.multishrink.store.DummyProcessor;
import io.github.ianawp.multishrink.store.Image;
import io.github.ianawp.multishrink.store.ImageProcessor;
import io.github.ianawp.multishrink.store.JobDescription;
import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;


/**
 * Created by IanAWP on 12/05/2017.
 */

public class MagickProcessor implements ImageProcessor {

    private final File base;
    private final DummyProcessor copier;

    @Inject
    public MagickProcessor(@Named("FSBase") File base, DummyProcessor d){
        this.base = base;
        this.copier = d;
    }

    @Override
    public void processImage(Image image, JobDescription description, OutputStream stream) {
        try {
            File temp = new File(base, image.getImageName());
            temp.createNewFile();

            copier.processImage(image, description, new FileOutputStream(temp));

            ImageInfo img = new ImageInfo(temp.getAbsolutePath());

          MagickImage im=  new MagickImage(img);
            boolean switchDimen = false;
            // source image may be portrait or landscape.
            // larger of the two is the max dimension
            // set other dimen proportionately
            int x,y;

            if(im.getWidth()>im.getHeight()){
                x=description.getMaxDimension();
                y=(int)(((double)im.getHeight()/(double)im.getWidth())*x);
            }
            else{
                y=description.getMaxDimension();
                x=(int)(((double)im.getHeight()/(double)im.getWidth())*y);
            }
            MagickImage im2 =            im.scaleImage(x,y);

            im2.writeImage(img);
            copyImage(new FileInputStream(temp), stream);
        } catch (MagickException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void copyImage(InputStream i, OutputStream outputStream) {
        InputStream inputStream;
        try {

            byte[] buffer    =   new byte[10*1024];

            for (int length; (length = i.read(buffer)) != -1; ){
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();
            i.close();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
