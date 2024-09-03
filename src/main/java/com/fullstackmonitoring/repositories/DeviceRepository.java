package com.fullstackmonitoring.repositories;

import com.fullstackmonitoring.model.DeviceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repositório para operações de persistência relacionadas a DeviceModel.
 * Fornece métodos CRUD padrão para a entidade DeviceModel.
 */
@Repository
public interface DeviceRepository extends JpaRepository<DeviceModel, UUID> {
}
