package loader.level;

/* The abstract class LevelItem describes the general functionality of each item that inherits it */
public abstract class LevelItem implements Item{

    Location currentLocation;

    public String getType(){
        return this.getClass().getSimpleName();
    }

    @Override
    public Location getLocation() {
        return null;
    }

    @Override
    public void setLocation(Location location) {
        this.currentLocation=location;
    }


}