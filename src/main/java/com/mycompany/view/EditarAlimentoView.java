package com.mycompany.view;

import com.mycompany.model.Alimento;
import com.mycompany.model.User;
import com.mycompany.util.dao.AlimentoDao;
import java.awt.Color;
import java.awt.Cursor;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditarAlimentoView extends JFrame {

    private JTextField txtNomeAlimento = new JTextField(12);
    private JTextField txtQuantidade = new JTextField(12);
    private JTextField txtProteina = new JTextField(12);
    private JTextField txtCarboidrato = new JTextField(12);
    private JTextField txtGordura = new JTextField(12);
    private JButton btnSalvar = new JButton("Salvar");
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
    
    public EditarAlimentoView(User usuario) {
        this.usuarioLogado = usuario;
    }

    Alimento alimento = new Alimento();

    //Método construtor que configura o JFrame
    private void configurarUI() {
        setTitle("Editar Alimento");
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
        // Painel de fundo
        JPanel backgroundPanel = new JPanel() {
            private final Image imagemFundo = new ImageIcon("C:\\Users\\Erick\\OneDrive\\Documentos\\NetBeansProjects\\1-Projetos\\ProjetoNutricao\\src\\images\\background.jpg").getImage();

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
        centralPanel.setPreferredSize(new Dimension(300, 400));
        centralPanel.setLayout(new GridBagLayout());

        // Posicionamento no topo absoluto
        GridBagConstraints positionPainelCentral = new GridBagConstraints();
        positionPainelCentral.gridx = 0;
        positionPainelCentral.gridy = 0;
        positionPainelCentral.weightx = 0;
        positionPainelCentral.weighty = 0; // ← isso força ele a ficar no topo
        positionPainelCentral.anchor = GridBagConstraints.CENTER;
        positionPainelCentral.fill = GridBagConstraints.NONE;
        positionPainelCentral.insets = new Insets(0, 0, 0, 0); // margem superior
        backgroundPanel.add(centralPanel, positionPainelCentral);

        JLabel lblNomeAlimento = new JLabel("Nome alimento:");
        lblNomeAlimento.setFont(new Font("Calibri", Font.BOLD, 20));
        centralPanel.add(lblNomeAlimento);

        txtNomeAlimento.setFont(new Font("Calibri", Font.PLAIN, 14));
        txtNomeAlimento.setToolTipText("Insira o nome do alimento");
        txtNomeAlimento.setMargin(new Insets(3, 1, 1, 1));

        GridBagConstraints posicaoTxtNomeAlimento = new GridBagConstraints();
        posicaoTxtNomeAlimento.gridx = 0;
        posicaoTxtNomeAlimento.gridy = 1;
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
        posicaoLblQuantidade.gridy = 2;
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
        posicaoTxtQuantidade.gridy = 3;
        posicaoTxtQuantidade.weightx = 0;
        posicaoTxtQuantidade.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTxtQuantidade.anchor = GridBagConstraints.CENTER;
        posicaoTxtQuantidade.fill = GridBagConstraints.NONE;
        posicaoTxtQuantidade.insets = new Insets(0, 0, 10, 0); // margem superior
        centralPanel.add(txtQuantidade, posicaoTxtQuantidade);

        JLabel lblProteina = new JLabel("Proteína(g)");
        lblProteina.setFont(new Font("Calibri", Font.BOLD, 20));
        centralPanel.add(lblProteina);

        GridBagConstraints posicaoLblProteina = new GridBagConstraints();
        posicaoLblProteina.gridx = 0;
        posicaoLblProteina.gridy = 4;
        posicaoLblProteina.weightx = 0;
        posicaoLblProteina.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblProteina.anchor = GridBagConstraints.CENTER;
        posicaoLblProteina.fill = GridBagConstraints.NONE;
        posicaoLblProteina.insets = new Insets(0, 0, 0, 0); // margem superior
        centralPanel.add(lblProteina, posicaoLblProteina);

        txtProteina.setFont(new Font("Calibri", Font.PLAIN, 14));
        txtProteina.setToolTipText("Insira a quantidade de proteína");
        txtProteina.setMargin(new Insets(3, 1, 1, 1));

        GridBagConstraints posicaoTxtProteina = new GridBagConstraints();
        posicaoTxtProteina.gridx = 0;
        posicaoTxtProteina.gridy = 5;
        posicaoTxtProteina.weightx = 0;
        posicaoTxtProteina.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTxtProteina.anchor = GridBagConstraints.CENTER;
        posicaoTxtProteina.fill = GridBagConstraints.NONE;
        posicaoTxtProteina.insets = new Insets(0, 0, 10, 0); // margem superior
        centralPanel.add(txtProteina, posicaoTxtProteina);

        JLabel lblCarboidrato = new JLabel("Carboidrato(g)");
        lblCarboidrato.setFont(new Font("Calibri", Font.BOLD, 20));
        centralPanel.add(lblCarboidrato);

        GridBagConstraints posicaoLblCarboidrato = new GridBagConstraints();
        posicaoLblCarboidrato.gridx = 0;
        posicaoLblCarboidrato.gridy = 6;
        posicaoLblCarboidrato.weightx = 0;
        posicaoLblCarboidrato.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblCarboidrato.anchor = GridBagConstraints.CENTER;
        posicaoLblCarboidrato.fill = GridBagConstraints.NONE;
        posicaoLblCarboidrato.insets = new Insets(0, 0, 0, 0); // margem superior
        centralPanel.add(lblCarboidrato, posicaoLblCarboidrato);

        txtCarboidrato.setFont(new Font("Calibri", Font.PLAIN, 14));
        txtCarboidrato.setToolTipText("Insira a quantidade de carboidrato");
        txtCarboidrato.setMargin(new Insets(3, 1, 1, 1));

        GridBagConstraints posicaoTxtCarboidrato = new GridBagConstraints();
        posicaoTxtCarboidrato.gridx = 0;
        posicaoTxtCarboidrato.gridy = 7;
        posicaoTxtCarboidrato.weightx = 0;
        posicaoTxtCarboidrato.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTxtCarboidrato.anchor = GridBagConstraints.CENTER;
        posicaoTxtCarboidrato.fill = GridBagConstraints.NONE;
        posicaoTxtCarboidrato.insets = new Insets(0, 0, 10, 0); // margem superior
        centralPanel.add(txtCarboidrato, posicaoTxtCarboidrato);

        JLabel lblGordura = new JLabel("Gordura(g)");
        lblGordura.setFont(new Font("Calibri", Font.BOLD, 20));
        centralPanel.add(lblGordura);

        GridBagConstraints posicaoLblGordura = new GridBagConstraints();
        posicaoLblGordura.gridx = 0;
        posicaoLblGordura.gridy = 8;
        posicaoLblGordura.weightx = 0;
        posicaoLblGordura.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblGordura.anchor = GridBagConstraints.CENTER;
        posicaoLblGordura.fill = GridBagConstraints.NONE;
        posicaoLblGordura.insets = new Insets(0, 0, 0, 0); // margem superior
        centralPanel.add(lblGordura, posicaoLblGordura);

        txtGordura.setFont(new Font("Calibri", Font.PLAIN, 14));
        txtGordura.setToolTipText("Insira a quantidade de gordura");
        txtGordura.setMargin(new Insets(3, 1, 1, 1));

        GridBagConstraints posicaoTxtGordura = new GridBagConstraints();
        posicaoTxtGordura.gridx = 0;
        posicaoTxtGordura.gridy = 9;
        posicaoTxtGordura.weightx = 0;
        posicaoTxtGordura.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTxtGordura.anchor = GridBagConstraints.CENTER;
        posicaoTxtGordura.fill = GridBagConstraints.NONE;
        posicaoTxtGordura.insets = new Insets(0, 0, 20, 0); // margem superior
        centralPanel.add(txtGordura, posicaoTxtGordura);

        btnSalvar.setToolTipText("Cadastrar");
        btnSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        GridBagConstraints posicaoBotaoCadastrar = new GridBagConstraints();
        posicaoBotaoCadastrar.gridx = 0;
        posicaoBotaoCadastrar.gridy = 10;
        posicaoBotaoCadastrar.weightx = 0;
        posicaoBotaoCadastrar.weighty = 0; // ← isso força ele a ficar no topo
        posicaoBotaoCadastrar.anchor = GridBagConstraints.CENTER;
        posicaoBotaoCadastrar.fill = GridBagConstraints.NONE;
        posicaoBotaoCadastrar.insets = new Insets(0, 0, 0, 0); // margem superior
        centralPanel.add(btnSalvar, posicaoBotaoCadastrar);

        setContentPane(backgroundPanel);
    }

    public void editarAlimentoView() {
        configurarUI();
        setVisible(true);
        editarAlimento();
        tabelaAlimentosView();
        cadastroAlimentoView();
        calculoTmbView();
        refeicoesView();
        adicionarRefeicaoView();
        sair();
    }

    //Método que edita/atualiza o alimento
    public void editarAlimento() {
        btnSalvar.addActionListener(new ActionListener() {
            AlimentoDao dao = new AlimentoDao();
            SignUpView view = new SignUpView();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!emptyFields()) {
                    if (view.validarNome(txtNomeAlimento.getText()) == false) {
                        JOptionPane.showMessageDialog(null, "Nome deve conter apenas letras!");
                    } else if (validarEntradaNumerica(txtQuantidade.getText()) == false) {
                        JOptionPane.showMessageDialog(null, "Quantidade no formato incorreto! \n"
                                + "Ex: 000 ou 000,0 ou 000,00");
                    } else if (validarEntradaNumerica(txtProteina.getText()) == false) {
                        JOptionPane.showMessageDialog(null, "Proteína no formato incorreto! \n"
                                + "Ex: 000 ou 000,0 ou 000,00");
                    } else if (validarEntradaNumerica(txtCarboidrato.getText()) == false) {
                        JOptionPane.showMessageDialog(null, "Carboidrato no formato incorreto! \n"
                                + "Ex: 000 ou 000,0 ou 000,00");
                    } else if (validarEntradaNumerica(txtGordura.getText()) == false) {
                        JOptionPane.showMessageDialog(null, "Gordura no formato incorreto! \n"
                                + "Ex: 000 ou 000,0 ou 000,00");
                    } else {
                        alimento.setNomeAlimento(txtNomeAlimento.getText());
                        alimento.setQuantidade(txtQuantidade.getText());
                        alimento.setProteina(txtProteina.getText());
                        alimento.setCarboidrato(txtCarboidrato.getText());
                        alimento.setGordura(txtGordura.getText());
                        alimento.setKcal(alimento.getKcal());

                        dao.atualizarAlimento(alimento);
                        dispose();
                        new MainView(usuarioLogado).mainView();
                    }
                }
            }
        });
    }

    //Método que pega o alimento escolhido pelo usuário
    public Alimento getAlimento(Alimento alimento) {
        txtNomeAlimento.setText(alimento.getNomeAlimento());
        txtQuantidade.setText(alimento.getQuantidade());
        txtProteina.setText(alimento.getProteina());
        txtCarboidrato.setText(alimento.getCarboidrato());
        txtGordura.setText(alimento.getGordura());
        this.alimento = alimento;
        return alimento;
    }

    //Método que chama a tela calculoTmbView
    public void calculoTmbView() {
        calcularTmb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CalculoTmbView(usuarioLogado).calculoTmbView();
            }
        });
    }

    //Método que chama a tela refeicoesView
    public void refeicoesView() {
        refeicoes.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RefeicoesView(usuarioLogado).refeifoes();
            }
        });
    }

    //Método que chama a tela mainView
    public void tabelaAlimentosView() {
        tabelaAlimentos.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainView(usuarioLogado).mainView();
            }
        });
    }

    //Método que chama a tela cadastroAlimentoView
    public void cadastroAlimentoView() {
        cadastrarAlimento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CadastroAlimentoView(usuarioLogado).cadastroAlimentoView();
            }
        });
    }

    //Método que chama a tela adicionarAlimentoRefeicao
    public void adicionarRefeicaoView() {
        adicionarRefeicao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AdicionarAlimentoRefeicao(usuarioLogado).adicionarRefeicao();
            }
        });
    }

    //Método que desloga o usuário
    public void sair() {
        sair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginView().login();
            }
        });
    }

    //Método que verifica os campos vazios
    public boolean emptyFields() {

        boolean empty = true;

        if (txtNomeAlimento.getText().trim().isEmpty() || txtQuantidade.getText().trim().isEmpty() || txtProteina.getText().trim().isEmpty()
                || txtCarboidrato.getText().trim().isEmpty() || txtGordura.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os campos precisam ser preenchidos");
        } else {
            empty = false;
        }
        return empty;
    }

    //Método que valida as entradas numéricas
    public boolean validarEntradaNumerica(String numero) {
        String regex = "^\\d+(,\\d+)?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(numero);
        return matcher.matches();
    }
}
