package tests;

import moulin.Color;
import org.junit.Test;

public class testColor {
    @Test
    public static boolean gettersAndBooleans() {
        System.out.println("Getters and Booleans");
        Color noir = Color.NOIR;
        if(noir.getValue()!='N')return false;
        System.out.println("\tgetValue ok");
        if(!noir.getString().equals("\u001B[30m"))return false;
        System.out.println("\tgetString ok");
        if(!Color.isColor(noir.toString()))return false;
        if(!Color.isColor("Noir"))return false;
        if(!Color.isColor("noiR"))return false;
        if(!Color.isColor("noir"))return false;
        System.out.println("\tisColor ok");
        return true;
    }
}
