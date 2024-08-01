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
        try{
        //criando o metodo de salvar 
        conn = new conectaDAO().connectDB();
        prep = conn.prepareStatement(sql);
        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());
        
        // execultando a sql
        int linhasafetadas  =prep.executeUpdate();
        
        // se efetuado com sucesso 
        if(linhasafetadas >0){
            System.out.println("produto cadastrado com sucesso");
        }else{
            System.out.println("falha no cadastro do produto");
        }
         
        }catch(SQLException e){
            System.out.println("erro ao cadastrar o produto:"+e.getMessage());
        }finally{
            //fechando as coneccoes
            try{
            if(prep != null)prep.close();
            if(conn != null) conn.close();  
        }catch(SQLException e){
                System.out.println("Erro ao fechar os recursos "+ e.getMessage());
            
        }}
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

