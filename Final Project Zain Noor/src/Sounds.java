/*
 * Class that plays/handles sounds
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import com.badlogic.gdx.*;

public class Sounds {
	//loads sounds
	private Sound getShrekt = Gdx.audio.newSound(Gdx.files.internal("sounds/getshrekt.mp3"));
	private Sound starting = Gdx.audio.newSound(Gdx.files.internal("sounds/420.wav"));
	private Sound enemyDown = Gdx.audio.newSound(Gdx.files.internal("sounds/pew.mp3"));
	
	public void getShrekt() {
		getShrekt.play();
	}
	
	public void starting() {
		starting.play(0.6f);
	}
	
	public void enemyDown() {
		enemyDown.play(0.1f);
	}
}
