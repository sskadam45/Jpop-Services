package com.jpop.awss3dynamodbtest;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.List;

@SpringBootApplication
@Slf4j
public class AwsS3DynamodbTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsS3DynamodbTestApplication.class, args);
		AWSCredentials credentials = new BasicAWSCredentials(
				"AKIAXJIFHDPVOEJB4B4A",
				"AWppXUO8NchR7HMsveLmkJu+G/Hf2eF0liPaAha6"
		);
		AmazonS3 s3client = AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.AP_SOUTH_1)
				.build();

		createBucket(s3client);
		listBucket(s3client);
		uploadObjectsToS3(s3client);
		listingObjectsOfBucket(s3client);
		//deleteBucket(s3client)
	}

	//Let's create a bucket:
	private static void createBucket(AmazonS3 s3client) {
		String bucketName = "once4sk-bucket";
		if (s3client.doesBucketExist(bucketName)) {
			log.info("Bucket name is not available."
					+ " Try again with a different Bucket name.");
			return;
		}
		s3client.createBucket(bucketName);
		log.info("Bucket "+bucketName
				+ " is created successfully...");
	}

	private static void listBucket(AmazonS3 s3client) {
		List<Bucket> buckets = s3client.listBuckets();
		for (Bucket bucket : buckets) {
			System.out.println(bucket.getName());
		}
	}

	private static void deleteBucket(AmazonS3 s3client){
		String bucketName = "once4sk-bucket";
		try {
			s3client.deleteBucket(bucketName);
		} catch (AmazonServiceException e) {
			System.err.println(e.getErrorMessage());
			return;
		}
	}

	private static void uploadObjectsToS3(AmazonS3 s3client){
		String bucketName = "once4sk-bucket";
		s3client.putObject(
				bucketName,
				"Documents/Mission-2021.pdf",
				new File("C:/Users/Sagar_Kadam/Documents/Mission-2021.pdf")
		);
	}

	private static void listingObjectsOfBucket(AmazonS3 s3client){
		String bucketName = "once4sk-bucket";
		ObjectListing objectListing = s3client.listObjects(bucketName);
		for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
			log.info(os.getKey());
		}
	}

	private static void deleteBucketObjects(AmazonS3 s3client){
		String bucketName = "once4sk-bucket";
		s3client.deleteObject(bucketName,"week2/~$JPoP-6_S3_DynamoDB.pptx");
	}
}