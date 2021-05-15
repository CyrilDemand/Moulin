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
        for (int idx=0;idx<2;idx++ ){
            ArrayList<Vecteur> v = new ArrayList<Vecteur>();
            for (int i = 0; i<this.getPlayers().get(idx).getPieces().size()-1;i++){
                v.add(new Vecteur(this.getPlayers().get(idx).getPieces().get(i).getNode().getX(),
                        this.getPlayers().get(idx).getPieces().get(i+1).getNode().getX(),
                        this.getPlayers().get(idx).getPieces().get(i).getNode().getY(),
                        this.getPlayers().get(idx).getPieces().get(i+1).getNode().getY())
                );
            }
            if (v.get(0).isCollinear(v.get(1)) && this.getPlayers().get(idx).arePiecesLinked(board,2))return true;
        }
        return false;
    }

    public void displayPlayers(){
        for (Player player:this.players){
            System.out.println(player);
        }
    }//debugage
}
