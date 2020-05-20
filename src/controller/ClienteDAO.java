/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.ConnectionFactory;
import model.Cliente;

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
public class ClienteDAO {

	//Metodo que salva um cliente dentro do banco de dados 
	public void Salvar(Cliente c) {

		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("INSERT INTO tbl_cliente(nomeCliente, "
					+ "cpfCliente, "
					+ "endCliente, "
					+ "baiCliente, "
					+ "cidCliente, "
					+ "ufCliente, "
					+ "cepCliente, "
					+ "telCliente,"
					+ " emCliente)"
					+ "VALUES(?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, c.getNomeCliente());
			stmt.setString(2, c.getCpfCliente());
			stmt.setString(3, c.getEndCliente());
			stmt.setString(4, c.getBaiCliente());
			stmt.setString(5, c.getCidCliente());
			stmt.setString(6, c.getUfCliente());
			stmt.setString(7, c.getCepCliente());
			stmt.setString(8, c.getTelCliente());
			stmt.setString(9, c.getEmCliente());

			stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar!: " + ex);
			Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}

	}

	//Mostra uma lista de clientes cadastrados        
	public List<Cliente> Leitura() {

		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Cliente> objclientes = new ArrayList<>();

		try {
			stmt = con.prepareStatement("SELECT * FROM tbl_cliente");
			rs = stmt.executeQuery();

			while (rs.next()) {

				Cliente objcliente = new Cliente();

				objcliente.setIdCliente(rs.getInt("idCliente"));
				objcliente.setNomeCliente(rs.getString("nomeCliente"));
				objcliente.setCpfCliente(rs.getString("cpfCliente"));
				objcliente.setEndCliente(rs.getString("endCliente"));
				objcliente.setBaiCliente(rs.getString("baiCliente"));
				objcliente.setCidCliente(rs.getString("cidCliente"));
				objcliente.setUfCliente(rs.getString("ufCliente"));
				objcliente.setCepCliente(rs.getString("cepCliente"));
				objcliente.setTelCliente(rs.getString("telCliente"));
				objcliente.setEmCliente(rs.getString("emCliente"));
				objclientes.add(objcliente);

			}

		} catch (SQLException ex) {

			Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}

		return objclientes;

	}

	//Método de alterar um cliente    
	public void Alterar(Cliente c) {

		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(
					"UPDATE tbl_cliente SET nomeCliente = ?,"
					+ " cpfCliente = ?,"
					+ " endCliente = ?,"
					+ " baiCliente = ?, "
					+ " cidCliente = ?, "
					+ " ufCliente = ?, "
					+ " cepCliente = ?, "
					+ " telCliente = ?, "
					+ " emCliente = ? "
					+ "WHERE idCliente = ?");
			stmt.setString(1, c.getNomeCliente());
			stmt.setString(2, c.getCpfCliente());
			stmt.setString(3, c.getEndCliente());
			stmt.setString(4, c.getBaiCliente());
			stmt.setString(5, c.getCidCliente());
			stmt.setString(6, c.getUfCliente());
			stmt.setString(7, c.getCepCliente());
			stmt.setString(8, c.getTelCliente());
			stmt.setString(9, c.getEmCliente());
			stmt.setInt(10, c.getIdCliente());
			stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar!: " + ex);
			Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}

//Exclui um cliente do banco de dados
	public void Excluir(Cliente c) {

		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("DELETE FROM tbl_cliente WHERE idCliente = ?");
			stmt.setInt(1, c.getIdCliente());
			stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso!");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar!: " + ex);
			Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}

//Método de pesquisa de um cliente por nome    
	public List<Cliente> Pesquisar(String nomeCliente) {

		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Cliente> objclientes = new ArrayList<>();

		try {
			stmt = con.prepareStatement("SELECT * FROM tbl_cliente WHERE nomeCliente LIKE ?");
			stmt.setString(1, "%" + nomeCliente + "%");

			rs = stmt.executeQuery();

			while (rs.next()) {

				Cliente objcliente = new Cliente();

				objcliente.setIdCliente(rs.getInt("idCliente"));
				objcliente.setNomeCliente(rs.getString("nomeCliente"));
				objcliente.setCpfCliente(rs.getString("cpfCliente"));
				objcliente.setEndCliente(rs.getString("endCliente"));
				objcliente.setBaiCliente(rs.getString("baiCliente"));
				objcliente.setCidCliente(rs.getString("cidCliente"));
				objcliente.setUfCliente(rs.getString("ufCliente"));
				objcliente.setCepCliente(rs.getString("cepCliente"));
				objcliente.setTelCliente(rs.getString("telCliente"));
				objcliente.setEmCliente(rs.getString("emCliente"));
				objclientes.add(objcliente);

			}

		} catch (SQLException ex) {

			Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}

		return objclientes;

	}

}
