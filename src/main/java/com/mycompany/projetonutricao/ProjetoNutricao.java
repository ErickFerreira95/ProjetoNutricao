package com.mycompany.projetonutricao;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.mycompany.view.CadastroAlimentoView;
import com.mycompany.view.CalculoTmbView;
import com.mycompany.view.LoginView;
import com.mycompany.view.MainView;
import com.mycompany.view.SignUpView;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ProjetoNutricao {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf()); // Ou FlatLightLaf()
        } catch (Exception ex) {
            System.err.println("Erro ao aplicar FlatLaf.");
        }

        SwingUtilities.invokeLater(() -> {
            //new LoginView().login();
            //new SignUpView().setVisible(true);
            //new MainView().mainView();
            //new CadastroAlimentoView().CadastroAlimentoView();
            new CalculoTmbView().calculoTmbView();
        });
    }
}
