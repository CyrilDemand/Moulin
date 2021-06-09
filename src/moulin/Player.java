package moulin;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Creates a player
 */
public class Player {
    private final String name;

    private final ColorEnum color;

    private final ArrayList<Piece> pieces;

    private int nbTrap;

    ArrayList<Trap> traps;
    /**
     * Creates a Player class
     * @param name String for the player's name
     * @param color the color that the player will be associated with
     */
    public Player(String name, ColorEnum color){
        this(name,color,Config.numberOfTraps);
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
     * creates a player class
     * @param name player name
     * @param color player color
     * @param nbTrap amount of traps the player own
     */
    public Player(String name, ColorEnum color, int nbTrap){
        if(name.equals("")){
            throw new Error("Pas de nom de joueurs");
        }
        this.name = name;
        this.color = color;
        this.pieces = new ArrayList<>();
        traps = new ArrayList<>();
        for(int i =0;i<nbTrap;i++){
            traps.add(new Trap(3));
        }
        this.nbTrap=nbTrap;
    }
    /**
     *  returns the amount of traps that the Player can place
     * @return the amount of usable traps
     */
    public int getNbTrap() {
        return nbTrap;
    }

    /**
     * get the traps
     * @return the trap list
     */
    public ArrayList<Trap> getTraps() {
        return traps;
    }

    /**
     * decrements the Trap counter
     */
    public void placedATrap(){
        getTraps().remove(0);
        this.nbTrap=Math.max(0,this.getNbTrap()-1);
    }

    /**
     * Returns the Player's name
     * @return the String for the player's name
     */
    public String getName(){
        return this.name;
    }

    /**
     * returns the player's color
     * @return the player's color
     */
    public ColorEnum getColor(){
        return this.color;
    }

    /**
     * Returns the Pieces of the Player
     * @return the list of pieces owned by the player
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
     * @return the chosen name
     */

    public static String chooseName(){
        Scanner scan = new Scanner(System.in);
        System.out.println("What is your name ?");
        String user = "";
        user = scan.nextLine();
        while (user.equals("") || user.length()<3){
            System.out.println("Please choose a valid name (more than 2 letters)");
            user = scan.nextLine();
        }
        return user;
    }

    /**
     * Allows the user to choose his color
     * @param list color list
     * @return the color
     */
    public static ColorEnum chooseColor(ArrayList<ColorEnum> list){
        Scanner scan = new Scanner(System.in);
        System.out.println("Here are the colors you can choose :");
        ColorEnum.diplayColor(list);
        String user = "";
        user = scan.nextLine();
        while (!ColorEnum.isColor(user.toLowerCase(),list)){
            System.out.println("Choose a real color");
            user = scan.nextLine();
        }
        list.remove(ColorEnum.valueOf(user.toUpperCase()));
        return ColorEnum.valueOf(user.toUpperCase());
    }

    /**
     *Returns a String summary of the Player
     * @return the String generated
     */

    public String toString(){
        return this.getName();
        //return this.getName()+" possède "+this.pieces.size()+" pions de couleurs "+this.getColor();
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