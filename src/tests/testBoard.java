package tests;

import moulin.Board;
import moulin.Edge;
import moulin.Node;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class testBoard {

    @Test
    public static void main(String[] args){

        Board board=Board.loadBoard("ressources\\mapTest.json");
        board.render(3,2);

        assertTrue(testNodes());
        assertTrue(testEdges());
    }

    @Test
    public static boolean testNodes(){
        int x = 0;
        int y = 0;
        int id = 0;

        Node newNode1 = new Node(x,y,id);
        if(newNode1.getX()!=x)return false;
        if(newNode1.getY()!=y)return false;
        if(newNode1.getId()!=id)return false;

        Node  newNode2 = new Node(x,y);
        if(newNode2.getX()!=x)return false;
        if(newNode2.getY()!=y)return false;
        if(newNode2.getId()!=id)return false;

        return true;
    }

    public static boolean testEdges(){
        Node newNode1 = new Node(0,0,0);
        Node newNode2 = new Node(0,1,1);
        Node newNode3 = new Node(0,2,2);

        Edge e1 = new Edge(newNode1, newNode2);
        Edge e2 = new Edge(newNode2, newNode3);

        if(!(e1.getStart().equals(newNode1)))return false;
        if(!(e2.getStart().equals(newNode2)))return false;
        if(!(e1.getEnd().equals(newNode2)))return false;
        if(!(e2.getEnd().equals(newNode3)))return false;

        // Cela peut paraître ridicule,
        // mais j'ai du faire de cette façon
        // pour que ça return true et pour éviter
        // la création d'une methode notEquals qui
        // aurait été inutile.

        return true;

    }


}
