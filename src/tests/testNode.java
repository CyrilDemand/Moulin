package tests;

import moulin.*;
import org.junit.Test;

public class testNode {
    @Test
    public static boolean testCounters() {
        System.out.println("Counter");
        int x = 0;
        int y = 0;
        int id = 0;

        Node.resetCounter();
        Node newNode1 = new Node(x,y,id);
        Node.resetCounter();
        Node newNode1bis = new Node(x,y,id);
        int c1=newNode1.getCounter();
        int c2=newNode1bis.getCounter();
        Node newNode2 = new Node(x,y);
        int c3=newNode2.getCounter();

        //Tests des counters
        if(c1!=c2)return false;
        System.out.println("\tc1==c2 == "+c1+"=="+c2+" ok");
        if(c1==c3)return false;
        System.out.println("\tc1!=c3 == "+c1+"!="+c3+" ok");
        Node.resetCounter();
        return true;
    }

    @Test
    public static boolean testGettersAndSetters(){
        System.out.println("Getters and Setters");
        int x = 0;
        int y = 0;
        int id = 0;

        Node newNode1 = new Node(x,y,id);


        //Tests des Getters
        if(newNode1.getX()!=x)return false;
        System.out.println("\tgetX ok");
        if(newNode1.getY()!=y)return false;
        System.out.println("\tgetY ok");
        if(newNode1.getId()!=id)return false;
        System.out.println("\tgetId ok");

        //Tests des Setters
        x++;y++;id++;

        newNode1.setX(x);
        newNode1.setY(y);
        newNode1.setId(id);

        if(newNode1.getX()!=x)return false;
        System.out.println("\tsetX ok");
        if(newNode1.getY()!=y)return false;
        System.out.println("\tsetY ok");
        if(newNode1.getId()!=id)return false;
        System.out.println("\tsetId ok");

        return true;
    }

    @Test
    public static boolean testEquals() {
        System.out.println("Equals");
        int x = 0;
        int y = 0;
        int id = 0;

        Node newNode1 = new Node(x,y,id);
        Node newNode1bis = new Node(x,y,id);
        Node newNode2 = new Node(x,y);
        Node newNode3 = new Node(x+1,y+1);

        if(!(newNode1.equals(newNode1bis)))return false;
        System.out.println("\tequals1 ok");
        if(newNode1.equals(null))return false;
        System.out.println("\tequals2 ok");
        if(newNode1.equals(newNode3))return false;
        System.out.println("\tequals3 ok");
        return true;
    }


    public static boolean testItsATrapNode(){
        System.out.println("Trapped Node");
        Node n1 = new Node(0,0,0);
        Node n2 = new Node(0,1,1);
        TrapTeleport trap = new TrapTeleport(3, n2);
        Piece p = new Piece(ColorEnum.ROUGE,0);
        p.put(n1);

        n1.setTrap(trap);
        if(!n1.getTrap().equals(trap))return false;
        System.out.println("\tset and getTrap ok");
        if(!n1.isTrapped())return false;
        System.out.println("\tisTrapped ok");
        p.put(n2);
        if(p.getNode()!=n2)return false;
        System.out.println("\tgetNode1 ok");
        p.put(n1);
        if(p.getNode()==n1)return false;
        System.out.println("\tgetNode2 ok");
        if(p.getNode()!=n2)return false;
        System.out.println("\tgetNode3 ok");
        return true;
    }

}