package inf112.skeleton.app.Game;

import com.badlogic.gdx.Game;

public class RoboRally extends Game {

    private MenuScreen mainMenuScreen;
    private MyGame gameScreen;
    private PreferencesScreen preferencesScreen;
    private GamePreferences preferences;
    private MultiplayerScreen multiplayerScreen;
    private PlayOptions playOptions;

    static final int MENU = 1;
    static final int PREFERENCES = 2;
    static final int GAME = 3;
    static final int MULTIPLAYER = 4;
    static final int PLAYOPTIONS = 5;

    @Override
    public void create() {
        MenuScreen menuScreen = new MenuScreen(this);
        setScreen(menuScreen);
        preferences = new GamePreferences();
    }

    void changeScreen(int screen) {
        switch (screen) {
            case MENU:
                if (mainMenuScreen == null) mainMenuScreen = new MenuScreen(this);
                this.setScreen(mainMenuScreen);
                break;
            case PREFERENCES:
                if (preferencesScreen == null) preferencesScreen = new PreferencesScreen(this);
                this.setScreen(preferencesScreen);
                break;
            case GAME:
                if (gameScreen == null) gameScreen = new MyGame(this); gameScreen.create();
                this.setScreen(gameScreen);
                break;
            case MULTIPLAYER:
                if(multiplayerScreen == null) multiplayerScreen = new MultiplayerScreen(this);
                this.setScreen(multiplayerScreen);
                break;
            case PLAYOPTIONS:
                if(playOptions == null) playOptions = new PlayOptions(this);
                this.setScreen(playOptions);
                break;
        }
    }

    GamePreferences getPreferences(){
        return this.preferences;
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }
}
