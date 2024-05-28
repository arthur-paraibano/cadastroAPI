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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void remove(Pessoa enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
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

    @Override
    public Pessoa findById(Pessoa enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    public List<Pessoa> findAlla() throws SQLException, Exception {
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
}
