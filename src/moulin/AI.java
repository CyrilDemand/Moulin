package moulin;

public abstract class AI extends Player{
    /**
     * creates an AI class
     * @param name Name of the AI
     * @param color color of the AI
     */
    public AI(String name, ColorEnum color) {
        super(name, color);
    }
    public AI(String name, ColorEnum color, int nbTrap) { super(name, color,nbTrap); }

    /**
     * starts the Game
     * @param jeu Game used to play
     * @param piece piece used by the AI
     */
    public abstract void start(Jeu jeu,Piece piece);
    /**
     * ends the game
     * @param board board used by the game
     */
    public abstract void endGame(Board board);
    /**
     * picks a piece in the available pieces
     * @param board board of the current game
     * @return the picked piece
     */
    public abstract Piece pickPiece(Board board);
    /**
     * picks a node in the available nodes
     * @param board board of the current game
     * @param node initial node
     * @return the picked node
     */
    public abstract Node pickNode(Board board,Node node);
}
