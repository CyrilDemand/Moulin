package moulin;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private String name;

    private Color color;

    private ArrayList<Piece> pieces;

    public Player(Scanner scanner){
        System.out.println("C'est quoi ton p'tit nom ? UwU");
        this.name = scanner.nextLine();
        Color.List();
        System.out.println("Tu veux quelle couleur ? OwO");
        this.color = Color.valueOf(scanner.nextLine());
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
