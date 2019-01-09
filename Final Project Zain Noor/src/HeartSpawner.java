/*
 * Class that handles spawning hearts for pick ups and lifebar
 */
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class HeartSpawner {
	//ArrayLists for hearts
	static ArrayList<HeartSpawner> hearts = new ArrayList<HeartSpawner>();
	static ArrayList<HeartSpawner> lifeBar = new ArrayList<HeartSpawner>();
	
	//X and Y location
	private int x;
	private int y;
	
	//Image for the hearts
	private Sprite heartImage;
	
	//heart boundary 
	private Rectangle boundary;
	
	HeartSpawner(int x, int y) {
		this.x = x;
		this.y = y;
		
		heartImage = new Sprite(new Texture(Gdx.files.internal("images/heart.png")));
		heartImage.setPosition(x, y);
		boundary = new Rectangle(x, y, 50, 50);
	}
	
	public void addHeart(HeartSpawner heart) {
		hearts.add(heart);
	}
	
	public void addLifeHeart(HeartSpawner heart) {
		lifeBar.add(heart);
	}
	
	public static void removeLifeHeart(int x) {
		lifeBar.remove(x);
	}
	
	public Sprite getHeartImage() {
		return heartImage;
	}
	
	public static HeartSpawner getHeart(int x) {
		return hearts.get(x);
	}
	
	public static HeartSpawner getLifeHeart(int x) {
		return lifeBar.get(x);
	}
	
	public Rectangle heartBoundary() {
		return this.boundary;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
