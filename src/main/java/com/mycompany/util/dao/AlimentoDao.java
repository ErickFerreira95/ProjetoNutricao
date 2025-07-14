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

    // Salvar usuário com senha criptografada
    public boolean salvarAlimento(Alimento alimento) {
        String sql = "INSERT INTO alimentos (nome, quantidade, proteina, carboidrato, gordura, kcal) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, alimento.getNomeAlimento());
            stmt.setString(2, alimento.getQuantidade());
            stmt.setString(3, alimento.getProteina());
            stmt.setString(4, alimento.getCarboidrato());
            stmt.setString(5, alimento.getGordura());
            stmt.setString(6, alimento.getKcal());

            stmt.executeUpdate();
            System.out.println("Alimento salvo com sucesso.");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar Alimento: " + e.getMessage());
            return false;
        }
    }

    public List<Alimento> carregarAlimentos() {
        List<Alimento> lista = new ArrayList<>();

        String sql = "SELECT id, nome, quantidade, proteina, carboidrato, gordura, kcal FROM alimentos";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Alimento alimento = new Alimento();

                alimento.setId(rs.getInt("id"));
                alimento.setNomeAlimento(rs.getString("nome"));
                alimento.setQuantidade(rs.getString("quantidade"));
                alimento.setProteina(rs.getString("proteina"));
                alimento.setCarboidrato(rs.getString("carboidrato"));
                alimento.setGordura(rs.getString("gordura"));
                alimento.setKcal(rs.getString("kcal"));

                lista.add(alimento);
            }

        } catch (Exception e) {
            e.printStackTrace(); // ou log
        }
        return lista;
    }
    
    public List<Alimento> carregarAlimentosRefeicao1() {
        List<Alimento> lista = new ArrayList<>();

        String sql = "SELECT alimento, quantidade, proteina, carboidrato, gordura, kcal FROM refeicao1";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Alimento alimento = new Alimento();

                alimento.setNomeAlimento(rs.getString("alimento"));
                alimento.setQuantidade(rs.getString("quantidade"));
                alimento.setProteina(rs.getString("proteina"));
                alimento.setCarboidrato(rs.getString("carboidrato"));
                alimento.setGordura(rs.getString("gordura"));
                alimento.setKcal(rs.getString("kcal"));

                lista.add(alimento);
            }

        } catch (Exception e) {
            e.printStackTrace(); // ou log
        }
        return lista;
    }
    
    public List<Alimento> carregarAlimentosRefeicao2() {
        List<Alimento> lista = new ArrayList<>();

        String sql = "SELECT alimento, quantidade, proteina, carboidrato, gordura, kcal FROM refeicao2";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Alimento alimento = new Alimento();

                alimento.setNomeAlimento(rs.getString("alimento"));
                alimento.setQuantidade(rs.getString("quantidade"));
                alimento.setProteina(rs.getString("proteina"));
                alimento.setCarboidrato(rs.getString("carboidrato"));
                alimento.setGordura(rs.getString("gordura"));
                alimento.setKcal(rs.getString("kcal"));

                lista.add(alimento);
            }

        } catch (Exception e) {
            e.printStackTrace(); // ou log
        }
        return lista;
    }
    
    public List<Alimento> carregarAlimentosRefeicao3() {
        List<Alimento> lista = new ArrayList<>();

        String sql = "SELECT alimento, quantidade, proteina, carboidrato, gordura, kcal FROM refeicao3";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Alimento alimento = new Alimento();

                alimento.setNomeAlimento(rs.getString("alimento"));
                alimento.setQuantidade(rs.getString("quantidade"));
                alimento.setProteina(rs.getString("proteina"));
                alimento.setCarboidrato(rs.getString("carboidrato"));
                alimento.setGordura(rs.getString("gordura"));
                alimento.setKcal(rs.getString("kcal"));

                lista.add(alimento);
            }

        } catch (Exception e) {
            e.printStackTrace(); // ou log
        }
        return lista;
    }
    
    public List<Alimento> carregarAlimentosRefeicao4() {
        List<Alimento> lista = new ArrayList<>();

        String sql = "SELECT alimento, quantidade, proteina, carboidrato, gordura, kcal FROM refeicao4";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Alimento alimento = new Alimento();

                alimento.setNomeAlimento(rs.getString("alimento"));
                alimento.setQuantidade(rs.getString("quantidade"));
                alimento.setProteina(rs.getString("proteina"));
                alimento.setCarboidrato(rs.getString("carboidrato"));
                alimento.setGordura(rs.getString("gordura"));
                alimento.setKcal(rs.getString("kcal"));

                lista.add(alimento);
            }

        } catch (Exception e) {
            e.printStackTrace(); // ou log
        }
        return lista;
    }
    
    public List<Alimento> carregarAlimentosRefeicao5() {
        List<Alimento> lista = new ArrayList<>();

        String sql = "SELECT alimento, quantidade, proteina, carboidrato, gordura, kcal FROM refeicao5";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Alimento alimento = new Alimento();

                alimento.setNomeAlimento(rs.getString("alimento"));
                alimento.setQuantidade(rs.getString("quantidade"));
                alimento.setProteina(rs.getString("proteina"));
                alimento.setCarboidrato(rs.getString("carboidrato"));
                alimento.setGordura(rs.getString("gordura"));
                alimento.setKcal(rs.getString("kcal"));

                lista.add(alimento);
            }

        } catch (Exception e) {
            e.printStackTrace(); // ou log
        }
        return lista;
    }
    
    public List<Alimento> carregarAlimentosRefeicao6() {
        List<Alimento> lista = new ArrayList<>();

        String sql = "SELECT alimento, quantidade, proteina, carboidrato, gordura, kcal FROM refeicao6";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Alimento alimento = new Alimento();

                alimento.setNomeAlimento(rs.getString("alimento"));
                alimento.setQuantidade(rs.getString("quantidade"));
                alimento.setProteina(rs.getString("proteina"));
                alimento.setCarboidrato(rs.getString("carboidrato"));
                alimento.setGordura(rs.getString("gordura"));
                alimento.setKcal(rs.getString("kcal"));

                lista.add(alimento);
            }

        } catch (Exception e) {
            e.printStackTrace(); // ou log
        }
        return lista;
    }

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
    
    // Salvar usuário com senha criptografada
    public boolean salvarAlimentoRefeicao1(Alimento alimento) {
        String sql = "INSERT INTO refeicao1 (alimento, quantidade, proteina, carboidrato, gordura, kcal) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, alimento.getNomeAlimento());
            stmt.setString(2, alimento.getQuantidade());
            stmt.setString(3, alimento.getProteina());
            stmt.setString(4, alimento.getCarboidrato());
            stmt.setString(5, alimento.getGordura());
            stmt.setString(6, alimento.getKcal());

            stmt.executeUpdate();
            System.out.println("Alimento salvo com sucesso.");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar Alimento: " + e.getMessage());
            return false;
        }
    }
    
    public boolean salvarAlimentoRefeicao2(Alimento alimento) {
        String sql = "INSERT INTO refeicao2 (alimento, quantidade, proteina, carboidrato, gordura, kcal) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, alimento.getNomeAlimento());
            stmt.setString(2, alimento.getQuantidade());
            stmt.setString(3, alimento.getProteina());
            stmt.setString(4, alimento.getCarboidrato());
            stmt.setString(5, alimento.getGordura());
            stmt.setString(6, alimento.getKcal());

            stmt.executeUpdate();
            System.out.println("Alimento salvo com sucesso.");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar Alimento: " + e.getMessage());
            return false;
        }
    }
    
    public boolean salvarAlimentoRefeicao3(Alimento alimento) {
        String sql = "INSERT INTO refeicao3 (alimento, quantidade, proteina, carboidrato, gordura, kcal) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, alimento.getNomeAlimento());
            stmt.setString(2, alimento.getQuantidade());
            stmt.setString(3, alimento.getProteina());
            stmt.setString(4, alimento.getCarboidrato());
            stmt.setString(5, alimento.getGordura());
            stmt.setString(6, alimento.getKcal());

            stmt.executeUpdate();
            System.out.println("Alimento salvo com sucesso.");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar Alimento: " + e.getMessage());
            return false;
        }
    }
    
    public boolean salvarAlimentoRefeicao4(Alimento alimento) {
        String sql = "INSERT INTO refeicao4 (alimento, quantidade, proteina, carboidrato, gordura, kcal) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, alimento.getNomeAlimento());
            stmt.setString(2, alimento.getQuantidade());
            stmt.setString(3, alimento.getProteina());
            stmt.setString(4, alimento.getCarboidrato());
            stmt.setString(5, alimento.getGordura());
            stmt.setString(6, alimento.getKcal());

            stmt.executeUpdate();
            System.out.println("Alimento salvo com sucesso.");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar Alimento: " + e.getMessage());
            return false;
        }
    }
    
    public boolean salvarAlimentoRefeicao5(Alimento alimento) {
        String sql = "INSERT INTO refeicao5 (alimento, quantidade, proteina, carboidrato, gordura, kcal) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, alimento.getNomeAlimento());
            stmt.setString(2, alimento.getQuantidade());
            stmt.setString(3, alimento.getProteina());
            stmt.setString(4, alimento.getCarboidrato());
            stmt.setString(5, alimento.getGordura());
            stmt.setString(6, alimento.getKcal());

            stmt.executeUpdate();
            System.out.println("Alimento salvo com sucesso.");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar Alimento: " + e.getMessage());
            return false;
        }
    }
    
    public boolean salvarAlimentoRefeicao6(Alimento alimento) {
        String sql = "INSERT INTO refeicao6 (alimento, quantidade, proteina, carboidrato, gordura, kcal) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConexaoBd(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, alimento.getNomeAlimento());
            stmt.setString(2, alimento.getQuantidade());
            stmt.setString(3, alimento.getProteina());
            stmt.setString(4, alimento.getCarboidrato());
            stmt.setString(5, alimento.getGordura());
            stmt.setString(6, alimento.getKcal());

            stmt.executeUpdate();
            System.out.println("Alimento salvo com sucesso.");
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao salvar Alimento: " + e.getMessage());
            return false;
        }
    }
}
