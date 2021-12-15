package domain.service;

import domain.ports.CatalogServicePort;
import domain.data.CatalogDto;
import domain.ports.CatalogPersistencePort;

import java.util.List;

public class CatalogServiceImpl implements CatalogServicePort {

    private CatalogPersistencePort catalogPersistencePort;

    public CatalogServiceImpl(CatalogPersistencePort catalogPersistencePort) {
        this.catalogPersistencePort = catalogPersistencePort;
    }

    @Override
    public CatalogDto addCatalog(CatalogDto CatalogDto) {
        return catalogPersistencePort.addCatalog(CatalogDto);
    }

    @Override
    public void deleteCatalogById(Long id) {
        catalogPersistencePort.deleteCatalogById(id);
    }

    @Override
    public CatalogDto updateCatalog(CatalogDto CatalogDto) {
        return catalogPersistencePort.updateCatalog(CatalogDto);
    }

    @Override
    public List<CatalogDto> getCatalogs() {
        return catalogPersistencePort.getCatalogs();
    }

    @Override
    public CatalogDto getCatalogById(Long CatalogId) {
        return catalogPersistencePort.getCatalogById(CatalogId);
    }
}
