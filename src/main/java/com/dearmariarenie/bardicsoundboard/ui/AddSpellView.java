package com.dearmariarenie.bardicsoundboard.ui;

import com.dearmariarenie.bardicsoundboard.utils.Fmt;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddSpellView extends JDialog
{
    public enum UserAction
    {
        Browse,
        Confirm,
        Cancel
    }

    private final JTextField nameField = new JTextField();
    private final JTextField fileField = new JTextField();
    private final JButton browseButton = new JButton("Browse...");
    private final JButton okButton = new JButton("OK");
    private final JButton cancelButton = new JButton("Cancel");

    public AddSpellView(Frame owner)
    {
        super(owner, "Add New Spell", true);
        createUi();
    }

    private void createUi()
    {
        var nameLabel = new JLabel("Name");
        var fileLabel = new JLabel("File");

        // cancel button should close the window, we don't need to involve the controller in that
        addUserActionListener(UserAction.Cancel, this::dispose);

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

    public void addUserActionListener(UserAction action, Runnable callback)
    {
        switch(action)
        {
            case Browse:
                browseButton.addActionListener(evt -> callback.run());
                break;
            case Confirm:
                okButton.addActionListener(evt -> callback.run());
                break;
            case Cancel:
                // add to both cancel button and dialog close
                cancelButton.addActionListener(evt -> callback.run());
                addWindowListener(new WindowAdapter()
                {
                    @Override
                    public void windowClosing(WindowEvent e)
                    {
                        callback.run();
                    }
                });
                break;
            default:
                throw new RuntimeException(
                    Fmt.format("Unhandled action {}", action)
                );
        }
    }

}
