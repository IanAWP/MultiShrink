package io.github.ianawp.multishrink.store.fs;

import android.graphics.Path;
import android.net.Uri;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.github.ianawp.multishrink.store.db.JobPersistenceManager;

/**
 * Created by IanAWP on 5/05/2017.
 */

public class FSPersistenceManager implements JobPersistenceManager {
    public File getBase() {
        return base;
    }

    public void setBase(File base) {
        this.base = base;
    }

    File base;

    @Inject
    public FSPersistenceManager(@Named("FSBase") File base){
        setBase(base);

    }
    private File determineFile(String scope, String key){

        File scopeDir = new File(getBase(), scope);
        if(!scopeDir.exists()){
            scopeDir.mkdir();
        }
        File target = new File(scopeDir, key);


        return  target;
    }

    @Override
    public OutputStream getOutputStream(String scope, String key) throws IOException {
        File target = determineFile(scope, key);
        if(target.exists()){
            target.delete();
        }
        target.createNewFile();
        return  new FileOutputStream(target , false);
    }

    @Override
    public Uri getUri(String scope, String key) {
         return Uri.fromFile( determineFile(scope, key));
    }

    @Override
    public void deleteScope(String scope) {
        File scopeDir = new File(getBase(), scope);
        if(scopeDir.exists()){
            scopeDir.delete();
        }
    }

    @Override
    public void delete(String scope, String key) {
        File target = determineFile(scope, key);
        if(target.exists())  {
            target.delete();
        }
    }
}
