package utils;

/* FilePathUtil provides method for working with file paths */

import java.io.FileNotFoundException;
import java.io.IOException;

public class FilePathUtil {

    public String getExtension(String filePath) throws IOException{

        if (filePath==null)
            throw new FileNotFoundException("Please provide a valid file path");
        String ext;
        // getting the substring after the last dot
        int i = filePath.lastIndexOf(".");
        if (i>=0) {
            ext = filePath.substring(i + 1);
            return ext;
        } else
            throw new FileNotFoundException("Please provide a supported file format");
    }

    public String getFileNameFromPath(String filePath) throws IOException{

        if (filePath==null)
            throw new FileNotFoundException("Please provide a valid file path");

        String fileName;
        // getting the substring after the last '\'
        int i = filePath.lastIndexOf("\\");
        if (i>=0) {
            fileName = filePath.substring(i + 1);
            return fileName;
        } else
            return filePath;
    }
}
