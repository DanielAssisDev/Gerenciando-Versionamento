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
            prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) values (?, ?, ?);");
            prep.setString(1, produto.getNome());
            prep.setDouble(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("SELECT * FROM produtos;");
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
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void venderProduto(int id) {
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("UPDATE produtos SET status = 'Vendido' WHERE id = ?;");
            prep.setInt(1, id);
            prep.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<ProdutosDTO> buscarProdutosVendidos(String status) {
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("SELECT * FROM produtos WHERE status LIKE ?;");
            prep.setString(0, "%" + status + "%");
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
            System.out.println(e.getMessage());
            return null;
        }
    }
}
