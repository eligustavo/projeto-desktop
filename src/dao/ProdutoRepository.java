package dao;

import conexao.ConnectionFactory;
import model.ModelProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Elivelton
 */
public class ProdutoRepository extends ModelProduto {

//Salva um produto no banco de dados    
    public void Salvar(ModelProduto p) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tbl_produto(nomeProduto, descProduto, valProduto, estoProduto)VALUES(?, ?, ?, ?)");
            stmt.setString(1, p.getNomeProduto());
            stmt.setString(2, p.getDescProduto());
            stmt.setDouble(3, p.getValProduto());
            stmt.setInt(4, p.getEstoProduto());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!: " + ex);
            Logger.getLogger(ProdutoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

//Método que faz uma lista de produtos    
    public List<ModelProduto> Leitura() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ModelProduto> objProduto = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_produto");
            rs = stmt.executeQuery();

            while (rs.next()) {

                ModelProduto objProdutos = new ModelProduto();

                objProdutos.setIdProduto(rs.getInt("idProduto"));
                objProdutos.setNomeProduto(rs.getString("nomeProduto"));
                objProdutos.setDescProduto(rs.getString("descProduto"));
                objProdutos.setValProduto(rs.getDouble("valProduto"));
                objProdutos.setEstoProduto(rs.getInt("estoProduto"));
                objProduto.add(objProdutos);

            }

        } catch (SQLException ex) {

            Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return objProduto;

    }

//Altera um produto na Banco de dados    
    public void Alterar(ModelProduto p) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE tbl_produto SET nomeProduto = ?, descProduto = ?, valProduto = ?, estoProduto = ? WHERE idProduto = ?");
            stmt.setString(1, p.getNomeProduto());
            stmt.setString(2, p.getDescProduto());
            stmt.setDouble(3, p.getValProduto());
            stmt.setInt(4, p.getEstoProduto());
            stmt.setInt(5, p.getIdProduto());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!: " + ex);
            Logger.getLogger(ProdutoRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    
//Exclui um produto    
    public void Excluir(ModelProduto p) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM tbl_produto WHERE idProduto = ?");
            stmt.setInt(1, p.getIdProduto());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!: " + ex);
            Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

//Método de pesquisa de produtos    
    public List<ModelProduto> Pesquisar(String nomeProduto) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ModelProduto> objProduto = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_produto WHERE nomeProduto LIKE ?");
            stmt.setString(1, "%" + nomeProduto + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {

                ModelProduto objProdutos = new ModelProduto();

                objProdutos.setIdProduto(rs.getInt("idProduto"));
                objProdutos.setNomeProduto(rs.getString("nomeProduto"));
                objProdutos.setDescProduto(rs.getString("descProduto"));
                objProdutos.setValProduto(rs.getDouble("valProduto"));
                objProdutos.setEstoProduto(rs.getInt("estoProduto"));
                objProduto.add(objProdutos);

            }

        } catch (SQLException ex) {

            Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return objProduto;

    }

    public ArrayList<ModelProduto> retornar(int id) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<ModelProduto> objProduto = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_produto WHERE idProduto = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {

                ModelProduto objProdutos = new ModelProduto();

                objProdutos.setIdProduto(rs.getInt("idProduto"));
                objProdutos.setNomeProduto(rs.getString("nomeProduto"));
                objProdutos.setDescProduto(rs.getString("descProduto"));
                objProdutos.setValProduto(rs.getDouble("valProduto"));
                objProdutos.setEstoProduto(rs.getInt("estoProduto"));
                objProduto.add(objProdutos);

            }

        } catch (SQLException ex) {

            Logger.getLogger(ClienteRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return objProduto;

    }
}
