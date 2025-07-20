package com.mycompany.view;

import com.mycompany.model.Tmb;
import com.mycompany.model.User;
import com.mycompany.util.dao.UserDao;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculoTmbView extends JFrame {

    private JTextField txtAltura = new JTextField(12);
    private JTextField txtPeso = new JTextField(12);
    private JTextField txtIdade = new JTextField(12);
    private JTextField txtFatorAtividade = new JTextField(12);
    private JButton btnCalcular = new JButton("Calcular");
    private JComboBox<String> comboBox = new JComboBox();
    private JLabel lblResultado = new JLabel("Seu TMB é: ");
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
    private Tmb tmb = new Tmb();

    public CalculoTmbView(User usuario) {
        this.usuarioLogado = usuario;
    }

    private void configurarUI() {
        setTitle("Cálculo Taxa Metabólica Basal");
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
        JPanel painelCentral = new JPanel() {
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

        painelCentral.setOpaque(false);
        painelCentral.setPreferredSize(new Dimension(300, 450));
        painelCentral.setLayout(new GridBagLayout());

        // Posicionamento no topo absoluto
        GridBagConstraints positionPainelCentral = new GridBagConstraints();
        positionPainelCentral.gridx = 0;
        positionPainelCentral.gridy = 0;
        positionPainelCentral.weightx = 0;
        positionPainelCentral.weighty = 0; // ← isso força ele a ficar no topo
        positionPainelCentral.anchor = GridBagConstraints.CENTER;
        positionPainelCentral.fill = GridBagConstraints.NONE;
        positionPainelCentral.insets = new Insets(0, 0, 0, 0); // margem superior
        backgroundPanel.add(painelCentral, positionPainelCentral);

        // Painel transparente com cantos arredondados
        JPanel painelCentral2 = new JPanel() {
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

        painelCentral2.setOpaque(false);
        painelCentral2.setPreferredSize(new Dimension(500, 300));
        painelCentral2.setLayout(new GridBagLayout());

        // Posicionamento no topo absoluto
        GridBagConstraints posicaoPainelCentral2 = new GridBagConstraints();
        posicaoPainelCentral2.gridx = 1;
        posicaoPainelCentral2.gridy = 0;
        posicaoPainelCentral2.weightx = 0;
        posicaoPainelCentral2.weighty = 0; // ← isso força ele a ficar no topo
        posicaoPainelCentral2.anchor = GridBagConstraints.CENTER;
        posicaoPainelCentral2.fill = GridBagConstraints.NONE;
        posicaoPainelCentral2.insets = new Insets(0, 50, 0, 0); // margem superior
        backgroundPanel.add(painelCentral2, posicaoPainelCentral2);

        String texto = "<html><div style='width:300px;'>"
                + "<b>Fator de atividade (FA):</b><br><br>"
                + "1,3 - sedentário.<br>"
                + "1,4 - levemente ativo.<br>"
                + "1,5 - treino e cardio iniciante a intermediário quase todos os dias, com rotina de pouca atividade.<br>"
                + "1,6 - treino e cardio avançado ou extremamente avançado com rotina de pouca atividade.<br>"
                + "1,7 - treino e cardio avançado ou extremamente avançado com rotina de muita atividade ou hormonizado."
                + "</div></html>";

        JLabel lblTexto = new JLabel(texto);
        lblTexto.setFont(new Font("Calibri", Font.BOLD, 18));

        GridBagConstraints posicaoLblTexto = new GridBagConstraints();
        posicaoLblTexto.gridx = 0;
        posicaoLblTexto.gridy = 0;
        posicaoLblTexto.weightx = 0;
        posicaoLblTexto.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblTexto.anchor = GridBagConstraints.CENTER;
        posicaoLblTexto.fill = GridBagConstraints.NONE;
        posicaoLblTexto.insets = new Insets(0, 20, 0, 20); // margem superior
        painelCentral2.add(lblTexto, posicaoLblTexto);

        JLabel lblGenero = new JLabel("Gênero:");
        lblGenero.setFont(new Font("Calibri", Font.BOLD, 20));
        painelCentral.add(lblGenero);

        GridBagConstraints posicaoLblGenero = new GridBagConstraints();
        posicaoLblGenero.gridx = 0;
        posicaoLblGenero.gridy = 0;
        posicaoLblGenero.weightx = 0;
        posicaoLblGenero.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblGenero.anchor = GridBagConstraints.CENTER;
        posicaoLblGenero.fill = GridBagConstraints.NONE;
        posicaoLblGenero.insets = new Insets(0, 0, 0, 0); // margem superior
        painelCentral.add(lblGenero, posicaoLblGenero);

        String[] opcoes = {"Selecione", "Masculino", "Feminino"};
        comboBox = new JComboBox<>(opcoes);
        comboBox.setToolTipText("Selecione seu gênero");

        GridBagConstraints posicaoComboBox = new GridBagConstraints();
        posicaoComboBox.gridx = 0;
        posicaoComboBox.gridy = 1;
        posicaoComboBox.weightx = 0;
        posicaoComboBox.weighty = 0; // ← isso força ele a ficar no topo
        posicaoComboBox.anchor = GridBagConstraints.CENTER;
        posicaoComboBox.fill = GridBagConstraints.NONE;
        posicaoComboBox.insets = new Insets(0, 0, 20, 0); // margem superior
        painelCentral.add(comboBox, posicaoComboBox);

        JLabel lblIdade = new JLabel("Idade:");
        lblIdade.setFont(new Font("Calibri", Font.BOLD, 20));
        painelCentral.add(lblIdade);

        GridBagConstraints posicaoLblIdade = new GridBagConstraints();
        posicaoLblIdade.gridx = 0;
        posicaoLblIdade.gridy = 2;
        posicaoLblIdade.weightx = 0;
        posicaoLblIdade.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblIdade.anchor = GridBagConstraints.CENTER;
        posicaoLblIdade.fill = GridBagConstraints.NONE;
        posicaoLblIdade.insets = new Insets(0, 0, 0, 0); // margem superior
        painelCentral.add(lblIdade, posicaoLblIdade);

        txtIdade.setFont(new Font("Calibri", Font.PLAIN, 14));
        txtIdade.setToolTipText("Insira sua idade");
        txtIdade.setMargin(new Insets(3, 1, 1, 1));

        // Posicionamento no topo absoluto
        GridBagConstraints posicaoTxtIdade = new GridBagConstraints();
        posicaoTxtIdade.gridx = 0;
        posicaoTxtIdade.gridy = 3;
        posicaoTxtIdade.weightx = 0;
        posicaoTxtIdade.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTxtIdade.anchor = GridBagConstraints.CENTER;
        posicaoTxtIdade.fill = GridBagConstraints.NONE;
        posicaoTxtIdade.insets = new Insets(0, 0, 10, 0); // margem superior
        painelCentral.add(txtIdade, posicaoTxtIdade);

        JLabel lblAltura = new JLabel("Altura(cm):");
        lblAltura.setFont(new Font("Calibri", Font.BOLD, 20));
        painelCentral.add(lblAltura);

        GridBagConstraints posicaoLblAltura = new GridBagConstraints();
        posicaoLblAltura.gridx = 0;
        posicaoLblAltura.gridy = 4;
        posicaoLblAltura.weightx = 0;
        posicaoLblAltura.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblAltura.anchor = GridBagConstraints.CENTER;
        posicaoLblAltura.fill = GridBagConstraints.NONE;
        posicaoLblAltura.insets = new Insets(0, 0, 0, 0); // margem superior
        painelCentral.add(lblAltura, posicaoLblAltura);

        txtAltura.setFont(new Font("Calibri", Font.PLAIN, 14));
        txtAltura.setToolTipText("Insira sua altura em centímetros");
        txtAltura.setMargin(new Insets(3, 1, 1, 1));

        // Posicionamento no topo absoluto
        GridBagConstraints posicaoTxtAltura = new GridBagConstraints();
        posicaoTxtAltura.gridx = 0;
        posicaoTxtAltura.gridy = 5;
        posicaoTxtAltura.weightx = 0;
        posicaoTxtAltura.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTxtAltura.anchor = GridBagConstraints.CENTER;
        posicaoTxtAltura.fill = GridBagConstraints.NONE;
        posicaoTxtAltura.insets = new Insets(0, 0, 10, 0); // margem superior
        painelCentral.add(txtAltura, posicaoTxtAltura);

        JLabel lblPeso = new JLabel("Peso(kg):");
        lblPeso.setFont(new Font("Calibri", Font.BOLD, 20));

        // Posicionamento no topo absoluto
        GridBagConstraints posicaoLblPeso = new GridBagConstraints();
        posicaoLblPeso.gridx = 0;
        posicaoLblPeso.gridy = 6;
        posicaoLblPeso.weightx = 0;
        posicaoLblPeso.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblPeso.anchor = GridBagConstraints.CENTER;
        posicaoLblPeso.fill = GridBagConstraints.NONE;
        posicaoLblPeso.insets = new Insets(0, 0, 0, 0); // margem superior
        painelCentral.add(lblPeso, posicaoLblPeso);

        txtPeso.setFont(new Font("Calibri", Font.PLAIN, 14));
        txtPeso.setToolTipText("Insira seu peso");
        txtPeso.setMargin(new Insets(3, 1, 1, 1));

        // Posicionamento no topo absoluto
        GridBagConstraints posicaoTxtPeso = new GridBagConstraints();
        posicaoTxtPeso.gridx = 0;
        posicaoTxtPeso.gridy = 7;
        posicaoTxtPeso.weightx = 0;
        posicaoTxtPeso.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTxtPeso.anchor = GridBagConstraints.CENTER;
        posicaoTxtPeso.fill = GridBagConstraints.NONE;
        posicaoTxtPeso.insets = new Insets(0, 0, 10, 0); // margem superior
        painelCentral.add(txtPeso, posicaoTxtPeso);

        JLabel lblFatorAtividade = new JLabel("Fator de atividade:");
        lblFatorAtividade.setFont(new Font("Calibri", Font.BOLD, 20));
        painelCentral.add(lblFatorAtividade);

        GridBagConstraints posicaoLblFatorAtividade = new GridBagConstraints();
        posicaoLblFatorAtividade.gridx = 0;
        posicaoLblFatorAtividade.gridy = 8;
        posicaoLblFatorAtividade.weightx = 0;
        posicaoLblFatorAtividade.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblFatorAtividade.anchor = GridBagConstraints.CENTER;
        posicaoLblFatorAtividade.fill = GridBagConstraints.NONE;
        posicaoLblFatorAtividade.insets = new Insets(0, 0, 0, 0); // margem superior
        painelCentral.add(lblFatorAtividade, posicaoLblFatorAtividade);

        txtFatorAtividade.setFont(new Font("Calibri", Font.PLAIN, 14));
        txtFatorAtividade.setToolTipText("Insira seu fator de atividade");
        txtFatorAtividade.setMargin(new Insets(3, 1, 1, 1));

        // Posicionamento no topo absoluto
        GridBagConstraints posicaoTxtFatorAtividade = new GridBagConstraints();
        posicaoTxtFatorAtividade.gridx = 0;
        posicaoTxtFatorAtividade.gridy = 9;
        posicaoTxtFatorAtividade.weightx = 0;
        posicaoTxtFatorAtividade.weighty = 0; // ← isso força ele a ficar no topo
        posicaoTxtFatorAtividade.anchor = GridBagConstraints.CENTER;
        posicaoTxtFatorAtividade.fill = GridBagConstraints.NONE;
        posicaoTxtFatorAtividade.insets = new Insets(0, 0, 5, 0); // margem superior
        painelCentral.add(txtFatorAtividade, posicaoTxtFatorAtividade);

        btnCalcular.setToolTipText("Calcular");
        btnCalcular.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Posicionamento no topo absoluto
        GridBagConstraints posicaoBotaoCalcular = new GridBagConstraints();
        posicaoBotaoCalcular.gridx = 0;
        posicaoBotaoCalcular.gridy = 10;
        posicaoBotaoCalcular.weightx = 0;
        posicaoBotaoCalcular.weighty = 0; // ← isso força ele a ficar no topo
        posicaoBotaoCalcular.anchor = GridBagConstraints.CENTER;
        posicaoBotaoCalcular.fill = GridBagConstraints.NONE;
        posicaoBotaoCalcular.insets = new Insets(5, 0, 0, 0); // margem superior
        painelCentral.add(btnCalcular, posicaoBotaoCalcular);

        lblResultado.setFont(new Font("Calibri", Font.BOLD, 20));

        GridBagConstraints posicaoLblResultado = new GridBagConstraints();
        posicaoLblResultado.gridx = 0;
        posicaoLblResultado.gridy = 11;
        posicaoLblResultado.weightx = 0;
        posicaoLblResultado.weighty = 0; // ← isso força ele a ficar no topo
        posicaoLblResultado.anchor = GridBagConstraints.CENTER;
        posicaoLblResultado.fill = GridBagConstraints.NONE;
        posicaoLblResultado.insets = new Insets(20, 0, 0, 0); // margem superior
        painelCentral.add(lblResultado, posicaoLblResultado);

        setContentPane(backgroundPanel);
    }

    public void calculoTmbView() {
        configurarUI();
        setVisible(true);
        calcularTmb();
        CadastroAlimentoView();
        refeicoesView();
        tabelaAlimentosView();
        adicionarRefeicaoView();
        sair();
    }

    public void calcularTmb() {
        btnCalcular.addActionListener(new ActionListener() {
            double resultadoTmb = 0;
            UserDao dao = new UserDao();
            
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!emptyFields()) {
                    if (!validarIdadeAltura(txtIdade.getText())) {
                        JOptionPane.showMessageDialog(null, "Idade no formato incorreto!\nEx: 30");
                        txtIdade.requestFocus();
                        return;
                    }

                    if (!validarIdadeAltura(txtAltura.getText())) {
                        JOptionPane.showMessageDialog(null, "Altura no formato incorreto!\nEx: 175");
                        txtAltura.requestFocus();
                        return;
                    }

                    if (!validarPesoFator(txtPeso.getText())) {
                        JOptionPane.showMessageDialog(null, "Peso no formato incorreto!\nEx: 70 ou 70,5");
                        txtPeso.requestFocus();
                        return;
                    }

                    if (!validarPesoFator(txtFatorAtividade.getText())) {
                        JOptionPane.showMessageDialog(null, "Fator atividade no formato incorreto!\nEx: 1,3");
                        txtFatorAtividade.requestFocus();
                        return;
                    }

                    if (comboBox.getSelectedIndex() == 0) {
                        JOptionPane.showMessageDialog(null, "Selecione um gênero!");
                        comboBox.requestFocus();
                        return;
                    }

                    try {
                        double fatorAtividade = Double.parseDouble(txtFatorAtividade.getText().replace(",", "."));
                        double peso = Double.parseDouble(txtPeso.getText().replace(",", "."));
                        double altura = Double.parseDouble(txtAltura.getText());
                        double idade = Double.parseDouble(txtIdade.getText());

                        if (comboBox.getSelectedIndex() == 1) { // Masculino
                            resultadoTmb = fatorAtividade * (88.362 + (13.397 * peso) + (4.799 * altura) - (5.677 * idade));
                            
                        } else { // Feminino
                            resultadoTmb = fatorAtividade * (447.593 + (9.247 * peso) + (3.098 * altura) - (4.330 * idade));
                        }
                        
                        double resultado = Math.round(resultadoTmb);
                        lblResultado.setText("Seu TMB é: " + resultado);
                        
                        tmb.setIdade(Integer.parseInt(txtIdade.getText()));
                        tmb.setAltura(Integer.parseInt(txtAltura.getText()));
                        tmb.setPeso(peso);
                        tmb.setFatorAtividade(fatorAtividade);
                        tmb.setTmb(resultado);
                        tmb.setIdUsuario(usuarioLogado.getId());
                        usuarioLogado.setTmb(resultado);
                        dao.salvarTmb(tmb);

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao converter valores. Verifique os campos.");
                    }
                }
            }
        });
    }

    public void CadastroAlimentoView() {
        cadastrarAlimento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CadastroAlimentoView(usuarioLogado).cadastroAlimentoView();
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

    public void tabelaAlimentosView() {
        tabelaAlimentos.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainView(usuarioLogado).mainView();
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

    public boolean emptyFields() {

        boolean empty = true;

        if (txtIdade.getText().trim().isEmpty() || txtAltura.getText().trim().isEmpty() || txtPeso.getText().trim().isEmpty()
                || txtFatorAtividade.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os campos precisam ser preenchidos");
        } else {
            empty = false;
        }
        return empty;
    }

    public boolean validarIdadeAltura(String idadeAltura) {
        String regex = "[0-9]{1,3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(idadeAltura);
        return matcher.matches();
    }

    public boolean validarPesoFator(String pesoFator) {
        String regex = "^[0-9]{1,3}([.,][0-9]{1,2})?$";
        return pesoFator.matches(regex);
    }

}
