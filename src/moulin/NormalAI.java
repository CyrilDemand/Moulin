package moulin;

import java.util.ArrayList;

public class NormalAI extends RandomAI{

    /**
     * Creates a Normal AI class
     * @param name name of the AI
     * @param color color of the AI
     */
    public NormalAI(String name, ColorEnum color) {
        super(name, color);
    }

    /**
     * creates a Normal AI class
     * @param name name of the AI
     * @param color color of the AI
     * @param nbTrap amount of traps owned by the AI
     */
    public NormalAI(String name, ColorEnum color, int nbTrap) { super(name, color,nbTrap); }


    /**
     * starts the Game
     * @param jeu Game used to play
 * @param piece piece used by the AI
     */
    public void start(Jeu jeu,Piece piece) {
        int position = (int)(Math.random()*jeu.getBoard().getNodes().size())+1;
        try {
            if (this.willBeWin(jeu.getBoard()))position = this.alignPiece(jeu.getBoard()).getId();
            else {
                System.out.println(position);
                position = this.willBeLoose(jeu.getBoard(),jeu.getPlayers()).getId();
                System.out.println(position);
            }
        } catch (Exception ignored) {
            position = this.alignPiece(jeu.getBoard()).getId();
        }
        while (!this.put(jeu.getBoard().getNodeById(position),piece)){
            position = (int)(Math.random()*jeu.getBoard().getNodes().size())+1;
        }
    }

