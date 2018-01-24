package TwitterData.PersistingTweets;
import io.minio.MinioClient;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.xmlpull.v1.XmlPullParserException;

public class FileUploader {
  public static void uploadTweets() throws NoSuchAlgorithmException, IOException, InvalidKeyException, XmlPullParserException {
    try {
      // Create a minioClient with the Minio Server name, Port, Access key and Secret key.
      MinioClient minioClient = new MinioClient(Constants.MinioServerName, Constants.MinioAccessKey, Constants.MinioSecretKey);
      // Check if the bucket already exists.
      boolean isExist = minioClient.bucketExists(Constants.MinioBucketName);
      if(isExist) {
        System.out.println("Bucket already exists.");
      } else {
        // Make a new bucket to hold the json files.
        minioClient.makeBucket(Constants.MinioBucketName);
      }
      // Upload the json file
      minioClient.putObject(Constants.MinioBucketName,"twitterData.json", Constants.FileName);
      
    } catch(Exception e) {
      System.out.println("Error occurred: " + e);
    }
  }
}