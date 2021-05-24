package moulin;

public abstract class AI extends Player{

    public AI(String name, Color color) {
        super(name, color);
    }

    public abstract void start(Jeu jeu,Piece piece);
    public abstract void endGame(Board board);
    public abstract Piece pickPiece(Board board);
    public abstract Node pickNode(Board board,Node node);
}
