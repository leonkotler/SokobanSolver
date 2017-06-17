package utils;


import loader.LevelLoader;
import loader.MyObjectLevelLoader;
import loader.MyTextLevelLoader;
import loader.MyXMLLevelLoader;
import loader.level.Level;
import loader.level.Tile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FileReader {

    private LevelLoader loader;
    private Level level = null;
    private FilePathUtil fp = new FilePathUtil();

    public ArrayList<char[]> fileToGrid(String filePath) {

        try {
            // Get the file's extension and instantiate with the proper loader
            String fileExtension = fp.getExtension(filePath);

            switch (fileExtension) {
                case "txt":
                    loader = new MyTextLevelLoader();
                    break;
                case "obj":
                    loader = new MyObjectLevelLoader();
                    break;
                case "xml":
                    loader = new MyXMLLevelLoader();
                    break;
                default:
                    throw new IOException("Unsupported file extension");
            }

            level = loader.loadLevel(new FileInputStream(filePath));

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (level != null)
            return getGridFromLevel(level);

        return null;
    }

    private ArrayList<char[]> getGridFromLevel(Level level) {

        ArrayList<ArrayList<Tile>> levelMap = level.getLevelMap();

        ArrayList<char[]> grid = new ArrayList<>();

        for (ArrayList<Tile> row : levelMap){
            grid.add(rowToCharArray(row));
        }

        return grid;
    }

    private char[] rowToCharArray(ArrayList<Tile> row){

        char[] newRow = new char[row.size()];
        int i=0;

        for (Tile tile : row){
            newRow[i] = tile.toString().toCharArray()[0];
            i++;
        }

        return newRow;
    }
}
