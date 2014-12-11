package stopWatchMVC;

import javafx.application.Application;
import javafx.stage.Stage;

public class Starter extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Timer timer = new Timer(50);
		StopWatchController swc = new StopWatchController(timer);
		StopWatch sw1 = new StopWatch(timer, swc);		
		StopWatch sw2 = new StopWatch(timer, swc);
		StopWatch sw3 = new StopWatch(timer, swc);
		sw1.setX(200);
		sw1.setY(200);
		sw2.setX(300);
		sw2.setY(300);
		sw3.setX(400);
		sw3.setY(400);
	}
	
	public static void main(String[]args){
		launch(args);
	}
}
