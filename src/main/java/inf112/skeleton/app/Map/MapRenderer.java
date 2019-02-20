package inf112.skeleton.app.Map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.NewGame;

public class MapRenderer extends ApplicationAdapter  {

    public MapRenderer(){

    }

    //Camera camera = getCamera();
    public static TiledMap map;
   public TiledMap map1;
    public TiledMap map2;
    public TiledMap map3;
    public TiledMapRenderer renderer;

    @Override
    public void create() {
        map1 = new TmxMapLoader().load("ROBORALLY_MAP.tmx");
        map2 = new TmxMapLoader().load("RoboMap.tmx");
        map3 = new TmxMapLoader().load("MapTest.tmx");
        Maps.addMap(map1);
        Maps.addMap(map2);
        Maps.addMap(map3);

        map = map1;

        renderer = new OrthogonalTiledMapRenderer(map);

    }

    @Override
    public void render() {
        renderer.setView(NewGame.camera);
        renderer.render();
    }

    public Vector2 getTileCord(int index){
        TiledMapTile tile = map.getTileSets().getTile(index);
        //Somehow return the coordinates of a specific tile
        return null;
    }
    public static void setMap(TiledMap selectedMap){
        map = selectedMap;
    }
}
