package loader;

import loader.level.Level;

import java.io.IOException;
import java.io.InputStream;

/**
 * A: The LevelLoader is responsible for creating and returning the level. Any LevelLoader must implement loadLevel(InputStream):Level
 *    The level is the final product, it's responsible only for the representation of the data.
 * B: We will be able to provide many different level generators without having to modify Level itself, meaning - we have many ways to alter the creation process
 *    of the level without actually having to modify the already created code.
 * C: Because every kind of loader implements LevelLoader we'll be able to reference each of them through the LevelLoader
 *    interface and not directly. This provides us with great flexibility.
 * D: Because we want to be able to inject ANY input stream, and not only a file input stream.
 */

public interface LevelLoader {
    Level loadLevel(InputStream stream) throws IOException,ClassNotFoundException;
}
