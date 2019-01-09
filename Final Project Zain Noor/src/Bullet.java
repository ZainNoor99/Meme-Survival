/*
 * Bullet Class
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {
	//speed of the bullets
	private int bulletSpeed = 30;
	
	//coordinates of bullet
	int bulletX;
	int bulletY;
	
	//sprite of bullet
	private Sprite bulletImage;
	
	//rectangle and direction of travel
	private Rectangle boundary;
	private int direction;
	
	public void newBullet(int x, int y, int z) {
		this.bulletX = x;
		this.bulletY = y;
		this.direction = z;
		if (z == 1) {
			bulletImage = new Sprite (new Texture( Gdx.files.internal("images/bulletup.png")));
			bulletImage.setPosition(x, y);
		} else if (z == 2) {
			bulletImage = new Sprite (new Texture( Gdx.files.internal("images/bulletleft.png")));
			bulletImage.setPosition(x, y);
		} else if (z == 3) {
			bulletImage = new Sprite (new Texture( Gdx.files.internal("images/bulletdown.png")));
			bulletImage.setPosition(x, y);
		} else if (z == 4) {
			bulletImage = new Sprite (new Texture( Gdx.files.internal("images/bulletright.png")));
			bulletImage.setPosition(x, y);
		}
	}
	
	public int getDirection() {
		return this.direction;
	}
	public int getX() {
		return bulletX;
	}
	
	public int getY() {
		return bulletY;
	}
	
	public Sprite getBulletImage() {
		return bulletImage;
	}

	public void setBoundary(Rectangle boundary) {
		this.boundary = boundary;
	}
	
	public Rectangle bulletBoundary() {
		return this.boundary;
	}
}
