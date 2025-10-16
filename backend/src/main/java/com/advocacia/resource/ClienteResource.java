package com.advocacia.resource;

import com.advocacia.entity.Cliente;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * Resource REST para gerenciar Clientes
 * 
 * Endpoints disponíveis:
 * - GET /api/clientes - lista todos os clientes
 * - GET /api/clientes/{id} - busca cliente por ID
 * - POST /api/clientes - cria novo cliente
 * - PUT /api/clientes/{id} - atualiza cliente existente
 * - DELETE /api/clientes/{id} - remove cliente
 */
@Path("/api/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {
    
    /**
     * Lista todos os clientes cadastrados
     * 
     * @return Lista de clientes
     */
    @GET
    public List<Cliente> listarTodos() {
        return Cliente.listAll();
    }
    
    /**
     * Busca um cliente por ID
     * 
     * @param id ID do cliente
     * @return Cliente encontrado ou 404 se não existir
     */
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Cliente cliente = Cliente.findById(id);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Cliente não encontrado")
                    .build();
        }
        return Response.ok(cliente).build();
    }
    
    /**
     * Cria um novo cliente
     * 
     * @param cliente Dados do cliente a ser criado
     * @return Cliente criado com status 201
     */
    @POST
    @Transactional // Necessário para operações de escrita no banco
    public Response criar(@Valid Cliente cliente) {
        cliente.persist(); // Panache persiste automaticamente
        return Response.status(Response.Status.CREATED).entity(cliente).build();
    }
    
    /**
     * Atualiza um cliente existente
     * 
     * @param id ID do cliente a ser atualizado
     * @param clienteAtualizado Novos dados do cliente
     * @return Cliente atualizado ou 404 se não existir
     */
    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, @Valid Cliente clienteAtualizado) {
        Cliente cliente = Cliente.findById(id);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Cliente não encontrado")
                    .build();
        }
        
        // Atualiza os campos
        cliente.nome = clienteAtualizado.nome;
        cliente.email = clienteAtualizado.email;
        cliente.telefone = clienteAtualizado.telefone;
        cliente.cpf = clienteAtualizado.cpf;
        
        return Response.ok(cliente).build();
    }
    
    /**
     * Remove um cliente
     * 
     * @param id ID do cliente a ser removido
     * @return Status 204 (No Content) se removido com sucesso
     */
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        boolean deletado = Cliente.deleteById(id);
        if (!deletado) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Cliente não encontrado")
                    .build();
        }
        return Response.noContent().build();
    }
}
