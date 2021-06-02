package tests;

import moulin.*;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class testJeu {

        @Test
        public static boolean testGettersAndSetters() {
            System.out.println("Getters and Setters");
            Board b = Save.loadBoard("src"+ File.separator+"tests"+ File.separator+"ressources"+ File.separator+"mapTest.json");

            Player p = new Player("Test", Color.ROUGE);
            ArrayList pl = new ArrayList<Player>();
            pl.add(p);

            Jeu j = new Jeu(b,pl);

            if(!(j.getBoard().equals(b)))return false;
            System.out.println("\tequals1 ok");
            if(!(j.getPlayers().equals(pl)))return false;
            System.out.println("\tequals2 ok");

            Board b2 = new Board();
            b2.addNode(new Node(0,0,1));
            b2.addNode(new Node(1,0,2));

            j.setBoard(b2);
            if(!(j.getBoard().equals(b2)))return false;
            System.out.println("\tsetter ok");

            return true;
        }

        @Test
        public static boolean testFinish() {
            System.out.println("Finish");
            Board b = Save.loadBoard("src"+ File.separator+"tests"+ File.separator+"ressources"+ File.separator+"mapTest.json");

            Player p = new Player("Test", Color.ROUGE);
            p.addPiece(new Piece(Color.ROUGE,0));
            p.addPiece(new Piece(Color.ROUGE,1));
            p.addPiece(new Piece(Color.ROUGE,2));
            p.addPiece(new Piece(Color.ROUGE,3));
            System.out.println("\taddPiecesP ok");

            Player p2 = new Player("Test", Color.BLEU);
            p2.addPiece(new Piece(Color.BLEU,0));
            p2.addPiece(new Piece(Color.BLEU,1));
            p2.addPiece(new Piece(Color.BLEU,2));
            p2.addPiece(new Piece(Color.BLEU,3));
            System.out.println("\tequalsP2 ok");

            ArrayList pl = new ArrayList<Player>();
            pl.add(p);
            pl.add(p2);

            Jeu j = new Jeu(b,pl);
            System.out.println("\tequalsPl ok");
            System.out.println(j.getPlayers().toString());
            j.getPlayers().get(0).put(b.getNodeById(4),j.getPlayers().get(0).getPieces().get(0));
            j.getPlayers().get(0).put(b.getNodeById(1),j.getPlayers().get(0).getPieces().get(1));
            j.getPlayers().get(0).put(b.getNodeById(2),j.getPlayers().get(0).getPieces().get(2));
            j.getBoard().render(3,3);
            if(j.isFinished()!=null)return false;
            System.out.println("\tnoFinish ok");
            j.getPlayers().get(0).put(b.getNodeById(3),j.getPlayers().get(0).getPieces().get(3));
            j.getBoard().render(3,3);
            if(j.isFinished()==null)return false;
            System.out.println("\tfinish ok");

            return true;
        }

    }
