package TwitterData.PersistingTweets;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterObjectFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Example application that gets public timeline and store raw JSON strings into statuses/ directory..<br>
 *
 * @author Ashish Sinha -https://github.com/sinhaashish
 */
public final class SaveTweets {
    /**
     * Usage: java twitter4j.examples.json.SaveRawJSON
     *
     * @param args String[]
     */
    	public static void SaveTweetsToFile(){
    	
    	ConfigurationBuilder cf = new ConfigurationBuilder();
		cf.setDebugEnabled(true)
		.setOAuthConsumerKey(Constants.ConsumerKey)
		.setOAuthConsumerSecret(Constants.ConsumerSecret)
		.setOAuthAccessToken(Constants.OAuthAccessToken)
		.setOAuthAccessTokenSecret(Constants.OAuthAccessTokenSecret)
		.setJSONStoreEnabled(Constants.IsJSONStoreEnabled);
        Twitter twitter = new TwitterFactory(cf.build()).getInstance();
        System.out.println("Saving public timeline.");
        String finalJson ="";
        try {
             List<Status> statuses = twitter.getHomeTimeline();
             for (Status status : statuses) {
                 String rawJSON = TwitterObjectFactory.getRawJSON(status);
                 finalJson= finalJson+rawJSON;               
                 
            }
            
             storeJSON(finalJson, Constants.FileName);

        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Failed to store tweets: " + ioe.getMessage());
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
    }

    private static void storeJSON(String rawJSON, String fileName) throws IOException {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            fos = new FileOutputStream(fileName);
            osw = new OutputStreamWriter(fos, "UTF-8");
            bw = new BufferedWriter(osw);
            bw.write(rawJSON);
            bw.flush();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ignore) {
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException ignore) {
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException ignore) {
                }
            }
        }
    }
}

