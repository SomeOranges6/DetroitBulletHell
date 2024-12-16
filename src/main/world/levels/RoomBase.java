package main.world.levels;

import main.BulletHellLogic;
import main.MathUtil;
import main.entities.EntityBase;
import main.entities.enemies.TestEnemy;
import main.entities.interfaces.IUpdatable;
import main.world.tiles.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class RoomBase extends Rectangle {

    private static final ArrayList<EntityBase> entitiesToRender = new ArrayList<>();
    private static final ArrayList<IUpdatable> entitiesToUpdate = new ArrayList<>();

    public static ArrayList<EntityBase>
            renderableAddCache= new ArrayList<>(),
            renderableRemoveCache = new ArrayList<>();

    public static ArrayList<IUpdatable>
            updatableAddCache  = new ArrayList<>(),
            updatableRemoveCache = new ArrayList<>();

    /**The list of all tiles this room has, by id **/
    public int[][] mapTileNum;

    /**List of all areas in the map that will perform an action.
     * The key is the area which will perform the action, the value is the action to be performed**/
    public static ArrayList<Trigger> triggers;

}
