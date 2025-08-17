package com.dearmariarenie.bardicsoundboard.ui;

import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import org.springframework.stereotype.Component;

@Component
public class MainWindowView extends JFrame
{
    private final JButton addSpellButton = new JButton("Add Spell");
    private final JButton editSpellButton = new JButton("Edit Spell");
    private final JButton removeSpellButton = new JButton("Remove Spell");
    private final JList<String> spellList = new JList<>();
    private final JLabel nowPlayingDisplay = new JLabel();
    private final JButton playButton = new JButton("Play");
    private final JButton stopButton = new JButton("Stop");
    private final JSlider volumeSlider = new JSlider();

    public MainWindowView()
    {
        createUi();
    }

    public void createUi()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(640, 480));

        var nowPlayingLabel = new JLabel("Now Playing:");

        var layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup()
            .addGroup(
                layout.createSequentialGroup()
                .addComponent(addSpellButton)
                .addComponent(editSpellButton)
                .addComponent(removeSpellButton)
            )
            .addComponent(spellList, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
            .addGroup(
                layout.createSequentialGroup()
                .addComponent(nowPlayingLabel)
                .addComponent(nowPlayingDisplay, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
            )
            .addGroup(
                layout.createSequentialGroup()
                .addComponent(playButton)
                .addComponent(stopButton)
                .addComponent(volumeSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addGroup(
                layout.createParallelGroup()
                .addComponent(addSpellButton)
                .addComponent(editSpellButton)
                .addComponent(removeSpellButton)
            )
            .addComponent(spellList, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
            .addGroup(
                layout.createParallelGroup()
                .addComponent(nowPlayingLabel)
                .addComponent(nowPlayingDisplay)
            )
            .addGroup(
                layout.createParallelGroup(Alignment.CENTER)
                .addComponent(playButton)
                .addComponent(stopButton)
                .addComponent(volumeSlider)
            )
        );
    }

    public void showView()
    {
        pack();
        setVisible(true);
    }
}
