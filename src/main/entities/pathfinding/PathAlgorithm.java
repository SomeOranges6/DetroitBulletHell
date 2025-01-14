package main.entities.pathfinding;

import main.world.tiles.Tile;

import java.awt.*;
import java.util.ArrayList;

public class PathAlgorithm {
    static ArrayList<Point> openList, closedList;
    static Point startTile, endTile;


    public static ArrayList<Point> pathToTile(Point start, Point end){
        openList = new ArrayList<>();
        closedList = new ArrayList<>();
        startTile = start;
        endTile = start;
        openList.add(start);
        while(!closedList.contains(end)){

        }


        return null;
    }

    public int getCost(Point point){
        int distanceCost = (int) (Math.pow(Math.abs(point.x - point.x),2) + Math.pow(Math.abs(startTile.x - point.y),2));
        int heuristicCost = (int) (Math.pow(Math.abs(point.x - point.x),2) + Math.pow(Math.abs(startTile.x - point.y),2));
    }


}
