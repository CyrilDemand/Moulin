package tests;

import moulin.*;
import org.junit.Test;

public class testEdge {
    @Test
    public static boolean testGettersAndSetters(){
        System.out.println("Getters and Setters");
        Node newNode1 = new Node(0,0,0);
        Node newNode2 = new Node(0,1,1);
        Node newNode3 = new Node(0,2,2);

        Edge e1 = new Edge(newNode1, newNode2);
        Edge e2 = new Edge(newNode2, newNode3);

        if(!(e1.getStart().equals(newNode1)))return false;
        if(!(e2.getStart().equals(newNode2)))return false;
        System.out.println("\tgetStart ok");
        if(!(e1.getEnd().equals(newNode2)))return false;
        if(!(e2.getEnd().equals(newNode3)))return false;
        System.out.println("\tgetEnd ok");

        e1.setStart(newNode2);
        e1.setEnd(newNode3);
        e2.setStart(newNode3);
        e2.setEnd(newNode1);

        if(!(e1.getStart().equals(newNode2)))return false;
        if(!(e2.getStart().equals(newNode3)))return false;
        System.out.println("\tsetStart ok");
        if(!(e1.getEnd().equals(newNode3)))return false;
        if(!(e2.getEnd().equals(newNode1)))return false;
        System.out.println("\tsetEnd ok");

        return true;
    }

    @Test
    public static boolean testEqualsAndToString() {
        System.out.println("Equals and toString");
        Node newNode1 = new Node(0, 0, 0);
        Node newNode2 = new Node(0, 1, 1);
        Node newNode2bis = new Node(0, 1, 1);

        Edge e1 = new Edge(newNode1,newNode2);
        Edge e1bis = new Edge(newNode1,newNode2bis);
        Edge e2 = new Edge(newNode2,newNode1);

        if(!(e1.equals(e1bis)))return false;
        if(e1.equals(e2))return false;
        System.out.println("\tequals ok");

        String s1="Edge{start=0, end=1}";
        if(!(e1.toString().equals(s1)))return false;
        System.out.println("\ttoString ok");
        return true;
    }
    public static boolean testItsATrapEdge(){
        System.out.println("Trapped Edge");
        Trap trap = new Trap(3);
        Board b = new Board();
        Node n1 = new Node(0,0,0);
        Node n2 = new Node(0,1,1);

        Piece p = new Piece(ColorEnum.ROUGE,0);
        p.put(n1);

        Edge e = new Edge(n1,n2);
        b.addNode(n1);
        b.addNode(n2);
        b.addEdge(e);

        if(n1.isLinkedWith(b).contains(n2)) {
            System.out.println("\tLinked nodes");
            if (e.isTrapped()) return false;
            System.out.println("\tisTrapped ok");
            e.setTrap(trap);
            if (!e.getTrap().equals(trap)) return false;
            System.out.println("\tset and getTrap ok");
            if (!e.isTrapped()) return false;
            System.out.println("\tisNoLongerTrapped ok");
        }
        return true;
    }
}
