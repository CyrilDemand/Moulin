package moulin;
import java.io.File;
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
        if (Jeu.turn==this.getPlayers().size()-1){
            Jeu.turn=0;
        }else{
            Jeu.turn++;
        }

    }

    public static int getTurn() {
        return turn;
    }

    /**
     * changes the board of the game
     * @param board the new board
     */
    public void setBoard(Board board){
        this.board = board;
    }


    public static ArrayList<Player> initPlayer(int nbPlayer){
        ArrayList<Player> players = new ArrayList<>();
        boolean chooseAtLeastOnePlayer=false;

        Scanner scanner = new Scanner(System.in);
        int choix;

        for (int i = 0; i<nbPlayer;i++){
            if (i==nbPlayer-1 && !chooseAtLeastOnePlayer){
                System.out.println("You need at least one human player in the game ! ");
                choix=1;
            }else{
                System.out.println("Choose a player type : ");
                System.out.println("[1] Player");
                System.out.println("[2] Random AI");
                System.out.println("[3] AI");

                System.out.print("Player n°"+(i+1)+": ");
                choix=Config.nextInt(1,3);
            }


            if (choix==1){
                players.add(new Player(Player.chooseName(),Player.chooseColor()));
                chooseAtLeastOnePlayer=true;
            }else if (choix==2){
                players.add(new RandomAI(Config.aiRandomNames.get((int)(Math.random()*Config.aiRandomNames.size())),Color.values()[(int) (Math.random() * Color.values().length)]));
            }else if (choix==3){
                players.add(new NormalAI(Config.aiRandomNames.get((int)(Math.random()*Config.aiRandomNames.size())),Color.values()[(int) (Math.random() * Color.values().length)]));
            }
        }

        return players;
    }

    /**
     * starts the game : each player places their Pieces on the board
     * @return the player who won, null if no one won
     */
    public Player start(){
        for(int i = 0; i<this.getPlayers().get(0).getPieces().size();i++){
            for (Player p: this.getPlayers()) {
                System.out.println(p.getClass().getSimpleName());
                if (p.getClass().getSimpleName().equals("Player")){
                    board.render();
                    System.out.println("Enter the ID of the node where you want to place your piece n° "+i);
                    boolean posed=false;
                    do{
                        posed=p.getPieces().get(i).put(this.getBoard().getNodeById(Config.nextInt(board.getMinId(),board.getMaxId())));
                        if (!posed)System.out.println("Error : Please try again");
                    }while (!posed);
                }else {
                    ((RandomAI)p).start(this,p.getPieces().get(i));
                }
                try {
                    Player winner = this.isFinished();
                    if(winner!=null){
                        return winner;
                    }
                }catch (Exception ignored){

                }

            }
        }
        return null;
    }

    /**
     * starts the game but everyone places their pieces randomly
     * @return the player who won, null if no one won
     */

    public static Jeu randomStart(Board board,ArrayList<Player> players){
        Jeu jeu;
        do {
            jeu = new Jeu(board,players);
            jeu.getBoard().render();
            for(int i = 0; i<jeu.getPlayers().get(0).getPieces().size();i++){
                for (Player p: jeu.getPlayers()) {
                    jeu.getBoard().render();
                    int position = (int)(Math.random()*jeu.getBoard().getNodes().size())+1;
                    while (!p.getPieces().get(i).put(jeu.getBoard().getNodeById(position))){
                        position = (int)(Math.random()*jeu.getBoard().getNodes().size())+1;
                    }
                }
            }
        }while (jeu.isFinished()!=null);

        return jeu;
    }

    /**
     * starts the second part of the game : players move their pieces and try to make a line
     * @return the player who won, null if the player decided to save and quit the game
     */

    public Player endGame(){
        while (this.isFinished()==null){
            Scanner scanner = new Scanner(System.in);
            for (Player player:this.getPlayers()) {
                if (this.getPlayers().get(Jeu.turn).equals(player)){
                    board.render();
                    if (player.getClass().getSimpleName().equals("Player")){
                        if(!menuPlayer(player))return null;
                    }else{
                        try {
                            ((RandomAI)player).endGame(board);
                        }catch (Exception ignored){}
                        try {
                            ((NormalAI)player).endGame(this);
                        }catch (Exception ignored){}
                    }
                    this.addTurn();
                    if (isFinished()!=null)break;
                }
            }
            board.nextTurn();
        }
        return this.isFinished();
    }

    private boolean menuPlayer(Player player){
        int choix;
        System.out.println("Player's turn "+player.getColor().getString()+player.getName()+Color.ANSI_RESET);
        if (player.getNbTrap()>0){
            choix = menuWithTrap(player);
        }else{
            choix = menuWithoutTrap();
        }
        if (choix==1) {
            menuMovePiece(player);
        }else if (choix==2 && player.getNbTrap()>0){
            menuPlaceTrapEdge(player);
        }else if (choix==3){
            menuPlaceTrap(player);
        }else if ((choix==2 && player.getNbTrap()<=0) || choix==4){
            menuSave(player);
            return false;
        }
        return true;
    }

    private int menuWithTrap(Player player){
        int choix;
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to do ?");
        System.out.println("[1] Move a piece");
        System.out.println("[2] Block a line");
        System.out.println("[3] Place a teleporter");
        System.out.println("[4] Save and Quit");
        System.out.println("Remaining traps : "+player.getNbTrap());

        System.out.print("Your choice : ");
        choix=Config.nextInt(1,4);

        return choix;
    }

    private int menuWithoutTrap(){
        int choix;
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to do ?");
        System.out.println("[1] Move a piece");
        System.out.println("[2] Save and quit");

        System.out.print("Your choice : ");
        choix=Config.nextInt(1,2);
        return choix;
    }

    private void menuMovePiece(Player player){
        Scanner scanner = new Scanner(System.in);
        int piece;
        int endroit;
        System.out.println("Enter the ID of the piece you want to move, and ID of the node where you want to move it");
        System.out.println("Whether you are asked for the id of your piece or the destination, enter -1 if you want to go back");
        do {
            System.out.print("Piece : ");
            piece = Config.nextInt(-1,player.getPieces().size()-1);
            if (piece==-1){
                this.menuPlayer(player);
                break;
            }
            System.out.print("Node : ");
            endroit = Config.nextInt(-1,board.getMaxId());
            if (endroit==-1)this.menuPlayer(player);
            boolean isMovable = false;
            for (Node node:player.getPieces().get(piece).getNode().isLinkedWith(board)) {
                if (node.getId() == endroit)isMovable = true;
            }
            if (!isMovable)System.out.println("Error : Please try again");
        }while (!(piece<player.getPieces().size() && piece>=0 && player.getPieces().get(piece).move(board,endroit)));
    }

    private void menuPlaceTrapEdge(Player player){
        int idStart;
        int idEnd;
        System.out.println("Enter the ID of the beginning and ending node of the line you want to block");
        System.out.println("Whether you are asked for the id of the start or the end, enter 0 if you want to go back");
        do {
            System.out.print("Start : ");
            idStart = Config.nextInt(0,board.getMaxId());
            if (idStart==0){
                this.menuPlayer(player);
                break;
            }
            System.out.print("End : ");
            idEnd=Config.nextInt(0,board.getMaxId());
            if (idEnd==0){
                this.menuPlayer(player);
                break;
            }
            boolean canBeTrapped = false;
            System.out.println(idStart+idEnd);
            for (Edge e: board.getEdges()) {
                System.out.println(e.getStart().getId()+e.getEnd().getId());
                if (e.equals(new Edge(board.getNodeById(idStart), board.getNodeById(idEnd))))canBeTrapped = true;
            }
            if (!canBeTrapped)System.out.println("Error : Please try again");
        }while (!board.trapEdge(idStart,idEnd,3));
        player.placedATrap();
    }

    private void menuPlaceTrap(Player player){
        Scanner scanner = new Scanner(System.in);
        int idNode;
        int idDestination;
        System.out.println("Enter the ID of the node where you want to place the teleporter, and the node where it will teleport");
        System.out.println("Whether you are asked for the id of the start or the end, enter 0 if you want to go back");
        do {
            System.out.print("Node : ");
            idNode = Config.nextInt(0,board.getMaxId());
            if (idNode==0){
                this.menuPlayer(player);
                break;
            }
            System.out.print("Destination : ");
            idDestination=Config.nextInt(0,board.getMaxId());
            if (idDestination==0){
                this.menuPlayer(player);
                break;
            }
            int canBeTrapped = 0;
            for (Node node: board.getNodes()) {
                if (node.getId()==idNode || node.getId()==idDestination)canBeTrapped++;
            }
            if (canBeTrapped!=2)System.out.println("Error : Please try again");
        }while (!board.trapNode(idNode,idDestination,3));
        player.placedATrap();
    }

    private void menuSave(Player player){
        Scanner scanner = new Scanner(System.in);
        System.out.println("How do you want to name your save ?");
        System.out.println("Enter IWANTTOGOBACK if you want to go back to the game !");
        String nom="";
        do{
            nom=scanner.nextLine();
            if (nom.equals("IWANTTOGOBACK")){
                this.menuPlayer(player);
                break;
            }
        }while (nom.equals("") || nom.length()<=0);

        new Save(nom).generateSave(this);
        System.out.println("The current game has been successfully saved");
    }
    /**
     * gets all the json files in a directory
     * @param directory the directory your want to get your files from (either saves or maps)
     * @return the list of files as an ArrayList of strings
     */

    public static ArrayList<String> getFiles(String directory){
        String path=System.getProperty("user.dir")+File.separator+directory;
        File f=new File(path);
        String[] files=f.list();
        if (files==null)return null;

        ArrayList<String> names=new ArrayList<String>();

        for (String s : files){
            if (s.substring(Math.max(0,s.length()-4),s.length()).equals("json")){
                names.add(s.substring(0,Math.max(0,s.length()-5)));
            }
        }
        return names;
    }

    /**
     * checks if the game is finished or not
     * @return true if someone has made a line on the board, false otherwise
     */

    public Player isFinished(){
        for (Player p: this.getPlayers()) {
            for (Line l:this.getBoard().getLines()) {
                int nbPiece = 0;
                for (Piece p1: p.getPieces()) {
                    if (l.getNodes().contains(p1.getNode()))nbPiece++;
                }
                if (nbPiece==l.getNodes().size())return p;
            }
        }
        return null;
    }
}
