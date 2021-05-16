package moulin;
import java.util.ArrayList;
import java.util.Scanner;

public class Jeu {
    private static int turn = 0;
    private Board board;
    private final ArrayList<Player> players;

    public Jeu(Board board,ArrayList<Player> players){
        this.board=board;
        this.players=players;
    }

    public Jeu(Jeu jeu){
        this(new Board(jeu.getBoard()),jeu.copyPlayers(jeu.getPlayers()));
    }

    public ArrayList<Player> copyPlayers(ArrayList<Player> p1){
        ArrayList<Player> p = new ArrayList<>();
        for (Player p2: p1) {
            p.add(new Player(p2));
        }
        return p;
    }

    public Board getBoard(){
        return this.board;
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public void addTurn(){
        Jeu.turn++;
    }
    public void setBoard(Board board){
        this.board = board;
    }

    public void start(){
        for(int i = 0; i<this.getPlayers().get(0).getPieces().size();i++){
            for (Player p: this.getPlayers()) {
                System.out.println(p.getClass().getSimpleName());
                if (p.getClass().getSimpleName().equals("Player")){
                    Scanner scanner = new Scanner(System.in);
                    board.render(3,3);
                    System.out.println("Mettez l'id de là où vous voulez mettre votre pion "+i);
                    p.getPieces().get(i).put(this.getBoard().getNodeById(scanner.nextInt()));
                }else {
                    ((RandomAI)p).start(board,p.getPieces().get(i));
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

    public void endGame(){
        Scanner scanner = new Scanner(System.in);
        for (Player player:this.getPlayers()) {
            if (player.getClass().getSimpleName().equals("Player")){
                int piece;
                int endroit;
                do {
                    System.out.println("Tour du joueur "+player.getColor()+" \nquelle piece voulez vous bouger");
                    board.render(3,3);
                    piece = scanner.nextInt();
                    System.out.println("et ou ?");
                    endroit =scanner.nextInt();
                }while (!player.getPieces().get(piece).move(board,endroit));
            }else{
                ((RandomAI)player).endGame(board);
            }
            if (isFinished())break;
        }
    }

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

    public void displayPlayers(){
        for (Player player:this.players){
            System.out.println(player);
        }
    }//debugage
}
