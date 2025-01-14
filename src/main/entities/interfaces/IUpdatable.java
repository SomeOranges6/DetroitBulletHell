package main.entities.interfaces;

/** Interface used in anything that needs to be updated.
 *  The update rate is once every 1/20th of a second**/
public interface IUpdatable {

    /**Called from BulletHellLogic.java, use a timer if the entity does not need
     * to update every second **/
    void onUpdate();

}
