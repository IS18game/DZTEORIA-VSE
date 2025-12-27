package com.example.threaddemo;

import com.example.threaddemo.service.ThreadService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThreadDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThreadDemoApplication.class, args);

        ThreadService service = new ThreadService();
        service.runThreads();
    }
}