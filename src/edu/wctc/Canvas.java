package edu.wctc;

import java.io.Serializable;

public class Canvas implements Serializable, Paintable {

    private double width;
    private double height;

    public Canvas(double width, double height) throws BadWidthException, BadHeightException {
        this.width = width;

        if (width <= 0) {
            throw new BadWidthException("Width must be greater than zero!");
        }

        this.height = height;

        if (height <= 0) {
            throw new BadHeightException("Height must be greater than zero!");
        }
    }

    public double getArea(){
        return this.height * this.width;
    }

    public String toString(){
        return  "A canvas has an area of " + getArea();
    }

    @Override
    public double getPremiumCost() {
         return (Math.ceil(getArea()/530))*PREMIUM_PAINT_COST_PER_GALLON;
    }



    @Override
    public double getStandardCost() {
        return (Math.ceil(getArea()/420))*STANDARD_PAINT_COST_PER_GALLON;
    }
}
