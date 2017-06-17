package loader;


import loader.level.Level;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/* MyObjectLevelLoader will return a level from a binary saved format */
public class MyObjectLevelLoader implements LevelLoader {
    @Override
    public Level loadLevel(InputStream stream) throws IOException,ClassNotFoundException {
            // loading the level from the stream
            Level level = (Level) new ObjectInputStream(stream).readObject();
            if (level!=null && level.getLevelMap()!=null)
                return level;
        return null;
    }
}
