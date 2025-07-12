package com.mycompany.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDataBase {
    
    Connection conn;
    
    /**
     * Método para fazer a conexão com o banco de dados
     * @return Conexão bem sucedida, caso não seja possível conectar, retorna nulo
     */
    public Connection getConexaoBd() {
                
        try {
            conn = DriverManager.getConnection(
                            "jdbc:mysql://localhost/projeto_nutricao",
                            "root", 
                            "Picoricos2Nina."
                    );
            System.out.println("Conexão realizada com sucesso!");
                    return conn;
            
        } catch (Exception e){
            System.out.println("Erro ao conectar: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Método para fazer a desconexão com o banco de dados
     * @return Desconexão com o banco de dados
     */
    public Connection desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {
   
        }
        return conn;
    }
}
