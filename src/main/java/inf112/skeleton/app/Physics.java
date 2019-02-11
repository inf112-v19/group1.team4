package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Physics extends ApplicationAdapter {

    SpriteBatch batch;
    Sprite sprite;
    Texture img;
    World world;
    Body body;

    @Override
    public void create(){
        img = new Texture("badlogic.jpg");
        sprite = new Sprite(img);

        //Center to middle of screen
        sprite.setPosition(Gdx.graphics.getWidth()/2 - sprite.getWidth()/2, Gdx.graphics.getHeight());

        //Create a physics world, where the vector passed in is gravity
        world = new World(new Vector2(0, .98f), true);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(sprite.getX(), sprite.getY());

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();

        shape.setAsBox(sprite.getWidth()/2, sprite.getHeight()/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();
    }

    @Override
    public void render(){
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);

        sprite.setPosition(body.getPosition().x, body.getPosition().y);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(sprite, sprite.getX(), sprite.getY());
        batch.end();
    }

    @Override
    public void dispose() {
        // Hey, I actually did some clean up in a code sample!
        img.dispose();
        world.dispose();
    }
}