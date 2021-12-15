package infrastructure.repository;
import infrastructure.entity._Catalog;
//import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogRepository {//extends JpaRepository<_Catalog, Long>{

    List<_Catalog> findAll();
    List<_Catalog> findAll(String phanLoai);
    <S extends _Catalog> S save(S entity);
    void deleteById(Long aLong);
    Optional<_Catalog> findById(Long aLong);
    _Catalog update(_Catalog catalog);

}
