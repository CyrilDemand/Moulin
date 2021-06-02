package tests;

import moulin.*;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class testsMoulin{

    @Test
    public static void main(String[] args){
        String d="\n============";
        //testBoard
        System.out.println("\n**TESTS BOARD**"+d);
        assertTrue(testBoard.testAddNodes());
        assertTrue(testBoard.testAddEdges());
        assertTrue(testBoard.testGetters());
        assertTrue(testBoard.testLoadBoardJson());

        //testColor
        System.out.println("\n**TESTS COLOR**"+d);
        assertTrue(testColor.gettersAndBooleans());

        //testEdge
        System.out.println("\n**TESTS EDGE**"+d);
        assertTrue(testEdge.testGettersAndSetters());
        assertTrue(testEdge.testEqualsAndToString());
        assertTrue(testEdge.testItsATrapEdge());

        //testJeu
        System.out.println("\n**TESTS JEU**"+d);
        assertTrue(testJeu.testGettersAndSetters());
        assertTrue(testJeu.testFinish());

        //testNode
        System.out.println("\n**TESTS NODE**"+d);
        assertTrue(testNode.testCounters());
        assertTrue(testNode.testGettersAndSetters());
        assertTrue(testNode.testEquals());
        assertTrue(testNode.testItsATrapNode());

        //testPiece
        System.out.println("\n**TESTS PIECE**"+d);
        assertTrue(testPiece.gettersAndBooleans());


        //testPlayer
        System.out.println("\n**TESTS PLAYER**"+d);
        assertTrue(testPlayer.gettersAndBooleans());
        assertTrue(testPlayer.chooseNameAndColor());

    }


}