package com.mycompany.projetonutricao;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.mycompany.view.LoginView;
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
            new LoginView().login();
        });
    }
}
