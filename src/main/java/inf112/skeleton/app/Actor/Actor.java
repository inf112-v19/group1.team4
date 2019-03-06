package inf112.skeleton.app.Actor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.CardFunctionality.Card;
import inf112.skeleton.app.CardFunctionality.Deck;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Actor extends ApplicationAdapter implements InputProcessor {
    private Deck deck = new Deck();
    ArrayList<Card> handout = new ArrayList<>(9);

    public float actorLeft;
    public float actorRight;
    public float actorTop;
    public float actorBottom;



    // Objectify and visualize all cards - then make it possible to choose
    ArrayDeque<Card> chosen = new ArrayDeque<>(5);

    private Batch batch;
    private Texture aTexture;
    private com.badlogic.gdx.scenes.scene2d.Actor actor = new com.badlogic.gdx.scenes.scene2d.Actor();

    public Actor(){
    }

    float getX() {
        return actor.getX();
    }

    float getY() {
        return actor.getY();
    }

    void chooseCard(int i) {
        Card card = handout.get(i);
        handout.remove(i);
        chosen.addFirst(card);
        while (chosen.size() > 5) {
            Card deletedCard = chosen.removeLast();
            handout.add(deletedCard);
        }
    }

    void handOut() {
        handout.clear();
        for (int i = 0; i < 9; i++) {
            handout.add(deck.handOut());
        }
    }

    @Override
    public void create() {
        Gdx.input.setInputProcessor(this);

        // Sprite
        batch = new SpriteBatch();
        aTexture = new Texture(Gdx.files.internal("robbie.png"));

        // Get cards, place in handout (From Deck.handout)
        handOut();
    }

    @Override
    public void render() {
        int middleWidth = Gdx.graphics.getWidth()/2;
        int middleHeight = Gdx.graphics.getHeight()/2;

        actorBottom = actor.getY();
        actorLeft = actor.getX();
        actorTop = actor.getY()+70;
        actorRight = actor.getX()+100;

        batch.begin();
        batch.draw(aTexture, middleWidth+actor.getX(), middleHeight+actor.getY(), 100, 70);
        actor.draw(batch, 1);
        batch.end();
        //chosen.getLast().render();
    }

    public ArrayDeque getChosen(){
        return chosen;
    }

    //Actor Input
    @Override
    public boolean keyDown(int keycode) {

        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        int middleWidth = width / 2;
        int middleHeight = height / 2;

        int speedWidth = 100;
        int speedHeight = 50;


        if (keycode == Input.Keys.LEFT) {
            middleWidth-=speedWidth;

            if (middleWidth+actorLeft < 0) {
                actor.moveBy(0, 0);
                middleWidth+=speedWidth;
            } else {
                actor.moveBy(-speedWidth, 0);
            }
        }

        if (keycode == Input.Keys.RIGHT) {
            middleWidth+=speedWidth;
            if (middleWidth+actorRight > width) {
                actor.moveBy(0, 0);
                middleWidth-=speedWidth;
            } else {
                actor.moveBy(speedWidth, 0);
            }
        }

        if (keycode == Input.Keys.UP) {
            middleHeight+=speedHeight;
            if(middleHeight+actorTop > height) {
                actor.moveBy(0,0);
                middleHeight-=speedHeight;
            } else {
                actor.moveBy(0, speedHeight);
            }
        }

        if (keycode == Input.Keys.DOWN) {
            middleHeight-=speedHeight;
            if (middleHeight + actorBottom < 0) {
                actor.moveBy(0, 0);
                middleHeight+=speedHeight;
            } else {
                actor.moveBy(0, -speedHeight);
            }
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // choose cards with keypad 1-9 / vector and add iteratively to chosen arraylist
        if (keycode >= Input.Keys.NUM_1 && keycode <= Input.Keys.NUM_9) {
            chooseCard(keycode);
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}