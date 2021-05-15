package tests;

import moulin.Board;
import moulin.Node;
import org.junit.Test;

public class testBoard {
    @Test
    public static boolean testBoard(){
        Board boardTestAdd = new Board();

        Node nodeAdd = new Node(0,2,81);
        boardTestAdd.addNode(0,0);
        boardTestAdd.addNode(0,1,80);
        boardTestAdd.addNode(nodeAdd);

        Board board=Board.loadBoard("ressources\\mapTest.json");
        Board boardBis = new Board();

        Node n1 = new Node(0,0,1);
        Node n2 = new Node(1,0,2);
        Node n3 = new Node(2,0,3);

        boardBis.addNode(n1);
        boardBis.addNode(n2);
        boardBis.addNode(n3);
        boardBis.addEdge(1,2);
        boardBis.addEdge(2,3);
        System.out.println(board.toString());
        if(!(board.equals(boardBis))) return false;
        return true;
    }
}
