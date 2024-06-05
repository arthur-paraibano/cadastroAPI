package com.cadastro.api.controller;

import java.util.ArrayList;
import java.util.List;

import com.cadastro.api.dao.PessoaDao;
import com.cadastro.api.domain.Pessoa;

public class PessoaController {

    private final PessoaDao dao;

    public PessoaController() {
        this.dao = new PessoaDao();
    }

    public void addPessoa(Pessoa enty) {
        try {
            dao.add(enty);
        } catch (Exception e) {
        }
    }

    public List<Pessoa> listAll() {
        try {
            return dao.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public boolean insert(Pessoa pessoa) {
        try {
            dao.add(pessoa);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Pessoa pessoa) {
        try {
            dao.update(pessoa);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Pessoa enty) {
        try {
            dao.delete(enty);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
