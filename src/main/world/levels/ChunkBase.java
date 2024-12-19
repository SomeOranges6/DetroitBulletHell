package main.world.levels;

import main.entities.EntityBase;
import main.entities.interfaces.IUpdatable;
import java.awt.*;
import java.util.ArrayList;

public class ChunkBase extends Rectangle {

    private static final ArrayList<EntityBase> entitiesToRender = new ArrayList<>();
    private static final ArrayList<IUpdatable> entitiesToUpdate = new ArrayList<>();

    public static ArrayList<EntityBase>
            renderableAddCache= new ArrayList<>(),
            renderableRemoveCache = new ArrayList<>();

    public static ArrayList<IUpdatable>
            updatableAddCache  = new ArrayList<>(),
            updatableRemoveCache = new ArrayList<>();

    /**List of all areas in the map that will perform an action.
     * The key is the area which will perform the action, the value is the action to be performed**/
    public static ArrayList<Trigger> triggers;

}
