package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import tile.TileManager;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    TileManager tileM;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH, TileManager tileM) {
        this.gp = gp;
        this.keyH = keyH;
        this.tileM = tileM;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(6, 16, 36, 20);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues () {

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_stand_up.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_walk_up.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_stand_down.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_walk_down.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_stand_left.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_walk_left.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_stand_right.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_walk_right.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.onePRessed) {
            
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";  
            } else if (keyH.leftPressed) {
                direction = "left"; 
            } else if (keyH.rightPressed) {
                direction = "right";
            } else if (keyH.onePRessed) {
                tileM.currentMap += 2;
                tileM.loadCurrentMap();
                
                worldX = gp.tileSize * 23; // column 5
                worldY = gp.tileSize * 21; // row 8
            }
            
    
            // check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
    
            // if collision is false, player can move
            if (!collisionOn) {
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }
    
            // animation
            spriteCounter++;
            if (spriteCounter >= 10) {
                spriteNum = (spriteNum == 1) ? 2 : 1;
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject (int i) {

        if (i != 999) {
            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                hasKey++;
                gp.obj[i] = null;
                    break;
                case "Door":
                if (hasKey > 0) {
                    gp.obj[i] = null;
                    hasKey--;
                }
                    break;
                case "Castle":
                tileM.currentMap++;
                tileM.loadCurrentMap();
                
                worldX = gp.tileSize * 23; // column 5
                worldY = gp.tileSize * 21; // row 8


                System.out.println("hi");
                    break;
            }
        }
    }

    
    public void draw(Graphics2D g2) {
        
        BufferedImage image = null;


        switch(direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                    break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                    break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                    break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                    break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);


    }

}
