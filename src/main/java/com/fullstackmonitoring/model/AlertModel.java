package com.fullstackmonitoring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

/**
 * Representa um alerta no sistema de monitoramento.
 * Cada alerta está associado a um dispositivo específico.
 */
@Entity
@Table(name = "TD_ALERT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlertModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private DeviceModel device;
    private String condition;
    private String threshold;
    private String message;
}
