package moulin;

import java.util.ArrayList;

public class RandomAI extends AI{

    public RandomAI(String name, Color color) {
        super(name, color);
    }

    public void start(Jeu jeu,Piece piece) {
        int position = (int)(Math.random()*jeu.getBoard().getNodes().size())+1;
        while (!jeu.getBoard().getNodeById(position).isEmpty()){
            position = (int)(Math.random()*jeu.getBoard().getNodes().size())+1;
        }
        this.put(jeu.getBoard().getNodeById(position),piece);
    }

    public void endGame(Board board) {
        Piece piece = pickPiece(board);
        piece.move(board,pickNode(board,piece.getNode()).getId());
    }

    public Piece pickPiece(Board board) {
        int piece = (int)(Math.random()*this.getPieces().size());
        while (!this.getPieces().get(piece).isMovable(board)){
            piece = (int)(Math.random()*board.getNodes().size());
        }
        return this.getPieces().get(piece);
    }

    public Node pickNode(Board board,Node node) {
        ArrayList<Node> nodes = node.isLinkedWith(board);
        int n = (int)(Math.random()*nodes.size());
        while (!nodes.get(n).isEmpty()){
            n = (int)(Math.random()*nodes.size());
        }
        return nodes.get(n);
    }
}
