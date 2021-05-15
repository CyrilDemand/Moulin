package tests;

import moulin.Color;
import moulin.Piece;
import moulin.Player;
import org.junit.Test;

public class testPlayer {
    @Test
    public static boolean testPlayer(){
        String nomValid = "testNom";
        Color colorValid = Color.ROUGE;
        Piece pieceValid = new Piece(colorValid,0);
        Player newTestP = new Player(nomValid,colorValid);
        newTestP.addPiece(pieceValid);

        if(!(newTestP.getName().equals(nomValid)))return false;
        if(!(newTestP.getColor().equals(colorValid)))return false;
        if(!(newTestP.getPieces().get(0).getColor().equals(colorValid)))return false;

        return true;
    }
}
