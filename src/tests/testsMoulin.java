package tests;

import moulin.*;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class testsMoulin{

    @Test
    public static void main(String[] args){
        assertTrue(testNode.testNodes());
        assertTrue(testEdge.testEdges());
        assertTrue(testBoard.testBoard());
        assertTrue(testPlayer.testPlayer());
    }
    
}