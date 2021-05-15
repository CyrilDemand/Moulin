package tests;

import moulin.Node;
import org.junit.Test;

public class testNode {
    @Test
    public static boolean testNodes(){
        int x = 0;
        int y = 0;
        int id = 0;

        Node newNode1 = new Node(x,y,id);
        Node newNode1bis = new Node(x,y,id);
        Node newNode2 = new Node(x,y);
        Node newNode3 = new Node(x+1,y+1);

        if(newNode1.getX()!=x)return false;
        if(newNode1.getY()!=y)return false;
        if(newNode1.getId()!=id)return false;

        if(!(newNode1.equals(newNode1bis)))return false;
        if(newNode1.equals(null))return false;

        if(newNode2.getX()!=x)return false;
        if(newNode2.getY()!=y)return false;

        return true;
    }
}