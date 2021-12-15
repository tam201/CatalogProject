package infrastructure.database;

import infrastructure.entity._Catalog;
import infrastructure.repository.CatalogRepository;

import java.sql.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class MySQLDB implements CatalogRepository {
    private static final String DB_URL = "jdbc:mysql://localhost:3306?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = "20082000";
    private static final String TABLE_NAME = "catalogdb.`catalog`";




    @Override
    public List<_Catalog> findAll() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM " + TABLE_NAME);

            return setToList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<_Catalog> findAll(String phanLoai) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = conn.createStatement();

            String sql = "SELECT * FROM " + TABLE_NAME + "WHERE phanloai = \"" + phanLoai + "\"";
            ResultSet rs = st.executeQuery(sql);
            return setToList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //2 phuong thuc ben tren co doan code trung nhau nen tach ra de tranh lap code
    public List<_Catalog> setToList (ResultSet rs){
        try {

            List<_Catalog> list = new ArrayList<>();
            while (rs.next()) {

                _Catalog tmp = new _Catalog();

                tmp.setId(rs.getLong("id"));
                tmp.setPhanLoai(rs.getString("phanloai"));
                tmp.setMoTa(rs.getString("mota"));
                list.add(tmp);

            }
            rs.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    @Override
    public Optional<_Catalog> findById(Long aLong) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = conn.createStatement();

            String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = " + aLong.toString() ;

            ResultSet rs = st.executeQuery(sql);


            _Catalog res = new _Catalog();
            if (rs.next() ) {

                res.setId(rs.getLong("id"));
                res.setPhanLoai(rs.getString("phanloai"));
                res.setMoTa(rs.getString("mota"));
                rs.close();
                return Optional.of(res);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }






    @Override
    public <S extends _Catalog> S save(S entity) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "INSERT INTO " + TABLE_NAME + " (`id`, `phanloai`,`mota`) VALUES (?, ?, ?)";

            PreparedStatement p = conn.prepareStatement(sql);

            p.setString(1, entity.getId().toString());
            p.setString(2, entity.getPhanLoai());
            p.setString(3, entity.getMoTa());


            p.execute();

            return entity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void deleteById(Long aLong) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "DELETE FROM " + TABLE_NAME + "WHERE id = ?";

            PreparedStatement p = conn.prepareStatement(sql);

            p.setString(1, aLong.toString());

            p.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public _Catalog update(_Catalog catalog) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "UPDATE " + TABLE_NAME + "SET mota = ? , phanloai = ?" + "WHERE id = ?";

            PreparedStatement p = conn.prepareStatement(sql);


            p.setString(1, catalog.getMoTa());
            p.setString(2,catalog.getPhanLoai());
            p.setString(3, catalog.getId().toString());

            p.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return catalog;
    }
    public void print(List<_Catalog> list){
        for (_Catalog c : list){
            System.out.println(c.getId()+" "+c.getPhanLoai()+" "+c.getMoTa());
        }
    }
    public static void main(String[] args) {




        MySQLDB db = new MySQLDB();
        //db.save(new _Catalog(Long.valueOf("1"),"tinh","QN"));

        db.save(new _Catalog(Long.valueOf("5"),"school","HUST"));
        _Catalog catalog = new _Catalog(Long.valueOf("3"),"sinh vien","Nguyen Tien Manh");
        db.update(catalog);
        List<_Catalog> list = db.findAll();
        db.print(list);
        System.out.println();
        List<_Catalog> list1 = db.findAll("tinh/tp");
        db.print(list1);
        System.out.println();
        _Catalog c = db.findById( Long.valueOf("1")).get();
        System.out.println(c.getId()+" "+c.getPhanLoai()+" "+c.getMoTa());
        db.deleteById(Long.valueOf("5"));





    }

}