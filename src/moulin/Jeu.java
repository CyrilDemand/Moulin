package moulin;

public class Jeu {

    public static void main(String[] args) {
        //test discord
        Board board = new Board();
        Player player1 = new Player("Pierre",Color.BLANC);
        Player player2 = new Player("Morgane",Color.BLEU);
        for (int i = 0; i<3;i++){
            player1.addPiece(new Piece(player1.getColor()));
            player2.addPiece(new Piece(player2.getColor()));
        }
        System.out.println(player1+"\n"+player2);
    }
}
