package com.glw.jvm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * -Xmx32M -Xms32M
 */
@SpringBootApplication
public class JvmApplication {

    public static void main(String[] args) {
        SpringApplication.run(JvmApplication.class, args);
    }

}
