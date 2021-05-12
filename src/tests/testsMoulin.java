package tests;

import moulin.*;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class testsMoulin{

    @Test
    public static void main(String[] args){
        assertTrue(testNodes());
        assertTrue(testEdges());
        assertTrue(testVector());
        assertTrue(testBoard());
        assertTrue(testPlayer());
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

        Node newNode1bis = new Node(x,y,id);

        if(!(newNode1.equals(newNode1bis)))return false;
        if(newNode1.equals(null))return false;

        Node  newNode2 = new Node(x,y);
        if(newNode2.getX()!=x)return false;
        if(newNode2.getY()!=y)return false;

        return true;
    }

    @Test
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

        return true;
    }

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