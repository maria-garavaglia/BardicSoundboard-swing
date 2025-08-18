package com.dearmariarenie.bardicsoundboard.ui;

import com.dearmariarenie.bardicsoundboard.models.CharacterModel;
import com.dearmariarenie.bardicsoundboard.models.SpellModel;
import com.dearmariarenie.bardicsoundboard.ui.MainWindowView.UserAction;
import com.dearmariarenie.bardicsoundboard.utils.Fmt;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MainWindowController
{
    private static final Logger logger = LoggerFactory.getLogger(MainWindowController.class);

    private MainWindowView view;
    private CharacterModel characterModel = new CharacterModel();

    private final JFileChooser fileChooser = new JFileChooser();

    public MainWindowController()
    {
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setFileFilter(new FileNameExtensionFilter("JSON Files", "json"));
    }

    public void setView(MainWindowView view)
    {
        this.view = view;
        setupActions();
    }

    private void setupActions()
    {
        view.addUserActionListener(UserAction.Load, this::load);
        view.addUserActionListener(UserAction.Save, this::save);
        view.addUserActionListener(UserAction.SaveAs, this::saveAs);
        view.addUserActionListener(UserAction.OpenPreferences, this::openPreferences);
        view.addUserActionListener(UserAction.OpenAbout, this::openAbout);
        view.addUserActionListener(UserAction.AddSpell, this::addSpell);
        view.addUserActionListener(UserAction.EditSpell, this::editSpell);
        view.addUserActionListener(UserAction.RemoveSpell, this::removeSpell);
        view.addUserActionListener(UserAction.Play, this::play);
        view.addUserActionListener(UserAction.Stop, this::stop);
        view.addUserActionListener(UserAction.SetVolume, this::setVolume);
    }

    private void load()
    {
        var result = fileChooser.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            var file = fileChooser.getSelectedFile();
            logger.info("Opening {}", file.getName());

            try
            {
                characterModel = CharacterModel.load(file);
            }
            catch(IOException e)
            {
                logger.error("Failed to load file {}", file.getName(), e);
                JOptionPane.showMessageDialog(
                    view,
                    Fmt.format(
                        "Failed to load file '{}'. Check the logs for more information.",
                        file.getName()
                    ),
                    "Load Failed",
                    JOptionPane.ERROR_MESSAGE
                );
            }

            view.setCharName(characterModel.getName());
            view.setSpellList(
                characterModel.getSpells().stream()
                    .map(SpellModel::getName)
                    .toList()
            );
        }

    }

    private void save()
    {
        try
        {
            characterModel.save();
        }
        catch(RuntimeException e)
        {
            // file doesn't exist yet, try saveAs instead
            saveAs();
        }
        catch(IOException e)
        {
            logger.error(
                "Failed to save to file {}",
                characterModel.getSaveFile().getAbsolutePath(),
                e
            );
            JOptionPane.showMessageDialog(
                view,
                "Failed to save character. Check the logs for more information.",
                "Save Failed",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void saveAs()
    {
        var result = fileChooser.showSaveDialog(view);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            var file = fileChooser.getSelectedFile();
            try
            {
                characterModel.saveAs(file);
            }
            catch(IOException e)
            {
                logger.error("Failed to save file {}", file.getName(), e);
                JOptionPane.showMessageDialog(
                    view,
                    "Failed to save character. Check the logs for more information.",
                    "Save As Failed",
                    JOptionPane.ERROR_MESSAGE
                );
            }

            view.setCharName(characterModel.getName());
            view.setSpellList(
                characterModel.getSpells().stream()
                    .map(SpellModel::getName)
                    .toList()
            );
        }
    }

    private void openPreferences()
    {
        logger.info("openPreferences() called");
        // TODO implement
    }

    private void openAbout()
    {
        logger.info("openAbout() called");
        // TODO implement
    }

    private void addSpell()
    {
        logger.info("addSpell() called");
        // TODO implement
    }

    private void editSpell()
    {
        logger.info("editSpell() called");
        // TODO implement
    }

    private void removeSpell()
    {
        logger.info("removeSpell() called");
        // TODO implement
    }

    private void play()
    {
        logger.info("play() called");
        // TODO implement
    }

    private void stop()
    {
        logger.info("stop() called");
        // TODO implement
    }

    private void setVolume()
    {
        logger.info("setVolume() called");
        // TODO implement
    }
}
