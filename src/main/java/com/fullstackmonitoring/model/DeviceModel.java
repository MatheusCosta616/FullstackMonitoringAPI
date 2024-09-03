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
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String name;
    private String status;
    private String lastPing;
    private String location;
    private String logs;
}
