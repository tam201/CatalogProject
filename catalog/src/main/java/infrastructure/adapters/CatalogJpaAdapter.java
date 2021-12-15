package infrastructure.adapters;

import infrastructure.database.MySQLDB;
import infrastructure.entity._Catalog;
import infrastructure.mapper.CatalogMapper;
import infrastructure.repository.CatalogRepository;
import domain.data.CatalogDto;
import domain.ports.CatalogPersistencePort;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CatalogJpaAdapter implements CatalogPersistencePort{

    private final CatalogRepository catalogRepository;

    public CatalogJpaAdapter(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }


    @Override
    public CatalogDto addCatalog(CatalogDto CatalogDto) {

        _Catalog catalog = CatalogMapper.INSTANCE.CatalogDtoToCatalog(CatalogDto);

        _Catalog catalogSaved = catalogRepository.save(catalog);

        return CatalogMapper.INSTANCE.CatalogToCatalogDto(catalogSaved);
    }

    @Override
    public void deleteCatalogById(Long id) {
        catalogRepository.deleteById(id);
    }

    @Override
    public CatalogDto updateCatalog(CatalogDto CatalogDto) {
        _Catalog catalog = CatalogMapper.INSTANCE.CatalogDtoToCatalog(CatalogDto);

        _Catalog catalogSaved = catalogRepository.update(catalog);

        return CatalogMapper.INSTANCE.CatalogToCatalogDto(catalogSaved);
    }

    @Override
    public List<CatalogDto> getCatalogs() {

        List<_Catalog> catalogList = catalogRepository.findAll();

        return CatalogMapper.INSTANCE.CatalogListToCatalogDtoList(catalogList);
    }

    @Override
    public CatalogDto getCatalogById(Long CatalogId) {

        Optional<_Catalog> catalog = catalogRepository.findById(CatalogId);

        if (catalog.isPresent()) {
            return CatalogMapper.INSTANCE.CatalogToCatalogDto(catalog.get());
        }

        return null;
    }

    public static void main(String[] args) {
        CatalogJpaAdapter adapter = new CatalogJpaAdapter(new MySQLDB());
        CatalogDto c = new CatalogDto("6","tinh/tp","TH");

        adapter.addCatalog(c);
        c.setMoTa("Thanh Hoa");
        adapter.updateCatalog(c);
        List<CatalogDto> list = adapter.getCatalogs();
        for (CatalogDto obj :list){
            System.out.println(obj.getId()+" "+obj.getPhanLoai()+" "+obj.getMoTa());
        }
        CatalogDto c1 = adapter.getCatalogById(Long.valueOf("4"));

        if (c1 != null){

            System.out.println("\n"+c1.getId()+" "+c1.getPhanLoai()+" "+c1.getMoTa());
        }
        adapter.deleteCatalogById(Long.valueOf(c.getId()));

    }


}
