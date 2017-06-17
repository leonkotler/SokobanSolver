package loader;



import loader.level.Level;

import java.io.IOException;
import java.io.OutputStream;

/* LevelSaver defines an interface for saving a level to a given output stream */
public interface LevelSaver {

    // Getting a level object and an OutputStream from outside
    void saveLevel(Level level, OutputStream stream) throws IOException;
}
