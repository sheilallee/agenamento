package com.advocacia.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Entidade Cliente - representa um cliente do escritório de advocacia
 * 
 * Extends PanacheEntity que já fornece:
 * - ID auto-incrementado (Long id)
 * - Métodos estáticos para CRUD (findAll, findById, persist, delete, etc.)
 */
@Entity
@Table(name = "clientes")
public class Cliente extends PanacheEntity {
    
    /**
     * Nome completo do cliente
     * @NotBlank garante que o campo não seja vazio
     */
    @NotBlank(message = "Nome é obrigatório")
    public String nome;
    
    /**
     * Email do cliente
     * @Email valida formato de email
     */
    @Email(message = "Email deve ser válido")
    @NotBlank(message = "Email é obrigatório")
    public String email;
    
    /**
     * Telefone do cliente
     */
    @NotBlank(message = "Telefone é obrigatório")
    public String telefone;
    
    /**
     * CPF do cliente
     */
    @NotBlank(message = "CPF é obrigatório")
    public String cpf;
    
    /**
     * Endereço do cliente (opcional)
     */
    public String endereco;
    
    // Construtor padrão necessário para JPA
    public Cliente() {
    }
    
    // Construtor com parâmetros para facilitar criação
    public Cliente(String nome, String email, String telefone, String cpf, String endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.endereco = endereco;
    }
}
