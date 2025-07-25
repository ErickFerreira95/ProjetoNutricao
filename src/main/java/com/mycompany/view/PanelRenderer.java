package com.mycompany.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class PanelRenderer extends JPanel implements TableCellRenderer {

    public PanelRenderer() {
        setLayout(new GridBagLayout());

        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");

        btnEditar.setOpaque(true);
        btnEditar.setBackground(new Color(0, 191, 255));
        btnEditar.setBorderPainted(false);
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setToolTipText("Editar");
        btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnExcluir.setBackground(new Color(255, 99, 71));
        btnExcluir.setOpaque(true);
        btnExcluir.setBorderPainted(false);
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setToolTipText("Excluir");
        btnExcluir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        GridBagConstraints posicaoBtnEditar = new GridBagConstraints();
        posicaoBtnEditar.gridx = 0;
        posicaoBtnEditar.gridy = 0;
        posicaoBtnEditar.weightx = 0;
        posicaoBtnEditar.weighty = 0;
        posicaoBtnEditar.anchor = GridBagConstraints.CENTER;
        posicaoBtnEditar.fill = GridBagConstraints.NONE;
        posicaoBtnEditar.insets = new Insets(0, 0, 0, 5);
        add(btnEditar, posicaoBtnEditar);

        GridBagConstraints posicaoBtnExcluir = new GridBagConstraints();
        posicaoBtnExcluir.gridx = 1;
        posicaoBtnExcluir.gridy = 0;
        posicaoBtnExcluir.weightx = 0;
        posicaoBtnExcluir.weighty = 0;
        posicaoBtnExcluir.anchor = GridBagConstraints.CENTER;
        posicaoBtnExcluir.fill = GridBagConstraints.NONE;
        posicaoBtnExcluir.insets = new Insets(0, 0, 0, 0);
        add(btnExcluir, posicaoBtnExcluir);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}
