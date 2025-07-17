package com.mycompany.view;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JFrame {

    private JTextField txtEmail = new JTextField(12);
    private JPasswordField txtSenha = new JPasswordField(12);
    private JButton btnLogin = new JButton("Login");
    private JLabel lblCadastrar = new JLabel("<html><a href=''>Cadastre-se</a></html>");

    private void configurarUI() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);

        // Painel de fundo com imagem
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

        JLabel lblEmail = new JLabel("Login");
        lblEmail.setFont(new Font("Calibri", Font.BOLD, 20));

        GridBagConstraints positionLblEmail = new GridBagConstraints();
        positionLblEmail.gridx = 0;
        positionLblEmail.gridy = 0;
        positionLblEmail.weightx = 0;
        positionLblEmail.weighty = 0; // ← isso força ele a ficar no topo
        positionLblEmail.anchor = GridBagConstraints.CENTER;
        positionLblEmail.fill = GridBagConstraints.NONE;
        positionLblEmail.insets = new Insets(0, 0, 0, 0); // margem superior
        centralPanel.add(lblEmail, positionLblEmail);

        txtEmail.setFont(new Font("Calibri", Font.PLAIN, 14));
        txtEmail.setToolTipText("Digite seu email");
        txtEmail.setMargin(new Insets(3, 1, 1, 1));

        // Posicionamento no topo absoluto
        GridBagConstraints positionTxtEmail = new GridBagConstraints();
        positionTxtEmail.gridx = 0;
        positionTxtEmail.gridy = 1;
        positionTxtEmail.weightx = 0;
        positionTxtEmail.weighty = 0; // ← isso força ele a ficar no topo
        positionTxtEmail.anchor = GridBagConstraints.CENTER;
        positionTxtEmail.fill = GridBagConstraints.NONE;
        positionTxtEmail.insets = new Insets(0, 0, 10, 0); // margem superior
        centralPanel.add(txtEmail, positionTxtEmail);

        JLabel lblPassword = new JLabel("Senha:");
        lblPassword.setFont(new Font("Calibri", Font.BOLD, 20));

        // Posicionamento no topo absoluto
        GridBagConstraints positionLblPassword = new GridBagConstraints();
        positionLblPassword.gridx = 0;
        positionLblPassword.gridy = 2;
        positionLblPassword.weightx = 0;
        positionLblPassword.weighty = 0; // ← isso força ele a ficar no topo
        positionLblPassword.anchor = GridBagConstraints.CENTER;
        positionLblPassword.fill = GridBagConstraints.NONE;
        positionLblPassword.insets = new Insets(0, 0, 0, 0); // margem superior
        centralPanel.add(lblPassword, positionLblPassword);

        txtSenha.setFont(new Font("Calibri", Font.PLAIN, 14));
        txtSenha.setToolTipText("Digite sua senha");
        txtSenha.setMargin(new Insets(3, 1, 1, 1));

        // Posicionamento no topo absoluto
        GridBagConstraints positionTxtPassword = new GridBagConstraints();
        positionTxtPassword.gridx = 0;
        positionTxtPassword.gridy = 3;
        positionTxtPassword.weightx = 0;
        positionTxtPassword.weighty = 0; // ← isso força ele a ficar no topo
        positionTxtPassword.anchor = GridBagConstraints.CENTER;
        positionTxtPassword.fill = GridBagConstraints.NONE;
        positionTxtPassword.insets = new Insets(0, 0, 5, 0); // margem superior
        centralPanel.add(txtSenha, positionTxtPassword);

        btnLogin.setToolTipText("Login");
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Posicionamento no topo absoluto
        GridBagConstraints positionBtnLogin = new GridBagConstraints();
        positionBtnLogin.gridx = 0;
        positionBtnLogin.gridy = 4;
        positionBtnLogin.weightx = 0;
        positionBtnLogin.weighty = 0; // ← isso força ele a ficar no topo
        positionBtnLogin.anchor = GridBagConstraints.CENTER;
        positionBtnLogin.fill = GridBagConstraints.NONE;
        positionBtnLogin.insets = new Insets(10, 0, 15, 0); // margem superior
        centralPanel.add(btnLogin, positionBtnLogin);

        JLabel lblDontHaveAccount = new JLabel("Não tem uma conta?");
        lblDontHaveAccount.setFont(new Font("Calibri", Font.BOLD, 14));

        // Posicionamento no topo absoluto
        GridBagConstraints positionDontHaveAccount = new GridBagConstraints();
        positionDontHaveAccount.gridx = 0;
        positionDontHaveAccount.gridy = 5;
        positionDontHaveAccount.weightx = 0;
        positionDontHaveAccount.weighty = 0; // ← isso força ele a ficar no topo
        positionDontHaveAccount.anchor = GridBagConstraints.CENTER;
        positionDontHaveAccount.fill = GridBagConstraints.NONE;
        positionDontHaveAccount.insets = new Insets(10, 0, 0, 0); // margem superior
        centralPanel.add(lblDontHaveAccount, positionDontHaveAccount);

        lblCadastrar.setFont(new Font("Calibri", Font.BOLD, 18));
        lblCadastrar.setForeground(Color.WHITE);
        lblCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblCadastrar.setToolTipText("Clique aqui para se cadastrar");

        GridBagConstraints positionSignUp = new GridBagConstraints();
        positionSignUp.gridx = 0;
        positionSignUp.gridy = 6;
        positionSignUp.weightx = 0;
        positionSignUp.weighty = 0; // ← isso força ele a ficar no topo
        positionSignUp.anchor = GridBagConstraints.CENTER;
        positionSignUp.fill = GridBagConstraints.NONE;
        positionSignUp.insets = new Insets(0, 0, 0, 0); // margem superior
        centralPanel.add(lblCadastrar, positionSignUp);

        add(backgroundPanel);
    }

    public void login() {
        configurarUI();
        setVisible(true);
        SignUpView();
        fazerLogin();
    }

    /*public void fazerLogin() {
        btnLogin.addActionListener(new ActionListener() {
            UserDao dao = new UserDao();
            SignUpView signUpView = new SignUpView();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!emptyFields()) {
                    if (validarEmail(txtEmail.getText()) == false) {
                        JOptionPane.showMessageDialog(null, "Formato email incorreto! \n"
                                + "Ex: nome@email.com");
                    } else if (validarSenha(txtSenha.getText()) == false) {
                        JOptionPane.showMessageDialog(null, "A senha deve conter no mínimo 6 caracteres e no máximo 40");
                    } else {
                        if (dao.autenticarUsuario(txtEmail.getText(), txtSenha.getText())) {
                            dispose();
                            new MainView().mainView();
                        } else {
                            System.out.println("Email ou senha inválidos.");
                        }
                    }
                }
            }

        });
    }*/
    public void fazerLogin() {
        btnLogin.addActionListener(new ActionListener() {
            UserDao dao = new UserDao();
            SignUpView signUpView = new SignUpView();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!emptyFields()) {
                    if (validarEmail(txtEmail.getText()) == false) {
                        JOptionPane.showMessageDialog(null, "Formato email incorreto! \n"
                                + "Ex: nome@email.com");
                    } else if (validarSenha(txtSenha.getText()) == false) {
                        JOptionPane.showMessageDialog(null, "A senha deve conter no mínimo 6 caracteres e no máximo 40");
                    } else {
                        User usuario = dao.autenticarUsuario(txtEmail.getText(), txtSenha.getText());
                        if (usuario != null) {
                            dispose();
                            new MainView(usuario).mainView(); // <-- passe o usuário
                        } else {
                            JOptionPane.showMessageDialog(null, "Email ou senha inválidos.");
                        }
                    }
                }
            }

        });
    }

    // Ação ao clicar signUp
    public void SignUpView() {
        lblCadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    dispose();
                    new SignUpView().signUpView();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        );
    }

    public boolean emptyFields() {

        boolean empty = true;

        if (txtEmail.getText().trim().isEmpty() || txtSenha.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os campos precisam ser preenchidos");
        } else {
            empty = false;
        }
        return empty;
    }

    public boolean validarEmail(String email) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validarSenha(String senha) {
        String regex = "^.{6,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(senha);
        return matcher.matches();
    }
}
