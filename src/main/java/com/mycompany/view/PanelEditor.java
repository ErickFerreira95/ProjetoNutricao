package com.mycompany.view;

import com.mycompany.model.Alimento;
import com.mycompany.util.dao.AlimentoDao;
import java.awt.Color;
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

public class PanelEditor extends AbstractCellEditor implements TableCellEditor {

    private JPanel painel;
    private JButton btnEditar;
    private JButton btnExcluir;
    private MainView view;
    JTable table;

    public PanelEditor(JTable table, MainView view) {
        
        this.table = table;
        this.view = view;
        painel = new JPanel(new GridBagLayout());
        //btnEditar = new JButton(new ImageIcon("src/images/lapis.png"));
        btnEditar = new JButton("Editar");
        btnEditar.setToolTipText("Editar");
        btnEditar.setBackground(new Color(0, 191, 255));
        btnEditar.setOpaque(true);
        btnEditar.setBorderPainted(false);
        btnEditar.setForeground(Color.WHITE);
        //btnExcluir = new JButton(new ImageIcon("src/images/lixeira.png"));
        btnExcluir = new JButton("Excluir");
        btnExcluir.setBackground(new Color(255, 99, 71));
        btnExcluir.setOpaque(true);
        btnExcluir.setBorderPainted(false);
        btnExcluir.setToolTipText("Excluir");
        btnExcluir.setForeground(Color.WHITE);

        GridBagConstraints posicaoBtnEditar = new GridBagConstraints();
        posicaoBtnEditar.gridx = 0;
        posicaoBtnEditar.gridy = 0;
        posicaoBtnEditar.weightx = 0;
        posicaoBtnEditar.weighty = 0; // ← isso força ele a ficar no topo
        posicaoBtnEditar.anchor = GridBagConstraints.CENTER;
        posicaoBtnEditar.fill = GridBagConstraints.NONE;
        posicaoBtnEditar.insets = new Insets(0, 0, 0, 5); // margem superior
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
            
            int row = table.getSelectedRow();

            if (row >= 0) {
                Alimento alimento = new Alimento();
                alimento.setId(Integer.parseInt(table.getValueAt(row, 0).toString()));
                alimento.setNomeAlimento(table.getValueAt(row, 1).toString());
                alimento.setQuantidade(table.getValueAt(row, 2).toString());
                alimento.setProteina(table.getValueAt(row, 3).toString());
                alimento.setCarboidrato(table.getValueAt(row, 4).toString());
                alimento.setGordura(table.getValueAt(row, 5).toString());
                alimento.setKcal(table.getValueAt(row, 6).toString());

                // Abre o novo JFrame com os dados da linha
                EditarAlimentoView telaEditar = new EditarAlimentoView();
                telaEditar.getAlimento(alimento); // ← envia os dados para os campos da tela
                telaEditar.editarAlimentoView();
                
                view.dispose();
            }
            fireEditingStopped(); // para encerrar o modo de edição da célula
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
