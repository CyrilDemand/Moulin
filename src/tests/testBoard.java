package tests;

import moulin.Board;
import moulin.Edge;
import moulin.Node;
import moulin.Save;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class testBoard {
    @Test
    public static boolean testAddNodes(){
        System.out.println("Add Nodes");

        Node.resetCounter();
        Board b1 = new Board();

        Node n = new Node(0,0,1);
        b1.addNode(n);
        b1.addNode(0,1);
        b1.addNode(1,1,3);
        System.out.println("\taddNodes1 ok");

        Board b2 = new Board();

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node(0,0,1));
        nodes.add(new Node(0,1,2));
        nodes.add(new Node(1,1,3));
        b2.addNodes(nodes);
        System.out.println("\taddNodes2 ok");

        if(!(b1.equals(b2)))return false;
        System.out.println("\tequals ok");

        return true;
    }

    @Test
    public static boolean testAddEdges(){
        System.out.println("Add Edges");
        Board b1 = new Board();

        b1.addNode(new Node(0,0,1));
        b1.addNode(new Node(0,0,2));
        Node n1 = new Node(0,0,3);
        Node n2 = new Node(0,0,4);
        b1.addNode(n1);
        b1.addNode(n2);
        System.out.println("\taddNode ok");

        b1.addEdge(1,2);
        b1.addEdge(n1,n2);
        System.out.println("\taddEdges1 ok");

        Board b2 = new Board();
        ArrayList<Edge> edges = new ArrayList<>();
        edges.add(new Edge(new Node(0,0,1),new Node(0,0,2)));
        edges.add(new Edge(new Node(0,0,3),new Node(0,0,4)));
        b2.addEdges(edges);
        System.out.println("\taddEdges2 ok");

        if(!(b1.equals(b2)))return false;
        System.out.println("\tequals ok");

        return true;
    }

    @Test
    public static boolean testGetters() {
        System.out.println("Getters");

        Board nodes = new Board();
        int minX=1, minY=1, minID= 1, maxX = 3, maxY = 3, maxID = 3;

        nodes.addNode(new Node(minX,minY,minID));
        nodes.addNode(new Node(2,maxY,2));
        nodes.addNode(new Node(maxX,2,maxID));

        if(nodes.getMinX()!=minX)return false;
        System.out.println("\tgetMinX ok");
        if(nodes.getMinY()!=minY)return false;
        System.out.println("\tgetMinY ok");
        if(nodes.getMinId()!=minID)return false;
        System.out.println("\tgetMinId ok");
        if(nodes.getMaxX()!=maxX)return false;
        System.out.println("\tgetMaxX ok");
        if(nodes.getMaxY()!=maxY)return false;
        System.out.println("\tgetMaxY ok");
        if(nodes.getMaxId()!=maxID)return false;
        System.out.println("\tgetMaxId ok");

        return true;
    }

    @Test
    public static boolean testLoadBoardJson(){
        System.out.println("LoadBoardJson");

        Board board= Save.loadBoard("src"+ File.separator+"tests"+ File.separator+"ressources"+ File.separator+"mapTest.json");
        System.out.println("\tload ok");

        Board boardBis = new Board();
        boardBis.addNode(new Node(0,0,1));
        boardBis.addNode(new Node(1,0,2));
        boardBis.addNode(new Node(2,0,3));
        boardBis.addNode(new Node(0,1,4));
        System.out.println("\taddNodes ok");
        boardBis.addEdge(1,2);
        boardBis.addEdge(2,3);
        boardBis.addEdge(1,4);
        System.out.println("\taddEdges ok");

        if(!(board.equals(boardBis))) return false;
        System.out.println("\tequals ok");

        return true;
    }

}