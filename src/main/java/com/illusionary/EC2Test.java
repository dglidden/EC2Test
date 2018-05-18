package com.illusionary;

import com.amazonaws.util.EC2MetadataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class EC2Test implements CommandLineRunner {

    private final static Logger LOGGER = LoggerFactory.getLogger(EC2Test.class);

    @Override
    public void run(String... args) {

        LOGGER.info("Getting EC2 MetaData");
        EC2MetadataUtils.InstanceInfo instanceInfo = EC2MetadataUtils.getInstanceInfo();

        String accountId = instanceInfo.getAccountId();
        String architecture = instanceInfo.getArchitecture();
        String availabilityZone = instanceInfo.getAvailabilityZone();
        String imageId = instanceInfo.getImageId();
        String instanceId = instanceInfo.getInstanceId();
        String instanceType = instanceInfo.getInstanceType();
        String kernelId = instanceInfo.getKernelId();
        String privateAddress = instanceInfo.getPrivateIp();
        String region = instanceInfo.getRegion();
        String version = instanceInfo.getVersion();

        LOGGER.info("Instance Info");
        LOGGER.info("Account ID: {}", accountId);
        LOGGER.info("Architecture: {}", architecture);
        LOGGER.info("Availability Zone: {}", availabilityZone);
        LOGGER.info("Image ID: {}", imageId);
        LOGGER.info("Instance ID: {}", instanceId);
        LOGGER.info("Instance Type: {}", instanceType);
        LOGGER.info("Kernel ID: {}", kernelId);
        LOGGER.info("Private Address: {}", privateAddress);
        LOGGER.info("Region: {}", region);
        LOGGER.info("Version: {}", version);

        Map<String, EC2MetadataUtils.IAMSecurityCredential> iam = EC2MetadataUtils.getIAMSecurityCredentials();
        EC2MetadataUtils.IAMSecurityCredential sec = iam.get("RLEADEVCE");
        if(sec != null) {
            LOGGER.info("IAM Info");
            LOGGER.info("AccessKeyId: {}", sec.accessKeyId);
            LOGGER.info("Code: {}", sec.code);
            LOGGER.info("Expiration: {}", sec.expiration);
            LOGGER.info("Last Updated: {}", sec.lastUpdated);
            LOGGER.info("Message: {}", sec.message);
            LOGGER.info("Secret Access Key: {}", sec.secretAccessKey);
            LOGGER.info("Token: {}", sec.token);
            LOGGER.info("Type: {}", sec.type);
        } else {
            LOGGER.info("No IAM Information Available");
        }
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(EC2Test.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}

