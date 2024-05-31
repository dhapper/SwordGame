package utilz;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {

    private BufferedImage spriteSheet;
    private int spriteWidth;
    private int spriteHeight;

    public SpriteSheet(String filePath, int rows, int cols) {//throws IOException {
        try {
			spriteSheet = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        spriteWidth = spriteSheet.getWidth() / cols;
        spriteHeight = spriteSheet.getHeight() / rows;
    }

    public BufferedImage getSprite(int choice) {
        int col = (choice % (spriteSheet.getWidth() / spriteWidth));
        int row = (choice / (spriteSheet.getWidth() / spriteWidth));
        
        return spriteSheet.getSubimage(col * spriteWidth, row * spriteHeight, spriteWidth, spriteHeight);
    }

}
