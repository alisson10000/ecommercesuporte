package com.suporte.ecommercesuporte.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suporte.ecommercesuporte.models.Produto;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
