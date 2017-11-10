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
        String instanceId = EC2MetadataUtils.getInstanceId();
        String privateAddress = EC2MetadataUtils.getPrivateIpAddress();
        String instanceType = EC2MetadataUtils.getInstanceType();

        LOGGER.log(Level.INFO, "EC2: {0}, {1}, {2}", new Object[]{instanceId, privateAddress, instanceType});

        EC2MetadataUtils.InstanceInfo instanceInfo = EC2MetadataUtils.getInstanceInfo();
        LOGGER.log(Level.INFO, "Instance Info: {0}", instanceInfo);
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(EC2Test.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}

