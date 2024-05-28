package com.cadastro.api.services;

import com.cadastro.api.controller.PessoaController;
import com.cadastro.api.domain.Pessoa;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("pessoa")
public class PessoaService {

    private final PessoaController pessoaController;
    private final Gson gson;

    public PessoaService() {
        this.pessoaController = new PessoaController();
        this.gson = new Gson();
    }

    @GET
    @Path("/list")
    public Response list(String json) {

        Pessoa u = gson.fromJson(json, Pessoa.class);
        //  if (u != null && u.getNome().equals("admin") && u.getSenha().equals("admin")) {

        List<Pessoa> obj = this.pessoaController.listAll();

        Type convert = new TypeToken<List<Pessoa>>() {
        }.getType();

        String json2 = gson.toJson(obj, convert);

        return Response.ok(json2).build();
       // } else {
        //      return Response.serverError().entity("Usuario nao autenciado").build();
        //  }
    }

    @POST
    @Path("/insert")
    public Response list() {

        List<Pessoa> obj = this.pessoaController.listAll();
        
        Type convert = new TypeToken<List<Pessoa>>() {}.getType();
        String json2 = gson.toJson(obj, convert);

        return Response.ok(json2).build();
    }

}
