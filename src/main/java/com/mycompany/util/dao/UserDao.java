package com.mycompany.util.dao;

import com.mycompany.connection.ConnectionDataBase;
import com.mycompany.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class UserDao {
    
    private ConnectionDataBase connection;
    private Connection conn;

    public UserDao() {
        this.connection = new ConnectionDataBase();
        this.conn = this.connection.getConexaoBd();
    }

    public List<User> getUsuario() {
        String sql = "SELECT * FROM user";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            List<User> listaUsuarios = new ArrayList<>();

            while (rs.next()) { //.next retorna verdadeiro caso exista uma próxima posição dentro do array
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));

                listaUsuarios.add(user);
            }
            return listaUsuarios;
        } catch (Exception e) {
            return null;
        }
    }

    /*public boolean verificarLogin(String email, String senha) {
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        
        try (Connection conn = conexaoBd.getConexaoBd();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            return rs.next(); // Se encontrou algum resultado, o login está correto

        } catch (Exception e) {
            System.out.println("Erro ao verificar login: " + e.getMessage());
            return false;
        }
    }*/
    // Salvar usuário com senha criptografada
    public boolean salvarUsuario(String name, String email, String password) {
        String sql = "INSERT INTO user (name, email, password) VALUES (?, ?, ?)";

        // Criptografa a senha
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, passwordHash);

            stmt.executeUpdate();
            System.out.println("Usuário salvo com sucesso.");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
            System.out.println(passwordHash);
            return false;
        }
    }

    // Autenticar usuário
    public boolean autenticarUsuario(String email, String typedpassword) {
        String sql = "SELECT password FROM user WHERE email = ?";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String passwordHash = rs.getString("password");
                return BCrypt.checkpw(typedpassword, passwordHash);
            }

        } catch (Exception e) {
            System.out.println("Erro ao autenticar usuário: " + e.getMessage());
        }
        return false;
    }
}
