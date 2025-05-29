package application;


import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class App {
    public static void main(String[] args) {
        Department department = new Department(1, "Books");
        Seller seller = new Seller.Builder()
                .name("Kakaroto")
                        .department(new Department(2, "selling"))
                                .email("kakaroto@gmail.com")
                                        .id(2)
                                                .build();

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println(department);
        System.out.println(seller);
    }
}