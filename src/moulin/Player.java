package moulin;

import java.util.ArrayList;

public class Player {
    private String name;

    private Color color;

    private ArrayList<Piece> pieces;

    public Player(String name, Color color){
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

    public String toString(){
        return this.getName()+" possède "+this.pieces.size()+" pions de couleurs "+this.getColor();
    }
}
