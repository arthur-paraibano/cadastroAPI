package com.cadastro.api.dao;

import com.cadastro.api.domain.Pessoa;
import com.cadastro.api.imp.GenericDao;
import com.cadastro.api.util.base.BaseDados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDao implements GenericDao<Pessoa> {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    @Override
    public void add(Pessoa enty) throws SQLException, Exception {
        this.con = BaseDados.getConnection();
        String sql = "INSERT INTO cadastro.cfg_pessoa (CFG_NOME, CFG_TELEFONE, CFG_EMAIL) VALUES(?,?,?)";
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setString(1, enty.getNome());
        this.stmt.setString(2, enty.getTelefone());
        this.stmt.setString(3, enty.getEmail());
        this.stmt.executeUpdate();
        BaseDados.closeConnection(con, stmt, rs);
    }

    @Override
    public void update(Pessoa enty) throws SQLException, Exception {
        String sql = "UPDATE cadastro.cfg_pessoa SET CFG_NOME = ?, CFG_TELEFONE = ?, CFG_EMAIL = ? WHERE ID =?";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);
        this.stmt.setString(1, enty.getNome());
        this.stmt.setString(2, enty.getTelefone());
        this.stmt.setString(3, enty.getEmail());
        this.stmt.setInt(4, enty.getId());
        this.stmt.executeUpdate();
        BaseDados.closeConnection(con, stmt, rs);
    }

    public Pessoa findById(Integer id) throws SQLException, Exception {
        Pessoa object = null;
        String sql = "SELECT ID, CFG_NOME, CFG_TELEFONE, CFG_EMAIL FROM cadastro.cfg_pessoa where id = ? ";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setInt(1, id);
        this.rs = this.stmt.executeQuery();

        if (this.rs.next()) {
            object = new Pessoa(
                    this.rs.getInt("ID"),
                    this.rs.getString("CFG_NOME"),
                    this.rs.getString("CFG_TELEFONE"),
                    this.rs.getString("CFG_EMAIL"));
        }
        return object;
    }

    @Override
    public void remove(Pessoa enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    public void delete(Pessoa enty) throws SQLException, Exception {
        String sql = "DELETE FROM cfg_pessoa WHERE id = ?";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);
        this.stmt.setInt(1, enty.getId());
        this.stmt.execute();
        BaseDados.closeConnection(con, stmt);
    }

    @Override
    public List<Pessoa> findAll() throws SQLException, Exception {
        List<Pessoa> lista = new ArrayList<>();
        String sql = "SELECT ID, CFG_NOME AS NOME, CFG_TELEFONE AS TELEFONE, CFG_EMAIL AS EMAIL FROM cadastro.cfg_pessoa ORDER BY NOME ASC";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.rs = this.stmt.executeQuery();
        while (rs.next()) {
            lista.add(
                    new Pessoa(rs.getInt("ID"), rs.getString("NOME"), rs.getString("TELEFONE"), rs.getString("EMAIL")));
        }
        BaseDados.closeConnection(con, stmt, rs);
        return lista;
    }

    public List<Pessoa> findId(Pessoa enty) throws SQLException, Exception {
        List<Pessoa> lista = new ArrayList<>();
        String sql = "SELECT ID, CFG_NOME AS NOME, CFG_TELEFONE AS TELEFONE, CFG_EMAIL AS EMAIL FROM cadastro.cfg_pessoa where ID = ? ORDER BY NOME ASC";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setInt(1, enty.getId());
        this.rs = this.stmt.executeQuery();
        while (rs.next()) {
            lista.add(
                    new Pessoa(rs.getInt("ID"), rs.getString("NOME"), rs.getString("TELEFONE"), rs.getString("EMAIL")));
        }
        BaseDados.closeConnection(con, stmt, rs);
        return lista;
    }

    @Override
    public Pessoa findById(Pessoa enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}
