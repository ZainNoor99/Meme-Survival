
/*
 * Manager class that handles the whole game
 */
import java.util.Random;

import java.nio.file.Paths;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Manager extends Game {

	private SpriteBatch batch;

	private int soldierWidth = 100;
	private int soldierLength = 100;

	private Sprite backGround;
	private Sprite wastedMeme;

	private HumanPlayer soldier;
	private EnemySpawner meme;

	private Random num = new Random();
	private int randomNumber;

	private int randomX;
	private int randomY;

	int spawnTick = 1;

	// coordinates for spawning hearts
	private int heartX;
	private int heartY;

	// counter
	private int counter = 0;

	private HeartSpawner heart;

	// checks if there is a pickup on the field
	boolean heartOnField = false;

	// score on screen
	private Label scoreLabel;

	// UI interface that holds the score
	private Stage uiStage;

	private HeartSpawner lifeBarHeart;

	// height of the screen
	private int y = 900;

	// spawn rate
	private int spawnRate = 30;

	private Bullet bullet;

	// various sprites
	private Sprite backBar;
	private Sprite challengeAccepted;
	private Sprite challengeDenied;
	private Sprite challengeAcceptedfaded;
	private Sprite challengeDeniedfaded;

	// dead screen counter
	private int deadCounter = 0;

	// should mainscreen be on
	private boolean mainScreen = true;

	// object to handle sounds
	private Sounds sound;

	public void create() {

		System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
		// makes UI stage
		uiStage = new Stage();

		// sets all the images
		batch = new SpriteBatch();
		backGround = new Sprite(new Texture(Gdx.files.internal("images/background.jpg")));
		soldier = new HumanPlayer();
		wastedMeme = new Sprite(new Texture(Gdx.files.internal("images/getshrekt.png")));
		backBar = new Sprite(new Texture(Gdx.files.internal("images/blackbar.jpg")));
		challengeAccepted = new Sprite(new Texture(Gdx.files.internal("images/challengeaccepted.jpg")));
		challengeDenied = new Sprite(new Texture(Gdx.files.internal("images/challengedenied.jpg")));
		challengeAcceptedfaded = new Sprite(new Texture(Gdx.files.internal("images/challengeacceptedfaded.jpg")));
		challengeDeniedfaded = new Sprite(new Texture(Gdx.files.internal("images/challengedeniedfaded.jpg")));

		sound = new Sounds();

		// sets the UI stage with score label
		BitmapFont font = new BitmapFont();
		String text = "Score: " + soldier.getScore();
		LabelStyle style = new LabelStyle(font, Color.RED);
		scoreLabel = new Label(text, style);
		scoreLabel.setFontScale(3);
		scoreLabel.setPosition(0, 865);
		uiStage.addActor(scoreLabel);

		// creates the life bar
		for (int i = 1; i <= 3; i++) {
			lifeBarHeart = new HeartSpawner(1600 - (35 * i), y - 35);
			lifeBarHeart.addLifeHeart(lifeBarHeart);
		}

		bullet = new Bullet();

		meme = new EnemySpawner();

	}

	public void render() {

		if (mainScreen) {
			Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			batch.draw(backBar, 0, 0, 1600, 900);
			batch.draw(challengeAccepted, 300, 450, 1000, 450);
			batch.draw(challengeDenied, 300, 0, 1000, 450);
			batch.end();
			// runs game if challenge accepted
			if (Gdx.input.getX() > 300 && Gdx.input.getX() < 1300 && Gdx.input.getY() < 450) {
				batch.begin();
				batch.draw(challengeAcceptedfaded, 300, 450, 1000, 450);
				batch.end();
				if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
					sound.starting();
					mainScreen = false;
				}
				// exits game if challenge denied
			} else if (Gdx.input.getX() > 300 && Gdx.input.getX() < 1300 && Gdx.input.getY() > 450) {
				batch.begin();
				batch.draw(challengeDeniedfaded, 300, 0, 1000, 450);
				batch.end();
				if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
					Gdx.app.exit();
				}
			}

			// game runs while soldier is alive
		} else {
			if (soldier.isAlive()) {
				collision();
				randomNumber = num.nextInt(15) + 1;

				// random number gen for heart spawner
				randomX = num.nextInt(1500);
				randomY = num.nextInt(700);

				spawnTick = (int) (spawnRate / (float) (1 + (soldier.getScore() * 0.01f)));

				MathUtils.clamp(spawnTick, 1, 15);

				if (counter % spawnTick == 0) {
					meme.normalEnemySpawner(randomNumber);
				}

				soldier.drawSoldier();
				soldier.shooter();
				soldier.drawBullet();

				for (int i = 0; i < meme.listSize(); i++) {
					meme.get(i).attack(soldier.soldierX(), soldier.soldierY());
					if (meme.get(i).getBoundary().overlaps(soldier.soldierBoundary())) {
						meme.remove(i);
						soldier.subtractHP();
						HeartSpawner.removeLifeHeart(HeartSpawner.lifeBar.size() - 1);
						if (soldier.getHP() <= 0) {
							soldier.dead();
						}
					}
				}

				// spawns a heart if damaged and there already isnt a heart on
				// the field
				if (soldier.getHP() < 3 && !heartOnField && counter % 200 == 0) {
					heartX = randomX;
					heartY = randomY;
					heart = new HeartSpawner(heartX, heartY);
					heart.addHeart(heart);
					heartOnField = true;
				}

				// checks if soldier has picked up a heart
				for (int i = 0; i < HeartSpawner.hearts.size(); i++) {
					if (soldier.soldierBoundary().overlaps(HeartSpawner.getHeart(0).heartBoundary()) && heartOnField) {
						soldier.addHP();
						lifeBarHeart = new HeartSpawner(1565 - (35 * HeartSpawner.lifeBar.size() + 1), y - 35);
						lifeBarHeart.addLifeHeart(lifeBarHeart);
						HeartSpawner.hearts.remove(0);
						heartOnField = false;
					}
				}

				// grahpics cleared
				Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

				// draws everything
				batch.begin();
				batch.draw(backGround, 0, 0, 1600, 900);

				batch.draw(soldier.getImage(), soldier.soldierX(), soldier.soldierY(), soldierWidth, soldierLength);
				for (int i = 0; i < meme.listSize(); i++) {
					batch.draw(meme.get(i).getEnemyImage(), meme.get(i).getEnemyX(), meme.get(i).getEnemyY(), 50, 50);
				}
				if (heartOnField) {
					batch.draw(heart.getHeartImage(), heartX, heartY, 50, 50);
				}
				batch.draw(backBar, 0, 850, 1600, 100);
				for (int i = 0; i < HeartSpawner.lifeBar.size(); i++) {
					batch.draw(HeartSpawner.getLifeHeart(i).getHeartImage(), HeartSpawner.getLifeHeart(i).getX(),
							HeartSpawner.getLifeHeart(i).getY(), 35, 35);
				}
				for (int i = 0; i < soldier.bulletSize(); i++) {
					if (soldier.getBullet(i).getDirection() % 2 == 0) {
						batch.draw(soldier.getBullet(i).getBulletImage(), soldier.getBullet(i).getX(),
								soldier.getBullet(i).getY(), 30, 10);
					} else {
						batch.draw(soldier.getBullet(i).getBulletImage(), soldier.getBullet(i).getX(),
								soldier.getBullet(i).getY(), 10, 30);
					}

				}
				batch.end();
				uiStage.draw();
				counter++;
				uiStage.draw();
			} else {
				// when soldier dies displays dead screen with sound and then
				// exits
				if (deadCounter == 10) {
					sound.getShrekt();
				}
				if (deadCounter > 200) {
					Gdx.app.exit();
				}

				batch.begin();
				batch.draw(wastedMeme, 0, 0, 1600, 900);
				batch.end();
				deadCounter++;
			}
		}

	}

	// collision checker for bullets and memes
	void collision() {
		if (soldier.bulletSize() > 0) {
			for (int j = 0; j < meme.listSize() - 1; j++) {
				for (int i = 0; i < soldier.bulletSize(); i++) {
					if (soldier.getBullet(i).getDirection() == 1 || soldier.getBullet(i).getDirection() == 3) {
						Rectangle boundary = new Rectangle(soldier.getBullet(i).getX(), soldier.getBullet(i).getY(), 10,
								30);
						soldier.getBullet(i).setBoundary(boundary);
					} else if (soldier.getBullet(i).getDirection() == 2 || soldier.getBullet(i).getDirection() == 4) {
						Rectangle boundary = new Rectangle(soldier.getBullet(i).getX(), soldier.getBullet(i).getY(), 30,
								10);
						soldier.getBullet(i).setBoundary(boundary);
					}

					if (soldier.getBullet(i).bulletBoundary().overlaps(meme.get(j).getBoundary())) {
						soldier.removeBullet(i);
						meme.remove(j);
						sound.enemyDown();
						soldier.addScore();
						scoreLabel.setText("Score: " + soldier.getScore());

					}
				}
			}
		}

	}
}
