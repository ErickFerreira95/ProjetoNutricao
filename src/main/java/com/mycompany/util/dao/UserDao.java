package com.mycompany.util.dao;

import com.mycompany.connection.ConnectionDataBase;
import com.mycompany.model.Tmb;
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
        String sql = "SELECT * FROM usuarios";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            List<User> listaUsuarios = new ArrayList<>();

            while (rs.next()) { //.next retorna verdadeiro caso exista uma próxima posição dentro do array
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));

                listaUsuarios.add(user);
            }
            return listaUsuarios;
        } catch (Exception e) {
            return null;
        }
    }

    // Salvar usuário com senha criptografada
    public boolean salvarUsuario(String nome, String email, String senha) {
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";

        // Criptografa a senha
        String senhaHash = BCrypt.hashpw(senha, BCrypt.gensalt());

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senhaHash);

            stmt.executeUpdate();
            System.out.println("Usuário salvo com sucesso.");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
            System.out.println(senhaHash);
            return false;
        }
    }
    
    public boolean salvarTmb(Tmb tmb) {
        String sql = "INSERT INTO taxaMetabolicaBasal (idade, altura, peso, fatorAtividade, tmb, id_usuario) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, tmb.getIdade());
            stmt.setInt(2, tmb.getAltura());
            stmt.setDouble(3, tmb.getPeso());
            stmt.setDouble(4, tmb.getFatorAtividade());
            stmt.setDouble(5, tmb.getTmb());
            stmt.setInt(6, tmb.getIdUsuario());

            stmt.executeUpdate();
            System.out.println("Dados pessoais salvo com sucesso.");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar dados pessoais: " + e.getMessage());
            return false;
        }
    }

    public User autenticarUsuario(String email, String senhaDigitada) {
        String sql = "SELECT id, nome, email, senha FROM usuarios WHERE email = ?";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String senhaHash = rs.getString("senha");
                if (BCrypt.checkpw(senhaDigitada, senhaHash)) {
                    User usuario = new User();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    // qualquer outro campo necessário
                    return usuario;
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao autenticar usuário: " + e.getMessage());
        }
        return null;
    }

    public boolean emailExiste(String email) {
        User user = null;
        boolean existe = false;
        String sql = "SELECT * FROM usuarios WHERE email = ?";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                existe = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return existe;
    }
}
