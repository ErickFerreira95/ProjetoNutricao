package com.mycompany.view;

import com.mycompany.util.dao.AlimentoDao;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class PanelEditorRefeicao5 extends AbstractCellEditor implements TableCellEditor {
    
    private JPanel painel;
    private JButton btnExcluir;
    private JButton btnExcluir2;
    private RefeicoesView view;
    JTable tblTabela;

    public PanelEditorRefeicao5(JTable tblTabela, RefeicoesView view) {

        this.tblTabela = tblTabela;
        this.view = view;

        painel = new JPanel(new GridBagLayout());
        //btnExcluir = new JButton(new ImageIcon("src/images/lixeira.png"));
        btnExcluir = new JButton("Excluir");
        btnExcluir.setBackground(new Color(255, 99, 71));
        btnExcluir.setOpaque(true);
        btnExcluir.setBorderPainted(false);
        btnExcluir.setToolTipText("Excluir");
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setCursor(new Cursor(Cursor.HAND_CURSOR));

        GridBagConstraints posicaoBtnExcluir = new GridBagConstraints();
        posicaoBtnExcluir.gridx = 1;
        posicaoBtnExcluir.gridy = 0;
        posicaoBtnExcluir.weightx = 0;
        posicaoBtnExcluir.weighty = 0; // ← isso força ele a ficar no topo
        posicaoBtnExcluir.anchor = GridBagConstraints.CENTER;
        posicaoBtnExcluir.fill = GridBagConstraints.NONE;
        posicaoBtnExcluir.insets = new Insets(0, 0, 0, 0); // margem superior
        painel.add(btnExcluir, posicaoBtnExcluir);

        btnExcluir.addActionListener(e -> {
            JTable tabela = tblTabela;
            AlimentoDao dao = new AlimentoDao();
            RefeicoesView refeicoes = new RefeicoesView();
            int row = tabela.getEditingRow();

            if (row != -1) {
                int id = (int) tabela.getValueAt(row, 0);

                String alimento = (String) tabela.getValueAt(row, 2);

                int confirmar = JOptionPane.showConfirmDialog(
                        tabela,
                        "Deseja excluir o item " + alimento + "?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirmar == JOptionPane.YES_OPTION) {
                    // Pare a edição ANTES de remover a linha

                    fireEditingStopped();
                    boolean deletado = dao.deletarAlimentoRefeicao5(id);

                    if (deletado) {
                        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
                        modelo.removeRow(row);
                        refeicoes.atualizarTotais(modelo);

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
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        // Se for uma das duas últimas linhas, retorna painel vazio
        if (row >= table.getRowCount() - 2) {
            return new JPanel();
        }
        return painel;
    }

    @Override
    public Object getCellEditorValue() {
        return "";
    }
}
