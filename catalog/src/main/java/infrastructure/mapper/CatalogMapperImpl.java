package infrastructure.mapper;

import infrastructure.entity._Catalog;
import domain.data.CatalogDto;

import java.util.ArrayList;
import java.util.List;

public class CatalogMapperImpl implements  CatalogMapper{
    @Override
    public CatalogDto CatalogToCatalogDto(_Catalog catalog){
        return new CatalogDto(catalog.getId().toString(), catalog.getPhanLoai(), catalog.getMoTa());
    }

    public _Catalog CatalogDtoToCatalog(CatalogDto catalogDto){
        return new _Catalog(Long.valueOf(catalogDto.getId()),catalogDto.getPhanLoai(),catalogDto.getMoTa());
    }

    public List<CatalogDto> CatalogListToCatalogDtoList(List<_Catalog> catalogList){
        List<CatalogDto> list = new ArrayList<>();
        for (_Catalog obj : catalogList){
            list.add(CatalogToCatalogDto(obj));
        }
        return list;
    }

    public List<_Catalog> CatalogDtoListToCatalogList(List<CatalogDto> catalogDtoList){
        List<_Catalog> list = new ArrayList<>();
        for (CatalogDto obj : catalogDtoList){
            list.add(CatalogDtoToCatalog(obj));
        }
        return list;
    }
}
