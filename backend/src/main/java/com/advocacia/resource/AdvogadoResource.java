package com.advocacia.resource;

import java.util.List;

import com.advocacia.entity.Advogado;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Resource REST para gerenciar Advogados
 * 
 * Endpoints disponíveis:
 * - GET /api/advogados - lista todos os advogados
 * - GET /api/advogados/{id} - busca advogado por ID
 * - POST /api/advogados - cria novo advogado
 * - PUT /api/advogados/{id} - atualiza advogado existente
 * - DELETE /api/advogados/{id} - remove advogado
 */
@Path("/api/advogados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdvogadoResource {
    
    /**
     * Lista todos os advogados cadastrados
     * 
     * @return Lista de advogados
     */
    @GET
    public List<Advogado> listarTodos() {
        return Advogado.listAll();
    }
    
    /**
     * Busca um advogado por ID
     * 
     * @param id ID do advogado
     * @return Advogado encontrado ou 404 se não existir
     */
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Advogado advogado = Advogado.findById(id);
        if (advogado == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Advogado não encontrado")
                    .build();
        }
        return Response.ok(advogado).build();
    }
    
    /**
     * Cria um novo advogado
     * 
     * @param advogado Dados do advogado a ser criado
     * @return Advogado criado com status 201
     */
    @POST
    @Transactional
    public Response criar(@Valid Advogado advogado) {
        advogado.persist();
        return Response.status(Response.Status.CREATED).entity(advogado).build();
    }
    
    /**
     * Atualiza um advogado existente
     * 
     * @param id ID do advogado a ser atualizado
     * @param advogadoAtualizado Novos dados do advogado
     * @return Advogado atualizado ou 404 se não existir
     */
    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, @Valid Advogado advogadoAtualizado) {
        Advogado advogado = Advogado.findById(id);
        if (advogado == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Advogado não encontrado")
                    .build();
        }
        
        // Atualiza os campos
        advogado.nome = advogadoAtualizado.nome;
        advogado.oab = advogadoAtualizado.oab;
        advogado.especialidade = advogadoAtualizado.especialidade;
        advogado.email = advogadoAtualizado.email;
        advogado.telefone = advogadoAtualizado.telefone;
        
        return Response.ok(advogado).build();
    }
    
    /**
     * Remove um advogado
     * 
     * @param id ID do advogado a ser removido
     * @return Status 204 (No Content) se removido com sucesso
     */
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        boolean deletado = Advogado.deleteById(id);
        if (!deletado) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Advogado não encontrado")
                    .build();
        }
        return Response.noContent().build();
    }
}
