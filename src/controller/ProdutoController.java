package controller;

import connection.ConnectionFactory;
import model.Produto;

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
 * @author Elivelton
 */
public class ProdutoController extends Produto {

    public void Salvar(Produto p) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO tbl_produto("
                    + "nomeProduto, "
                    + "descProduto, "
                    + "valProduto, "
                    + "estoProduto)"
                    + "VALUES(?, ?, ?, ?)");
            stmt.setString(1, p.getNomeProduto());
            stmt.setString(2, p.getDescProduto());
            stmt.setDouble(3, p.getValProduto());
            stmt.setInt(4, p.getEstoProduto());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!: " + ex);
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Produto> Leitura() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> objProduto = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_produto");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto objProdutos = new Produto();

                objProdutos.setIdProduto(rs.getInt("idProduto"));
                objProdutos.setNomeProduto(rs.getString("nomeProduto"));
                objProdutos.setDescProduto(rs.getString("descProduto"));
                objProdutos.setValProduto(rs.getDouble("valProduto"));
                objProdutos.setEstoProduto(rs.getInt("estoProduto"));
                objProduto.add(objProdutos);
            }

        } catch (SQLException ex) {

            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return objProduto;

    }

    public void Alterar(Produto p) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                    "UPDATE tbl_produto SET "
                            + "nomeProduto = ?, "
                            + "descProduto = ?, "
                            + "valProduto = ?, "
                            + "estoProduto = ? "
                            + "WHERE idProduto = ?");
            stmt.setString(1, p.getNomeProduto());
            stmt.setString(2, p.getDescProduto());
            stmt.setDouble(3, p.getValProduto());
            stmt.setInt(4, p.getEstoProduto());
            stmt.setInt(5, p.getIdProduto());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!: " + ex);
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public void Excluir(Produto p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM tbl_produto WHERE idProduto = ?");
            stmt.setInt(1, p.getIdProduto());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!: " + ex);
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Produto> Pesquisar(String nomeProduto) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> objProduto = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_produto WHERE nomeProduto LIKE ?");
            stmt.setString(1, "%" + nomeProduto + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto objProdutos = new Produto();
                objProdutos.setIdProduto(rs.getInt("idProduto"));
                objProdutos.setNomeProduto(rs.getString("nomeProduto"));
                objProdutos.setDescProduto(rs.getString("descProduto"));
                objProdutos.setValProduto(rs.getDouble("valProduto"));
                objProdutos.setEstoProduto(rs.getInt("estoProduto"));
                objProduto.add(objProdutos);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return objProduto;
    }

    public ArrayList<Produto> retornar(int id) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Produto> objProduto = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_produto WHERE idProduto = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto objProdutos = new Produto();

                objProdutos.setIdProduto(rs.getInt("idProduto"));
                objProdutos.setNomeProduto(rs.getString("nomeProduto"));
                objProdutos.setDescProduto(rs.getString("descProduto"));
                objProdutos.setValProduto(rs.getDouble("valProduto"));
                objProdutos.setEstoProduto(rs.getInt("estoProduto"));
                objProduto.add(objProdutos);
            }
        } catch (SQLException ex) {

            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return objProduto;
    }
}
