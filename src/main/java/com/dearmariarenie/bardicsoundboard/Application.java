package com.dearmariarenie.bardicsoundboard;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application
{
    public static void main(String[] args)
    {
        new SpringApplicationBuilder(Application.class)
            .headless(false)
            .run(args)
        ;
    }

    @Bean
    ApplicationRunner appRunner(MainWindowView mainWindow)
    {
        return args -> {
            mainWindow.pack();
            mainWindow.setVisible(true);
        };
    }
}
