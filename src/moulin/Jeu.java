package moulin;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class containing the main game loop
 */

public class Jeu {
    private static int turn = 0;
    private Board board;
    private final ArrayList<Player> players;


    /**
     * creates a new game
     * @param board the board used in the game
     * @param players the list of players
     */
    public Jeu(Board board,ArrayList<Player> players){
        this.board=board;
        this.players=players;
    }

    /**
     * creates a copy of a game
     * @param jeu the game to copy
     */
    public Jeu(Jeu jeu){
        this(new Board(jeu.getBoard()),jeu.copyPlayers(jeu.getPlayers()));
    }

    /**
     * creates a copy of all the players
     * @param p1 the list to copy
     * @return a copy of the list
     */
    public ArrayList<Player> copyPlayers(ArrayList<Player> p1){
        ArrayList<Player> p = new ArrayList<>();
        for (Player p2: p1) {
            p.add(new Player(p2));
        }
        return p;
    }

    /**
     * gets the board
     * @return the board
     */
    public Board getBoard(){
        return this.board;
    }

    /**
     * gets the list of players
     * @return the list of players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * adds one the the turn counter
     */
    public void addTurn(){
        Jeu.turn++;
    }

    /**
     * changes the board of the game
     * @param board the new board
     */
    public void setBoard(Board board){
        this.board = board;
    }

    /**
     * starts the game : each player places their Pieces on the board
     */

    public void start(){
        for(int i = 0; i<this.getPlayers().get(0).getPieces().size();i++){
            for (Player p: this.getPlayers()) {
                System.out.println(p.getClass().getSimpleName());
                if (p.getClass().getSimpleName().equals("Player")){
                    Scanner scanner = new Scanner(System.in);
                    board.render(3,1);
                    System.out.println("Mettez l'id de là où vous voulez mettre votre pion "+i);
                    p.getPieces().get(i).put(this.getBoard().getNodeById(scanner.nextInt()));
                }else {
                    ((NormalAI)p).start(this,p.getPieces().get(i));
                }
                try {
                    if(this.isFinished()){
                        System.out.println("jeu fini");
                        break;
                    }
                }catch (Exception ignored){

                }

            }
        }
    }

    /**
     * starts the game but everyone places their pieces randomly
     */

    public void randomStart(){
        for(int i = 0; i<this.getPlayers().get(0).getPieces().size();i++){
            for (Player p: this.getPlayers()) {
                int position = (int)(Math.random()*this.getBoard().getNodes().size())+1;
                while (!this.getBoard().getNodeById(position).isEmpty()){
                    position = (int)(Math.random()*this.getBoard().getNodes().size())+1;
                }
                p.getPieces().get(i).put(this.getBoard().getNodeById(position));
                try {
                    if(this.isFinished()){
                        break;
                    }
                }catch (Exception ignored){

                }
            }
        }
    }

    /**
     * starts the second part of the game : players move their pieces and try to make a line
     */

    public void endGame(){
        Scanner scanner = new Scanner(System.in);
        for (Player player:this.getPlayers()) {
            board.render(3,1);
            if (player.getClass().getSimpleName().equals("Player")){
                int choix;

                System.out.println("Tour du joueur "+player.getColor());

                if (player.getNbTrap()>0){
                    System.out.println("Que voulez vous faire ?");
                    System.out.println("[1] Deplacer un pion");
                    System.out.println("[2] Bloquer une ligne");
                    System.out.println("[3] Placer un teleporteur");
                    System.out.println("Pièges Restants : "+player.getNbTrap());

                    do{
                        choix=scanner.nextInt();
                    }while (!(choix>=1 && choix<=3));
                }else{
                    choix=1;
                }

                if (choix==1) {

                    int piece;
                    int endroit;
                    do {
                        System.out.println("Entrez le nuremo de la piece que vous voulez bouger et le nuremo de la case où vous voulez la bouger");
                        System.out.print("Piece : ");
                        piece = scanner.nextInt();
                        System.out.print("Case : ");
                        endroit =scanner.nextInt();
                    }while (!player.getPieces().get(piece).move(board,endroit));

                }else if (choix==2){
                    int idStart;
                    int idEnd;
                    System.out.println("Entrez le nuremo de la case de debut et de fin de la ligne que vous voulez bloquer");
                    do {
                        System.out.print("Debut : ");
                        idStart = scanner.nextInt();
                        System.out.print("Fin : ");
                        idEnd=scanner.nextInt();
                    }while (!board.trapEdge(idStart,idEnd,3));
                    player.placedATrap();
                }else if (choix==3){
                    int idNode;
                    int idDestination;
                    System.out.println("Entrez le nuremo de la case sur laquelle vous voulez placer le teleporteur et la case où il va vous teleporter");
                    do {
                        System.out.print("Case : ");
                        idNode = scanner.nextInt();
                        System.out.print("Destination : ");
                        idDestination=scanner.nextInt();
                    }while (!board.trapNode(idNode,idDestination,3));
                    player.placedATrap();
                }
            }else{
                try {
                    ((RandomAI)player).endGame(board);
                }catch (Exception ignored){}
                try {
                    ((NormalAI)player).endGame(board);
                }catch (Exception ignored){}
            }

            if (isFinished())break;
        }
        board.nextTurn();
    }

    /**
     * checks if the game is finished or not
     * @return true if someone has made a line on the board, false otherwise
     */

    public boolean isFinished(){
        for (Player p: this.getPlayers()) {
            for (Line l:this.getBoard().getLines()) {
                int nbPiece = 0;
                for (Piece p1: p.getPieces()) {
                    if (l.getNodes().contains(p1.getNode()))nbPiece++;
                }
                if (nbPiece==l.getNodes().size())return true;
            }
        }
        return false;
    }
}
