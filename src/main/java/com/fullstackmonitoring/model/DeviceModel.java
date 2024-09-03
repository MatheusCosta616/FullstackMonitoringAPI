package com.fullstackmonitoring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;

/**
 * Representa um dispositivo no sistema de monitoramento fullstack.
 * Esta classe é uma entidade JPA que mapeia para a tabela "TD_DEVICE" no banco de dados.
 * 
 * <p>Cada dispositivo é identificado por um UUID único e contém informações como
 * nome, status, último ping, localização e logs.</p>
 * 
 * <p>A classe utiliza as anotações Lombok para gerar automaticamente getters, setters,
 * e construtores, reduzindo a verbosidade do código.</p>
 */
@Table(name = "TD_DEVICE")
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DeviceModel {
    
    /**
     * Identificador único do dispositivo.
     * Este campo é gerado automaticamente usando a estratégia UUID.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    /**
     * Nome do dispositivo.
     * Este campo é usado para identificar o dispositivo de forma amigável.
     */
    private String name;

    /**
     * Status atual do dispositivo.
     * Pode indicar se o dispositivo está ativo, inativo, em manutenção, etc.
     */
    private String status;

    /**
     * Timestamp do último ping recebido do dispositivo.
     * Usado para monitorar a atividade e conectividade do dispositivo.
     */
    private String lastPing;

    /**
     * Localização física do dispositivo.
     * Pode ser usado para rastrear onde o dispositivo está instalado ou sendo utilizado.
     */
    private String location;

    /**
     * Logs associados ao dispositivo.
     * Pode conter informações sobre atividades, erros ou eventos relacionados ao dispositivo.
     */
    private String logs;
}
