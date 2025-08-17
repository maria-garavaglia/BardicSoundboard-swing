package com.dearmariarenie.bardicsoundboard;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import org.springframework.stereotype.Component;

@Component
public class MainWindowView extends JFrame
{
    public MainWindowView()
    {
        createUi();
    }

    public void createUi()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        var helloWorldLabel = new JLabel("Hello World!");

        var layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
            .addComponent(helloWorldLabel)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(helloWorldLabel)
        );
    }

    public void showView()
    {
        pack();
        setVisible(true);
    }
}
