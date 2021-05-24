package moulin;

import java.util.ArrayList;

public class NormalAI extends AI{

    public NormalAI(String name, Color color) {
        super(name, color);
    }
    public void start(Jeu jeu,Piece piece) {
        int position = (int)(Math.random()*jeu.getBoard().getNodes().size())+1;
        try {
            System.out.println(position);
            position = this.willBeLoose(jeu.getBoard(),jeu.getPlayers().get(0)).getId();
            System.out.println(position);
        } catch (Exception ignored) {
            while (position == -1 &&jeu.getBoard().getNodeById(position).isEmpty()){
                position = (int)(Math.random()*jeu.getBoard().getNodes().size())+1;
            }
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

    public Node willBeLoose(Board board,Player player) throws Exception {
        int i = 0;
        System.out.println("je suis dans la fonction qui clc");
        System.out.println(board.getLines());
        try {
            ArrayList<Node> nodes = new ArrayList<>();
            for (Piece piece:player.getPieces()) {
                if (piece.getNode()!=null)nodes.add(piece.getNode());
            }
            for (Line line:board.getLines()) {
                i=0;
                for (Node node:nodes) {
                    if (line.getNodes().contains(node))i++;
                    System.out.println( line.whichIsNotIn(nodes));
                    if (i==2)return line.whichIsNotIn(nodes);
                }
            }
        } catch (Exception ignored) { System.out.println(i); }
        System.out.println(i);
        throw new Exception();
    }
}
