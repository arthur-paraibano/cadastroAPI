package com.cadastro.api.services;

import com.cadastro.api.controller.PessoaController;
import com.cadastro.api.domain.Pessoa;
import com.cadastro.api.util.ValidGmail;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("pessoa")
public class PessoaService {

    private final PessoaController pessoaController;
    private final Gson gson;

    public PessoaService() {
        this.pessoaController = new PessoaController();
        this.gson = new Gson();
    }

    // http://localhost:8084/cadastroAPI/cadastro/pessoa/list/11
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listID/{id}")
    public Response listID(@PathParam("id") int id) {
        if (id <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro: Informe o ID do Usuário valido.").build();
        }
        Pessoa u = new Pessoa();
        u.setId(id);
        List<Pessoa> obj = this.pessoaController.listID(u);
        Type convert = new TypeToken<List<Pessoa>>() {
        }.getType();
        String json2 = gson.toJson(obj, convert);
        return Response.ok(json2).build();
    }

    // http://localhost:8084/cadastroAPI/cadastro/pessoa/list
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response listAll(String json) {

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

    // http://localhost:8084/cadastroAPI/cadastro/pessoa/insert
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/insert")
    public Response insert(String json) {
        try {
            Pessoa pessoa = gson.fromJson(json, Pessoa.class);
            if (pessoa != null) {
                if (pessoa.getNome() != null && pessoa.getEmail() != null && pessoa.getTelefone() != null) {
                    if (ValidGmail.isValidEmail(pessoa.getEmail())) {
                        String telefone = pessoa.getTelefone().replaceAll("[^0-9]", "");
                        if (telefone.length() == 11) {
                            pessoa.setNome(pessoa.getNome().toUpperCase());
                            pessoa.setEmail(pessoa.getEmail().toUpperCase());
                            boolean valor = this.pessoaController.insert(pessoa);
                            if (valor) {
                                return Response.ok("Pessoa inserida com sucesso").build();
                            } else {
                                return Response.serverError().entity("Erro ao inserir.").build();
                            }
                        } else {
                            return Response.serverError().entity("Erro: O telefone deve conter 11 dígitos.").build();
                        }
                    } else {
                        return Response.serverError().entity("Erro: E-mail / Gmail inválido!!!.").build();
                    }
                } else {
                    return Response.serverError().entity("Erro: Preencha todas as informações.").build();
                }
            } else {
                return Response.serverError().entity("Erro Objeto Null").build();
            }
        } catch (Exception ex) {
            return Response.serverError().entity("Erro ao inserir pessoa: " + ex.getMessage()).build();
        }
    }

    // http://localhost:8084/cadastroAPI/cadastro/pessoa/update/15
    @PUT
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response put(@PathParam("id") int id, String json) {
        try {
            if (json == null || json.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Erro: O corpo da requisição está vazio.").build();
            }
            Pessoa pessoa = gson.fromJson(json, Pessoa.class);
            if (pessoa == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Erro: Objeto Pessoa não pode ser nulo.").build();
            }
            if (pessoa.getNome() == null || pessoa.getEmail() == null || pessoa.getTelefone() == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Erro: Preencha todas as informações.").build();
            }
            String telefone = pessoa.getTelefone().replaceAll("[^0-9]", "");
            if (telefone.length() != 11) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Erro: O telefone deve conter 11 dígitos.").build();
            }

            pessoa.setId(id);
            pessoa.setNome(pessoa.getNome().toUpperCase());
            pessoa.setEmail(pessoa.getEmail().toUpperCase());

            boolean result = pessoaController.update(pessoa);
            if (result) {
                return Response.ok("Dados atualizados com sucesso").build();
            } else {
                return Response.serverError().entity("Erro ao atualizar dados.").build();
            }
        } catch (JsonSyntaxException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro: JSON inválido.").build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao mudar dados: " + e.getMessage()).build();
        }
    }

    // http://localhost:8084/cadastroAPI/cadastro/pessoa/delete/14
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        try {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(id);
            if (id <= 0) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Erro: Informe o ID do Usuário valido.").build();
            }
            pessoa.setId(id);
            boolean result = pessoaController.delete(pessoa);
            if (result) {
                return Response.ok("Dados excluido!!!").build();
            } else {
                return Response.serverError().entity("Erro ao excluir dados.").build();
            }
        } catch (JsonSyntaxException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro: JSON inválido.").build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao mudar dados: " + e.getMessage()).build();
        }
    }
}
