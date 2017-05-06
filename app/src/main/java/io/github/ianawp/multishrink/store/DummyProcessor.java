package io.github.ianawp.multishrink.store;

import android.app.Application;
import android.net.Uri;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;

/**
 * Created by IanAWP on 4/05/2017.
 */

public class DummyProcessor implements ImageProcessor {
    private final Application app;

    @Inject
    public DummyProcessor(Application app){

        this.app = app;
    }

    @Override
    public void processImage(Image image, OutputStream outputStream) {
        InputStream inputStream;
        try {
             inputStream = app.getContentResolver().openInputStream(Uri.parse(image.getSourceUri()));
            byte[] buffer    =   new byte[10*1024];

            for (int length; (length = inputStream.read(buffer)) != -1; ){
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
