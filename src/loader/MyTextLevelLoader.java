package loader;

import loader.level.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* MyTextLevelLoader will return a level from a file input stream */
public class MyTextLevelLoader implements LevelLoader {

    @Override
    public Level loadLevel(InputStream stream) throws IOException{

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(stream));

        ArrayList<ArrayList<Tile>> levelMap = new ArrayList<>();
        levelMap.add(new ArrayList<Tile>());

        // variables for iterating through the 2d ArrayList
        int outerIndex=0;
        int innerIndex=0;
        int c;

            while ((c = fileReader.read())!=-1){ // while not end of stream
                char character = (char)c;
                    switch (character){
                        // for each char we'll add it's represented item to the tile
                        case 'x':
                            levelMap.get(outerIndex).add(innerIndex,new Tile(new Location(outerIndex,innerIndex),new WallItem()));
                            innerIndex++;
                            break;
                        case 'B':
                            levelMap.get(outerIndex).add(innerIndex,new Tile(new Location(outerIndex,innerIndex),new BoxItem()));
                            innerIndex++;
                            break;
                        case ' ':
                            levelMap.get(outerIndex).add(innerIndex,new Tile(new Location(outerIndex,innerIndex)));
                            innerIndex++;
                            break;
                        case 'A':
                            levelMap.get(outerIndex).add(innerIndex,new Tile(new Location(outerIndex,innerIndex),new PlayerItem()));
                            innerIndex++;
                            break;
                        case '@':
                            levelMap.get(outerIndex).add(innerIndex,new TargetTile(new Location(outerIndex,innerIndex)));
                            innerIndex++;
                            break;
                        case '\n':
                            outerIndex++;
                            innerIndex=0;
                            levelMap.add(new ArrayList<Tile>());
                            break;
                    }
            }

        // creating and returning our new level with the map we've received
        return new Level(levelMap);
    }
}

