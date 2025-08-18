package com.dearmariarenie.bardicsoundboard.ui;

import com.dearmariarenie.bardicsoundboard.models.CharacterModel;
import com.dearmariarenie.bardicsoundboard.models.SpellModel;
import com.dearmariarenie.bardicsoundboard.ui.MainWindowView.UserAction;
import com.dearmariarenie.bardicsoundboard.utils.Fmt;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
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
    private MediaPlayer audioPlayer;

    public MainWindowController()
    {
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setFileFilter(new FileNameExtensionFilter("JSON Files", "json"));

        // Start the JavaFX thread so audio can work
        Platform.startup(() -> {});
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
        var spellName = view.getSelectedSpell();
        if (spellName == null)
        {
            // nothing selected
            return;
        }
        
        var spell = characterModel.findSpell(view.getSelectedSpell()).orElseThrow();
        var file = new File(Paths.get("Audio", spell.getFile()).toString());

        logger.info("Playing audio for {}: {}", spell.getName(), file.toURI().toString());
        try
        {
            Media media = new Media(file.toURI().toString());

            if(audioPlayer != null && audioPlayer.getStatus() == MediaPlayer.Status.PLAYING)
            {
                audioPlayer.stop();
            }

            audioPlayer = new MediaPlayer(media);
            audioPlayer.setOnError(() ->
            {
                throw audioPlayer.getError();
            });

            audioPlayer.setVolume(view.getVolume() / 100.0);
            audioPlayer.play();
            view.setNowPlaying(spell.getName());
        }
        catch(MediaException e)
        {
            logger.error("Could not play audio file {}", file.getName(), e);
            JOptionPane.showMessageDialog(
                view,
                Fmt.format("Could not play audio for {}. Check the logs for more information.", spell.getName()),
                "Play failed",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void stop()
    {
        if(audioPlayer != null && audioPlayer.getStatus() == MediaPlayer.Status.PLAYING)
        {
            audioPlayer.stop();
        }
    }

    private void setVolume()
    {
        if(audioPlayer != null)
        {
            audioPlayer.setVolume(view.getVolume() / 100.0);
        }
    }
}
