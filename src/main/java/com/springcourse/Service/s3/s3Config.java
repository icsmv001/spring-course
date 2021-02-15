package com.springcourse.Service.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
//import com.amazonaws.partitions.model.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {
	
	// valores das proprietadas do AWS
	@Value("${app.aws.s3.access-key}")
	private String accessKey;
	
	@Value("${app.aws.s3.secretKey}")
	private String secretKey;
	
	@Value("${app.aws.s3.bucketName}")
	private String bucketName;
	
	
	//metodo para retornar o cliente s3  - instancia do S3, tornando ele um tipo Bean, para que possa se acessado de outra classe dentro do service 
    @Bean(name= "awsS3")	
	public AmazonS3 getAmazonS3(){
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);
		
		// criando o client do S3
		AmazonS3 s3 = AmazonS3ClientBuilder
				.standard()
				.withCredentials(credentialsProvider)
				.withRegion(getRegion())
				.build();
		
		return s3;
		
				
				
				
		
	}
	
	// s3 region
    @Bean(name= "awsRegion")	
	public String getRegion() {
		return Region.getRegion(Regions.AF_SOUTH_1).getName();
		
	}
	
	
	
	// s3 bucket
    @Bean(name= "awsS3Backut")
    public String getBucket() {
    	return bucketName;
		
	}
	
	
	
 

}
