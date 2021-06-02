package tests;

import moulin.Color;
import moulin.Piece;
import moulin.Player;
import org.junit.Test;

public class testPlayer {
    @Test
    public static boolean gettersAndBooleans(){
        System.out.println("Getters and Booleans");

        String nomValid = "testNom";
        Color colorValid = Color.ROUGE;
        Piece pieceValid = new Piece(colorValid,0);
        Player newTestP = new Player(nomValid,colorValid);

        newTestP.addPiece(pieceValid);
        System.out.println("\taddPiece ok");

        if(!(newTestP.getName().equals(nomValid)))return false;
        System.out.println("\tgetName ok");
        if(!(newTestP.getColor().equals(colorValid)))return false;
        System.out.println("\tgetColor ok");
        if(!(newTestP.getPieces().get(0).getColor().equals(colorValid)))return false;
        System.out.println("\tgetPieces ok");


        int nbTrap = newTestP.getNbTrap();
        newTestP.placedATrap();
        if(nbTrap-1!=newTestP.getNbTrap())return false;
        System.out.println("\tplacedATrap ok");

        return true;
    }

    @Test
    public static boolean chooseNameAndColor() {
        System.out.println("Choose Name and Color");
        System.out.println("Pour valider ce test, il est nécessaire d'avoir une saisie utilisateur");

        String name = Player.chooseName();
        Color color = Player.chooseColor();
        System.out.println("A vous d'en juger !\nNom : "+name+"\nCouleur : "+color.toString());
        return true;
    }

}
