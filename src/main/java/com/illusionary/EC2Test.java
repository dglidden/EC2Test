package com.illusionary;

import com.amazonaws.util.EC2MetadataUtils;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class EC2Test implements CommandLineRunner {

    private final static Logger LOGGER = Logger.getLogger(EC2Test.class.getName());

    @Override
    public void run(String... args) throws Exception {

        LOGGER.log(Level.INFO, "Getting EC2 MetaData");
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

        LOGGER.log(Level.INFO, "Account ID: {0}", accountId);
        LOGGER.log(Level.INFO, "Architecture: {0}", architecture);
        LOGGER.log(Level.INFO, "Availability Zone: {0}", availabilityZone);
        LOGGER.log(Level.INFO, "Image ID: {0}", imageId);
        LOGGER.log(Level.INFO, "Instance ID: {0}", instanceId);
        LOGGER.log(Level.INFO, "Instance Type: {0}", instanceType);
        LOGGER.log(Level.INFO, "Kernel ID: {0}", kernelId);
        LOGGER.log(Level.INFO, "Private Address: {0}", privateAddress);
        LOGGER.log(Level.INFO, "Region: {0}", region);
        LOGGER.log(Level.INFO, "Version: {0}", version);
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(EC2Test.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}

