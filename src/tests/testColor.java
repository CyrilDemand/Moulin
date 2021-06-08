package tests;

import moulin.ColorEnum;
import org.junit.Test;

public class testColor {
    @Test
    public static boolean gettersAndBooleans() {
        System.out.println("Getters and Booleans");
        ColorEnum noir = ColorEnum.NOIR;
        if(noir.getValue()!='N')return false;
        System.out.println("\tgetValue ok");
        if(!noir.getString().equals("\u001B[30m"))return false;
        System.out.println("\tgetString ok");
        if(!ColorEnum.isColor(noir.toString()))return false;
        if(!ColorEnum.isColor("Noir"))return false;
        if(!ColorEnum.isColor("noiR"))return false;
        if(!ColorEnum.isColor("noir"))return false;
        System.out.println("\tisColor ok");
        return true;
    }
}
