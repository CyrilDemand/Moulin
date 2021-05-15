package tests;

import moulin.Vecteur;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class testVector {


    @Test
    public static boolean testVector(){
        int x1= 2;
        int y1= 3;
        int x2= 4;
        int y2= 6;

        Vecteur v1 = new Vecteur(x1,y1);
        Vecteur v1bis = new Vecteur(x1,y1);
        Vecteur v2 = new Vecteur(x2,y2);

        if(!(v1.equals(v1bis)))return false;
        if(v1.equals(v2))return false;
        if(!(v1.isCollinear(v2)))return false;
        return true;
    }
}
