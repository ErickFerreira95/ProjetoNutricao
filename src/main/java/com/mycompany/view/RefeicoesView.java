package com.mycompany.view;

import com.mycompany.model.Alimento;
import com.mycompany.util.dao.AlimentoDao;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class RefeicoesView extends JFrame {

    private JTable tabela1;
    private JTable tabela2;
    private JTable tabela3;
    private JTable tabela4;
    private JTable tabela5;
    private JTable tabela6;
    private DefaultTableModel modelo1;
    private DefaultTableModel modelo2;
    private DefaultTableModel modelo3;
    private DefaultTableModel modelo4;
    private DefaultTableModel modelo5;
    private DefaultTableModel modelo6;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuCadastrarAlimento = new JMenu("Alimento");
    private JMenu menuCalcularTmb = new JMenu("Calculadora");
    private JMenu menuRefeicoes = new JMenu("Refeições");
    private JMenuItem cadastrarAlimento = new JMenuItem("Cadastrar alimento");
    private JMenuItem calcularTmb = new JMenuItem("Calcular TMB");
    private JMenuItem refeicoes = new JMenuItem("Refeições");
    private JMenuItem adicionarRefeicao = new JMenuItem("Adicionar Refeição");
    private JMenuItem tabelaAlimentos = new JMenuItem("Tabela de Alimentos");
    
    private void configurarUI() {
        setTitle("Refeições");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 720);
        setLocationRelativeTo(null);

        menuCadastrarAlimento.add(tabelaAlimentos);
        menuCadastrarAlimento.add(cadastrarAlimento);
        menuBar.add(menuCadastrarAlimento);

        menuCalcularTmb.add(calcularTmb);
        menuBar.add(menuCalcularTmb);

        menuRefeicoes.add(refeicoes);
        menuRefeicoes.add(adicionarRefeicao);
        menuBar.add(menuRefeicoes);
        setJMenuBar(menuBar);

        // Painel de fundo
        // Painel de fundo
        JPanel backgroundPanel = new JPanel() {
            private final Image imagemFundo = new ImageIcon("/C:/Users/Erick/OneDrive/Documentos/NetBeansProjects/1-Projetos/AppNutricao/build/classes/images/background.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());

        /*JPanel panelTopo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Shape forma = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 30, 30);
                g2.setColor(new Color(255, 255, 255, 200));
                g2.fill(forma);
                g2.dispose();
                super.paintComponent(g);
            }
        };

        panelTopo.setOpaque(false);
        panelTopo.setPreferredSize(new Dimension(400, 185));
        panelTopo.setLayout(new GridBagLayout());
        
        // Posicionamento no topo absoluto
        GridBagConstraints positionPainelTopo = new GridBagConstraints();
        positionPainelTopo.gridx = 0;
        positionPainelTopo.gridy = 0;
        positionPainelTopo.weightx = 0;
        positionPainelTopo.weighty = 0; // ← isso força ele a ficar no topo
        positionPainelTopo.anchor = GridBagConstraints.CENTER;
        positionPainelTopo.fill = GridBagConstraints.NONE;
        positionPainelTopo.insets = new Insets(0, 0, 0, 0); // margem superior
        backgroundPanel.add(panelTopo, positionPainelTopo);*/
        modelo1 = new DefaultTableModel(new String[]{"Quantidade(g)", "Alimento", "Proteína", "Carboidrato", "Gordura", "Kcal"}, 0);
        modelo2 = new DefaultTableModel(new String[]{"Quantidade(g)", "Alimento", "Proteína", "Carboidrato", "Gordura", "Kcal"}, 0);
        modelo3 = new DefaultTableModel(new String[]{"Quantidade(g)", "Alimento", "Proteína", "Carboidrato", "Gordura", "Kcal"}, 0);
        modelo4 = new DefaultTableModel(new String[]{"Quantidade(g)", "Alimento", "Proteína", "Carboidrato", "Gordura", "Kcal"}, 0);
        modelo5 = new DefaultTableModel(new String[]{"Quantidade(g)", "Alimento", "Proteína", "Carboidrato", "Gordura", "Kcal"}, 0);
        modelo6 = new DefaultTableModel(new String[]{"Quantidade(g)", "Alimento", "Proteína", "Carboidrato", "Gordura", "Kcal"}, 0);
        
        tabela1 = new JTable(modelo1);
        tabela1.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 12));
        tabela1.setFont(new Font("Calibri", Font.PLAIN, 12));
        tabela1.setPreferredScrollableViewportSize(new Dimension(400, 200));

        tabela2 = new JTable(modelo2);
        tabela2.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 12));
        tabela2.setFont(new Font("Calibri", Font.PLAIN, 12));
        tabela2.setPreferredScrollableViewportSize(new Dimension(400, 200));

        tabela3 = new JTable(modelo3);
        tabela3.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 12));
        tabela3.setFont(new Font("Calibri", Font.PLAIN, 12));
        tabela3.setPreferredScrollableViewportSize(new Dimension(400, 200));

        tabela4 = new JTable(modelo4);
        tabela4.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 12));
        tabela4.setFont(new Font("Calibri", Font.PLAIN, 12));
        tabela4.setPreferredScrollableViewportSize(new Dimension(400, 200));

        tabela5 = new JTable(modelo5);
        tabela5.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 12));
        tabela5.setFont(new Font("Calibri", Font.PLAIN, 12));
        tabela5.setPreferredScrollableViewportSize(new Dimension(400, 200));

        tabela6 = new JTable(modelo6);
        tabela6.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 12));
        tabela6.setFont(new Font("Calibri", Font.PLAIN, 12));
        tabela6.setPreferredScrollableViewportSize(new Dimension(400, 200));

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        // Aplica o alinhamento a todas as colunas
        for (int i = 0; i < tabela1.getColumnCount(); i++) {
            tabela1.getColumnModel().getColumn(i).setCellRenderer(centralizado);
            // Renderizador e editor para os dois botões
        }

        for (int i = 0; i < tabela2.getColumnCount(); i++) {
            tabela2.getColumnModel().getColumn(i).setCellRenderer(centralizado);
            // Renderizador e editor para os dois botões
        }

        for (int i = 0; i < tabela3.getColumnCount(); i++) {
            tabela3.getColumnModel().getColumn(i).setCellRenderer(centralizado);
            // Renderizador e editor para os dois botões
        }

        for (int i = 0; i < tabela4.getColumnCount(); i++) {
            tabela4.getColumnModel().getColumn(i).setCellRenderer(centralizado);
            // Renderizador e editor para os dois botões
        }

        for (int i = 0; i < tabela5.getColumnCount(); i++) {
            tabela5.getColumnModel().getColumn(i).setCellRenderer(centralizado);
            // Renderizador e editor para os dois botões
        }

        for (int i = 0; i < tabela6.getColumnCount(); i++) {
            tabela6.getColumnModel().getColumn(i).setCellRenderer(centralizado);
            // Renderizador e editor para os dois botões
        }

        GridBagConstraints posicaoTable1 = new GridBagConstraints();
        posicaoTable1.gridx = 0;
        posicaoTable1.gridy = 0;
        posicaoTable1.weightx = 0;
        posicaoTable1.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTable1.anchor = GridBagConstraints.CENTER;
        posicaoTable1.fill = GridBagConstraints.NONE;
        posicaoTable1.insets = new Insets(0, 0, 15, 15); // margem superior
        backgroundPanel.add(new JScrollPane(tabela1), posicaoTable1);

        GridBagConstraints posicaoTable2 = new GridBagConstraints();
        posicaoTable2.gridx = 1;
        posicaoTable2.gridy = 0;
        posicaoTable2.weightx = 0;
        posicaoTable2.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTable2.anchor = GridBagConstraints.CENTER;
        posicaoTable2.fill = GridBagConstraints.NONE;
        posicaoTable2.insets = new Insets(0, 0, 15, 15); // margem superior
        backgroundPanel.add(new JScrollPane(tabela2), posicaoTable2);

        GridBagConstraints posicaoTable3 = new GridBagConstraints();
        posicaoTable3.gridx = 2;
        posicaoTable3.gridy = 0;
        posicaoTable3.weightx = 0;
        posicaoTable3.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTable3.anchor = GridBagConstraints.CENTER;
        posicaoTable3.fill = GridBagConstraints.NONE;
        posicaoTable3.insets = new Insets(0, 0, 15, 0); // margem superior
        backgroundPanel.add(new JScrollPane(tabela3), posicaoTable3);

        GridBagConstraints posicaoTable4 = new GridBagConstraints();
        posicaoTable4.gridx = 0;
        posicaoTable4.gridy = 1;
        posicaoTable4.weightx = 0;
        posicaoTable4.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTable4.anchor = GridBagConstraints.CENTER;
        posicaoTable4.fill = GridBagConstraints.NONE;
        posicaoTable4.insets = new Insets(0, 0, 0, 15); // margem superior
        backgroundPanel.add(new JScrollPane(tabela4), posicaoTable4);

        GridBagConstraints posicaoTable5 = new GridBagConstraints();
        posicaoTable5.gridx = 1;
        posicaoTable5.gridy = 1;
        posicaoTable5.weightx = 0;
        posicaoTable5.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTable5.anchor = GridBagConstraints.CENTER;
        posicaoTable5.fill = GridBagConstraints.NONE;
        posicaoTable5.insets = new Insets(0, 0, 0, 15); // margem superior
        backgroundPanel.add(new JScrollPane(tabela5), posicaoTable5);

        GridBagConstraints posicaoTable6 = new GridBagConstraints();
        posicaoTable6.gridx = 2;
        posicaoTable6.gridy = 1;
        posicaoTable6.weightx = 0;
        posicaoTable6.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTable6.anchor = GridBagConstraints.CENTER;
        posicaoTable6.fill = GridBagConstraints.NONE;
        posicaoTable6.insets = new Insets(0, 0, 0, 0); // margem superior
        backgroundPanel.add(new JScrollPane(tabela6), posicaoTable6);

        setContentPane(backgroundPanel);
    }

    public void refeifoes() {
        setVisible(true);
        configurarUI();
        calculoTmbView();
        adicionarRefeicaoView();
        carregarRefeicao1();
        carregarRefeicao2();
        carregarRefeicao3();
        carregarRefeicao4();
        carregarRefeicao5();
        carregarRefeicao6();
        tabelaAlimentosView();
        cadastroAlimentoView();
    }

    // Adiciona ações
    public void adicionarRefeicaoView() {
        adicionarRefeicao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AdicionarAlimentoRefeicao().adicionarRefeicao();
            }
        });
    }

    public void calculoTmbView() {
        calcularTmb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CalculoTmbView().calculoTmbView();
            }
        });
    }
    
    private void carregarRefeicao1() {
        AlimentoDao dao = new AlimentoDao();
        List<Alimento> alimentos = dao.carregarAlimentosRefeicao1();

        for (Alimento a : alimentos) {
            modelo1.addRow(new Object[]{a.getNomeAlimento(), a.getQuantidade(), a.getProteina(), a.getCarboidrato(), a.getGordura(), a.getKcal()});
        }
    }
    
    private void carregarRefeicao2() {
        AlimentoDao dao = new AlimentoDao();
        List<Alimento> alimentos = dao.carregarAlimentosRefeicao2();

        for (Alimento a : alimentos) {
            modelo2.addRow(new Object[]{a.getNomeAlimento(), a.getQuantidade(), a.getProteina(), a.getCarboidrato(), a.getGordura(), a.getKcal()});
        }
    }
    
    private void carregarRefeicao3() {
        AlimentoDao dao = new AlimentoDao();
        List<Alimento> alimentos = dao.carregarAlimentosRefeicao3();

        for (Alimento a : alimentos) {
            modelo3.addRow(new Object[]{a.getNomeAlimento(), a.getQuantidade(), a.getProteina(), a.getCarboidrato(), a.getGordura(), a.getKcal()});
        }
    }
    
    private void carregarRefeicao4() {
        AlimentoDao dao = new AlimentoDao();
        List<Alimento> alimentos = dao.carregarAlimentosRefeicao4();

        for (Alimento a : alimentos) {
            modelo4.addRow(new Object[]{a.getNomeAlimento(), a.getQuantidade(), a.getProteina(), a.getCarboidrato(), a.getGordura(), a.getKcal()});
        }
    }
    
    private void carregarRefeicao5() {
        AlimentoDao dao = new AlimentoDao();
        List<Alimento> alimentos = dao.carregarAlimentosRefeicao5();

        for (Alimento a : alimentos) {
            modelo5.addRow(new Object[]{a.getNomeAlimento(), a.getQuantidade(), a.getProteina(), a.getCarboidrato(), a.getGordura(), a.getKcal()});
        }
    }
    
    private void carregarRefeicao6() {
        AlimentoDao dao = new AlimentoDao();
        List<Alimento> alimentos = dao.carregarAlimentosRefeicao6();

        for (Alimento a : alimentos) {
            modelo6.addRow(new Object[]{a.getNomeAlimento(), a.getQuantidade(), a.getProteina(), a.getCarboidrato(), a.getGordura(), a.getKcal()});
        }
    }
    
    public void tabelaAlimentosView() {
        tabelaAlimentos.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainView().mainView();
            }
        });
    }
    
    public void cadastroAlimentoView() {
        cadastrarAlimento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CadastroAlimentoView().CadastroAlimentoView();
            }
        });
    }
}
