/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.systec.dal;

import java.sql.*;


/**
 *
 * @author Júnior
 */
public class ModuloConexao {

    /* método para conexão com o banco de dados
    */
    
    public static Connection conector() {
        java.sql.Connection conexao = null;

        // "chama" o driver
        String driver = "com.mysql.cj.jdbc.Driver";

        // armazenando informações referente ao banco
        String url = "jdbc:mysql://localhost:3306/bd_farmacia";
        String user = "root";
        String password = "root";

        // estabelecendo a conexão com o banco
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            // exclarece o erro de conexão do banco de dados
            //System.out.println(e);
            return null;
        }
    }
}
