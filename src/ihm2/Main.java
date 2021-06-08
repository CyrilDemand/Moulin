package ihm2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import moulin.Config;

public class Main extends Application {

    private static Stage stage;

    private static final int defaultSceneWidth=1200,defaultSceneHeight=800;

    @Override
    public void start(Stage stage) throws Exception {
        Main.stage=stage;
        Config.loadConfig();
        //Creation of all the scenes
        SceneStartGame.create();
        SceneChoiceStartGame.create();
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
        changeScene(SceneMainMenu.getScene());
        stage.setResizable(true);
        stage.show();
    }

    public static void changeScene(Scene newScene){
        stage.setScene(newScene);
    }

    public static Stage getStage(){
        return stage;
    }

    public static int getDefaultSceneHeight() {
        return defaultSceneHeight;
    }

    public static int getDefaultSceneWidth() {
        return defaultSceneWidth;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }



}
