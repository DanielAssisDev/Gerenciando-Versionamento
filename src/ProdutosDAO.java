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
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) values (?, ?, ?)");
            prep.setString(1, produto.getNome());
            prep.setDouble(2, produto.getValor());
            prep.setString(3, "à venda");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ProdutosDTO getProduto(int id) {
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("SELECT * FROM produtos WHERE id = ?");
            prep.setInt(1, id);
            resultset = prep.executeQuery();

            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getDouble("valor"));
            produto.setStatus(resultset.getString("status"));

            return produto;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("SELECT * FROM produtos");
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getDouble("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }

            return listagem;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}
