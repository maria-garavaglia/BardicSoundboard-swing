package com.dearmariarenie.bardicsoundboard;

import com.dearmariarenie.bardicsoundboard.ui.MainWindowView;
import com.formdev.flatlaf.FlatLightLaf;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

/**
 * Bootstrap class for application. Shouldn't do much besides initialize Spring
 * and show the main window.
 */
@SpringBootApplication
public class Application
{
    public static void main(String[] args)
    {
        // init FlatLaf
        // TODO allow for more flexible skinning
        FlatLightLaf.setup();

        // Spring typically starts headless, need to explicitly turn that off
        // so Swing can run
        new SpringApplicationBuilder(Application.class)
            .headless(false)
            .run(args)
        ;
    }

    @Bean
    ApplicationRunner appRunner(MainWindowView mainWindow)
    {
        return args -> mainWindow.showView();
    }
}
