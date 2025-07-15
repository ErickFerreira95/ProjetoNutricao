package com.mycompany.projetonutricao;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.mycompany.view.AdicionarAlimentoRefeicao;
import com.mycompany.view.CadastroAlimentoView;
import com.mycompany.view.CalculoTmbView;
import com.mycompany.view.EditarAlimentoView;
import com.mycompany.view.LoginView;
import com.mycompany.view.MainView;
import com.mycompany.view.RefeicoesView;
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
            //new SignUpView().signUpView();
            //new MainView().mainView();
            //new CadastroAlimentoView().CadastroAlimentoView();
            //new CalculoTmbView().calculoTmbView();
            //new EditarAlimentoView().editarAlimentoView();
            new RefeicoesView().refeifoes();
            //new AdicionarAlimentoRefeicao().adicionarRefeicao();
        });
    }
}
