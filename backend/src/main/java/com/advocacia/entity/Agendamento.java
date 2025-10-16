package com.advocacia.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Entidade Agendamento - representa um agendamento de atendimento
 * 
 * Relaciona um Cliente com um Advogado em uma data/hora específica
 */
@Entity
@Table(name = "agendamentos")
public class Agendamento extends PanacheEntity {
    
    /**
     * Cliente que solicita o atendimento
     * ManyToOne: vários agendamentos podem pertencer a um cliente
     * EAGER: carrega o cliente automaticamente junto com o agendamento
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", nullable = false)
    @NotNull(message = "Cliente é obrigatório")
    public Cliente cliente;
    
    /**
     * Advogado responsável pelo atendimento
     * ManyToOne: vários agendamentos podem pertencer a um advogado
     * EAGER: carrega o advogado automaticamente
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "advogado_id", nullable = false)
    @NotNull(message = "Advogado é obrigatório")
    public Advogado advogado;
    
    /**
     * Data e hora do agendamento
     */
    @NotNull(message = "Data/hora é obrigatória")
    public LocalDateTime dataHora;
    
    /**
     * Descrição ou motivo do atendimento
     */
    @Column(length = 500)
    public String descricao;
    
    /**
     * Status do agendamento (PENDENTE, CONFIRMADO, CANCELADO, CONCLUIDO)
     */
    @Enumerated(EnumType.STRING)
    public StatusAgendamento status;
    
    // Construtor padrão
    public Agendamento() {
        this.status = StatusAgendamento.PENDENTE; // Status inicial
    }
    
    // Construtor com parâmetros
    public Agendamento(Cliente cliente, Advogado advogado, LocalDateTime dataHora, String descricao) {
        this.cliente = cliente;
        this.advogado = advogado;
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.status = StatusAgendamento.PENDENTE;
    }
    
    /**
     * Enum para status do agendamento
     */
    public enum StatusAgendamento {
        PENDENTE,      // Aguardando confirmação
        CONFIRMADO,    // Confirmado pelo advogado
        CANCELADO,     // Cancelado
        CONCLUIDO      // Atendimento realizado
    }
}
