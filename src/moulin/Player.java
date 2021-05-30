package moulin;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private final String name;

    private final Color color;

    private final ArrayList<Piece> pieces;

    private int nbTrap;

    /**
     * Creates a Player class
     * @param name String for the player's name
     * @param color the color that the player will be associated with
     */
    public Player(String name, Color color){
        if(name.equals("")){
            throw new Error("Pas de nom de joueurs");
        }
        this.name = name;
        this.color = color;
        this.pieces = new ArrayList<Piece>();
        this.nbTrap=3;
    }

    /**
     * Creates a Player Class
     * @param player Player to replicate
     */
    public Player(Player player) {
        this(player.getName(), player.getColor());
        for (Piece p : player.getPieces()) {
            this.pieces.add(new Piece(p));
        }
    }

    /**
     * returns the amount of traps that the Player can place
     * @return
     */
    public int getNbTrap() {
        return nbTrap;
    }

    /**
     * decrements the Trap counter
     */
    public void placedATrap(){
        this.nbTrap=Math.max(0,this.getNbTrap()-1);
    }

    /**
     * Returns the Player's name
     * @return
     */
    public String getName(){
        return this.name;
    }

    /**
     * returns the player's color
     * @return
     */
    public Color getColor(){
        return this.color;
    }

    /**
     * Returns the Pieces of the Player
     * @return
     */
    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    /**
     * Adds a piece to the Player
     * @param piece Piece to add
     */
    public void addPiece(Piece piece){
        this.pieces.add(piece);
    }

    /**
     * Places a Piece on a Node
     * @param node Node use for the piece
     * @param piece Piece to place
     * @return true if placed
     */
    public boolean put(Node node,Piece piece){
        return piece.put(node);
    }

    /**
     * Allows the user to type his name
     * @return
     */

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

    /**
     *  Allows the user to choose his color
     * @return
     */
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

    /**
     *Returns a String summary of the Player
     * @return
     */

    public String toString(){
        return this.getName()+" possède "+this.pieces.size()+" pions de couleurs "+this.getColor();
    }

    /**
     * Display the Player's pieces
     */
    public void displayPieces() {
        for (Piece piece : this.getPieces()) {
            System.out.println(piece.toString());
        }
    }//(debugage)
}