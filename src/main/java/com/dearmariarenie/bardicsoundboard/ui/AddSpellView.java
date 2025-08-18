package com.dearmariarenie.bardicsoundboard.ui;

import java.awt.Frame;
import java.io.File;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * UI to allow addition of new spells.
 *
 * Note: this view doesn't have a dedicated controller, due to its simplicity.
 * The parent view's controller should be able to handle any necessary actions.
 */
public class AddSpellView extends JDialog
{
    private final JTextField nameField = new JTextField();
    private final JTextField fileField = new JTextField();
    private final JButton browseButton = new JButton("Browse...");
    private final JButton okButton = new JButton("OK");
    private final JButton cancelButton = new JButton("Cancel");

    private final JFileChooser fileChooser = new JFileChooser();

    public AddSpellView(Frame owner)
    {
        super(owner, "Add New Spell", true);
        createUi();
        fileChooser.setCurrentDirectory(new File("Audio"));
    }

    private void createUi()
    {
        var nameLabel = new JLabel("Name");
        var fileLabel = new JLabel("File");

        browseButton.addActionListener(evt -> {
            var result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                setFileName(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });
        okButton.addActionListener(evt -> dispose());
        cancelButton.addActionListener(evt -> dispose());

        var layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.TRAILING)
            .addGroup(
                layout.createSequentialGroup()
                .addGroup(
                    layout.createParallelGroup()
                    .addComponent(nameLabel)
                    .addComponent(fileLabel)
                )
                .addGroup(
                    layout.createParallelGroup()
                    .addComponent(nameField, GroupLayout.PREFERRED_SIZE, 250, Short.MAX_VALUE)
                    .addGroup(
                        layout.createSequentialGroup()
                        .addComponent(fileField)
                        .addComponent(browseButton)
                    )
                )
            )
            .addGroup(
                layout.createSequentialGroup()
                .addComponent(okButton)
                .addComponent(cancelButton)
            )
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addGroup(
                layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(nameLabel)
                .addComponent(nameField)
            )
            .addGroup(
                layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(fileLabel)
                .addComponent(fileField)
                .addComponent(browseButton)
            )
            .addGap(10, 10, Short.MAX_VALUE)
            .addGroup(
                layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(okButton)
                .addComponent(cancelButton)
            )
        );
    }

    public void showView()
    {
        pack();
        setVisible(true);
    }

    public void addConfirmCallback(Runnable callback)
    {
        okButton.addActionListener(evt -> callback.run());
    }

    public String getSpellName()
    {
        return nameField.getText();
    }

    public void setSpellName(String name)
    {
        SwingUtilities.invokeLater(() -> nameField.setText(name));
    }

    public String getFileName()
    {
        return fileField.getText();
    }

    public void setFileName(String file)
    {
        SwingUtilities.invokeLater(() -> fileField.setText(file));
    }

}
