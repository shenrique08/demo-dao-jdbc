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

        System.out.println("\n=== TEST 5: Seller Update ===");
        Seller sellerToUpdate = sellerDao.findById(1);

        if (sellerToUpdate != null) {
            System.out.println("Seller before update: " + sellerToUpdate);
            sellerToUpdate.setName("Martha Wine");
            sellerToUpdate.setEmail("martha.wine@gmail.com");
            sellerToUpdate.setBaseSalary(62000.0);
            sellerToUpdate.setBirthDate(LocalDate.of(1980, 1, 15));
            Department existingDept = new Department(1, null);
            sellerToUpdate.setDepartment(existingDept);

            sellerDao.update(sellerToUpdate);
            System.out.println("Update method called for seller ID: " + sellerToUpdate.getId());

            Seller updatedSeller = sellerDao.findById(sellerToUpdate.getId());
            System.out.println("Seller after update: " + updatedSeller);
        } else {
            System.out.println("Seller with ID 1 not found, cannot perform update test.");
        }

        // Example of how to test delete (if you have it)
        // System.out.println("\n=== TEST 6: Seller Delete ===");
        // int idToDelete = newSeller.getId(); // Or some other ID you want to test deletion with
        // if (idToDelete != null) {
        //     System.out.println("Attempting to delete seller with ID: " + idToDelete);
        //     sellerDao.deleteById(idToDelete);
        //     Seller deletedSeller = sellerDao.findById(idToDelete);
        //     if (deletedSeller == null) {
        //         System.out.println("Seller with ID " + idToDelete + " deleted successfully.");
        //     } else {
        //         System.out.println("Failed to delete seller with ID " + idToDelete + ". Seller still found: " + deletedSeller);
        //     }
        // } else {
        //     System.out.println("No seller ID available from insert test to perform delete test.");
        // }

    }
}