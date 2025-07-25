package com.mycompany.view;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUpView extends JFrame {

    private JTextField txtNome = new JTextField(12);
    private JTextField txtEmail = new JTextField(12);
    private JPasswordField txtSenha = new JPasswordField(12);
    private JButton btnCadastrar = new JButton("Cadastrar");
    private JLabel lblVoltar = new JLabel("<html><a href=''>Voltar</a></html>");

    //Método que configura o JFrame
    private void configurarUI() {
        setTitle("Cadastre-se");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

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

        JLabel lblName = new JLabel("Nome");
        lblName.setFont(new Font("Calibri", Font.BOLD, 20));

        GridBagConstraints positionLblName = new GridBagConstraints();
        positionLblName.gridx = 0;
        positionLblName.gridy = 0;
        positionLblName.weightx = 0;
        positionLblName.weighty = 0; // ← isso força ele a ficar no topo
        positionLblName.anchor = GridBagConstraints.CENTER;
        positionLblName.fill = GridBagConstraints.NONE;
        positionLblName.insets = new Insets(0, 0, 0, 0); // margem superior
        centralPanel.add(lblName, positionLblName);

        txtNome.setFont(new Font("Calibri", Font.PLAIN, 14));
        txtNome.setToolTipText("Digite seu nome");
        txtNome.setMargin(new Insets(3, 1, 1, 1));

        // Posicionamento no topo absoluto
        GridBagConstraints positionTxtname = new GridBagConstraints();
        positionTxtname.gridx = 0;
        positionTxtname.gridy = 1;
        positionTxtname.weightx = 0;
        positionTxtname.weighty = 0; // ← isso força ele a ficar no topo
        positionTxtname.anchor = GridBagConstraints.CENTER;
        positionTxtname.fill = GridBagConstraints.NONE;
        positionTxtname.insets = new Insets(0, 0, 10, 0); // margem superior
        centralPanel.add(txtNome, positionTxtname);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Calibri", Font.BOLD, 20));

        GridBagConstraints positionLblEmail = new GridBagConstraints();
        positionLblEmail.gridx = 0;
        positionLblEmail.gridy = 2;
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
        positionTxtEmail.gridy = 3;
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
        positionLblPassword.gridy = 4;
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
        positionTxtPassword.gridy = 5;
        positionTxtPassword.weightx = 0;
        positionTxtPassword.weighty = 0; // ← isso força ele a ficar no topo
        positionTxtPassword.anchor = GridBagConstraints.CENTER;
        positionTxtPassword.fill = GridBagConstraints.NONE;
        positionTxtPassword.insets = new Insets(0, 0, 0, 0); // margem superior
        centralPanel.add(txtSenha, positionTxtPassword);

        btnCadastrar.setToolTipText("Clique para se cadastrar");
        btnCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Posicionamento no topo absoluto
        GridBagConstraints positionBtnLogin = new GridBagConstraints();
        positionBtnLogin.gridx = 0;
        positionBtnLogin.gridy = 6;
        positionBtnLogin.weightx = 0;
        positionBtnLogin.weighty = 0; // ← isso força ele a ficar no topo
        positionBtnLogin.anchor = GridBagConstraints.CENTER;
        positionBtnLogin.fill = GridBagConstraints.NONE;
        positionBtnLogin.insets = new Insets(10, 0, 15, 0); // margem superior
        centralPanel.add(btnCadastrar, positionBtnLogin);

        lblVoltar.setFont(new Font("Calibri", Font.BOLD, 18));
        lblVoltar.setForeground(Color.WHITE);
        lblVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblVoltar.setToolTipText("Voltar");

        GridBagConstraints positionBack = new GridBagConstraints();
        positionBack.gridx = 0;
        positionBack.gridy = 7;
        positionBack.weightx = 0;
        positionBack.weighty = 0; // ← isso força ele a ficar no topo
        positionBack.anchor = GridBagConstraints.CENTER;
        positionBack.fill = GridBagConstraints.NONE;
        positionBack.insets = new Insets(0, 0, 0, 0); // margem superior
        centralPanel.add(lblVoltar, positionBack);

        add(backgroundPanel);
    }

    public void signUpView() {
        configurarUI();
        setVisible(true);
        salvarUsuario();
        voltar();
    }

    //Método que salva o usuário cadastrado
    public void salvarUsuario() {

        btnCadastrar.addActionListener(new ActionListener() {
            UserDao dao = new UserDao();
            LoginView view = new LoginView();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!emptyFields()) {
                    if (validarNome(txtNome.getText()) == false) {
                        JOptionPane.showMessageDialog(null, "Nome deve conter apenas letras!");
                    } else if (view.validarEmail(txtEmail.getText()) == false) {
                        JOptionPane.showMessageDialog(null, "Formato email incorreto! \n"
                                + "Ex: nome@email.com");
                    } else if (view.validarSenha(txtSenha.getText()) == false) {
                        JOptionPane.showMessageDialog(null, "A senha deve conter no mínimo 6 caracteres e no máximo 40");
                    } else {
                        if (dao.emailExiste(txtEmail.getText())) {
                            JOptionPane.showMessageDialog(null, "Email já cadastrado!");
                        } else {
                            dao.salvarUsuario(txtNome.getText(), txtEmail.getText(), txtSenha.getText());
                            dispose();
                            new LoginView().login();
                        }
                    }
                }
            }
        });
    }

    //Método que chama a tela loginView
    public void voltar() {
        lblVoltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    dispose();
                    new LoginView().login();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        );
    }

    //Método que verifica os campos vazios
    public boolean emptyFields() {

        boolean empty = true;

        if (txtNome.getText().trim().isEmpty() || txtEmail.getText().trim().isEmpty() || txtSenha.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os campos precisam ser preenchidos");
        } else {
            empty = false;
        }
        return empty;
    }

    //Método que valida o nome
    public boolean validarNome(String nome) {
        String regex = "^[\\p{L}\\s,]{1,50}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nome);
        return matcher.matches();
    }
}
