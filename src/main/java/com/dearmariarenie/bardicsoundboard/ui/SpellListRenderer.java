package com.dearmariarenie.bardicsoundboard.ui;

import com.dearmariarenie.bardicsoundboard.models.SpellModel;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

public class SpellListRenderer extends JLabel implements ListCellRenderer<SpellModel>
{
    public SpellListRenderer()
    {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends SpellModel> list, SpellModel value, int index, boolean isSelected, boolean cellHasFocus)
    {
        setText(value.getName());

        // TODO not sure if there's more I need to specify to make it match FlatLaf
        if (isSelected)
        {
            setBackground(UIManager.getColor("List.selectionBackground"));
            setForeground(UIManager.getColor("List.selectionForeground"));
        }
        else
        {
            setBackground(UIManager.getColor("List.background"));
            setForeground(UIManager.getColor("List.foreground"));
        }

        return this;
    }
}
