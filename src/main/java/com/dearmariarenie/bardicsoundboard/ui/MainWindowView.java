package com.dearmariarenie.bardicsoundboard.ui;

import com.dearmariarenie.bardicsoundboard.utils.Fmt;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainWindowView extends JFrame
{
    public enum UserAction
    {
        Load,
        Save,
        SaveAs,
        OpenPreferences,
        OpenAbout,
        AddSpell,
        EditSpell,
        RemoveSpell,
        Play,
        Stop,
        SetVolume
    }

    // menu items (as members to allow setting up actions)
    private final JMenuItem menuLoad = new JMenuItem("Load...");
    private final JMenuItem menuSave = new JMenuItem("Save");
    private final JMenuItem menuSaveAs = new JMenuItem("Save As...");
    private final JMenuItem menuPrefs = new JMenuItem("Preferences...");
    private final JMenuItem menuAbout = new JMenuItem("About...");

    // main window controls
    // TODO add character name view/edit
    private final JButton addSpellButton = new JButton("Add Spell");
    private final JButton editSpellButton = new JButton("Edit Spell");
    private final JButton removeSpellButton = new JButton("Remove Spell");
    private final JList<String> spellList = new JList<>();
    private final JLabel nowPlayingDisplay = new JLabel();
    private final JButton playButton = new JButton("Play");
    private final JButton stopButton = new JButton("Stop");
    private final JSlider volumeSlider = new JSlider();

    private MainWindowController controller;

    @Autowired
    public MainWindowView(MainWindowController controller)
    {
        createUi();
        this.controller = controller;
        this.controller.setView(this);
    }

    private void createUi()
    {
        setTitle("Bardic Soundboard");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(640, 480));
        setJMenuBar(createMenu());

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
            .addComponent(spellList,
                GroupLayout.PREFERRED_SIZE,
                GroupLayout.PREFERRED_SIZE,
                Short.MAX_VALUE
            )
            .addGroup(
                layout.createSequentialGroup()
                .addComponent(nowPlayingLabel)
                .addComponent(nowPlayingDisplay,
                    GroupLayout.PREFERRED_SIZE,
                    GroupLayout.PREFERRED_SIZE,
                    Short.MAX_VALUE
                )
            )
            .addGroup(
                layout.createSequentialGroup()
                .addComponent(playButton)
                .addComponent(stopButton)
                .addComponent(volumeSlider,
                    GroupLayout.PREFERRED_SIZE,
                    GroupLayout.PREFERRED_SIZE,
                    GroupLayout.PREFERRED_SIZE
                )
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
            .addComponent(spellList,
                GroupLayout.PREFERRED_SIZE,
                GroupLayout.PREFERRED_SIZE,
                Short.MAX_VALUE
            )
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

    private JMenuBar createMenu()
    {
        var menuBar = new JMenuBar();

        var fileMenu = new JMenu("File");
        fileMenu.add(menuLoad);
        fileMenu.add(menuSave);
        fileMenu.add(menuSaveAs);
        fileMenu.add(new JSeparator());
        fileMenu.add(menuPrefs);
        menuBar.add(fileMenu);

        var helpMenu = new JMenu("Help");
        helpMenu.add(menuAbout);
        menuBar.add(helpMenu);

        return menuBar;
    }

    public void showView()
    {
        pack();
        setVisible(true);
    }

    public void addUserActionListener(UserAction action, Runnable callback)
    {
        // TODO keyboard shortcuts
        switch(action)
        {
            case Load:
                menuLoad.addActionListener(evt -> callback.run());
                break;
            case Save:
                menuSave.addActionListener(evt -> callback.run());
                break;
            case SaveAs:
                menuSaveAs.addActionListener(evt -> callback.run());
                break;
            case OpenPreferences:
                menuPrefs.addActionListener(evt -> callback.run());
                break;
            case OpenAbout:
                menuAbout.addActionListener(evt -> callback.run());
                break;
            case AddSpell:
                addSpellButton.addActionListener(evt -> callback.run());
                break;
            case EditSpell:
                editSpellButton.addActionListener(evt -> callback.run());
                break;
            case RemoveSpell:
                removeSpellButton.addActionListener(evt -> callback.run());
                break;
            case Play:
                playButton.addActionListener(evt -> callback.run());
                // also play on spell double click
                spellList.addMouseListener(new MouseAdapter()
                {
                    public void mouseClicked(MouseEvent evt)
                    {
                        if (evt.getClickCount() == 2)
                        {
                            callback.run();
                        }
                    }
                });
                break;
            case Stop:
                stopButton.addActionListener(evt -> callback.run());
                break;
            case SetVolume:
                volumeSlider.addChangeListener(evt -> callback.run());
                break;
            default:
                throw new RuntimeException(
                    Fmt.format("Unhandled action {}", action)
                );
        }
    }

    public void setCharName(String name)
    {
        SwingUtilities.invokeLater(() -> setTitle(Fmt.format("Bardic Soundboard -- {}", name)));
    }

    public void setSpellList(List<String> spells)
    {
        SwingUtilities.invokeLater(
            () -> spellList.setListData(spells.toArray(new String[0]))
        );
    }

    public String getSelectedSpell()
    {
        return spellList.getSelectedValue();
    }

    public void setNowPlaying(String playing)
    {
        SwingUtilities.invokeLater(
            () -> nowPlayingDisplay.setText(playing)
        );
    }

    public int getVolume()
    {
        return volumeSlider.getValue();
    }
}
