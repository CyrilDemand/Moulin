package tests;

import moulin.Board;
import moulin.Color;
import moulin.Piece;
import moulin.Save;
import org.junit.Test;

import java.io.File;

public class testPiece {
    @Test
    public static boolean gettersAndBooleans() {
        System.out.println("Getters And Booleans");
        Color c = Color.BLEU;
        int id = 0;
        int idNode=1;

        Piece p1 = new Piece(c,id);
        Piece p2 = new Piece(c,1);

        Board b = Save.loadBoard("src"+ File.separator+"tests"+ File.separator+"ressources"+ File.separator+"mapTest.json");
        p1.put(b.getNodeById(idNode));
        p2.put(b.getNodeById(2));


        if(p1.getColor()!=c)return false;
        System.out.println("\tgetColor ok");
        if(p1.getId()!=id)return false;
        System.out.println("\tgetID ok");
        if(!p1.getNode().equals(b.getNodeById(idNode)))return false;
        System.out.println("\tgetNode ok");


        if(p1.isMovable(b))return false;
        System.out.println("\tisMovable 1");
        if(!p2.isMovable(b))return false;
        System.out.println("\tisMovable 2");
        if(!p2.move(b,3))return false;
        System.out.println("\tmove ok");
        if(b.getNodeById(3).isEmpty())return false;
        System.out.println("\tnodeNon vide");
        if(!p1.isMovable(b))return false;
        System.out.println("\tisMovable 3");
        System.out.println("\tisMovable ok");

        return true;
    }

}
