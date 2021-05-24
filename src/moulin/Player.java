package moulin;

import java.util.ArrayList;
import java.util.Scanner;

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

    public Player(Player player){
        this(player.getName(),player.getColor());
        for (Piece p:player.getPieces()) {
            this.pieces.add(new Piece(p));
        }
    }

    public String getName(){
        return this.name;
    }

    public Color getColor(){
        return this.color;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void addPiece(Piece piece){
        this.pieces.add(piece);
    }

    public boolean put(Node node,Piece piece){
        return piece.put(node);
    }

    public boolean arePiecesLinked(Board board,int x){
        int a = 0;
        for (int i =0;i<this.getPieces().size();i++) {
            for (int idx = 0; idx < this.getPieces().size(); idx++) {
                if (board.isLinked(this.getPieces().get(i).getNode().getId(), this.getPieces().get(idx).getNode().getId()))
                    a++;
            }
        }
        return (int)(a/2)==x;
    }

    public static String chooseName(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Quel est votre nom ?");
        String user = "";
        user = scan.nextLine();
        while (user.equals("") || user.length()<3){
            System.out.println("Choisissez un vrai nom");
            user = scan.nextLine();
        }
        return user;
    }

    public static Color chooseColor(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Voici les couleurs que l'on a :");
        Color.diplayColor();
        String user = "";
        user = scan.nextLine();
        while (!Color.isColor(user.toLowerCase())){
            System.out.println("Choisissez une vraie couleur");
            user = scan.nextLine();
        }
        return Color.valueOf(user.toUpperCase());
    }

    public String toString(){
        return this.getName()+" possède "+this.pieces.size()+" pions de couleurs "+this.getColor();
    }

    public void displayPieces() {
        for (Piece piece : this.getPieces()) {
            System.out.println(piece.toString());
        }
    }//(debugage)
}