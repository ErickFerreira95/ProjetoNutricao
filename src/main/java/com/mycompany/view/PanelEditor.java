package com.mycompany.view;

import com.mycompany.util.dao.AlimentoDao;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class PanelEditor extends AbstractCellEditor implements TableCellEditor{
    
    private JPanel painel;
    private JButton btnEditar;
    private JButton btnExcluir;

    public PanelEditor(JTable table) {
        painel = new JPanel(new GridBagLayout());
        btnEditar = new JButton(new ImageIcon("src/images/lapis.png"));
        btnEditar.setToolTipText("Editar");
        btnExcluir = new JButton(new ImageIcon("src/images/lixeira.png"));
        btnExcluir.setToolTipText("Excluir");

        GridBagConstraints posicaoBtnEditar = new GridBagConstraints();
        posicaoBtnEditar.gridx = 0;
        posicaoBtnEditar.gridy = 0;
        posicaoBtnEditar.weightx = 0;
        posicaoBtnEditar.weighty = 0; // ← isso força ele a ficar no topo
        posicaoBtnEditar.anchor = GridBagConstraints.CENTER;
        posicaoBtnEditar.fill = GridBagConstraints.NONE;
        posicaoBtnEditar.insets = new Insets(0, 0, 0, 15); // margem superior
        painel.add(btnEditar, posicaoBtnEditar);

        GridBagConstraints posicaoBtnExcluir = new GridBagConstraints();
        posicaoBtnExcluir.gridx = 1;
        posicaoBtnExcluir.gridy = 0;
        posicaoBtnExcluir.weightx = 0;
        posicaoBtnExcluir.weighty = 0; // ← isso força ele a ficar no topo
        posicaoBtnExcluir.anchor = GridBagConstraints.CENTER;
        posicaoBtnExcluir.fill = GridBagConstraints.NONE;
        posicaoBtnExcluir.insets = new Insets(0, 0, 0, 0); // margem superior
        painel.add(btnExcluir, posicaoBtnExcluir);

        btnEditar.addActionListener(e -> {
            int row = table.getEditingRow();
            String nome = table.getValueAt(row, 0).toString();
            JOptionPane.showMessageDialog(table, "Editar " + nome);
            fireEditingStopped();
        });

        btnExcluir.addActionListener(e -> {
            JTable tabela = table;
            AlimentoDao dao = new AlimentoDao();
            int row = tabela.getEditingRow();

            if (row != -1) {
                int id = (int) tabela.getValueAt(row, 0);

                int confirmar = JOptionPane.showConfirmDialog(
                        tabela,
                        "Deseja excluir o item com ID " + id + "?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirmar == JOptionPane.YES_OPTION) {
                    // Pare a edição ANTES de remover a linha
                    fireEditingStopped();

                    boolean deletado = dao.deletarAlimento(id);
                    if (deletado) {
                        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
                        modelo.removeRow(row);

                        // Evita renderização indevida após a remoção da última linha
                        if (modelo.getRowCount() == 0) {
                            tabela.clearSelection();
                        }

                        JOptionPane.showMessageDialog(tabela, "Item deletado com sucesso.");
                    } else {
                        JOptionPane.showMessageDialog(tabela, "Erro ao deletar do banco de dados.");
                    }
                } else {
                    fireEditingStopped(); // também necessário se cancelado
                }
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column
    ) {
        return painel;
    }

    @Override
    public Object getCellEditorValue() {
        return "";
    }
}
