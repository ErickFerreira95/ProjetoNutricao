package com.mycompany.util.dao;

import com.mycompany.connection.ConnectionDataBase;
import com.mycompany.model.Alimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AlimentoDao {

    private ConnectionDataBase connection;
    private Connection conn;

    public AlimentoDao() {
        this.connection = new ConnectionDataBase();
        this.conn = this.connection.getConexaoBd();
    }

    
    
    //Método que carrega os alimentos adicionados as refeição1 para cada usuário
    public List<Alimento> listarPorusuarioRefeicao1(int idUsuario) {
        List<Alimento> lista = new ArrayList<>();
        String sql = "SELECT * FROM refeicao1 WHERE id_usuario = ?";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Alimento a = new Alimento();
                a.setId(rs.getInt("id"));
                a.setQuantidade(rs.getString("quantidade"));
                a.setNomeAlimento(rs.getString("alimento"));
                a.setProteina(rs.getString("proteina"));
                a.setCarboidrato(rs.getString("carboidrato"));
                a.setGordura(rs.getString("gordura"));
                a.setKcal(rs.getString("kcal"));
                a.setIdUsuario(rs.getInt("id_usuario"));
                lista.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    //Método que carrega os alimentos adicionados as refeição2 para cada usuário
    public List<Alimento> listarPorusuarioRefeicao2(int idUsuario) {
        List<Alimento> lista = new ArrayList<>();
        String sql = "SELECT * FROM refeicao2 WHERE id_usuario = ?";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Alimento a = new Alimento();
                a.setId(rs.getInt("id"));
                a.setQuantidade(rs.getString("quantidade"));
                a.setNomeAlimento(rs.getString("alimento"));
                a.setProteina(rs.getString("proteina"));
                a.setCarboidrato(rs.getString("carboidrato"));
                a.setGordura(rs.getString("gordura"));
                a.setKcal(rs.getString("kcal"));
                a.setIdUsuario(rs.getInt("id_usuario"));
                lista.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    //Método que carrega os alimentos adicionados as refeição3 para cada usuário
    public List<Alimento> listarPorusuarioRefeicao3(int idUsuario) {
        List<Alimento> lista = new ArrayList<>();
        String sql = "SELECT * FROM refeicao3 WHERE id_usuario = ?";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Alimento a = new Alimento();
                a.setId(rs.getInt("id"));
                a.setQuantidade(rs.getString("quantidade"));
                a.setNomeAlimento(rs.getString("alimento"));
                a.setProteina(rs.getString("proteina"));
                a.setCarboidrato(rs.getString("carboidrato"));
                a.setGordura(rs.getString("gordura"));
                a.setKcal(rs.getString("kcal"));
                a.setIdUsuario(rs.getInt("id_usuario"));
                lista.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    //Método que carrega os alimentos adicionados as refeição4 para cada usuário
    public List<Alimento> listarPorusuarioRefeicao4(int idUsuario) {
        List<Alimento> lista = new ArrayList<>();
        String sql = "SELECT * FROM refeicao4 WHERE id_usuario = ?";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Alimento a = new Alimento();
                a.setId(rs.getInt("id"));
                a.setQuantidade(rs.getString("quantidade"));
                a.setNomeAlimento(rs.getString("alimento"));
                a.setProteina(rs.getString("proteina"));
                a.setCarboidrato(rs.getString("carboidrato"));
                a.setGordura(rs.getString("gordura"));
                a.setKcal(rs.getString("kcal"));
                a.setIdUsuario(rs.getInt("id_usuario"));
                lista.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    //Método que carrega os alimentos adicionados as refeição5 para cada usuário
    public List<Alimento> listarPorusuarioRefeicao5(int idUsuario) {
        List<Alimento> lista = new ArrayList<>();
        String sql = "SELECT * FROM refeicao5 WHERE id_usuario = ?";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Alimento a = new Alimento();
                a.setId(rs.getInt("id"));
                a.setQuantidade(rs.getString("quantidade"));
                a.setNomeAlimento(rs.getString("alimento"));
                a.setProteina(rs.getString("proteina"));
                a.setCarboidrato(rs.getString("carboidrato"));
                a.setGordura(rs.getString("gordura"));
                a.setKcal(rs.getString("kcal"));
                a.setIdUsuario(rs.getInt("id_usuario"));
                lista.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    //Método que carrega os alimentos adicionados as refeição6 para cada usuário
    public List<Alimento> listarPorusuarioRefeicao6(int idUsuario) {
        List<Alimento> lista = new ArrayList<>();
        String sql = "SELECT * FROM refeicao6 WHERE id_usuario = ?";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Alimento a = new Alimento();
                a.setId(rs.getInt("id"));
                a.setQuantidade(rs.getString("quantidade"));
                a.setNomeAlimento(rs.getString("alimento"));
                a.setProteina(rs.getString("proteina"));
                a.setCarboidrato(rs.getString("carboidrato"));
                a.setGordura(rs.getString("gordura"));
                a.setKcal(rs.getString("kcal"));
                a.setIdUsuario(rs.getInt("id_usuario"));
                lista.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    //Método que deleta os alimentos do banco/JTable
    public boolean deletarAlimento(int id) {
        try (Connection conn = connection.getConexaoBd()) {
            String sql = "DELETE FROM alimentos WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Método que deleta os alimentos da refeição1
    public boolean deletarAlimentoRefeicao1(int id) {
        try (Connection conn = connection.getConexaoBd()) {
            String sql = "DELETE FROM refeicao1 WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Método que deleta os alimentos da refeição2
    public boolean deletarAlimentoRefeicao2(int id) {
        try (Connection conn = connection.getConexaoBd()) {
            String sql = "DELETE FROM refeicao2 WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Método que deleta os alimentos da refeição3
    public boolean deletarAlimentoRefeicao3(int id) {
        try (Connection conn = connection.getConexaoBd()) {
            String sql = "DELETE FROM refeicao3 WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Método que deleta os alimentos da refeição4
    public boolean deletarAlimentoRefeicao4(int id) {
        try (Connection conn = connection.getConexaoBd()) {
            String sql = "DELETE FROM refeicao4 WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Método que deleta os alimentos da refeição5
    public boolean deletarAlimentoRefeicao5(int id) {
        try (Connection conn = connection.getConexaoBd()) {
            String sql = "DELETE FROM refeicao5 WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Método que deleta os alimentos da refeição6
    public boolean deletarAlimentoRefeicao6(int id) {
        try (Connection conn = connection.getConexaoBd()) {
            String sql = "DELETE FROM refeicao6 WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Método para editar um alimento
    public void atualizarAlimento(Alimento alimento) {
        String sql = "UPDATE alimentos SET nome = ?, quantidade = ?, proteina = ?, carboidrato = ?, gordura = ?, kcal = ? WHERE id = ?";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, alimento.getNomeAlimento());
            stmt.setString(2, alimento.getQuantidade());
            stmt.setString(3, alimento.getProteina());
            stmt.setString(4, alimento.getCarboidrato());
            stmt.setString(5, alimento.getGordura());
            stmt.setString(6, alimento.getKcal());
            stmt.setInt(7, alimento.getId());

            stmt.executeUpdate();
            System.out.println("Alimento atualizado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao atualizar alimento: " + e.getMessage());
        }
    }

    //Método que busca um alimento do banco pelo nome
    public Alimento buscarPorNome(String nome) {
        Alimento alimento = null;

        String sql = "SELECT * FROM alimentos WHERE nome = ?";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                alimento = new Alimento();
                alimento.setId(rs.getInt("id"));
                alimento.setNomeAlimento(rs.getString("nome"));
                alimento.setQuantidade(rs.getString("quantidade"));
                alimento.setProteina(rs.getString("proteina"));
                alimento.setCarboidrato(rs.getString("carboidrato"));
                alimento.setGordura(rs.getString("gordura"));
                alimento.setKcal(rs.getString("kcal"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return alimento;
    }
    
    //Método que salva a refeição1
    public boolean salvarAlimentoRefeicao1(Alimento alimento) {
        String sql = "INSERT INTO refeicao1 (quantidade, alimento, proteina, carboidrato, gordura, kcal, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, alimento.getQuantidade());
            stmt.setString(2, alimento.getNomeAlimento());
            stmt.setString(3, alimento.getProteina());
            stmt.setString(4, alimento.getCarboidrato());
            stmt.setString(5, alimento.getGordura());
            stmt.setString(6, alimento.getKcal());
            stmt.setInt(7, alimento.getIdUsuario());

            stmt.executeUpdate();
            System.out.println("Alimento salvo com sucesso.");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar Alimento: " + e.getMessage());
            return false;
        }
    }

    //Método que salva a refeição2
    public boolean salvarAlimentoRefeicao2(Alimento alimento) {
        String sql = "INSERT INTO refeicao2 (quantidade, alimento, proteina, carboidrato, gordura, kcal, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, alimento.getQuantidade());
            stmt.setString(2, alimento.getNomeAlimento());
            stmt.setString(3, alimento.getProteina());
            stmt.setString(4, alimento.getCarboidrato());
            stmt.setString(5, alimento.getGordura());
            stmt.setString(6, alimento.getKcal());
            stmt.setInt(7, alimento.getIdUsuario());

            stmt.executeUpdate();
            System.out.println("Alimento salvo com sucesso.");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar Alimento: " + e.getMessage());
            return false;
        }
    }
    
    //Método que salva a refeição3
    public boolean salvarAlimentoRefeicao3(Alimento alimento) {
        String sql = "INSERT INTO refeicao3 (quantidade, alimento, proteina, carboidrato, gordura, kcal, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, alimento.getQuantidade());
            stmt.setString(2, alimento.getNomeAlimento());
            stmt.setString(3, alimento.getProteina());
            stmt.setString(4, alimento.getCarboidrato());
            stmt.setString(5, alimento.getGordura());
            stmt.setString(6, alimento.getKcal());
            stmt.setInt(7, alimento.getIdUsuario());

            stmt.executeUpdate();
            System.out.println("Alimento salvo com sucesso.");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar Alimento: " + e.getMessage());
            return false;
        }
    }
    
    //Método que salva a refeição4
    public boolean salvarAlimentoRefeicao4(Alimento alimento) {
        String sql = "INSERT INTO refeicao4 (quantidade, alimento, proteina, carboidrato, gordura, kcal, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, alimento.getQuantidade());
            stmt.setString(2, alimento.getNomeAlimento());
            stmt.setString(3, alimento.getProteina());
            stmt.setString(4, alimento.getCarboidrato());
            stmt.setString(5, alimento.getGordura());
            stmt.setString(6, alimento.getKcal());
            stmt.setInt(7, alimento.getIdUsuario());

            stmt.executeUpdate();
            System.out.println("Alimento salvo com sucesso.");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar Alimento: " + e.getMessage());
            return false;
        }
    }
    
    //Método que salva a refeição5
    public boolean salvarAlimentoRefeicao5(Alimento alimento) {
        String sql = "INSERT INTO refeicao5 (quantidade, alimento, proteina, carboidrato, gordura, kcal, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, alimento.getQuantidade());
            stmt.setString(2, alimento.getNomeAlimento());
            stmt.setString(3, alimento.getProteina());
            stmt.setString(4, alimento.getCarboidrato());
            stmt.setString(5, alimento.getGordura());
            stmt.setString(6, alimento.getKcal());
            stmt.setInt(7, alimento.getIdUsuario());

            stmt.executeUpdate();
            System.out.println("Alimento salvo com sucesso.");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar Alimento: " + e.getMessage());
            return false;
        }
    }
    
    //Método que salva a refeição6
    public boolean salvarAlimentoRefeicao6(Alimento alimento) {
        String sql = "INSERT INTO refeicao6 (quantidade, alimento, proteina, carboidrato, gordura, kcal, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, alimento.getQuantidade());
            stmt.setString(2, alimento.getNomeAlimento());
            stmt.setString(3, alimento.getProteina());
            stmt.setString(4, alimento.getCarboidrato());
            stmt.setString(5, alimento.getGordura());
            stmt.setString(6, alimento.getKcal());
            stmt.setInt(7, alimento.getIdUsuario());

            stmt.executeUpdate();
            System.out.println("Alimento salvo com sucesso.");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar Alimento: " + e.getMessage());
            return false;
        }
    }

    //Método que carrega os alimentos cadastrados pelo usuário
    public List<Alimento> listarPorUsuario(int idUsuario) {
        List<Alimento> lista = new ArrayList<>();
        String sql = "SELECT * FROM alimentos WHERE id_usuario = ?";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Alimento a = new Alimento();
                a.setId(rs.getInt("id"));
                a.setNomeAlimento(rs.getString("nome"));
                a.setQuantidade(rs.getString("quantidade"));
                a.setProteina(rs.getString("proteina"));
                a.setCarboidrato(rs.getString("carboidrato"));
                a.setGordura(rs.getString("gordura"));
                a.setKcal(rs.getString("kcal"));
                a.setIdUsuario(rs.getInt("id_usuario"));
                lista.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    //Método que salva os alimentos no banco
    public boolean salvarAlimento(Alimento alimento) {
        String sql = "INSERT INTO alimentos (nome, quantidade, proteina, carboidrato, gordura, kcal, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, alimento.getNomeAlimento());
            stmt.setString(2, alimento.getQuantidade());
            stmt.setString(3, alimento.getProteina());
            stmt.setString(4, alimento.getCarboidrato());
            stmt.setString(5, alimento.getGordura());
            stmt.setString(6, alimento.getKcal());
            stmt.setInt(7, alimento.getIdUsuario());

            stmt.executeUpdate();
            System.out.println("Alimento salvo com sucesso.");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar Alimento: " + e.getMessage());
            return false;
        }
    }
}