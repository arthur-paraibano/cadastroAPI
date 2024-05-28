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

    public List<Pessoa> findAllPessoas() {
        try {
            return dao.findAlla();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
