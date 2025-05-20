package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Castle extends SuperObject{
    public OBJ_Castle () {
        name = "Castle";
        try {
        image = ImageIO.read(getClass().getResourceAsStream("/objects/castle.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
