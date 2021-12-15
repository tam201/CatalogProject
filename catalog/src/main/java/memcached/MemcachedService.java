package memcached;

import infrastructure.database.MySQLDB;
import infrastructure.repository.CatalogRepository;
import infrastructure.entity._Catalog;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemcachedService {

    private final CatalogRepository catalogRepository;

    public MemcachedService() {
        this.catalogRepository = new MySQLDB();
    }



    private void simulateSlowService() {
        try {
            long time = 2000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }




    /* Kết quả của hàm này sẽ được lưu vào bộ nhớ cache,
    mỗi lần gọi hàm  nó sẽ kiểm tra với id truyền vào đã có dữ liệu trong cache chưa,
    nếu có rồi thì  lấy từ trong cache.
     */
    @Cacheable(value= "catalog", key ="#aLong.toString()")
    public Optional<_Catalog> findById(Long aLong) {

        simulateSlowService();

        return catalogRepository.findById(aLong);
    }
    @Cacheable(value = "catalog",key ="#catalog.id.toString()")
    public _Catalog insert(_Catalog catalog){
        return catalogRepository.save(catalog);
    }


    /* Mỗi lần  gọi hàm này nó sẽ xóa toàn bộ dữ liệu  trong cache catalog.*/
    @CacheEvict(value = "catalog", allEntries = true)
    public void clearCache() {
    }

    /* Mỗi lần  gọi hàm này nó sẽ xóa dữ liệu với id tương ứng ở trong cache catalog.*/
    @CacheEvict(value= "catalog", key ="#aLong.toString()")
    public void deleteById(Long aLong) {
        catalogRepository.deleteById(aLong);
    }

    /* Dữ liệu trả về sẽ được ghi đè vào cache */
    @CachePut(value = "catalog", key ="#catalog.id.toString()")
    public _Catalog update(_Catalog catalog) {

        simulateSlowService();
        return catalogRepository.update(catalog);
    }


}