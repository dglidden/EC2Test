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
        LOGGER.info("Account ID: {0}", accountId);
        LOGGER.info("Architecture: {0}", architecture);
        LOGGER.info("Availability Zone: {0}", availabilityZone);
        LOGGER.info("Image ID: {0}", imageId);
        LOGGER.info("Instance ID: {0}", instanceId);
        LOGGER.info("Instance Type: {0}", instanceType);
        LOGGER.info("Kernel ID: {0}", kernelId);
        LOGGER.info("Private Address: {0}", privateAddress);
        LOGGER.info("Region: {0}", region);
        LOGGER.info("Version: {0}", version);

        Map<String, EC2MetadataUtils.IAMSecurityCredential> iam = EC2MetadataUtils.getIAMSecurityCredentials();
        EC2MetadataUtils.IAMSecurityCredential sec = iam.get("RLEADEVCE");
        if(sec != null) {
            LOGGER.info("IAM Info");
            LOGGER.info("AccessKeyId: {0}", sec.accessKeyId);
            LOGGER.info("Code: {0}", sec.code);
            LOGGER.info("Expiration: {0}", sec.expiration);
            LOGGER.info("Last Updated: {0}", sec.lastUpdated);
            LOGGER.info("Message: {0}", sec.message);
            LOGGER.info("Secret Access Key: {0}", sec.secretAccessKey);
            LOGGER.info("Token: {0}", sec.token);
            LOGGER.info("Type: {0}", sec.type);
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

