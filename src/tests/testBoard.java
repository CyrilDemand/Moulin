package tests;

import moulin.Board;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class testBoard {

    @Test
    public static void main(String[] args){

        Board board=Board.loadBoard("ressources\\mapTest.json");
        board.render(3,2);

        assertTrue(testNodes);

    }

    @Test
    public boolean testNodes(){
        int x = 0;
        int y = 0;
        int id = 0;

        Node newNode1 = new Node(x,y,id);
        if(testNewNode1.getX!=x)return false;
        if(testNewNode1.getY!=y)return false;
        if(testNewNode1.getId!=id)return false;

        Node  newNode2 = new Node(x,y);
        if(testNewNode2.getX!=x)return false;
        if(testNewNode2.getY!=y)return false;
        if(testNewNode2.getId!=id)return false;

        return true;
    }

    public boolean testEdges(){
        Node newNode1 = new Node(0,0,0);
        Node newNode2 = new Node(0,1,1);
        Node newNode3 = new Node(0,2,2);

        Edge e1 = Edge(newNode1, newNode2);
        Edge e2 = Edge(newNode2, newNode3Edge);

        return true;

    }


}
