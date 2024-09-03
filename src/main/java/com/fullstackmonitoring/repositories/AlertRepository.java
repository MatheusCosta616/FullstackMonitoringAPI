package com.fullstackmonitoring.repositories;

import com.fullstackmonitoring.model.AlertModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repositório para operações de persistência relacionadas a AlertModel.
 * Fornece métodos CRUD padrão e métodos personalizados para a entidade AlertModel.
 */
@Repository
public interface AlertRepository extends JpaRepository<AlertModel, UUID> {
    
    /**
     * Encontra todos os alertas associados a um dispositivo específico.
     *
     * @param deviceId O UUID do dispositivo.
     * @return Uma lista de AlertModel associados ao dispositivo.
     */
    List<AlertModel> findByDeviceId(UUID deviceId);
}