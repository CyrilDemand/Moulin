package moulin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class used for saving the current game to a file
 */

public class Save {

    /**
     * creates a new save object
     * @param path the path of the file where you want your game to be saved
     */

    String path;
    String name;
    public Save(String path){
        this.path = path;
        ArrayList<String> s = new ArrayList<>(Arrays.asList(this.path.split("/")));
        name = s.get(s.size()-1);
    }

    public Save(File file){
        this.path = file.getAbsolutePath();
        ArrayList<String> s = new ArrayList<>(Arrays.asList(this.path.split(File.pathSeparator)));
        name = s.get(s.size()-1);
    }

    public String getName() {
        return this.name;
    }

    /**
     * generates a file containing the data of a game
     * @param jeu the game you want to save
     * @return true if it managed to create a file, false otherwise
     */

    public boolean generateSave(Jeu jeu){
        ArrayList<Character> save = new ArrayList<>();
        save.add('{');
        save.addAll(this.StringToList(" \n\t \"type\":[\"game\"],\n"));
        save.addAll(this.saveTurn(jeu));
        save.addAll(this.SaveBoard(jeu.getBoard()));
        save.addAll(this.SavePlayers(jeu.getPlayers()));
        save.add('\n');;
        save.add('}');
        char[] b = new char[save.size()];
        File fichier =  new File(this.path.replace('/',File.separatorChar));
        for (int i = 0; i<save.size();i++) {
            b[i]=save.get(i);
        }
        try {
            // ouverture d'un flux de sortie sur un fichier
            // a pour effet de créer le fichier
            Writer writer =  new FileWriter(fichier) ;

            // écriture dans le fichier
            writer.write(b) ;

            // la méthode close de FileWriter appelle elle-même flush()
            writer.close() ;

        }  catch (IOException e) {

            // affichage du message d'erreur et de la pile d'appel
            System.out.println("Erreur " + e.getMessage()) ;
            e.printStackTrace() ;
        }

        return false;
    }

    /**
     * saves the current turn
     * @param jeu current game
     * @return character arraylist
     */
    public ArrayList<Character> saveTurn(Jeu jeu){
        return new ArrayList<>(this.StringToList("\t\"turn\":\n\t\t["+Jeu.getTurn()+"\n\t\t],"));
    }

    /**
     * create a text version of a board used for saving
     * @param board the board you want to save
     * @return the data of the board as an arraylist of characters
     */

    public ArrayList<Character> SaveBoard(Board board){
        ArrayList<Character> res = new ArrayList<>();
        res.addAll(this.SaveNodes(board));
        res.addAll(this.SaveEdges(board));
        res.addAll(this.SaveLines(board));
        res.addAll(this.SaveTrap(board));
        return res;
    }

    /**
     * create a text version of a board's nodes used for saving
     * @param board the board you want to save the nodes from
     * @return the data of the nodes as an arraylist of characters
     */

    public ArrayList<Character> SaveNodes(Board board) {
        ArrayList<Character> res = new ArrayList<>(this.StringToList("\n\t\"nodes\":[\n"));
        for (Node n : board.getNodes()) {
            res.add('\t');res.add('\t');
            res.add('[');
            res.addAll(this.StringToList(Integer.toString(n.getX())));
            res.add(',');
            res.addAll(this.StringToList(Integer.toString(n.getY())));
            res.add(',');
            res.addAll(this.StringToList(Integer.toString(n.getId())));
            res.add(']');
            if (!n.equals(board.getNodes().get(board.getNodes().size()-1))){
                res.add(',');
            }
            res.add('\n');
        }
        res.add('\t');res.add(']');res.add(',');
        return res;
    }

    /**
     * create a text version of a board's edges used for saving
     * @param board the board you want to save the edges from
     * @return the data of the edges as an arraylist of characters
     */
    public ArrayList<Character> SaveEdges(Board board) {
        ArrayList<Character> res = new ArrayList<>(this.StringToList("\n\t\"edges\":[\n"));
        for (Edge e: board.getEdges()) {
            res.add('\t');res.add('\t');
            res.add('[');
            res.addAll(this.StringToList(Integer.toString(e.getStart().getId())));
            res.add(',');
            res.addAll(this.StringToList(Integer.toString(e.getEnd().getId())));
            res.add(']');
            if (!e.equals(board.getEdges().get(board.getEdges().size()-1))){
                res.add(',');
            }
            res.add('\n');
        }
        res.add('\t');res.add(']');
        res.add(',');
        return res;
    }

    /**
     * create a text version of a board's lines used for saving
     * @param board the board you want to save the lines from
     * @return the data of the lines as an arraylist of characters
     */
    public ArrayList<Character> SaveLines(Board board) {
        ArrayList<Character> res = new ArrayList<>(this.StringToList("\n\t\"lines\":[\n"));
        for (Line l: board.getLines()) {
            res.add('\t');res.add('\t');
            res.add('[');
            for (Node n :l.getNodes()) {
                res.addAll(this.StringToList(Integer.toString(n.getId())));
                if (!n.equals(l.getNodes().get(l.getNodes().size()-1))){
                    res.add(',');
                }
            }
            res.add(']');
            if (!l.equals(board.getLines().get(board.getLines().size()-1))){
                res.add(',');
            }
            res.add('\n');
        }
        res.add('\t');res.add(']');
        res.add(',');
        return res;
    }

