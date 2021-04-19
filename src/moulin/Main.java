package moulin;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //test discord
        Scanner scanner = new Scanner( System.in );
        Board board = new Board();
        Player player1 = new Player(scanner);
        Player player2 = new Player(scanner);
        for (int i = 0; i<3;i++){
            player1.addPiece(new Piece(player1.getColor()));
            player2.addPiece(new Piece(player2.getColor()));
        }
        System.out.println(player1+"\n"+player2);
    }
}
