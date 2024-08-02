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
        
        return listagem;
    }
    
    
    
        
}

