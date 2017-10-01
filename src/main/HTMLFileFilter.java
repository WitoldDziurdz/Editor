package main;

import javax.swing.filechooser.FileFilter;
import java.io.File;


/**
 * Created by vitia on 20.09.2017.
 */
public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        if(f.isDirectory()) return true; 
        if(f.getName().toLowerCase().endsWith(".html")||f.getName().toLowerCase().endsWith(".htm")){
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
