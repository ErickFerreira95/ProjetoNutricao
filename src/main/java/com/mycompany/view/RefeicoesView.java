package com.mycompany.view;

import com.mycompany.model.Alimento;
import com.mycompany.model.Tmb;
import com.mycompany.model.User;
import com.mycompany.util.dao.AlimentoDao;
import com.mycompany.util.dao.UserDao;
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
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class RefeicoesView extends JFrame {

    private JTable tblRefeicao1;
    private JTable tblRefeicao2;
    private JTable tblRefeicao3;
    private JTable tblRefeicao4;
    private JTable tblRefeicao5;
    private JTable tblRefeicao6;
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
    private JMenu menuSair = new JMenu("Sair");
    private JMenuItem sair = new JMenuItem("Sair");
    private User usuarioLogado;

    JLabel lblResultadoProteina = new JLabel("Consumo de porteína: ");
    private JLabel lblResultadoCarboidrato = new JLabel("Consumo de carboidrato: ");
    private JLabel lblResultadoGordura = new JLabel("Consumo de gordura: ");
    private JLabel lblResultadoKcal = new JLabel("Consumo de kcal: ");
    private Tmb tmb = new Tmb();
    private UserDao dao = new UserDao();

    public RefeicoesView(User usuario) {
        this.usuarioLogado = usuario;
    }

    private void configurarUI() {
        setTitle("Refeições");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 900);
        setLocationRelativeTo(null);

        menuCadastrarAlimento.add(tabelaAlimentos);
        menuCadastrarAlimento.add(cadastrarAlimento);
        menuBar.add(menuCadastrarAlimento);

        menuCalcularTmb.add(calcularTmb);
        menuBar.add(menuCalcularTmb);

        menuRefeicoes.add(refeicoes);
        menuRefeicoes.add(adicionarRefeicao);
        menuBar.add(menuRefeicoes);

        menuSair.add(sair);
        menuBar.add(menuSair);

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

        // Painel transparente com cantos arredondados
        JPanel centralPanel = new JPanel() {
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

        centralPanel.setOpaque(false);
        centralPanel.setPreferredSize(new Dimension(350, 200));
        centralPanel.setLayout(new GridBagLayout());

        // Posicionamento no topo absoluto
        GridBagConstraints positionPainelCentral = new GridBagConstraints();
        positionPainelCentral.gridx = 0;
        positionPainelCentral.gridy = 0;
        positionPainelCentral.weightx = 0;
        positionPainelCentral.weighty = 0; // ← isso força ele a ficar no topo
        positionPainelCentral.anchor = GridBagConstraints.CENTER;
        positionPainelCentral.fill = GridBagConstraints.NONE;
        positionPainelCentral.insets = new Insets(0, 0, 30, 0); // margem superior
        backgroundPanel.add(centralPanel, positionPainelCentral);

        JLabel lblResultadoTmb = new JLabel("Seu TMB é: " + dao.buscarTmbPorUsuario("tmb", "taxaMetabolicaBasal", usuarioLogado.getId()));
        lblResultadoTmb.setFont(new Font("Calibri", Font.BOLD, 20));

        GridBagConstraints posicaoLblTmb = new GridBagConstraints();
        posicaoLblTmb.gridx = 0;
        posicaoLblTmb.gridy = 0;
        posicaoLblTmb.weightx = 0;
        posicaoLblTmb.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblTmb.anchor = GridBagConstraints.WEST;
        posicaoLblTmb.fill = GridBagConstraints.NONE;
        posicaoLblTmb.insets = new Insets(0, 0, 5, 0); // margem superior
        centralPanel.add(lblResultadoTmb, posicaoLblTmb);

        lblResultadoProteina.setFont(new Font("Calibri", Font.BOLD, 20));

        GridBagConstraints posicaoLblProteina = new GridBagConstraints();
        posicaoLblProteina.gridx = 0;
        posicaoLblProteina.gridy = 1;
        posicaoLblProteina.weightx = 0;
        posicaoLblProteina.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblProteina.anchor = GridBagConstraints.WEST;
        posicaoLblProteina.fill = GridBagConstraints.NONE;
        posicaoLblProteina.insets = new Insets(0, 0, 5, 0); // margem superior
        centralPanel.add(lblResultadoProteina, posicaoLblProteina);

        lblResultadoCarboidrato.setFont(new Font("Calibri", Font.BOLD, 20));

        GridBagConstraints posicaoLblCarboidrato = new GridBagConstraints();
        posicaoLblCarboidrato.gridx = 0;
        posicaoLblCarboidrato.gridy = 2;
        posicaoLblCarboidrato.weightx = 0;
        posicaoLblCarboidrato.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblCarboidrato.anchor = GridBagConstraints.WEST;
        posicaoLblCarboidrato.fill = GridBagConstraints.NONE;
        posicaoLblCarboidrato.insets = new Insets(0, 0, 5, 0); // margem superior
        centralPanel.add(lblResultadoCarboidrato, posicaoLblCarboidrato);

        lblResultadoGordura.setFont(new Font("Calibri", Font.BOLD, 20));

        GridBagConstraints posicaoLblGordura = new GridBagConstraints();
        posicaoLblGordura.gridx = 0;
        posicaoLblGordura.gridy = 3;
        posicaoLblGordura.weightx = 0;
        posicaoLblGordura.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblGordura.anchor = GridBagConstraints.WEST;
        posicaoLblGordura.fill = GridBagConstraints.NONE;
        posicaoLblGordura.insets = new Insets(0, 0, 5, 0); // margem superior
        centralPanel.add(lblResultadoGordura, posicaoLblGordura);

        lblResultadoKcal.setFont(new Font("Calibri", Font.BOLD, 20));

        GridBagConstraints posicaoLblKcal = new GridBagConstraints();
        posicaoLblKcal.gridx = 0;
        posicaoLblKcal.gridy = 4;
        posicaoLblKcal.weightx = 0;
        posicaoLblKcal.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblKcal.anchor = GridBagConstraints.WEST;
        posicaoLblKcal.fill = GridBagConstraints.NONE;
        posicaoLblKcal.insets = new Insets(0, 0, 0, 0); // margem superior
        centralPanel.add(lblResultadoKcal, posicaoLblKcal);

        modelo1 = new DefaultTableModel(new String[]{"ID", "Quantidade(g)", "Alimento", "Proteína", "Carboidrato", "Gordura", "Kcal", ""}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return row < getRowCount() - 2 && (column == 7);
            }
        };

        modelo2 = new DefaultTableModel(new String[]{"ID", "Quantidade(g)", "Alimento", "Proteína", "Carboidrato", "Gordura", "Kcal", ""}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return row < getRowCount() - 2 && (column == 7);
            }
        };

        modelo3 = new DefaultTableModel(new String[]{"ID", "Quantidade(g)", "Alimento", "Proteína", "Carboidrato", "Gordura", "Kcal", ""}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return row < getRowCount() - 2 && (column == 7);
            }
        };

        modelo4 = new DefaultTableModel(new String[]{"ID", "Quantidade(g)", "Alimento", "Proteína", "Carboidrato", "Gordura", "Kcal", ""}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return row < getRowCount() - 2 && (column == 7);
            }
        };

        modelo5 = new DefaultTableModel(new String[]{"ID", "Quantidade(g)", "Alimento", "Proteína", "Carboidrato", "Gordura", "Kcal", ""}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return row < getRowCount() - 2 && (column == 7);
            }
        };

        modelo6 = new DefaultTableModel(new String[]{"ID", "Quantidade(g)", "Alimento", "Proteína", "Carboidrato", "Gordura", "Kcal", ""}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return row < getRowCount() - 2 && (column == 7);
            }
        };

        modelo1.addRow(new Object[]{"", "", "", "", "", "", "", ""});
        modelo1.addRow(new Object[]{"TOTAL", "0,00", "", "0,00", "0,00", "0,00", "0,00", ""});

        modelo2.addRow(new Object[]{"", "", "", "", "", "", "", ""});
        modelo2.addRow(new Object[]{"TOTAL", "0,00", "", "0,00", "0,00", "0,00", "0,00", ""});

        modelo3.addRow(new Object[]{"", "", "", "", "", "", "", ""});
        modelo3.addRow(new Object[]{"TOTAL", "0,00", "", "0,00", "0,00", "0,00", "0,00", ""});

        modelo4.addRow(new Object[]{"", "", "", "", "", "", "", ""});
        modelo4.addRow(new Object[]{"TOTAL", "0,00", "", "0,00", "0,00", "0,00", "0,00", ""});

        modelo5.addRow(new Object[]{"", "", "", "", "", "", "", ""});
        modelo5.addRow(new Object[]{"TOTAL", "0,00", "", "0,00", "0,00", "0,00", "0,00", ""});

        modelo6.addRow(new Object[]{"", "", "", "", "", "", "", ""});
        modelo6.addRow(new Object[]{"TOTAL", "0,00", "", "0,00", "0,00", "0,00", "0,00", ""});

        tblRefeicao1 = new JTable(modelo1);
        tblRefeicao1.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 12));
        tblRefeicao1.setFont(new Font("Calibri", Font.PLAIN, 12));
        tblRefeicao1.setPreferredScrollableViewportSize(new Dimension(600, 200));
        tblRefeicao1.setToolTipText("REFEIÇÃO 1");
        tblRefeicao1.setRowHeight(30);
        tblRefeicao1.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblRefeicao1.getColumnModel().getColumn(6).setPreferredWidth(50);
        tblRefeicao1.getColumnModel().getColumn(7).setPreferredWidth(100);

        tblRefeicao2 = new JTable(modelo2);
        tblRefeicao2.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 12));
        tblRefeicao2.setFont(new Font("Calibri", Font.PLAIN, 12));
        tblRefeicao2.setPreferredScrollableViewportSize(new Dimension(600, 200));
        tblRefeicao2.setToolTipText("REFEIÇÃO 2");
        tblRefeicao2.setRowHeight(30);
        tblRefeicao2.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblRefeicao2.getColumnModel().getColumn(6).setPreferredWidth(50);
        tblRefeicao2.getColumnModel().getColumn(7).setPreferredWidth(100);

        tblRefeicao3 = new JTable(modelo3);
        tblRefeicao3.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 12));
        tblRefeicao3.setFont(new Font("Calibri", Font.PLAIN, 12));
        tblRefeicao3.setPreferredScrollableViewportSize(new Dimension(600, 200));
        tblRefeicao3.setToolTipText("REFEIÇÃO 3");
        tblRefeicao3.setRowHeight(30);
        tblRefeicao3.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblRefeicao3.getColumnModel().getColumn(6).setPreferredWidth(50);
        tblRefeicao3.getColumnModel().getColumn(7).setPreferredWidth(100);

        tblRefeicao4 = new JTable(modelo4);
        tblRefeicao4.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 12));
        tblRefeicao4.setFont(new Font("Calibri", Font.PLAIN, 12));
        tblRefeicao4.setPreferredScrollableViewportSize(new Dimension(600, 200));
        tblRefeicao4.setToolTipText("REFEIÇÃO 4");
        tblRefeicao4.setRowHeight(30);
        tblRefeicao4.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblRefeicao4.getColumnModel().getColumn(6).setPreferredWidth(50);
        tblRefeicao4.getColumnModel().getColumn(7).setPreferredWidth(100);

        tblRefeicao5 = new JTable(modelo5);
        tblRefeicao5.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 12));
        tblRefeicao5.setFont(new Font("Calibri", Font.PLAIN, 12));
        tblRefeicao5.setPreferredScrollableViewportSize(new Dimension(600, 200));
        tblRefeicao5.setToolTipText("REFEIÇÃO 5");
        tblRefeicao5.setRowHeight(30);
        tblRefeicao5.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblRefeicao5.getColumnModel().getColumn(6).setPreferredWidth(50);
        tblRefeicao5.getColumnModel().getColumn(7).setPreferredWidth(100);

        tblRefeicao6 = new JTable(modelo6);
        tblRefeicao6.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 12));
        tblRefeicao6.setFont(new Font("Calibri", Font.PLAIN, 12));
        tblRefeicao6.setPreferredScrollableViewportSize(new Dimension(600, 200));
        tblRefeicao6.setToolTipText("REFEIÇÃO 6");
        tblRefeicao6.setRowHeight(30);
        tblRefeicao6.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblRefeicao6.getColumnModel().getColumn(6).setPreferredWidth(50);
        tblRefeicao6.getColumnModel().getColumn(7).setPreferredWidth(100);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        // Aplica o alinhamento a todas as colunas
        for (int i = 0; i < tblRefeicao1.getColumnCount(); i++) {
            tblRefeicao1.getColumnModel().getColumn(i).setCellRenderer(centralizado);
            // Renderizador e editor para os dois botões
            tblRefeicao1.getColumn("").setCellRenderer(new PanelRendererRefeicoes());
            tblRefeicao1.getColumn("").setCellEditor(new PanelEditorRefeicoes(tblRefeicao1, this, usuarioLogado));
        }

        for (int i = 0; i < tblRefeicao2.getColumnCount(); i++) {
            tblRefeicao2.getColumnModel().getColumn(i).setCellRenderer(centralizado);
            // Renderizador e editor para os dois botões
            tblRefeicao2.getColumn("").setCellRenderer(new PanelRendererRefeicao2());
            tblRefeicao2.getColumn("").setCellEditor(new PanelEditorRefeicao2(tblRefeicao2, this, usuarioLogado));
        }

        for (int i = 0; i < tblRefeicao3.getColumnCount(); i++) {
            tblRefeicao3.getColumnModel().getColumn(i).setCellRenderer(centralizado);
            // Renderizador e editor para os dois botões
            tblRefeicao3.getColumn("").setCellRenderer(new PanelRendererRefeicao3());
            tblRefeicao3.getColumn("").setCellEditor(new PanelEditorRefeicao3(tblRefeicao3, this, usuarioLogado));
        }

        for (int i = 0; i < tblRefeicao4.getColumnCount(); i++) {
            tblRefeicao4.getColumnModel().getColumn(i).setCellRenderer(centralizado);
            // Renderizador e editor para os dois botões
            tblRefeicao4.getColumn("").setCellRenderer(new PanelRendererRefeicao4());
            tblRefeicao4.getColumn("").setCellEditor(new PanelEditorRefeicao4(tblRefeicao4, this, usuarioLogado));
        }

        for (int i = 0; i < tblRefeicao5.getColumnCount(); i++) {
            tblRefeicao5.getColumnModel().getColumn(i).setCellRenderer(centralizado);
            // Renderizador e editor para os dois botões
            tblRefeicao5.getColumn("").setCellRenderer(new PanelRendererRefeicao5());
            tblRefeicao5.getColumn("").setCellEditor(new PanelEditorRefeicao5(tblRefeicao5, this, usuarioLogado));
        }

        for (int i = 0; i < tblRefeicao6.getColumnCount(); i++) {
            tblRefeicao6.getColumnModel().getColumn(i).setCellRenderer(centralizado);
            // Renderizador e editor para os dois botões
            tblRefeicao6.getColumn("").setCellRenderer(new PanelRendererRefeicao6());
            tblRefeicao6.getColumn("").setCellEditor(new PanelEditorRefeicao6(tblRefeicao6, this, usuarioLogado));
        }

        GridBagConstraints posicaoTable1 = new GridBagConstraints();
        posicaoTable1.gridx = 0;
        posicaoTable1.gridy = 1;
        posicaoTable1.weightx = 0;
        posicaoTable1.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTable1.anchor = GridBagConstraints.CENTER;
        posicaoTable1.fill = GridBagConstraints.NONE;
        posicaoTable1.insets = new Insets(0, 0, 30, 15); // margem superior
        backgroundPanel.add(new JScrollPane(tblRefeicao1), posicaoTable1);

        GridBagConstraints posicaoTable2 = new GridBagConstraints();
        posicaoTable2.gridx = 1;
        posicaoTable2.gridy = 1;
        posicaoTable2.weightx = 0;
        posicaoTable2.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTable2.anchor = GridBagConstraints.CENTER;
        posicaoTable2.fill = GridBagConstraints.NONE;
        posicaoTable2.insets = new Insets(0, 0, 30, 15); // margem superior
        backgroundPanel.add(new JScrollPane(tblRefeicao2), posicaoTable2);

        GridBagConstraints posicaoTable3 = new GridBagConstraints();
        posicaoTable3.gridx = 2;
        posicaoTable3.gridy = 1;
        posicaoTable3.weightx = 0;
        posicaoTable3.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTable3.anchor = GridBagConstraints.CENTER;
        posicaoTable3.fill = GridBagConstraints.NONE;
        posicaoTable3.insets = new Insets(0, 0, 30, 0); // margem superior
        backgroundPanel.add(new JScrollPane(tblRefeicao3), posicaoTable3);

        GridBagConstraints posicaoTable4 = new GridBagConstraints();
        posicaoTable4.gridx = 0;
        posicaoTable4.gridy = 2;
        posicaoTable4.weightx = 0;
        posicaoTable4.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTable4.anchor = GridBagConstraints.CENTER;
        posicaoTable4.fill = GridBagConstraints.NONE;
        posicaoTable4.insets = new Insets(0, 0, 0, 15); // margem superior
        backgroundPanel.add(new JScrollPane(tblRefeicao4), posicaoTable4);

        GridBagConstraints posicaoTable5 = new GridBagConstraints();
        posicaoTable5.gridx = 1;
        posicaoTable5.gridy = 2;
        posicaoTable5.weightx = 0;
        posicaoTable5.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTable5.anchor = GridBagConstraints.CENTER;
        posicaoTable5.fill = GridBagConstraints.NONE;
        posicaoTable5.insets = new Insets(0, 0, 0, 15); // margem superior
        backgroundPanel.add(new JScrollPane(tblRefeicao5), posicaoTable5);

        GridBagConstraints posicaoTable6 = new GridBagConstraints();
        posicaoTable6.gridx = 2;
        posicaoTable6.gridy = 2;
        posicaoTable6.weightx = 0;
        posicaoTable6.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTable6.anchor = GridBagConstraints.CENTER;
        posicaoTable6.fill = GridBagConstraints.NONE;
        posicaoTable6.insets = new Insets(0, 0, 0, 0); // margem superior
        backgroundPanel.add(new JScrollPane(tblRefeicao6), posicaoTable6);

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
        atualizarTotalProteinas();
        atualizarTotalCarboidratos();
        atualizarTotalGorduras();
        atualizarTotalKcals();
        sair();
    }

    // Adiciona ações
    public void adicionarRefeicaoView() {
        adicionarRefeicao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AdicionarAlimentoRefeicao(usuarioLogado).adicionarRefeicao();
            }
        });
    }

    public void calculoTmbView() {
        calcularTmb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CalculoTmbView(usuarioLogado).calculoTmbView();
            }
        });
    }

    private void carregarRefeicao1() {
        AlimentoDao dao = new AlimentoDao();
        List<Alimento> alimentos = dao.listarPorusuarioRefeicao1(usuarioLogado.getId());

        for (Alimento a : alimentos) {
            modelo1.insertRow(modelo1.getRowCount() - 2, new Object[]{
                a.getId(),
                a.getQuantidade(),
                a.getNomeAlimento(),
                a.getProteina(),
                a.getCarboidrato(),
                a.getGordura(),
                a.getKcal()
            });
        }

        // Atualiza os totais após carregar todos os alimentos
        atualizarTotais(modelo1);
    }

    private void carregarRefeicao2() {
        AlimentoDao dao = new AlimentoDao();
        List<Alimento> alimentos = dao.listarPorusuarioRefeicao2(usuarioLogado.getId());

        for (Alimento a : alimentos) {
            modelo2.insertRow(modelo2.getRowCount() - 2, new Object[]{
                a.getId(),
                a.getQuantidade(),
                a.getNomeAlimento(),
                a.getProteina(),
                a.getCarboidrato(),
                a.getGordura(),
                a.getKcal()
            });
        }

        // Atualiza os totais após carregar todos os alimentos
        atualizarTotais(modelo2);
    }

    private void carregarRefeicao3() {
        AlimentoDao dao = new AlimentoDao();
        List<Alimento> alimentos = dao.listarPorusuarioRefeicao3(usuarioLogado.getId());

        for (Alimento a : alimentos) {
            modelo3.insertRow(modelo3.getRowCount() - 2, new Object[]{
                a.getId(),
                a.getQuantidade(),
                a.getNomeAlimento(),
                a.getProteina(),
                a.getCarboidrato(),
                a.getGordura(),
                a.getKcal()
            });
        }

        // Atualiza os totais após carregar todos os alimentos
        atualizarTotais(modelo3);
    }

    private void carregarRefeicao4() {
        AlimentoDao dao = new AlimentoDao();
        List<Alimento> alimentos = dao.listarPorusuarioRefeicao4(usuarioLogado.getId());

        for (Alimento a : alimentos) {
            modelo4.insertRow(modelo4.getRowCount() - 2, new Object[]{
                a.getId(),
                a.getQuantidade(),
                a.getNomeAlimento(),
                a.getProteina(),
                a.getCarboidrato(),
                a.getGordura(),
                a.getKcal()
            });
        }

        // Atualiza os totais após carregar todos os alimentos
        atualizarTotais(modelo4);
    }

    private void carregarRefeicao5() {
        AlimentoDao dao = new AlimentoDao();
        List<Alimento> alimentos = dao.listarPorusuarioRefeicao5(usuarioLogado.getId());

        for (Alimento a : alimentos) {
            modelo5.insertRow(modelo5.getRowCount() - 2, new Object[]{
                a.getId(),
                a.getQuantidade(),
                a.getNomeAlimento(),
                a.getProteina(),
                a.getCarboidrato(),
                a.getGordura(),
                a.getKcal()
            });
        }

        // Atualiza os totais após carregar todos os alimentos
        atualizarTotais(modelo5);
    }

    private void carregarRefeicao6() {
        AlimentoDao dao = new AlimentoDao();
        List<Alimento> alimentos = dao.listarPorusuarioRefeicao6(usuarioLogado.getId());

        for (Alimento a : alimentos) {
            modelo6.insertRow(modelo6.getRowCount() - 2, new Object[]{
                a.getId(),
                a.getQuantidade(),
                a.getNomeAlimento(),
                a.getProteina(),
                a.getCarboidrato(),
                a.getGordura(),
                a.getKcal()
            });
        }

        // Atualiza os totais após carregar todos os alimentos
        atualizarTotais(modelo6);
    }

    public void tabelaAlimentosView() {
        tabelaAlimentos.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainView(usuarioLogado).mainView();
            }
        });
    }

    public void cadastroAlimentoView() {
        cadastrarAlimento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CadastroAlimentoView(usuarioLogado).cadastroAlimentoView();
            }
        });
    }

    public void atualizarTotais(DefaultTableModel modelo) {
        double proteina = 0.0;
        double carboidrato = 0.0;
        double gordura = 0.0;
        double kcal = 0.0;
        double quantidade = 0.0;

        for (int i = 0; i < modelo.getRowCount() - 1; i++) {
            quantidade += parseDouble(modelo.getValueAt(i, 1));
            proteina += parseDouble(modelo.getValueAt(i, 3));
            carboidrato += parseDouble(modelo.getValueAt(i, 4));
            gordura += parseDouble(modelo.getValueAt(i, 5));
            kcal += parseDouble(modelo.getValueAt(i, 6));
        }

        int totalRow = modelo.getRowCount() - 1;
        modelo.setValueAt("TOTAL", totalRow, 0);
        modelo.setValueAt(String.format("%.2f", quantidade), totalRow, 1);
        modelo.setValueAt(String.format("%.2f", proteina), totalRow, 3);
        modelo.setValueAt(String.format("%.2f", carboidrato), totalRow, 4);
        modelo.setValueAt(String.format("%.2f", gordura), totalRow, 5);
        modelo.setValueAt(String.format("%.2f", kcal), totalRow, 6);
    }

    private double parseDouble(Object valor) {
        if (valor == null) {
            return 0.0;
        }
        try {
            return Double.parseDouble(valor.toString().replace(",", "."));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public void sair() {
        sair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginView().login();
            }
        });
    }

    private double pegarValorUltimaLinha(JTable tabela, int coluna) {
        int linha = tabela.getRowCount() - 1;

        if (linha >= 0) {
            Object valorObj = tabela.getValueAt(linha, coluna);

            if (valorObj != null && valorObj.toString().matches("[0-9.,]+")) {
                try {
                    String valor = valorObj.toString().replace(",", ".");
                    return Double.parseDouble(valor);
                } catch (NumberFormatException e) {
                    // valor inválido, ignora
                }
            }
        }

        return 0;
    }

    private void atualizarTotalProteinas() {
        double total = 0;
        total += pegarValorUltimaLinha(tblRefeicao1, 3); // suponha que coluna 2 é proteína
        total += pegarValorUltimaLinha(tblRefeicao2, 3);
        total += pegarValorUltimaLinha(tblRefeicao3, 3);
        total += pegarValorUltimaLinha(tblRefeicao4, 3);
        total += pegarValorUltimaLinha(tblRefeicao5, 3);
        total += pegarValorUltimaLinha(tblRefeicao6, 3);

        lblResultadoProteina.setText("Consumo de proteína: " + String.format("%.2f", total) + " g");
    }

    private void atualizarTotalCarboidratos() {
        double total = 0;
        total += pegarValorUltimaLinha(tblRefeicao1, 4); // suponha que coluna 2 é proteína
        total += pegarValorUltimaLinha(tblRefeicao2, 4);
        total += pegarValorUltimaLinha(tblRefeicao3, 4);
        total += pegarValorUltimaLinha(tblRefeicao4, 4);
        total += pegarValorUltimaLinha(tblRefeicao5, 4);
        total += pegarValorUltimaLinha(tblRefeicao6, 4);

        lblResultadoCarboidrato.setText("Consumo de carboidrato: " + String.format("%.2f", total) + " g");
    }
    
    private void atualizarTotalGorduras() {
        double total = 0;
        total += pegarValorUltimaLinha(tblRefeicao1, 5); // suponha que coluna 2 é proteína
        total += pegarValorUltimaLinha(tblRefeicao2, 5);
        total += pegarValorUltimaLinha(tblRefeicao3, 5);
        total += pegarValorUltimaLinha(tblRefeicao4, 5);
        total += pegarValorUltimaLinha(tblRefeicao5, 5);
        total += pegarValorUltimaLinha(tblRefeicao6, 5);

        lblResultadoGordura.setText("Consumo de gordura: " + String.format("%.2f", total) + " g");
    }
    
    private void atualizarTotalKcals() {
        double total = 0;
        total += pegarValorUltimaLinha(tblRefeicao1, 6); // suponha que coluna 2 é proteína
        total += pegarValorUltimaLinha(tblRefeicao2, 6);
        total += pegarValorUltimaLinha(tblRefeicao3, 6);
        total += pegarValorUltimaLinha(tblRefeicao4, 6);
        total += pegarValorUltimaLinha(tblRefeicao5, 6);
        total += pegarValorUltimaLinha(tblRefeicao6, 6);

        lblResultadoKcal.setText("Consumo de kal: " + String.format("%.2f", total) + " g");
    }
}
