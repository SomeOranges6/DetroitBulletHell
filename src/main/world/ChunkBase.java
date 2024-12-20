package main.world;

import main.entities.EntityBase;
import main.entities.interfaces.IUpdatable;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**A 32x32 tile subdivision of the map, which is used for optimizing entity operations, such as collisions */
public class ChunkBase extends Rectangle {

    private static final ArrayList<EntityBase> entitiesToRender = new ArrayList<>();
    private static final ArrayList<IUpdatable> entitiesToUpdate = new ArrayList<>();

    public static ArrayList<EntityBase>
            renderableAddCache= new ArrayList<>(),
            renderableRemoveCache = new ArrayList<>();

    public static ArrayList<IUpdatable>
            updatableAddCache  = new ArrayList<>(),
            updatableRemoveCache = new ArrayList<>();

}
