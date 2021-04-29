package moulin;

import java.util.ArrayList;

public class Player {
    private final String name;

    private final Color color;

    private final ArrayList<Piece> pieces;

    public Player(String name, Color color){
        if(name.equals("")){
            throw new Error("Pas de nom de joueurs");
        }
        this.name = name;
        this.color = color;
        this.pieces = new ArrayList<Piece>();
    }

    public String getName(){
        return this.name;
    }

    public Color getColor(){
        return this.color;
    }

    public void addPiece(Piece piece){
        this.pieces.add(piece);
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public String toString(){
        return this.getName()+" possède "+this.pieces.size()+" pions de couleurs "+this.getColor();
    }
}
