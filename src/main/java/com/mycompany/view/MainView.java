package com.mycompany.view;

import com.mycompany.model.Alimento;
import com.mycompany.model.Tmb;
import com.mycompany.model.User;
import com.mycompany.util.dao.AlimentoDao;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.List;
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

    private JTable tblAlimentos;
    private DefaultTableModel modelo;
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

    private void configurarUI() {
        setTitle("Tabela de Alimentos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
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
        JPanel backgroundPanel = new JPanel() {
            private final Image imagemFundo = new ImageIcon("/C:/Users/Erick/OneDrive/Documentos/NetBeansProjects/1-Projetos/AppNutricao/build/classes/images/background.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(new GridBagLayout());

        modelo = new DefaultTableModel(new String[]{"ID", "Alimento", "Quantidade(g)", "Proteína(g)", "Carboidrato(g)", "Gordura(g)", "Kcal", "Ações"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Permite edição apenas nas colunas 0 (Alimento) e 1 (Quantidade),
                // e somente se não for a última linha (TOTAL)
                return (column == 7);
            }
        };

        tblAlimentos = new JTable(modelo);
        tblAlimentos.getColumnModel().getColumn(0).setPreferredWidth(70);
        tblAlimentos.getColumnModel().getColumn(1).setPreferredWidth(300);
        tblAlimentos.getColumnModel().getColumn(2).setPreferredWidth(250);
        tblAlimentos.getColumnModel().getColumn(3).setPreferredWidth(200);
        tblAlimentos.getColumnModel().getColumn(4).setPreferredWidth(220);
        tblAlimentos.getColumnModel().getColumn(5).setPreferredWidth(200);
        tblAlimentos.getColumnModel().getColumn(6).setPreferredWidth(150);
        tblAlimentos.getColumnModel().getColumn(7).setPreferredWidth(350);
        tblAlimentos.setPreferredScrollableViewportSize(new Dimension(1500, 800));
        tblAlimentos.getTableHeader().setBackground(Color.WHITE);
        tblAlimentos.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 14));
        tblAlimentos.setFont(new Font("Calibri", Font.PLAIN, 14));
        tblAlimentos.setGridColor(Color.LIGHT_GRAY); // Cor das linhas da grade
        tblAlimentos.setRowHeight(30);

        JTableHeader cabecalho = tblAlimentos.getTableHeader();
        cabecalho.setPreferredSize(new Dimension(cabecalho.getWidth(), 30));

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        centralizado.setVerticalAlignment(SwingConstants.BOTTOM);

        // Aplica o alinhamento a todas as colunas
        for (int i = 0; i < tblAlimentos.getColumnCount(); i++) {
            tblAlimentos.getColumnModel().getColumn(i).setCellRenderer(centralizado);
            // Renderizador e editor para os dois botões
            tblAlimentos.getColumn("Ações").setCellRenderer(new PanelRenderer());
            tblAlimentos.getColumn("Ações").setCellEditor(new PanelEditor(tblAlimentos, this, usuarioLogado));
        }

        GridBagConstraints posicaoTable = new GridBagConstraints();
        posicaoTable.gridx = 0;
        posicaoTable.gridy = 1;
        posicaoTable.weightx = 0;
        posicaoTable.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTable.anchor = GridBagConstraints.CENTER;
        posicaoTable.fill = GridBagConstraints.NONE;
        posicaoTable.insets = new Insets(0, 0, 0, 0); // margem superior
        backgroundPanel.add(new JScrollPane(tblAlimentos), posicaoTable);

        setContentPane(backgroundPanel);

    }

    public void mainView() {
        configurarUI();
        setVisible(true);
        carregarTabela();
        CadastroAlimentoView();
        calculoTmbView();
        refeicoesView();
        adicionarRefeicaoView();
        sair();
    }

    public MainView(User usuario) {
        this.usuarioLogado = usuario;
    }

    /*private void carregarTabela() {
        AlimentoDao dao = new AlimentoDao();
        List<Alimento> alimentos = dao.carregarAlimentos();

        for (Alimento a : alimentos) {
            modelo.addRow(new Object[]{a.getId(), a.getNomeAlimento(), a.getQuantidade(), a.getProteina(), a.getCarboidrato(), a.getGordura(), a.getKcal()});
        }
    }*/
    private void carregarTabela() {
        AlimentoDao dao = new AlimentoDao();
        List<Alimento> alimentos = dao.listarPorUsuario(usuarioLogado.getId());

        for (Alimento a : alimentos) {
            modelo.addRow(new Object[]{a.getId(),
                a.getNomeAlimento(),
                a.getQuantidade(),
                a.getProteina(),
                a.getCarboidrato(),
                a.getGordura(),
                a.getKcal()});
        }
    }

    // Adiciona ações
    public void CadastroAlimentoView() {
        cadastrarAlimento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CadastroAlimentoView(usuarioLogado).cadastroAlimentoView();
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

    public void refeicoesView() {
        refeicoes.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RefeicoesView(usuarioLogado).refeifoes();
            }
        });
    }

    public void adicionarRefeicaoView() {
        adicionarRefeicao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AdicionarAlimentoRefeicao(usuarioLogado).adicionarRefeicao();
            }
        });
    }

    public void sair() {
        sair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginView().login();
            }
        });
    }
}
