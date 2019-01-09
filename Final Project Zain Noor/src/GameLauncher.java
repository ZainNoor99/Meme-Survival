/*
 * Game Launcher Class
 */
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class GameLauncher {

	public static void main(String[] args) {
		//Launches game and sets non resizable screen config
		Manager myGame= new Manager();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1600;
		config.height = 900;
		config.resizable = false;
        new LwjglApplication(myGame, config);

	}

}
