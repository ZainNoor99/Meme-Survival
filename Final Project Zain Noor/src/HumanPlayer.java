/*
 * Class that handles the player
 */
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class HumanPlayer {
	//ArrayLists for and bullets
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	//score and hp
	private int HP = 3;
	private int score;
	
	//bullet object and speed
	private Bullet bullet;
	private int bulletSpeed = 15;
	
	//starting coordinates
	private int soldierX = 800;
	private int soldierY = 450;
	
	//width, height and sprite
	private int soldierWidth = 100;
	private int soldierLength = 100;
	private Sprite soldierImage;
	
	//boundaries for soldier and bullets
	private Rectangle soldierBoundary;
	private Rectangle bulletBoundary;
	
	//boolean if soldier is dead or alive
	private boolean alive = true;
	
	//loads bullet firing sound
	private Sound bulletFired = Gdx.audio.newSound(Gdx.files.internal("sounds/pew.mp3"));
	
	public HumanPlayer () {
				
		soldierImage = new Sprite (new Texture( Gdx.files.internal("images/soldier.png")));
		soldierImage.setPosition(soldierX, soldierY);
		soldierBoundary = new Rectangle(soldierX, soldierY, 100, 100);
	}
	
	public Sprite getImage() {
		return soldierImage;
	}
	
	public void drawSoldier() {

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            if (soldierX <= 0) {
            	soldierX += 0;
            	soldierBoundary = new Rectangle(soldierX, soldierY, 100, 100);
            } else { 
            	soldierX -= 10;
            	soldierBoundary = new Rectangle(soldierX, soldierY, 100, 100);
             }
		}
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
        	if (soldierX + soldierWidth >= 1600) {
        		soldierX += 0;
        		soldierBoundary = new Rectangle(soldierX, soldierY, 100, 100);
        	} else {
        		soldierX += 10;
        		soldierBoundary = new Rectangle(soldierX, soldierY, 100, 100);
        	}				
        }	
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            if (soldierY + soldierLength >= 850) {
            	soldierY += 0;
            	soldierBoundary = new Rectangle(soldierX, soldierY, 100, 100);
            } else {
            	soldierY += 10;
            	soldierBoundary = new Rectangle(soldierX, soldierY, 100, 100);
            }
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
        	if (soldierY <= 0) {
        		soldierY += 0;
        		soldierBoundary = new Rectangle(soldierX, soldierY, 100, 100);
        	} else {
        		soldierY -= 10;
        		soldierBoundary = new Rectangle(soldierX, soldierY, 100, 100);
        	}
        }
       
	}
	
	public int bulletSize() {
		return bullets.size();
	}
	
	public int getHP() {
		return this.HP;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void shooter() {
		if (Gdx.input.isKeyJustPressed(Keys.W)) {
        	bullet = new Bullet();
        	bullet.newBullet(soldierX + 50, soldierY + 100, 1);
        	bullets.add(bullet);
        	bulletFired.play();
    	}
        if (Gdx.input.isKeyJustPressed(Keys.A)) {
        	bullet = new Bullet();
        	bullet.newBullet(soldierX, soldierY + 50, 2);
        	bullets.add(bullet);
        	bulletFired.play();
    	}
        if (Gdx.input.isKeyJustPressed(Keys.S)) {
        	bullet = new Bullet();
        	bullet.newBullet(soldierX + 50, soldierY, 3);
        	bullets.add(bullet);
        	bulletFired.play();
    	}
        if (Gdx.input.isKeyJustPressed(Keys.D)) {
        	bullet = new Bullet();
        	bullet.newBullet(soldierX + 100, soldierY + 50, 4);
        	bullets.add(bullet);
        	bulletFired.play();
    	}
	}
	
	public void drawBullet() {
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).getY() >= 800 || bullets.get(i).getY() <= 0 || bullets.get(i).getX() >= 1600 || bullets.get(i).getX() <= 0) {
				bullets.remove(i);
			} else if (bullets.get(i).getDirection() == 1) {
				bullets.get(i).bulletY += bulletSpeed;
				bulletBoundary = new Rectangle(bullets.get(i).getX(), bullets.get(i).getY(), 10, 30);
			} else if (bullets.get(i).getDirection() == 2) {
				bullets.get(i).bulletX -= bulletSpeed;
				bulletBoundary = new Rectangle(bullets.get(i).getX(), bullets.get(i).getY(), 30, 10);
			} else if (bullets.get(i).getDirection() == 3) {
				bullets.get(i).bulletY -= bulletSpeed;
				bulletBoundary = new Rectangle(bullets.get(i).getX(), bullets.get(i).getY(), 10, 30);
			} else if (bullets.get(i).getDirection() == 4) {
				bullets.get(i).bulletX += bulletSpeed;
				bulletBoundary = new Rectangle(bullets.get(i).getX(), bullets.get(i).getY(), 30, 10);
			}
		} 
	}
	
	public void removeBullet (int bullet) {
		bullets.remove(bullet);
	}
	
	public Bullet getBullet(int x) {
		return bullets.get(x);
	}
	
	public Rectangle soldierBoundary() {
		return this.soldierBoundary;
	}
	
	public int soldierX() {
		return soldierX;
	}
	
	public int soldierY() {
		return soldierY;
	}
	
	public void addHP() {
		this.HP += 1;
	}
	
	public void subtractHP() {
		this.HP -= 1;
	}
	
	public void addScore() {
		this.score += 10;
	}
	
	public boolean isAlive() {
		return this.alive;
	}
	
	public void dead() {
		this.alive = false;
	}
	
}
