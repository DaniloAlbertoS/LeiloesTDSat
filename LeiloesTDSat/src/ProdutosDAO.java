/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        String sql = "INSERT INTO produtos (nome, valor,status) VALUES (?, ?, ?)";
        // iniciando como nulo
        conn =null;
        prep = null;
        try{
        //criando o metodo de salvar 
        conn = new conectaDAO().connectDB();
        prep = conn.prepareStatement(sql);
        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());
        
        // execultando a sql
        int linhasafetadas  =prep.executeUpdate();
        
        // se efetuado com sucesso a mensagem de sucesso ao cadastrar
        if(linhasafetadas >0){
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        // se falhar a mensagem de falha
        }else{
            JOptionPane.showMessageDialog(null, "Falha no cadastro do produto.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
         
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }finally{
            //fechando as coneccoes
            try{
            if(prep != null)prep.close();
            if(conn != null) conn.close();  
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Erro ao fechar os recursos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            
        }}
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        // criando um array list de produtos dto
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        // estabelecendo conccao como nulla
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet rs = null;
        
        try {
            // Conectar ao banco de dados
            conn = new conectaDAO().connectDB();
            
            // Query SQL para listar todos os produtos
            String sql = "SELECT * FROM produtos";
            prep = conn.prepareStatement(sql);
            
            // Executar a query acima 
            rs = prep.executeQuery();
            
            // Processar o ResultSet
            while (rs.next()){ // enquanto tive proxima linha 
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id")); 
                produto.setNome(rs.getString("nome")); 
                produto.setValor(rs.getInt("valor")); 
                produto.setStatus(rs.getString("status")); 
                
                // Adicionar o produto à lista
                listagem.add(produto);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        } finally {
            // Fechar os recursos
            try {
                if (rs != null) rs.close();
                if (prep != null) prep.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        
       
    
        return listagem;
    }
    
    
    
        
}

