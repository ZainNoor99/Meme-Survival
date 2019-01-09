/*
 * Normal enemy class
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.Random;
import com.badlogic.gdx.math.Rectangle;

public class NormalEnemy {
	//sprite for memes
	private Sprite enemyImage;
	
	//coordinates for memes based on where its spawned
	private int enemySpawnTopX = 450;
	private int enemySpawnTopY = 950;
	
	private int enemySpawnLeftX = -50;
	private int enemySpawnLeftY = 450;
	
	private int enemySpawnBottomX = 450;
	private int enemySpawnBottomY = -50;
	
	private int enemySpawnRightX = 1650;
	private int enemySpawnRightY = 450;
	
	//where its spawned
	private int direction;
	
	//boundary for the memes
	private Rectangle boundary;
	
	//generates random number to choose which meme image to use
	private Random number = new Random();
	private int randomNumber;
	private String memeName;
	
	//enemy speed
	private int enemySpeed = 3;
	
	public NormalEnemy(int x) {
		randomNumber = number.nextInt(12) + 1;
		memeName = Integer.toString(randomNumber) + ".png";
		if (x == 1) {
			enemyImage = new Sprite(new Texture(Gdx.files.internal("memes/" + memeName)));
			enemyImage.setPosition(enemySpawnTopX, enemySpawnTopY);
			boundary = new Rectangle(enemySpawnTopX, enemySpawnTopY, 50, 50);
		} else if (x == 2) {
			enemyImage = new Sprite(new Texture(Gdx.files.internal("memes/" + memeName)));
			enemyImage.setPosition(enemySpawnLeftX, enemySpawnLeftY);
			boundary = new Rectangle(enemySpawnLeftX, enemySpawnLeftY, 50, 50);
		} else if (x == 3) {
			enemyImage = new Sprite(new Texture(Gdx.files.internal("memes/" + memeName)));
			enemyImage.setPosition(enemySpawnBottomX, enemySpawnBottomY);
			boundary = new Rectangle(enemySpawnBottomX, enemySpawnBottomY, 50, 50);
		} else if (x == 4) {
			enemyImage = new Sprite(new Texture(Gdx.files.internal("memes/" + memeName)));
			enemyImage.setPosition(enemySpawnRightX, enemySpawnRightY);
			boundary = new Rectangle(enemySpawnRightX, enemySpawnRightY, 50, 50);
		} 
		direction = x;
	}
	
	public void attack(int soldierX, int soldierY) {
		if (direction == 1) {
			if (soldierX + 25 >= this.enemySpawnTopX) {
				enemySpawnTopX += enemySpeed;
				boundary = new Rectangle(enemySpawnTopX, enemySpawnTopY, 50, 50);
			}
			if (soldierX <= this.enemySpawnTopX - 25) {
				enemySpawnTopX -= enemySpeed;
				boundary = new Rectangle(enemySpawnTopX, enemySpawnTopY, 50, 50);
			}
			if (soldierY + 25 >= this.enemySpawnTopY) {
				enemySpawnTopY += enemySpeed;
				boundary = new Rectangle(enemySpawnTopX, enemySpawnTopY, 50, 50);
			}
			if (soldierY <= this.enemySpawnTopY - 25) {
				enemySpawnTopY -= enemySpeed;
				boundary = new Rectangle(enemySpawnTopX, enemySpawnTopY, 50, 50);
			}
		
		} else if (direction == 2) {
			if (soldierX + 25 >= this.enemySpawnLeftX) {
				enemySpawnLeftX += enemySpeed;
				boundary = new Rectangle(enemySpawnLeftX, enemySpawnLeftY, 50, 50);
			}
			if (soldierX  <= this.enemySpawnLeftX - 25) {
				enemySpawnLeftX -= enemySpeed;
				boundary = new Rectangle(enemySpawnLeftX, enemySpawnLeftY, 50, 50);
			}
			if (soldierY + 25 >= this.enemySpawnLeftY) {
				enemySpawnLeftY += enemySpeed;
				boundary = new Rectangle(enemySpawnLeftX, enemySpawnLeftY, 50, 50);
			}
			if (soldierY <= this.enemySpawnLeftY - 25) {
				enemySpawnLeftY -= enemySpeed;
				boundary = new Rectangle(enemySpawnLeftX, enemySpawnLeftY, 50, 50);
			}
		} else if (direction == 3) {
			if (soldierX + 25 >= this.enemySpawnBottomX) {
				enemySpawnBottomX += enemySpeed;
				boundary = new Rectangle(enemySpawnBottomX, enemySpawnBottomY, 50, 50);
			}
			if (soldierX  <= this.enemySpawnBottomX - 25) {
				enemySpawnBottomX -= enemySpeed;
				boundary = new Rectangle(enemySpawnBottomX, enemySpawnBottomY, 50, 50);
			}
			if (soldierY + 25 >= this.enemySpawnBottomY) {
				enemySpawnBottomY += enemySpeed;
				boundary = new Rectangle(enemySpawnBottomX, enemySpawnBottomY, 50, 50);
			}
			if (soldierY <= this.enemySpawnBottomY - 25) {
				enemySpawnBottomY -= enemySpeed;
				boundary = new Rectangle(enemySpawnBottomX, enemySpawnBottomY, 50, 50);
			}
		} else if (direction == 4) {
			if (soldierX + 25 >= this.enemySpawnRightX) {
				enemySpawnRightX += enemySpeed;
				boundary = new Rectangle(enemySpawnRightX, enemySpawnRightY, 50, 50);
			}
			if (soldierX  <= this.enemySpawnRightX - 25) {
				enemySpawnRightX -= enemySpeed;
				boundary = new Rectangle(enemySpawnRightX, enemySpawnRightY, 50, 50);
			}
			if (soldierY + 25 >= this.enemySpawnRightY) {
				enemySpawnRightY += enemySpeed;
				boundary = new Rectangle(enemySpawnRightX, enemySpawnRightY, 50, 50);
			}
			if (soldierY <= this.enemySpawnRightY - 25) {
				enemySpawnRightY -= enemySpeed;
				boundary = new Rectangle(enemySpawnRightX, enemySpawnRightY, 50, 50);
			} 
		}
	}
	
	public Rectangle getBoundary() {
		return this.boundary;
	}
	
	public int getEnemyX() {
		int x = 0;
		if (direction == 1) {
			x = enemySpawnTopX;
		} else if (direction == 2) {
			x = enemySpawnLeftX;
		} else if (direction == 3) {
			x = enemySpawnBottomX;
		} else if (direction == 4) {
			x = enemySpawnRightX;
		}
		return x;
	}
	
	public int getEnemyY() {
		int y = 0;
		if (direction == 1) {
			y = enemySpawnTopY;
		} else if (direction == 2) {
			y = enemySpawnLeftY;
		} else if (direction == 3) {
			y = enemySpawnBottomY;
		} else if (direction == 4) {
			y = enemySpawnRightY;
		}
		return y;
	}
	
	public Sprite getEnemyImage() {
		return enemyImage;
	}
	
}
