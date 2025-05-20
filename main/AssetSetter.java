package main;

import java.text.BreakIterator;
import java.util.ArrayList;
import object.GameObject; // Replace with correct base class if named differently
import object.OBJ_Castle;
import object.OBJ_Door;
import object.OBJ_Key;
import object.SuperObject;


public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    private static class GameObjectPlacement {
        public SuperObject obj;
        public int tileX;
        public int tileY;
    
        public GameObjectPlacement(SuperObject obj, int tileX, int tileY) {
            this.obj = obj;
            this.tileX = tileX;
            this.tileY = tileY;
        }
    }
    

    public void setObjects() {

        for (int i = 0; i < gp.obj.length; i++) {
            gp.obj[i] = null;
        }

        ArrayList<GameObjectPlacement> placements = new ArrayList<>();

        switch (gp.tileM.currentMap) {
            case 0:
                placements.add(new GameObjectPlacement(new OBJ_Key(), 23, 7));
                placements.add(new GameObjectPlacement(new OBJ_Key(), 23, 40));
                placements.add(new GameObjectPlacement(new OBJ_Key(), 10, 33));
                placements.add(new GameObjectPlacement(new OBJ_Castle(), 10, 7));
                placements.add(new GameObjectPlacement(new OBJ_Door(), 8, 28));
                placements.add(new GameObjectPlacement(new OBJ_Door(), 10, 11));
                placements.add(new GameObjectPlacement(new OBJ_Door(), 12, 22));
                break;

            case 1:
                placements.clear();
                placements.add(new GameObjectPlacement(new OBJ_Door(), 41, 46));
                placements.add(new GameObjectPlacement(new OBJ_Key(), 41, 48));
                placements.add(new GameObjectPlacement(new OBJ_Key(), 42, 48));
                placements.add(new GameObjectPlacement(new OBJ_Door(), 15, 6));
                placements.add(new GameObjectPlacement(new OBJ_Door(), 15, 7));
                placements.add(new GameObjectPlacement(new OBJ_Key(), 42, 12));
                placements.add(new GameObjectPlacement(new OBJ_Castle(), 18, 2));
                break;
            case 2:
                placements.clear();
                placements.add(new GameObjectPlacement(new OBJ_Door(), 9, 17));
                placements.add(new GameObjectPlacement(new OBJ_Castle(), 7, 18));
                placements.add(new GameObjectPlacement(new OBJ_Key(), 19, 45));
                break;
            case 3:
                placements.add(new GameObjectPlacement(new OBJ_Door(), 19, 30));
                placements.add(new GameObjectPlacement(new OBJ_Castle(), 18, 30));
                placements.add(new GameObjectPlacement(new OBJ_Key(), 17, 30));
                break;
        
        }

        for (int i = 0; i < placements.size(); i++) {
            GameObjectPlacement p = placements.get(i);
            p.obj.worldX = p.tileX * gp.tileSize;
            p.obj.worldY = p.tileY * gp.tileSize;
            gp.obj[i] = p.obj;
        }
    }
}
