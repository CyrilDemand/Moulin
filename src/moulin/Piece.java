package moulin;

import java.util.ArrayList;
import java.util.Scanner;

public class Piece {
    private final Color color;

    private Node node;

    Piece(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void put(Node node){
        this.node=node;
        this.node.setPiece(this);
    }

    public Node getNode() {
        return node;
    }

    public static void main(String[] args) {
        //test discord
        Board board=Board.loadBoard("ressources\\mapSquare.json");
        Scanner scanner = new Scanner( System.in );

        Jeu jeu = new Jeu(board,Main.initPlayer());
        jeu.displayPlayers();
        ArrayList<Vecteur> v = new ArrayList<Vecteur>();
        ArrayList<Boolean> b = new ArrayList<Boolean>();
        jeu.getPlayers().get(0).getPieces().get(0).put(jeu.getBoard().getNodeById(1));
        System.out.println(jeu.getBoard().getNodeById(1));
        jeu.getPlayers().get(0).getPieces().get(1).put(jeu.getBoard().getNodeById(2));
        System.out.println(jeu.getBoard().getNodeById(2));
        jeu.getPlayers().get(0).getPieces().get(2).put(jeu.getBoard().getNodeById(4));
        System.out.println(jeu.getBoard().getNodeById(24));


        for (int i = 0; i<jeu.getPlayers().get(0).getPieces().size()-1;i++){
            v.add(new Vecteur(jeu.getPlayers().get(0).getPieces().get(i).getNode().getX(),
                    jeu.getPlayers().get(0).getPieces().get(i+1).getNode().getX(),
                    jeu.getPlayers().get(0).getPieces().get(i).getNode().getY(),
                    jeu.getPlayers().get(0).getPieces().get(i+1).getNode().getY())
            );
        }

        for (int i = 0; i<jeu.getPlayers().get(0).getPieces().size()-1;i++){
            b.add(jeu.getBoard().isLinked(jeu.getPlayers().get(0).getPieces().get(i).getNode().getId(),
                    jeu.getPlayers().get(0).getPieces().get(i+1).getNode().getId()));
        }

        System.out.println(b.get(0));
        System.out.println(b.get(0));
        System.out.println(v.get(0).isCollinear(v.get(1))+" "+(b.get(0)&&b.get(1)));

        //Main Noé !

        //System.out.println(board);
    }
}
