package finalproject;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
public class mediatestcontrol implements Initializable{
	@FXML
	private MediaView media;
//	@FXML
//	private Button skip;
	private MediaPlayer mediaplayer;
	private int ts=0;
	private static final String mu="開場動畫.mp4";
	@FXML
    void onSkipPressed(ActionEvent event) {
		mediaplayer.pause();
		new gameframe();
		main.mainStage.close();
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		mediaplayer = new MediaPlayer(new Media(this .getClass().getResource(mu).toExternalForm()));
		mediaplayer.setAutoPlay(true);
		media.setMediaPlayer(mediaplayer);
		
	}
}