    /**
     * create a text version of the players in the game
     * @param players the list of players you want to save
     * @return the data of the players as an arraylist of characters
     */
    public ArrayList<Character> SavePlayers(ArrayList<Player> players) {
        ArrayList<Character> res = new ArrayList<>(this.StringToList("\n\t\"players\":[\n"));
        for (Player p: players) {
            res.add('\t');res.add('\t');
            res.add('[');
            res.addAll(this.StringToList('\"'+p.getClass().getSimpleName()+'\"'));
            res.add(',');
            res.addAll(this.StringToList('\"'+p.getName()+'\"'));
            res.add(',');
            res.addAll(this.StringToList('\"'+p.getColor().toString()+'\"'));
            res.add(',');
            res.addAll(this.SavePiece(p));
            res.add(',');
            res.addAll(this.StringToList(Integer.toString(p.getNbTrap())));
            res.add(']');
            if (!p.equals(players.get(players.size()-1))){
                res.add(',');
            }
            res.add('\n');
        }
        res.add('\t');
        res.add(']');
        return res;
    }


    /**
     * create a text version of the pieces of a player
     * @param player the player you want to save pieces of
     * @return the data of the pieces as an arraylist of characters
     */
    public ArrayList<Character> SavePiece(Player player) {
        ArrayList<Character> res = new ArrayList<>();
        res.add('[');
        for (Piece p: player.getPieces()) {
            res.addAll(this.StringToList(Integer.toString(p.getNode().getId())));
            if (!p.equals(player.getPieces().get(player.getPieces().size()-1))){
                res.add(',');
            }
        }
        res.add(']');
        return res;
    }

    /**
     * reate a text version of the trap on the board
     * @param board the board you want to traps pieces of
     * @return the data of the traps as an arraylist of characters
     */
    public ArrayList<Character> SaveTrap(Board board) {
        ArrayList<Character> res = new ArrayList<>(this.StringToList("\n\t\"traps\":[\n"));
        ArrayList<String> traps = new ArrayList<>();
        for (Edge e: board.getEdges()) {
            if(e.isTrapped()){
                String tmp = "";
                tmp+="\t\t";
                tmp+='[';
                tmp+="\"edge\"";
                tmp+=',';
                tmp+=Integer.toString(e.getTrap().getTurnsLeft());
                tmp+=',';
                tmp+=Integer.toString(e.getStart().getId());
                tmp+=',';
                tmp+=Integer.toString(e.getEnd().getId());
                tmp+=']';
                traps.add(tmp);
            }
        }
        for (Node n: board.getNodes()) {
            if(n.isTrapped()){
                String tmp = "";
                tmp+="\t\t";
                tmp+='[';
                tmp+="\"node\"";
                tmp+=',';
                tmp+=Integer.toString(n.getTrap().getTurnsLeft());
                tmp+=',';
                tmp+=Integer.toString(n.getId());
                tmp+=',';
                tmp+=Integer.toString(n.getTrap().getDestination().getId());
                tmp+=']';
                traps.add(tmp);
            }
        }
        for (String s:traps) {
            res.addAll(this.StringToList(s));
            if(!s.equals(traps.get(traps.size()-1))){
                res.add(',');
                res.add('\n');
            }
        }
        res.add('\n');res.add('\t');res.add(']');
        res.add(',');
        return res;
    }

    /**
     * converts a string into a list of character
     * @param s the string
     * @return the char tab
     */
    public ArrayList<Character> StringToList(String s){
        ArrayList<Character> res = new ArrayList<>();
        for (Character c:s.toCharArray()) {
            res.add(c);
        }
        return res;
    }

