package application;


import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public class App {
    public static void main(String[] args) {


        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println("=== TESTE 1: Seller findById ===");
        Seller seller = sellerDao.findById(3);

        System.out.println(seller);

        System.out.println("\n=== TESTE 2: Seller findByDepartment ===");
        Department department = new Department(2, null);
        List<Seller> sellerList = sellerDao.findByDepartment(department);

        sellerList.forEach(System.out::println);


    }
}