    /**
     * ends the game
     * @param jeu board used by the game
     */
    public void endGame(Jeu jeu) throws Exception {
        if (willBeLoose(jeu.getBoard(),jeu.getPlayers())!=null){
            if(this.getNbTrap()>0){
                Node loosingNode = this.willBeLoose(jeu.getBoard(), jeu.getPlayers());
                ArrayList<Node> nodes = new ArrayList<>((loosingNode).isLinkedWith(jeu.getBoard()));
                System.out.println(loosingNode.getId());
                System.out.println(nodes);
                for (Node n:nodes) {
                    System.out.println(getLoosingLine(jeu.getBoard(), jeu.getPlayers()));
                    if (!getLoosingLine(jeu.getBoard(), jeu.getPlayers()).getNodes().contains(n)){
                        if(this.getNbTrap()>1){
                            System.out.println(n.getId());
                            for (Edge e:jeu.getBoard().getEdges()) {
                                System.out.println(e);
                                if (!e.isTrapped()){
                                    System.out.println(new Edge(loosingNode,n));
                                    if (e.equals(new Edge(loosingNode,n)) && n.getPiece()!=null){
                                        e.setTrap(getTraps().get(0));
                                        this.placedATrap();
                                        return;
                                    }
                                }
                            }
                        }
                        if (!n.isTrapped()){
                            for (Piece p:this.getPieces()) {
                                if (p.isMovable(jeu.getBoard())){
                                    for (Node n1:p.getNode().isLinkedWith(jeu.getBoard())) {
                                        if (n1.isEmpty()) {
                                            if (this.getNbTrap()>0){
                                                n1.setTrap(new TrapTeleport(3,loosingNode));
                                                this.placedATrap();
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        for (Piece p:this.getPieces()) {
                            for(Node n1:p.getNode().isLinkedWith(jeu.getBoard())){
                                if (n1.isTrapped())p.move(jeu.getBoard(),n1.getId());
                                if (n1.equals(n) && aPieceWhichAlreadyBlockALoose(jeu)==null)p.move(jeu.getBoard(),n.getId());
                                aPieceWhichwillBlockALoose(jeu);
                                return;
                            }
                        }
                    }
                }
            }
            endGame(jeu.getBoard());
        }
    }

    /**
     * returns a piece that is blocking a line
     * @param jeu current game
     * @return the piece
     * @throws Exception Exception
     */
    private Piece aPieceWhichAlreadyBlockALoose(Jeu jeu) throws Exception {
        for (Piece piece: this.getPieces()) {
            if(this.getLoosingLine(jeu.getBoard(),jeu.getPlayers()).getNodes().contains(piece.getNode()))return piece;
            for (Node n:piece.getNode().isLinkedWith(jeu.getBoard())) {
                if(this.getLoosingLine(jeu.getBoard(),jeu.getPlayers()).getNodes().contains(n))return piece;
            }
        }
        return null;
    }

    /**
     * returns a piece that will block a line
     * @param jeu current game
     * @return the piece
     * @throws Exception Exception
     */
    private Piece aPieceWhichwillBlockALoose(Jeu jeu) throws Exception {
        for (Piece piece: this.getPieces()) {
            for (Node n:piece.getNode().isLinkedWith(jeu.getBoard())) {
                if(this.getLoosingLine(jeu.getBoard(),jeu.getPlayers()).getNodes().contains(n)){
                    piece.move(jeu.getBoard(),n.getId());
                    return piece;
                }
            }
        }
        return null;
    }

    /**
     * gets a loosing Line
     * @param board current board
     * @param players current players
     * @return the loosing line
     * @throws Exception Exception
     */
    private Line getLoosingLine(Board board,ArrayList<Player> players) throws Exception {
        int i = 0;
        try {
            for (Player player:players) {
                ArrayList<Node> nodes = new ArrayList<>();
                for (Piece piece:player.getPieces()) {
                    if (piece.getNode()!=null)nodes.add(piece.getNode());
                }
                for (Line line:board.getLines()) {
                    i=0;
                    for (Node n:nodes){
                        if (line.getNodes().contains(n))i++;
                        if (i==2)return line;
                    }
                }
            }

        } catch (Exception ignored) { System.out.println(i); }
        System.out.println(i);
        throw new Exception();
    }

    /**
     * picks a piece randomly in the available pieces
     * @param board board of the current game
     * @return the picked piece
     */
    public Piece pickPiece(Board board) {
        int piece = (int)(Math.random()*this.getPieces().size());
        while (!this.getPieces().get(piece).isMovable(board)){
            piece = (int)(Math.random()*board.getNodes().size());
        }
        return this.getPieces().get(piece);
    }

    /**
     * picks a node randomly
     * @param board board of the current game
     * @param node initial node
     * @return the picked node
     */
    public Node pickNode(Board board,Node node) {
        ArrayList<Node> nodes = node.isLinkedWith(board);
        int n = (int)(Math.random()*nodes.size());
        while (!nodes.get(n).isEmpty()){
            n = (int)(Math.random()*nodes.size());
        }
        return nodes.get(n);
    }

    /**
     * Checks if the next turn is a potential loss
     * @param board current board
     * @param players enemy Player
     * @return the empty node of the line
     * @throws Exception exception
     */
    public Node willBeLoose(Board board,ArrayList<Player> players) throws Exception {
        int i = 0;
        try {
            ArrayList<Node> nodes = new ArrayList<>();
            for (Player player:players) {
                for (Piece piece:player.getPieces()) {
                    if (piece.getNode()!=null)nodes.add(piece.getNode());
                }
                try {
                    for (Line line:board.getLines()) {
                        i=0;
                        for (Node node:nodes) {
                            if (line.getNodes().contains(node))i++;
                            if (i==2)return line.whichIsNotIn(nodes);
                        }
                    }
                }catch (Exception ignored){

                }
            }

        } catch (Exception ignored) { System.out.println(i); }
        System.out.println(i);
        throw new Exception();
    }

    /**
     * Checks if the next turn is winnable
     * @param board current board
     * @return true if winnable, false if not
     */
    public boolean willBeWin(Board board){
        int i = 0;
        System.out.println(board.getLines());
        try {
            ArrayList<Node> nodes = new ArrayList<>();
            for (Piece piece:this.getPieces()) {
                if (piece.getNode()!=null)nodes.add(piece.getNode());
            }
            for (Line line:board.getLines()) {
                i=0;
                for (Node node:nodes) {
                    if (line.getNodes().contains(node))i++;
                    System.out.println( line.whichIsNotIn(nodes));
                    if (i==2)return true;
                }
            }
        } catch (Exception ignored) { System.out.println(i); }
        return false;
    }

    /**
     * Aligns a piece with another one that can be aligned
     * @param board the current board
     * @return the node of the new piece
     */
    public Node alignPiece(Board board){
        ArrayList<Node> nodes = new ArrayList<>();
        for (Piece piece:this.getPieces()) {
            if (piece.getNode()!=null)nodes.add(piece.getNode());
        }
        for (Line line:board.getLines()) {
            for (Piece p:this.getPieces()) {
                if (line.getNodes().contains(p.getNode()))return line.whichIsNotIn(nodes);
            }
        }
        return board.getNodeById((int)(Math.random()*board.getNodes().size())+1);
    }
}
