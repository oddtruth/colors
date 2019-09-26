package colors;

import twitter4j.conf.*;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import twitter4j.*;
import twitter4j.auth.*;
import twitter4j.api.*;

public class TwitterColors {
	
	static final String CONSUMER_KEY = "uG5jgrYDk3ZkbCDqmelKMLiWC";
	static final String CONSUMER_SECRET = "iQAxE6RZ9a1Gy0Umb48VIdXXdCYWhjGRWlvApBXPXhMK5VVR4w";
	static final String ACCESS_TOKEN = "1148021484180426752-LepkuPW5IpSGueukddr7HnTNhYCsmw";
	static final String ACCESS_TOKEN_SECRET = "mfjSpxAyMJPG0yNMMAdaAOV570xqEzLOviAadoO7CcYBN";
	
	private static String imagePath = "image.png";
	
	public static Twitter twitter;
	
	public static Drawing draw = new Drawing();
	
	public static Timer t = new Timer();
	
	public static TimerTask daily = new TimerTask() {
		@Override 
		public void run() {
			try {
				buildImage(imagePath);
				tweetMedia(imagePath);
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

	public static void main(String[] args) throws TwitterException {
		// TODO Auto-generated method stub
		setup();
		//buildImage(imagePath);
		//tweetMedia(imagePath);
		t.schedule(daily, 0l, 1000*60*60*24);
	}
	
	public static void setup() {
		ConfigurationBuilder cb = new ConfigurationBuilder();

		cb.setOAuthConsumerKey(CONSUMER_KEY);
		cb.setOAuthConsumerSecret(CONSUMER_SECRET);
		cb.setOAuthAccessToken(ACCESS_TOKEN);
		cb.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
		
		TwitterFactory tf = new TwitterFactory(cb.build());
		
		twitter = tf.getInstance();
	}
	
	public static void tweet(String text) throws TwitterException {
		
		twitter.updateStatus(text);
		
	}
	
	public static void buildImage(String path) {
			draw.saveImage(path);
	}
	
	
	public static void tweetMedia(String path) throws TwitterException {
		
		File imageFile = new File(path);
		
		StatusUpdate stat = new StatusUpdate("");
		stat.setMedia(imageFile);
		
		
		twitter.updateStatus(stat);
		
	}
	

}
