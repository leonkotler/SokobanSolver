package loader.level;



import utils.StopWatch;

import java.io.Serializable;
import java.util.ArrayList;

public class Level implements Serializable {

    protected ArrayList<ArrayList<Tile>> levelMap; // 2D ArrayList that contains tiles
    protected Location playerLocation;             // holds the current player location
    protected ArrayList<Tile> targets;             // points to the targets
    protected ArrayList<Item> boxes;               // points to the boxes
    protected int x;                         // level dimensions
    protected int y;
    protected int stepCounter=0;
    StopWatch stopWatch;
    String levelName;



    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public StopWatch getStopWatch() {
        return stopWatch;
    }

    public void setStopWatch(StopWatch stopWatch) {
        this.stopWatch = stopWatch;
    }

    public int getStepCounter() {
        return stepCounter;
    }

    public void setStepCounter(int stepCounter) {
        this.stepCounter = stepCounter;
    }

    public ArrayList<Item> getBoxes() {
        return boxes;
    }

    public void setBoxes(ArrayList<Item> boxes) {
        this.boxes = boxes;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Level(ArrayList<ArrayList<Tile>> levelMap) {
        this.levelMap = levelMap;
        initData();
        startStopWatch();
    }

    public Level() {
    }

    public void setLevelMap(ArrayList<ArrayList<Tile>> levelMap) {
        this.levelMap = levelMap;
    }

    public ArrayList<ArrayList<Tile>> getLevelMap() {
        return levelMap;
    }

    public Location getPlayerLocation() {
        return playerLocation;
    }

    public void setPlayerLocation(Location playerLocation) {
        this.playerLocation = playerLocation;
    }

    public ArrayList<Tile> getTargets() {
        return targets;
    }

    public void setTargets(ArrayList<Tile> targets) {
        this.targets = targets;
    }

    public void initData() {
        targets = new ArrayList<>();
        boxes = new ArrayList<>();

        // setting the level's dimensions
        x = levelMap.size();
        y = levelMap.get(0).size();

        // iterates through the level's map and extracts the player's location and targets
        for (ArrayList<Tile> row : levelMap) {
            if (row.size() > y)
                y = row.size();
            for (Tile tile : row) {
                if (tile.toString().equals("@"))
                    targets.add(tile); // adding the tile to the targets array
                if (tile.toString().equals("A"))
                    setPlayerLocation(tile.getLocation()); // adding the player's location
                if (tile.toString().equals("B"))
                    boxes.add(tile.getContains());
            }
        }
    }

    public void moveItem(Location to, Location from) {
        // moves the Item in the provided location to the provided location
        levelMap.get(to.getX()).get(to.getY())
                .setContains(levelMap.get(from.getX()).get(from.getY()).getContains());
        // setting the from tile to contain nothing
        levelMap.get(from.getX()).get(from.getY()).freeTile();
    }

    public boolean isWon() {
        // checks if every target has a box in it
        boolean win = true;

        for (Tile tile : targets) {
            if (tile.getContains() != null) {
                if (!tile.getContains().getType().equals("BoxItem"))
                    win = false;
            } else win = false;
        }
        return win;
    }

    private void startStopWatch(){
        stopWatch=new StopWatch();
        stopWatch.start();
    }

    public String getTimePassed(){
        stopWatch.stop();
        return stopWatch.getElapsedTime();
    }
}
