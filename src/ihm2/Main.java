package ihm2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import moulin.Config;

public class Main extends Application {

    private static Stage stage;
    private static boolean fsHint;


    @Override
    public void start(Stage stage) throws Exception {
        Main.stage=stage;
        Config.loadConfig();
        //Creation of all the scenes
        SceneChoixJoueurs.create();
        SceneSidesMap.create();
        SceneCustomMap.create();
        SceneAskFile.create();
        SceneNewGame.create();
        SceneTypeOfMap.create();

        SceneOptions.create();
        SceneRules.create();

        SceneMainMenu.create();

        //Stage Settings
        stage.setTitle("The Mill Game");
        stage.setScene(SceneMainMenu.getScene());
        stage.setResizable(true);
        stage.show();
    }

    public static void changeScene(Scene newScene){
        stage.setScene(newScene);
        if(fsHint){
            stage.setFullScreen(true);
        }
    }

    public static void setFsHint(boolean b){
        fsHint=b;
    }

    public static Stage getStage(){
        return stage;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
