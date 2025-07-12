package com.mycompany.view;

import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class PanelRenderer extends JPanel implements TableCellRenderer {
    public PanelRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            add(new JButton(new ImageIcon("src/images/lapis.png"))).setEnabled(true);
            add(new JButton(new ImageIcon("src/images/lixeira.png"))).setEnabled(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
}
