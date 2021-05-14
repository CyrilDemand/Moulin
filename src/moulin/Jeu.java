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

    public void addTurn(){
        Jeu.turn++;
    }

    public Board getBoard(){
        return this.board;
    }

    public void setBoard(Board board){
        this.board = board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void displayPlayers(){
        for (Player player:this.players){
            System.out.println(player);
        }
    }

    public boolean isFinished(){
        for (int idx=0;idx<2;idx++ ){
            ArrayList<Vecteur> v = new ArrayList<Vecteur>();
            ArrayList<Boolean> b = new ArrayList<Boolean>();
            for (int i = 0; i<this.getPlayers().get(idx).getPieces().size()-1;i++){
                v.add(new Vecteur(this.getPlayers().get(idx).getPieces().get(i).getNode().getX(),
                        this.getPlayers().get(idx).getPieces().get(i+1).getNode().getX(),
                        this.getPlayers().get(idx).getPieces().get(i).getNode().getY(),
                        this.getPlayers().get(idx).getPieces().get(i+1).getNode().getY())
                );
            }
            for (int i = 0; i<this.getPlayers().get(idx).getPieces().size()-1;i++){
                b.add(this.getBoard().isLinked(this.getPlayers().get(idx).getPieces().get(i).getNode().getId(),
                        this.getPlayers().get(idx).getPieces().get(i+1).getNode().getId()));
            }
            if (v.get(0).isCollinear(v.get(1)) && (b.get(0)&&b.get(1)))return true;
        }
        return false;
    }

    public void start(){
        for(int i = 0; i<this.getPlayers().get(0).getPieces().size();i++){
            for (Player p: this.getPlayers()) {
                Scanner scanner = new Scanner(System.in);
                board.render(3,3);
                System.out.println("Mettez l'id de là où vous voulez mettre votre pion "+i);
                p.getPieces().get(i).put(this.getBoard().getNodeById(scanner.nextInt()));
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
                        System.out.println("jeu fini");
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
            int piece;
            int endroit;
            do {
                System.out.println("Tour du joueur "+player.getColor()+" \nquelle piece voulez vous bouger");
                board.render(3,3);
                piece = scanner.nextInt();
                System.out.println("et ou ?");
                endroit =scanner.nextInt();
            }while (!player.getPieces().get(piece).move(board,endroit));
            if (isFinished())break;
        }
    }
}
