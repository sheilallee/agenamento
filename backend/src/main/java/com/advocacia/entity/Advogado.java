package com.advocacia.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

/**
 * Entidade Advogado - representa um advogado do escritório
 * 
 * Extends PanacheEntity que já fornece:
 * - ID auto-incrementado (Long id)
 * - Métodos estáticos para CRUD
 */
@Entity
@Table(name = "advogados")
public class Advogado extends PanacheEntity {
    
    /**
     * Nome completo do advogado
     */
    @NotBlank(message = "Nome é obrigatório")
    public String nome;
    
    /**
     * Número de registro na OAB (Ordem dos Advogados do Brasil)
     */
    @NotBlank(message = "OAB é obrigatório")
    public String oab;
    
    /**
     * Especialidade do advogado (ex: Civil, Criminal, Trabalhista)
     */
    @NotBlank(message = "Especialidade é obrigatória")
    public String especialidade;
    
    /**
     * Email do advogado
     */
    @NotBlank(message = "Email é obrigatório")
    public String email;
    
    // Construtor padrão
    public Advogado() {
    }
    
    // Construtor com parâmetros
    public Advogado(String nome, String oab, String especialidade, String email) {
        this.nome = nome;
        this.oab = oab;
        this.especialidade = especialidade;
        this.email = email;
    }
}
