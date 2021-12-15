package domain.ports;
import domain.data.CatalogDto;

import java.util.List;
public interface CatalogPersistencePort {
    CatalogDto addCatalog(CatalogDto CatalogDto);

    void deleteCatalogById(Long id);

    CatalogDto updateCatalog(CatalogDto CatalogDto);

    List<CatalogDto> getCatalogs();

    CatalogDto getCatalogById(Long CatalogId);

}
