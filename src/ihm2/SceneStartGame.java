package ihm2;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import moulin.Config;
import moulin.Node;
import moulin.Player;
import moulin.RandomAI;

public class SceneStartGame {
    private static Scene scene;
    static CustomCanvas customCanvas;
    private static VBox root;
    private static int i;
    private static boolean posed;
    public static void create(){
        root=new VBox();
        customCanvas = new CustomCanvas(500,500);
        root.getChildren().add(customCanvas);

        scene=new Scene(root,Main.getDefaultSceneWidth(),Main.getDefaultSceneHeight());
    }

    public static Scene getScene(){
        return scene;
    }

    public static void switchTo(){
        customCanvas.render(SceneNewGame.getJeu());
        Main.changeScene(scene);
        for(i = 0; i<SceneNewGame.getJeu().getPlayers().get(0).getPieces().size();i++){
            for (Player p: SceneNewGame.getJeu().getPlayers()) {
                System.out.println(p.getClass().getSimpleName());
                if (p.getClass().getSimpleName().equals("Player")){
                    posed=false;
                    root.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            customCanvas.render(SceneNewGame.getJeu());
                            Main.changeScene(scene);
                            Node n = customCanvas.mouseOverNode(SceneNewGame.getJeu());
                            if (n != null) {
                                System.out.println("dans le null");
                                p.getPieces().get(i).put(n);
                                posed = true;
                            }
                        }
                    });
                }if (posed){
                    ((RandomAI)p).start(SceneNewGame.getJeu(),p.getPieces().get(i));
                }
                try {
                    Player winner = SceneNewGame.getJeu().isFinished();
                    if(winner!=null){
                        Alert a = new Alert(Alert.AlertType.INFORMATION,"AND THE WINNER IS : " + winner.getName());
                        a.setTitle("Winner !");
                        a.show();
                    }
                }catch (Exception ignored){

                }
            }
        }
    }
}
