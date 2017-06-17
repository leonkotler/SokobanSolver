package loader.level;

import java.io.Serializable;

/* Tile represents the tiles of the level */
public class Tile implements Serializable {

    // can hold any item inside of it
    protected Item contains;
    protected Location location;


    public Tile() {
        this.location = null;
        this.contains = null;
    }

    public Tile(Location location) {
        this.location = location;
    }

    public Tile(Location location, Item contains) {
        setLocation(location);
        setContains(contains);
    }

    public Item getContains() {
        return contains;
    }

    public void setContains(Item contains) {
        this.contains = contains;
        // setting the item's location to be the current tile's location
        if (contains!= null)
            this.contains.setLocation(this.getLocation());
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isEmpty() {
        return (contains == null);
    }

    public void freeTile() {
        setContains(null);
    }

    @Override
    public String toString() {
        // if the tile contains any other item, then it gets displayed, otherwise - displayer blank space;
        if (contains == null)
            return " ";
        else
            return contains.toString();
    }
}
