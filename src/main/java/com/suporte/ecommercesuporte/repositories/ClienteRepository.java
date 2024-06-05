package com.suporte.ecommercesuporte.repositories;

import com.suporte.ecommercesuporte.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Aqui você pode adicionar métodos de consulta personalizados, se necessário
}
