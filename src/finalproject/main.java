package finalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {
	public static Stage mainStage;
    public static Scene mainScene;
	public void start(Stage mainStage) throws Exception {
		// TODO Auto-generated method stub
		main.mainStage=mainStage;
    	FXMLLoader loadder=new FXMLLoader(//呼叫fxml
    			getClass().getResource("mediatest.fxml")
    	);
    	Parent root=loadder.load();
    	mainScene=new Scene(root);
    	mainStage.setTitle("植物大戰僵屍");
        mainStage.setScene(mainScene);
        mainStage.show();
	}
	public static void main(String[] args) {
			// TODO Auto-generated method stub
		//	gameframe g = new gameframe();
			launch(args);
		}
}


