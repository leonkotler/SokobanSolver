package loader;

import loader.level.Level;

import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.OutputStream;

/* MyXMLLevelSaver will save a level in an XML format to a stream */
public class MyXMLLevelSaver implements LevelSaver {
    @Override
    public void saveLevel(Level level, OutputStream stream) throws IOException {
        // checking if the level and it's map are legal
        if (level == null || level.getLevelMap() == null) throw new IOException("There is no level to save");

        // writing the level object to the stream
        XMLEncoder out = new XMLEncoder(stream);
        out.writeObject(level);
        out.close();
    }
}
