package com.mycompany.util.dao;

import com.mycompany.connection.ConnectionDataBase;
import com.mycompany.model.Alimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
}
