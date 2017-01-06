package org.dronezone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Main entry point for the spring boot application
 *
 * @author Nicholas Drone
 * @since 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GarageDoorOpenerApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(GarageDoorOpenerApplication.class, args);
    }
}
