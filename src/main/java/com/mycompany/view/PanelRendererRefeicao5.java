package com.mycompany.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class PanelRendererRefeicao5 extends JPanel implements TableCellRenderer {
    
    public PanelRendererRefeicao5() {
        setLayout(new GridBagLayout());
        
        JButton btnExcluir = new JButton("Excluir");

        btnExcluir.setBackground(new Color(255, 99, 71));
        btnExcluir.setOpaque(true);
        btnExcluir.setBorderPainted(false);
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setToolTipText("Excluir");
        btnExcluir.setCursor(new Cursor(Cursor.HAND_CURSOR));

        GridBagConstraints posicaoBtnExcluir = new GridBagConstraints();
        posicaoBtnExcluir.gridx = 1;
        posicaoBtnExcluir.gridy = 0;
        posicaoBtnExcluir.weightx = 0;
        posicaoBtnExcluir.weighty = 0; // ← isso força ele a ficar no topo
        posicaoBtnExcluir.anchor = GridBagConstraints.CENTER;
        posicaoBtnExcluir.fill = GridBagConstraints.NONE;
        posicaoBtnExcluir.insets = new Insets(0, 0, 0, 0); // margem superior
        add(btnExcluir, posicaoBtnExcluir);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (row >= table.getRowCount() - 2) {
            return new JPanel(); // painel vazio
        }
        return this;
    }
}
