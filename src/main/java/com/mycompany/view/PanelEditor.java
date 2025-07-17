package com.mycompany.view;

import com.mycompany.model.Alimento;
import com.mycompany.model.User;
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
    JTable tblTabela;
    private User usuarioLogado;

    public PanelEditor(JTable tblTabela, MainView view, User usuario) {
        
        this.tblTabela = tblTabela;
        this.view = view;
        this.usuarioLogado = usuario;
        
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
            
            int row = tblTabela.getSelectedRow();

            if (row >= 0) {
                Alimento alimento = new Alimento();
                alimento.setId(Integer.parseInt(tblTabela.getValueAt(row, 0).toString()));
                alimento.setNomeAlimento(tblTabela.getValueAt(row, 1).toString());
                alimento.setQuantidade(tblTabela.getValueAt(row, 2).toString());
                alimento.setProteina(tblTabela.getValueAt(row, 3).toString());
                alimento.setCarboidrato(tblTabela.getValueAt(row, 4).toString());
                alimento.setGordura(tblTabela.getValueAt(row, 5).toString());
                alimento.setKcal(tblTabela.getValueAt(row, 6).toString());

                // Abre o novo JFrame com os dados da linha
                EditarAlimentoView telaEditar = new EditarAlimentoView(usuarioLogado);
                telaEditar.getAlimento(alimento); // ← envia os dados para os campos da tela
                telaEditar.editarAlimentoView();
                
                view.dispose();
            }
            fireEditingStopped(); // para encerrar o modo de edição da célula
        });

        btnExcluir.addActionListener(e -> {
            JTable tabela = tblTabela;
            AlimentoDao dao = new AlimentoDao();
            int row = tabela.getEditingRow();

            if (row != -1) {
                int id = (int) tabela.getValueAt(row, 0);
                

                int confirmar = JOptionPane.showConfirmDialog(
                        tabela,
                        "Deseja excluir o item " + id + "?",
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
