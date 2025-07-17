package com.mycompany.view;

import com.mycompany.model.Alimento;
import com.mycompany.util.dao.AlimentoDao;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdicionarAlimentoRefeicao extends JFrame {

    private JTextField txtNomeAlimento = new JTextField(12);
    private JTextField txtQuantidade = new JTextField(12);
    private JButton btnAdicionarAlimento = new JButton("Adicionar Alimento");
    private JButton btnBuscar = new JButton("Buscar");
    private JLabel lblResultadoProteina = new JLabel();
    private JLabel lblResultadoCarboidrato = new JLabel();
    private JLabel lblResultadoGordura = new JLabel();
    private JLabel lblResultadoKcal = new JLabel();
    private JComboBox<String> comboBox = new JComboBox();
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

    private void configurarUI() {
        setTitle("Adionar Alimentos nas Refeições");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
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
        centralPanel.setPreferredSize(new Dimension(300, 300));
        centralPanel.setLayout(new GridBagLayout());

        // Posicionamento no topo absoluto
        GridBagConstraints positionPainelCentral = new GridBagConstraints();
        positionPainelCentral.gridx = 0;
        positionPainelCentral.gridy = 0;
        positionPainelCentral.weightx = 0;
        positionPainelCentral.weighty = 0; // ← isso força ele a ficar no topo
        positionPainelCentral.anchor = GridBagConstraints.CENTER;
        positionPainelCentral.fill = GridBagConstraints.NONE;
        positionPainelCentral.insets = new Insets(0, 0, 0, 30); // margem superior
        backgroundPanel.add(centralPanel, positionPainelCentral);

        JPanel centralPanel2 = new JPanel() {
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

        centralPanel2.setOpaque(false);
        centralPanel2.setPreferredSize(new Dimension(300, 300));
        centralPanel2.setLayout(new GridBagLayout());

        // Posicionamento no topo absoluto
        GridBagConstraints positionPainelCentral2 = new GridBagConstraints();
        positionPainelCentral.gridx = 1;
        positionPainelCentral.gridy = 0;
        positionPainelCentral.weightx = 0;
        positionPainelCentral.weighty = 0; // ← isso força ele a ficar no topo
        positionPainelCentral.anchor = GridBagConstraints.CENTER;
        positionPainelCentral.fill = GridBagConstraints.NONE;
        positionPainelCentral.insets = new Insets(0, 0, 0, 0); // margem superior
        backgroundPanel.add(centralPanel2, positionPainelCentral2);

        JLabel lblNomeAlimento = new JLabel("Nome alimento:");
        lblNomeAlimento.setFont(new Font("Calibri", Font.BOLD, 20));
        centralPanel.add(lblNomeAlimento);

        GridBagConstraints posicaoLblNomeAlimento = new GridBagConstraints();
        posicaoLblNomeAlimento.gridx = 0;
        posicaoLblNomeAlimento.gridy = 2;
        posicaoLblNomeAlimento.weightx = 0;
        posicaoLblNomeAlimento.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblNomeAlimento.anchor = GridBagConstraints.CENTER;
        posicaoLblNomeAlimento.fill = GridBagConstraints.NONE;
        posicaoLblNomeAlimento.insets = new Insets(0, 0, 0, 0); // margem superior
        centralPanel.add(lblNomeAlimento, posicaoLblNomeAlimento);

        txtNomeAlimento.setFont(new Font("Calibri", Font.PLAIN, 14));
        txtNomeAlimento.setToolTipText("Insira o nome do alimento");
        txtNomeAlimento.setMargin(new Insets(3, 1, 1, 1));

        GridBagConstraints posicaoTxtNomeAlimento = new GridBagConstraints();
        posicaoTxtNomeAlimento.gridx = 0;
        posicaoTxtNomeAlimento.gridy = 3;
        posicaoTxtNomeAlimento.weightx = 0;
        posicaoTxtNomeAlimento.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTxtNomeAlimento.anchor = GridBagConstraints.CENTER;
        posicaoTxtNomeAlimento.fill = GridBagConstraints.NONE;
        posicaoTxtNomeAlimento.insets = new Insets(0, 0, 10, 0); // margem superior
        centralPanel.add(txtNomeAlimento, posicaoTxtNomeAlimento);

        JLabel lblQuantidade = new JLabel("Quantidade(g)");
        lblQuantidade.setFont(new Font("Calibri", Font.BOLD, 20));
        centralPanel.add(lblQuantidade);

        GridBagConstraints posicaoLblQuantidade = new GridBagConstraints();
        posicaoLblQuantidade.gridx = 0;
        posicaoLblQuantidade.gridy = 4;
        posicaoLblQuantidade.weightx = 0;
        posicaoLblQuantidade.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblQuantidade.anchor = GridBagConstraints.CENTER;
        posicaoLblQuantidade.fill = GridBagConstraints.NONE;
        posicaoLblQuantidade.insets = new Insets(0, 0, 0, 0); // margem superior
        centralPanel.add(lblQuantidade, posicaoLblQuantidade);

        txtQuantidade.setFont(new Font("Calibri", Font.PLAIN, 14));
        txtQuantidade.setToolTipText("Insira a quantidade em gramas");
        txtQuantidade.setMargin(new Insets(3, 1, 1, 1));

        GridBagConstraints posicaoTxtQuantidade = new GridBagConstraints();
        posicaoTxtQuantidade.gridx = 0;
        posicaoTxtQuantidade.gridy = 5;
        posicaoTxtQuantidade.weightx = 0;
        posicaoTxtQuantidade.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTxtQuantidade.anchor = GridBagConstraints.CENTER;
        posicaoTxtQuantidade.fill = GridBagConstraints.NONE;
        posicaoTxtQuantidade.insets = new Insets(0, 0, 10, 0); // margem superior
        centralPanel.add(txtQuantidade, posicaoTxtQuantidade);

        btnBuscar.setToolTipText("Buscar alimento");
        btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        GridBagConstraints posicaoBtnBuscar = new GridBagConstraints();
        posicaoBtnBuscar.gridx = 0;
        posicaoBtnBuscar.gridy = 6;
        posicaoBtnBuscar.weightx = 0;
        posicaoBtnBuscar.weighty = 0; // ← isso força ele a ficar no topo
        posicaoBtnBuscar.anchor = GridBagConstraints.CENTER;
        posicaoBtnBuscar.fill = GridBagConstraints.NONE;
        posicaoBtnBuscar.insets = new Insets(0, 0, 30, 0); // margem superior
        centralPanel.add(btnBuscar, posicaoBtnBuscar);

        JLabel lblSelecioneRefeicao = new JLabel("Selecione a refeição:");
        lblSelecioneRefeicao.setFont(new Font("Calibri", Font.BOLD, 20));

        GridBagConstraints posicaoLblSelcioneRefeicao = new GridBagConstraints();
        posicaoLblSelcioneRefeicao.gridx = 0;
        posicaoLblSelcioneRefeicao.gridy = 0;
        posicaoLblSelcioneRefeicao.weightx = 0;
        posicaoLblSelcioneRefeicao.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblSelcioneRefeicao.anchor = GridBagConstraints.CENTER;
        posicaoLblSelcioneRefeicao.fill = GridBagConstraints.NONE;
        posicaoLblSelcioneRefeicao.insets = new Insets(0, 0, 0, 0); // margem superior
        centralPanel2.add(lblSelecioneRefeicao, posicaoLblSelcioneRefeicao);

        String[] opcoes = {"Refeição 1", "Refeição 2", "Refeição 3", "Refeição 4", "Refeição 5", "Refeição 6"};
        comboBox = new JComboBox<>(opcoes);
        comboBox.setToolTipText("Selecione a refeição");
        comboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

        GridBagConstraints posicaoComboBox = new GridBagConstraints();
        posicaoComboBox.gridx = 0;
        posicaoComboBox.gridy = 1;
        posicaoComboBox.weightx = 0;
        posicaoComboBox.weighty = 0; // ← isso força ele a ficar no topo
        posicaoComboBox.anchor = GridBagConstraints.CENTER;
        posicaoComboBox.fill = GridBagConstraints.NONE;
        posicaoComboBox.insets = new Insets(0, 0, 20, 0); // margem superior
        centralPanel2.add(comboBox, posicaoComboBox);

        JLabel lblProteina = new JLabel("Proteína(g):");
        lblProteina.setFont(new Font("Calibri", Font.BOLD, 20));

        GridBagConstraints posicaoLblProteina = new GridBagConstraints();
        posicaoLblProteina.gridx = 0;
        posicaoLblProteina.gridy = 2;
        posicaoLblProteina.weightx = 0;
        posicaoLblProteina.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblProteina.anchor = GridBagConstraints.CENTER;
        posicaoLblProteina.fill = GridBagConstraints.NONE;
        posicaoLblProteina.insets = new Insets(0, 0, 10, 0); // margem superior
        centralPanel2.add(lblProteina, posicaoLblProteina);

        lblResultadoProteina.setFont(new Font("Calibri", Font.PLAIN, 14));

        GridBagConstraints posicaoLblResultadoProteina = new GridBagConstraints();
        posicaoLblResultadoProteina.gridx = 1;
        posicaoLblResultadoProteina.gridy = 2;
        posicaoLblResultadoProteina.weightx = 0;
        posicaoLblResultadoProteina.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblResultadoProteina.anchor = GridBagConstraints.CENTER;
        posicaoLblResultadoProteina.fill = GridBagConstraints.NONE;
        posicaoLblResultadoProteina.insets = new Insets(0, 0, 10, 0); // margem superior
        centralPanel2.add(lblResultadoProteina, posicaoLblResultadoProteina);

        JLabel lblCarboidrato = new JLabel("Carboidrato(g):");
        lblCarboidrato.setFont(new Font("Calibri", Font.BOLD, 20));

        GridBagConstraints posicaoLblCarboidrato = new GridBagConstraints();
        posicaoLblCarboidrato.gridx = 0;
        posicaoLblCarboidrato.gridy = 3;
        posicaoLblCarboidrato.weightx = 0;
        posicaoLblCarboidrato.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblCarboidrato.anchor = GridBagConstraints.CENTER;
        posicaoLblCarboidrato.fill = GridBagConstraints.NONE;
        posicaoLblCarboidrato.insets = new Insets(0, 0, 10, 0); // margem superior
        centralPanel2.add(lblCarboidrato, posicaoLblCarboidrato);

        lblResultadoCarboidrato.setFont(new Font("Calibri", Font.PLAIN, 14));

        GridBagConstraints posicaoLblResultadoCarboidrato = new GridBagConstraints();
        posicaoLblResultadoCarboidrato.gridx = 1;
        posicaoLblResultadoCarboidrato.gridy = 3;
        posicaoLblResultadoCarboidrato.weightx = 0;
        posicaoLblResultadoCarboidrato.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblResultadoCarboidrato.anchor = GridBagConstraints.CENTER;
        posicaoLblResultadoCarboidrato.fill = GridBagConstraints.NONE;
        posicaoLblResultadoCarboidrato.insets = new Insets(0, 0, 10, 0); // margem superior
        centralPanel2.add(lblResultadoCarboidrato, posicaoLblResultadoCarboidrato);

        JLabel lblGordura = new JLabel("Gordura(g):");
        lblGordura.setFont(new Font("Calibri", Font.BOLD, 20));

        GridBagConstraints posicaoLblGordura = new GridBagConstraints();
        posicaoLblGordura.gridx = 0;
        posicaoLblGordura.gridy = 4;
        posicaoLblGordura.weightx = 0;
        posicaoLblGordura.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblGordura.anchor = GridBagConstraints.CENTER;
        posicaoLblGordura.fill = GridBagConstraints.NONE;
        posicaoLblGordura.insets = new Insets(0, 0, 10, 0); // margem superior
        centralPanel2.add(lblGordura, posicaoLblGordura);

        lblResultadoGordura.setFont(new Font("Calibri", Font.PLAIN, 14));

        GridBagConstraints posicaoLblResultadoGordura = new GridBagConstraints();
        posicaoLblResultadoGordura.gridx = 1;
        posicaoLblResultadoGordura.gridy = 4;
        posicaoLblResultadoGordura.weightx = 0;
        posicaoLblResultadoGordura.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblResultadoGordura.anchor = GridBagConstraints.CENTER;
        posicaoLblResultadoGordura.fill = GridBagConstraints.NONE;
        posicaoLblResultadoGordura.insets = new Insets(0, 0, 10, 0); // margem superior
        centralPanel2.add(lblResultadoGordura, posicaoLblResultadoGordura);

        JLabel lblKcal = new JLabel("Kcal:");
        lblKcal.setFont(new Font("Calibri", Font.BOLD, 20));

        GridBagConstraints posicaoLblKcal = new GridBagConstraints();
        posicaoLblKcal.gridx = 0;
        posicaoLblKcal.gridy = 5;
        posicaoLblKcal.weightx = 0;
        posicaoLblKcal.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblKcal.anchor = GridBagConstraints.CENTER;
        posicaoLblKcal.fill = GridBagConstraints.NONE;
        posicaoLblKcal.insets = new Insets(0, 0, 10, 0); // margem superior
        centralPanel2.add(lblKcal, posicaoLblKcal);

        lblResultadoKcal.setFont(new Font("Calibri", Font.PLAIN, 14));

        GridBagConstraints posicaoLblResultadoKcal = new GridBagConstraints();
        posicaoLblResultadoKcal.gridx = 1;
        posicaoLblResultadoKcal.gridy = 5;
        posicaoLblResultadoKcal.weightx = 0;
        posicaoLblResultadoKcal.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblResultadoKcal.anchor = GridBagConstraints.CENTER;
        posicaoLblResultadoKcal.fill = GridBagConstraints.NONE;
        posicaoLblResultadoKcal.insets = new Insets(0, 0, 10, 0); // margem superior
        centralPanel2.add(lblResultadoKcal, posicaoLblResultadoKcal);

        btnAdicionarAlimento.setToolTipText("Adicionar alimento");
        btnAdicionarAlimento.setCursor(new Cursor(Cursor.HAND_CURSOR));

        GridBagConstraints posicaoBtnAdicionarAlimento = new GridBagConstraints();
        posicaoBtnAdicionarAlimento.gridx = 0;
        posicaoBtnAdicionarAlimento.gridy = 6;
        posicaoBtnAdicionarAlimento.weightx = 0;
        posicaoBtnAdicionarAlimento.weighty = 0; // ← isso força ele a ficar no topo
        posicaoBtnAdicionarAlimento.anchor = GridBagConstraints.CENTER;
        posicaoBtnAdicionarAlimento.fill = GridBagConstraints.NONE;
        posicaoBtnAdicionarAlimento.insets = new Insets(0, 0, 0, 0); // margem superior
        centralPanel2.add(btnAdicionarAlimento, posicaoBtnAdicionarAlimento);

        setContentPane(backgroundPanel);
    }

    public void adicionarRefeicao() {
        configurarUI();
        setVisible(true);
        carregarAlimento();
        adicionarAlimentoRefeicao();
        CadastroAlimentoView();
        calculoTmbView();
        refeicoesView();
        tabelaAlimentosView();
        sair();
    }

    public void carregarAlimento() {
        btnBuscar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AlimentoDao dao = new AlimentoDao();
                Alimento alimento = dao.buscarPorNome(txtNomeAlimento.getText());
                SignUpView viewSignup = new SignUpView();
                EditarAlimentoView editarView = new EditarAlimentoView();

                if (!emptyFields()) {
                    if (viewSignup.validarNome(txtNomeAlimento.getText()) == false) {
                        JOptionPane.showMessageDialog(null, "Nome deve conter apenas letras!");
                    } else if (editarView.validarEntradaNumerica(txtQuantidade.getText()) == false) {
                        JOptionPane.showMessageDialog(null, "Quantidade no formato incorreto! \n"
                                + "Ex: 000 ou 000,0 ou 000,00");
                    } else {
                        if (alimento != null) {
                            double quantidade = Double.parseDouble(txtQuantidade.getText().replace(",", "."));
                            double proteina, carboidrato, gordura, kcal;

                            proteina = (quantidade * Double.parseDouble(alimento.getProteina().replace(",", "."))) / 100;
                            carboidrato = (quantidade * Double.parseDouble(alimento.getCarboidrato().replace(",", "."))) / 100;
                            gordura = (quantidade * Double.parseDouble(alimento.getGordura().replace(",", "."))) / 100;
                            kcal = (proteina * 4) + (carboidrato * 4) + (gordura * 9);

                            DecimalFormat formato = new DecimalFormat("#0.0");
                            String resultadoProteina = formato.format(proteina);
                            String resultadoCarboidrato = formato.format(carboidrato);
                            String resultadoGordura = formato.format(gordura);
                            String resultadoKcal = formato.format(kcal);

                            lblResultadoProteina.setText(resultadoProteina);
                            lblResultadoCarboidrato.setText(resultadoCarboidrato);
                            lblResultadoGordura.setText(resultadoGordura);
                            lblResultadoKcal.setText(resultadoKcal);

                            lblResultadoProteina.setFont(new Font("Calibri", Font.BOLD, 16));
                            lblResultadoCarboidrato.setFont(new Font("Calibri", Font.BOLD, 16));
                            lblResultadoGordura.setFont(new Font("Calibri", Font.BOLD, 16));
                            lblResultadoKcal.setFont(new Font("Calibri", Font.BOLD, 16));

                        } else {
                            JOptionPane.showMessageDialog(null, "Alimento não encontrado");
                        }
                    }
                }
            }
        });
    }

    public void adicionarAlimentoRefeicao() {
        btnAdicionarAlimento.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AlimentoDao dao = new AlimentoDao();
                Alimento alimento = dao.buscarPorNome(txtNomeAlimento.getText());

                if (!emptyFields()) {
                    if (alimento != null) {
                        double quantidade = Double.parseDouble(txtQuantidade.getText().replace(",", "."));
                        double proteina, carboidrato, gordura, kcal;

                        proteina = (quantidade * Double.parseDouble(alimento.getProteina().replace(",", "."))) / 100;
                            carboidrato = (quantidade * Double.parseDouble(alimento.getCarboidrato().replace(",", "."))) / 100;
                            gordura = (quantidade * Double.parseDouble(alimento.getGordura().replace(",", "."))) / 100;
                        kcal = (proteina * 4) + (carboidrato * 4) + (gordura * 9);

                        DecimalFormat formato = new DecimalFormat("#0.0");
                        String quantidadeFormatada = formato.format(quantidade);
                        String resultadoProteina = formato.format(proteina);
                        String resultadoCarboidrato = formato.format(carboidrato);
                        String resultadoGordura = formato.format(gordura);
                        String resultadoKcal = formato.format(kcal);

                        alimento.setNomeAlimento(alimento.getNomeAlimento());
                        alimento.setQuantidade(quantidadeFormatada);
                        alimento.setProteina(resultadoProteina);
                        alimento.setCarboidrato(resultadoCarboidrato);
                        alimento.setGordura(resultadoGordura);
                        alimento.setKcal(resultadoKcal);

                        if (comboBox.getSelectedIndex() == 0) {
                            dao.salvarAlimentoRefeicao1(alimento);
                        } else if (comboBox.getSelectedIndex() == 1) {
                            dao.salvarAlimentoRefeicao2(alimento);
                        } else if (comboBox.getSelectedIndex() == 2) {
                            dao.salvarAlimentoRefeicao3(alimento);
                        } else if (comboBox.getSelectedIndex() == 3) {
                            dao.salvarAlimentoRefeicao4(alimento);
                        } else if (comboBox.getSelectedIndex() == 4) {
                            dao.salvarAlimentoRefeicao5(alimento);
                        } else {
                            dao.salvarAlimentoRefeicao6(alimento);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Alimento não encontrado");
                    }
                }
            }
        });
    }

    public void CadastroAlimentoView() {
        cadastrarAlimento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CadastroAlimentoView().CadastroAlimentoView();
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

    public void refeicoesView() {
        refeicoes.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RefeicoesView().refeifoes();
            }
        });
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

    public void sair() {
        sair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginView().login();
            }
        });
    }

    public boolean emptyFields() {

        boolean empty = true;

        if (txtNomeAlimento.getText().trim().isEmpty() || txtQuantidade.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os campos precisam ser preenchidos");
        } else {
            empty = false;
        }
        return empty;
    }
}
