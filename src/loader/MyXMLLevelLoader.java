package loader;

import loader.level.Level;

import java.beans.XMLDecoder;
import java.io.IOException;
import java.io.InputStream;

/* MyObjectLevelLoader will return a level from an XML saved format */
public class MyXMLLevelLoader implements LevelLoader{
    @Override
    public Level loadLevel(InputStream stream) throws IOException, ClassNotFoundException {
        // loading the level from the stream
        Level level = (Level) new XMLDecoder(stream).readObject();
        if (level!=null && level.getLevelMap()!=null)
            return level;
        return null;
    }
}
