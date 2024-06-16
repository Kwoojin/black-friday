package org.example.CatalogService.cassandra.repository;

import org.example.CatalogService.cassandra.entity.ProductEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CassandraRepository<ProductEntity, Long> {
}
