package edu.wctc;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable, Paintable {
    private static int roomCount;
    private int roomNum;

    private ArrayList<Wall> wallList;

    public Room(double length, double width, double height) throws BadWidthException, BadHeightException {
        wallList = new ArrayList<Wall>();

        Wall wallA = new Wall(length, height);
        wallList.add(wallA);
        Wall wallB = new Wall(length, height);
        wallList.add(wallB);
        Wall wallC = new Wall(width, height);
        wallList.add(wallC);
        Wall wallD = new Wall(width, height);
        wallList.add(wallD);
        roomCount++;
        roomNum = roomCount;
    }

    public double getArea() {
        double area = 0;

        for (int i = 0; i < wallList.size(); i++) {
            Wall w = wallList.get(i);
            area += w.getArea();
        }

        return area;
    }



    public String toString(){
      return "A Room has an area of " + getArea();
    }



    @Override
    public double getPremiumCost() {
        double incr = 0;
        for(Wall w : wallList){
            incr += w.getArea();
        }
        return (Math.ceil(incr/530))*PREMIUM_PAINT_COST_PER_GALLON;
    }



    @Override
    public double getStandardCost() {
        double incr = 0;
        for(Wall w : wallList){
            incr += w.getArea();
        }
        return (Math.ceil(incr/420))*STANDARD_PAINT_COST_PER_GALLON;
    }
}
