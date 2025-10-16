package com.advocacia.resource;

import com.advocacia.entity.Agendamento;
import com.advocacia.entity.Advogado;
import com.advocacia.entity.Cliente;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * Resource REST para gerenciar Agendamentos
 * 
 * Endpoints disponíveis:
 * - GET /api/agendamentos - lista todos os agendamentos
 * - GET /api/agendamentos/{id} - busca agendamento por ID
 * - POST /api/agendamentos - cria novo agendamento
 * - PUT /api/agendamentos/{id} - atualiza agendamento existente
 * - PATCH /api/agendamentos/{id}/status - atualiza apenas o status
 * - DELETE /api/agendamentos/{id} - remove agendamento
 */
@Path("/api/agendamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AgendamentoResource {
    
    /**
     * Lista todos os agendamentos ordenados por data/hora
     * 
     * @return Lista de agendamentos
     */
    @GET
    public List<Agendamento> listarTodos() {
        return Agendamento.listAll();
    }
    
    /**
     * Busca um agendamento por ID
     * 
     * @param id ID do agendamento
     * @return Agendamento encontrado ou 404 se não existir
     */
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Agendamento agendamento = Agendamento.findById(id);
        if (agendamento == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Agendamento não encontrado")
                    .build();
        }
        return Response.ok(agendamento).build();
    }
    
    /**
     * Cria um novo agendamento
     * 
     * @param agendamento Dados do agendamento a ser criado
     * @return Agendamento criado com status 201
     */
    @POST
    @Transactional
    public Response criar(@Valid Agendamento agendamento) {
        // Valida se cliente e advogado existem
        if (agendamento.cliente != null && agendamento.cliente.id != null) {
            Cliente cliente = Cliente.findById(agendamento.cliente.id);
            if (cliente == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Cliente não encontrado")
                        .build();
            }
            agendamento.cliente = cliente;
        }
        
        if (agendamento.advogado != null && agendamento.advogado.id != null) {
            Advogado advogado = Advogado.findById(agendamento.advogado.id);
            if (advogado == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Advogado não encontrado")
                        .build();
            }
            agendamento.advogado = advogado;
        }
        
        agendamento.persist();
        return Response.status(Response.Status.CREATED).entity(agendamento).build();
    }
    
    /**
     * Atualiza um agendamento existente
     * 
     * @param id ID do agendamento a ser atualizado
     * @param agendamentoAtualizado Novos dados do agendamento
     * @return Agendamento atualizado ou 404 se não existir
     */
    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, @Valid Agendamento agendamentoAtualizado) {
        Agendamento agendamento = Agendamento.findById(id);
        if (agendamento == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Agendamento não encontrado")
                    .build();
        }
        
        // Atualiza os campos
        if (agendamentoAtualizado.cliente != null && agendamentoAtualizado.cliente.id != null) {
            Cliente cliente = Cliente.findById(agendamentoAtualizado.cliente.id);
            if (cliente != null) {
                agendamento.cliente = cliente;
            }
        }
        
        if (agendamentoAtualizado.advogado != null && agendamentoAtualizado.advogado.id != null) {
            Advogado advogado = Advogado.findById(agendamentoAtualizado.advogado.id);
            if (advogado != null) {
                agendamento.advogado = advogado;
            }
        }
        
        agendamento.dataHora = agendamentoAtualizado.dataHora;
        agendamento.descricao = agendamentoAtualizado.descricao;
        agendamento.status = agendamentoAtualizado.status;
        
        return Response.ok(agendamento).build();
    }
    
    /**
     * Atualiza apenas o status de um agendamento
     * 
     * @param id ID do agendamento
     * @param novoStatus Novo status (PENDENTE, CONFIRMADO, CANCELADO, CONCLUIDO)
     * @return Agendamento atualizado
     */
    @PATCH
    @Path("/{id}/status")
    @Transactional
    public Response atualizarStatus(@PathParam("id") Long id, 
                                     @QueryParam("status") Agendamento.StatusAgendamento novoStatus) {
        Agendamento agendamento = Agendamento.findById(id);
        if (agendamento == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Agendamento não encontrado")
                    .build();
        }
        
        if (novoStatus == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Status é obrigatório")
                    .build();
        }
        
        agendamento.status = novoStatus;
        return Response.ok(agendamento).build();
    }
    
    /**
     * Remove um agendamento
     * 
     * @param id ID do agendamento a ser removido
     * @return Status 204 (No Content) se removido com sucesso
     */
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        boolean deletado = Agendamento.deleteById(id);
        if (!deletado) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Agendamento não encontrado")
                    .build();
        }
        return Response.noContent().build();
    }
}
