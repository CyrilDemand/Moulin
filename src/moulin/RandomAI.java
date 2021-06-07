package moulin;

import java.util.ArrayList;

public class RandomAI extends AI{
    /**
     * Creates a RandomAI class
     * @param name Name of the AI
     * @param color color of the AI
     */
    public RandomAI(String name, Color color) {
        super(name, color);
    }
    public RandomAI(String name, Color color,int nbTrap) {
        super(name, color,nbTrap);
    }

    /**
     * starts the game
     * @param jeu current game
     * @param piece piece used by the AI
     */
    public void start(Jeu jeu,Piece piece) {
        int position = (int)(Math.random()*jeu.getBoard().getNodes().size())+1;
        while (!jeu.getBoard().getNodeById(position).isEmpty()){
            position = (int)(Math.random()*jeu.getBoard().getNodes().size())+1;
        }
        this.put(jeu.getBoard().getNodeById(position),piece);
    }

    /**
     * ends the game
     * @param board current board
     */
    public void endGame(Board board) {
        Piece piece = pickPiece(board);
        piece.move(board,pickNode(board,piece.getNode()).getId());
    }

    /**
     *  picks a piece randomly in the available pieces
     *  @param board board of the current game
     *  @return the picked piece
     */
    public Piece pickPiece(Board board) {
        int piece = (int)(Math.random()*this.getPieces().size());
        while (!this.getPieces().get(piece).isMovable(board)){
            piece = (int)(Math.random()*board.getNodes().size());
        }
        return this.getPieces().get(piece);
    }
    /**
     * picks a node randomly
     * @param board board of the current game
     * @param node initial node
     * @return the picked node
     */
    public Node pickNode(Board board,Node node) {
        ArrayList<Node> nodes = node.isLinkedWith(board);
        int n = (int)(Math.random()*nodes.size());
        while (!nodes.get(n).isEmpty()){
            n = (int)(Math.random()*nodes.size());
        }
        return nodes.get(n);
    }
}
