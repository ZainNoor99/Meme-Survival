/*
 * Enemy Spawner Class
 */
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class EnemySpawner {
	//ArrayListe of memes
	private ArrayList<NormalEnemy> memes = new ArrayList<NormalEnemy>();
	
	//Normal enemy object
	private NormalEnemy meme;
	
	public void normalEnemySpawner(int x) {
		if (x == 1) {
			meme = new NormalEnemy(1);
			memes.add(meme);
		} else if (x == 2) {
			meme = new NormalEnemy(2);
			memes.add(meme);
		} else if (x == 3) {
			meme = new NormalEnemy(3);
			memes.add(meme);
		} else if (x == 4) {
			meme = new NormalEnemy(4);
			memes.add(meme);
		} 
	}
	
	public int listSize() {
		return memes.size();
	}
	
	public NormalEnemy get(int i) {
		return memes.get(i);
	}
	
	public void remove(int i) {
		memes.remove(i);
	}
}
