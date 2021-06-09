package ihm;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import moulin.Config;

/**
 * Main class of the graphic project
 */
public class Main extends Application {

    private static Stage stage;

    private static final int defaultSceneWidth=1200,defaultSceneHeight=800;

    /**
     * Starts the interface
     * @param stage used Stage
     * @throws Exception Exception
     */
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
        SceneChoixMain.create();
        //Stage Settings
        stage.setTitle("The Mill Game");
        changeScene(SceneChoixMain.getScene());
        stage.setResizable(true);
        stage.show();
    }

    /**
     * changes the used scene
     * @param newScene new used scene
     */
    public static void changeScene(Scene newScene){
        stage.setScene(newScene);
    }

    /**
     * gets the current stage
     * @return the current stage
     */
    public static Stage getStage(){
        return stage;
    }

    /**
     * gets the default scene height
     * @return the default scene height
     */
    public static int getDefaultSceneHeight() {
        return defaultSceneHeight;
    }

    /**
     * gets the default scene width
     * @return the default scene width
     */
    public static int getDefaultSceneWidth() {
        return defaultSceneWidth;
    }

    /**
     * main fonction
     * @param args args
     */
    public static void main(String[] args) {
        Application.launch(args);
    }



}
