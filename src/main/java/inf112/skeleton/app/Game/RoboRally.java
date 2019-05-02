package inf112.skeleton.app.Game;

import com.badlogic.gdx.Game;

public class RoboRally extends Game {

    private MenuScreen mainMenuScreen;
    private MyGame gameScreen;
    private PreferencesScreen preferencesScreen;
    private GamePreferences preferences;
    private GameOverScreen gameOverScreen;
    private MultiplayerScreen multiplayerScreen;
    private PlayOptions playOptions;

    public static final int MENU = 1;
    public static final int PREFERENCES = 2;
    public static final int GAME = 3;
    public static final int MULTIPLAYER = 4;
    public static final int PLAYOPTIONS = 5;

    @Override
    public void create() {
        MenuScreen menuScreen = new MenuScreen(this);
        setScreen(menuScreen);
        preferences = new GamePreferences();
    }

    public void changeScreen(int screen) {
        this.changeScreen(screen, false);
    }

    public void changeScreen(int screen, boolean multiplayer) {
        this.changeScreen(screen, multiplayer, null);
    }

    public void changeScreen(int screen, boolean multiplayer, String host) {
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
                if (gameScreen == null) {
                    gameScreen = new MyGame(this, multiplayer, host);
                    gameScreen.create();
                } else if (multiplayer && host != null) {
                    gameScreen.joinGame(host);
                }
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

    public GamePreferences getPreferences(){
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
