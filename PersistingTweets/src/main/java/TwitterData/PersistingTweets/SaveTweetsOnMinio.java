package TwitterData.PersistingTweets;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.xmlpull.v1.XmlPullParserException;

public class SaveTweetsOnMinio {
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, IOException, XmlPullParserException {
		// TODO Auto-generated method stub
		SaveTweets.SaveTweetsToFile();
		FileUploader.uploadTweets();
	}

}
