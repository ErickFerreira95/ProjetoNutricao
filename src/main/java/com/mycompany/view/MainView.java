package com.mycompany.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class MainView extends JFrame {
    
    private JTable tabela;
    private DefaultTableModel modelo;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuCadastrarAlimento = new JMenu("Alimento");
    private JMenu menuCalcularTmb = new JMenu("Calculadora");
    private JMenuItem cadastrarAlimento = new JMenuItem("Cadastrar alimento");
    private JMenuItem calcularTmb = new JMenuItem("Calcular TMB");

    private void configurarUI() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        
        menuCadastrarAlimento.add(cadastrarAlimento);
        menuBar.add(menuCadastrarAlimento);
        
        menuCalcularTmb.add(calcularTmb);
        menuBar.add(menuCalcularTmb);
        setJMenuBar(menuBar);

        // Painel de fundo
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new GridBagLayout());
        
        modelo = new DefaultTableModel(new String[]{"ID", "Nome Alimento", "Quantidade(g)", "Proteína(g)", "Carboidrato(g)", "Gordura(g)", "Kcal", "Ações"}, 0);
        tabela = new JTable(modelo);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(70);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(300);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(250);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(200);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(220);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(200);
        tabela.getColumnModel().getColumn(6).setPreferredWidth(150);
        tabela.getColumnModel().getColumn(7).setPreferredWidth(350);
        tabela.setPreferredScrollableViewportSize(new Dimension(900, 500));
        tabela.getTableHeader().setBackground(Color.WHITE);
        tabela.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 14));
        tabela.setFont(new Font("Calibri", Font.PLAIN, 14));
        tabela.setGridColor(Color.LIGHT_GRAY); // Cor das linhas da grade
        tabela.setRowHeight(30);
        
        JTableHeader cabecalho = tabela.getTableHeader();
        cabecalho.setPreferredSize(new Dimension(cabecalho.getWidth(), 30));
        
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        centralizado.setVerticalAlignment(SwingConstants.BOTTOM);

        // Aplica o alinhamento a todas as colunas
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(centralizado);
            // Renderizador e editor para os dois botões
            tabela.getColumn("Ações").setCellRenderer(new PanelRenderer());
            tabela.getColumn("Ações").setCellEditor(new PanelEditor(tabela)); 
        }

        GridBagConstraints posicaoTable = new GridBagConstraints();
        posicaoTable.gridx = 0;
        posicaoTable.gridy = 1;
        posicaoTable.weightx = 0;
        posicaoTable.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTable.anchor = GridBagConstraints.CENTER;
        posicaoTable.fill = GridBagConstraints.NONE;
        posicaoTable.insets = new Insets(0, 0, 0, 0); // margem superior
        backgroundPanel.add(new JScrollPane(tabela), posicaoTable);
        
        setContentPane(backgroundPanel);
        
    }
    
    public void mainView() {
        configurarUI();
        setVisible(true);
    }
}
