package aplication;

import domain.data.CatalogDto;
import domain.ports.CatalogServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Catalog")
public class CatalogController {
    @Autowired
    private CatalogServicePort catalogServicePort;

//anh xa
    @PostMapping ("/add")
    public CatalogDto addCatalog(@RequestBody CatalogDto catalogDto){
        return catalogServicePort.addCatalog(catalogDto);
    }
    @PutMapping("/update")
    public CatalogDto updateCatalog(@RequestBody CatalogDto catalogDto){
        return catalogServicePort.updateCatalog(catalogDto);
    }

    @GetMapping("/get{id}")
    public CatalogDto getCatalogById(@PathVariable Long id){
        return catalogServicePort.getCatalogById(id);
    }

    @GetMapping("/get")
    public List<CatalogDto> getAllCatalog(){
        return catalogServicePort.getCatalogs();
    }

    @GetMapping("delete/{id}")
    public void deleteCatalogById(@PathVariable Long id){
         catalogServicePort.deleteCatalogById(id);
    }


}
