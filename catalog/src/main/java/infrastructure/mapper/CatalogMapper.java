package infrastructure.mapper;

import infrastructure.entity._Catalog;
import domain.data.CatalogDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CatalogMapper {
    CatalogMapper INSTANCE = Mappers.getMapper(CatalogMapper.class);

    CatalogDto CatalogToCatalogDto(_Catalog catalog);

    _Catalog CatalogDtoToCatalog(CatalogDto catalogDto);

    List<CatalogDto> CatalogListToCatalogDtoList(List<_Catalog> catalogList);

    List<_Catalog> CatalogDtoListToCatalogList(List<CatalogDto> catalogDtoList);
}