    /**
     * gives the current turn of the game
     * @param jeu current game
     * @param chemin current path
     */
    private static void loadTurn(Jeu jeu,String chemin){
        int res = 0;
        try {
            String content = new String((Files.readAllBytes(Paths.get(chemin))));
            JSONObject o = new JSONObject(content);
            int turn = o.getJSONArray("turn").getInt(0);
            while (Jeu.getTurn()!=turn){
                jeu.addTurn();
            }
        }
        catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     *return a player arraylist
     * @param board current board
     * @param chemin current path
     * @return players arraylist
     */
    public ArrayList<Player> loadPlayers(Board board,String chemin){
        ArrayList<Player> res = new ArrayList<>();
        try {
            String content = new String((Files.readAllBytes(Paths.get(chemin))));
            JSONObject o = new JSONObject(content);
            JSONArray players = o.getJSONArray("players");
            for (int i = 0; i< players.length();i++){
                if (players.getJSONArray(i).get(0).toString().equals("Player")){
                    res.add(new Player(players.getJSONArray(i).get(1).toString(), ColorEnum.valueOf(players.getJSONArray(i).get(2).toString()),players.getJSONArray(i).getInt(4)));
                }else if(players.getJSONArray(i).get(0).toString().equals("RandomAI")){
                    res.add(new RandomAI(players.getJSONArray(i).get(1).toString(), ColorEnum.valueOf(players.getJSONArray(i).get(2).toString()),players.getJSONArray(i).getInt(4)));
                }else{
                    res.add(new NormalAI(players.getJSONArray(i).get(1).toString(), ColorEnum.valueOf(players.getJSONArray(i).get(2).toString()),players.getJSONArray(i).getInt(4)));
                }
                for (int idx = 0; idx<players.getJSONArray(i).getJSONArray(3).length();idx++){
                    Piece p = new Piece(res.get(i).getColor(),idx);
                    p.put(board.getNodeById(players.getJSONArray(i).getJSONArray(3).getInt(idx)));
                    res.get(i).addPiece(p);
                }
            }
        }
        catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static Board loadBoard(String chemin) throws JSONException, IOException {
        Board board = new Board();
        try {
            String content = new String((Files.readAllBytes(Paths.get(chemin))));
            JSONObject o = new JSONObject(content);
            Save.loadNodes(o.getJSONArray("nodes"),board);
            Save.loadEdges(o.getJSONArray("edges"),board);
            Save.loadLines(o.getJSONArray("lines"),board);
            Save.loadTraps(o.getJSONArray("traps"), board);
        }
        catch (IOException | JSONException e) {
            throw e;
        }
        return board;
    }

    /**
     * load all the current nodes
     * @param nodes current nodes
     * @param board current board
     * @throws JSONException exception
     */
    private static void loadNodes(JSONArray nodes,Board board) throws JSONException {
        for (int i = 0;i<nodes.length();i++){
            board.addNode(nodes.getJSONArray(i).getInt(0),nodes.getJSONArray(i).getInt(1),nodes.getJSONArray(i).getInt(2));
        }
    }

    /**
     * load all the current edges
     * @param edges current edges
     * @param board current board
     * @throws JSONException exception
     */
    private static void loadEdges(JSONArray edges,Board board) throws JSONException {
        for (int i = 0;i<edges.length();i++){
            board.addEdge(edges.getJSONArray(i).getInt(0),edges.getJSONArray(i).getInt(1));
        }
    }

    /**
     * load all the current lines
     * @param lines current lines
     * @param board current board
     * @throws JSONException exception
     */
    private static void loadLines(JSONArray lines,Board board) throws JSONException {
        for (int i = 0;i<lines.length();i++){
            ArrayList<Node> lineNodes=new ArrayList<Node>();
            for (int j=0;j<lines.getJSONArray(i).length();j++) {
                lineNodes.add(board.getNodeById(lines.getJSONArray(i).getInt(j)));
            }
            board.addLine(lineNodes);
        }
    }

    /**
     * load all the current traps
     * @param traps current traps
     * @param board current board
     * @throws JSONException exception
     */
    private static void loadTraps(JSONArray traps,Board board) throws JSONException {
        for (int i = 0;i<traps.length();i++){
            if (traps.getJSONArray(i).get(0).equals("edge")){
                board.trapEdge(traps.getJSONArray(i).getInt(2),traps.getJSONArray(i).getInt(3),traps.getJSONArray(i).getInt(1));

            }else{
                board.trapNode(traps.getJSONArray(i).getInt(2),traps.getJSONArray(i).getInt(3),traps.getJSONArray(i).getInt(1));
            }
        }
    }

    public static Jeu loadJeu(String name) throws JSONException, IOException {
        Save save = new Save(name);
        Board board = Save.loadBoard(save.path);
        ArrayList<Player> players = new ArrayList<>(save.loadPlayers(board,save.path));
        Jeu jeu = new Jeu(board,players);
        Save.loadTurn(jeu, save.path);
        return jeu;
    }

    public static void main(String[] args) throws JSONException, IOException {
        Board board = Board.generateBoard(4);
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Patrick", ColorEnum.ROUGE));
        players.add(new RandomAI("Intelligence Artificielle", ColorEnum.BLEU));

        for (int i=0;i<3;i++){
            for (Player p:players) {
                p.addPiece(new Piece(p.getColor(),i));
            }
        }
        TrapTeleport trapTeleport = new TrapTeleport(3,board.getNodeById(5));
        board.getNodeById(10).setTrap(trapTeleport);
        Trap trap = new Trap(5);
        board.getEdges().get(5).setTrap(trap);
        Jeu jeu= new Jeu(board,players);
        jeu.addTurn();
        jeu = Jeu.randomStart(board,players);
        Save save = new Save("test");
        save.generateSave(jeu);
        Jeu jeu1 = Save.loadJeu(save.name);
        System.out.println(Jeu.getTurn());
        jeu1.getBoard().render();
    }
}
