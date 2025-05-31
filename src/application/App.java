package application;


import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class App {
    public static void main(String[] args) {


        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println("=== TEST 1: Seller findById ===");
        Seller seller = sellerDao.findById(3);

        System.out.println(seller);

        System.out.println("\n=== TEST 2: Seller findByDepartment ===");
        Department department = new Department(2, null);
        List<Seller> sellerList = sellerDao.findByDepartment(department);

        sellerList.forEach(System.out::println);

        System.out.println("\n=== TEST 3: Seller findAll ===");
        List<Seller> sellerListAll = sellerDao.findAll();

        sellerListAll.forEach(System.out::println);


        LocalDate localDate = LocalDate.of(1997, 8, 24);
        System.out.println("\n=== TEST 4: Seller Insert ===");
        Seller seller1 = new Seller.Builder()
                .name("Greg")
                        .department(department)
                                .baseSalary(40.000)
                                        .id(null)
                                                .email("greg@gmail.com")
                                                        .birthDate(localDate)
                                                                .build();
        sellerDao.insert(seller1);
        System.out.println(seller1.getId());


    }
}