package inf112.skeleton.app.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import inf112.skeleton.app.GridFunctionality.GridOfTiles;
import inf112.skeleton.app.GridFunctionality.Tile;
import inf112.skeleton.app.Objects.Actor.MyActor;

public class Flag implements IObject {
    Sprite sprite;
    public int y;
    public int x;
    Tile flagTile;

    public Flag(RectangleMapObject TiledFlag, GridOfTiles grid){
        y = (int)TiledFlag.getRectangle().getY();
        x = (int)TiledFlag.getRectangle().getX();

        Texture texture = new Texture("Flag.png");
        sprite = new Sprite(texture);
        sprite.setSize(100, 100);

        flagTile = grid.getTileWfloats(x ,y);
        flagTile.addObjOnTile(this);
    }

    public Flag(int x, int y, GridOfTiles grid){
        this.x = x;
        this.y = y;
        sprite=null;
        flagTile = grid.getTileWfloats(x ,y);
        flagTile.addObjOnTile(this);
    }

    public void remove(GridOfTiles grid){
        this.sprite = null;
        Tile tile = grid.getTileWfloats(this.x, this.y);
        tile.getObjOnTile().remove(this);
    }

    public Tile getFlagTile(){
        return this.flagTile;
    }

    public void handle(MyActor actor, GridOfTiles grid){
        Tile actorTile = grid.getTileWfloats(actor.getX(), actor.getY());
        if (flagTile.equals(actorTile)){
            actor.setBackupTile(flagTile);
            remove(grid);
        }
    }

    @Override
    public Sprite getSprite() {
        sprite.setY(y);
        sprite.setX(x);
        return sprite;
    }
}