package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.util.List;

public class App {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1: Seller findById ===");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("\n=== TEST 2: Seller findByDepartment ===");
        Department departmentForTests = new Department(2, null);
        List<Seller> sellerList = sellerDao.findByDepartment(departmentForTests);
        sellerList.forEach(System.out::println);

        System.out.println("\n=== TEST 3: Seller findAll ===");
        List<Seller> sellerListAll = sellerDao.findAll();
        sellerListAll.forEach(System.out::println);

        System.out.println("\n=== TEST 4: Seller Insert ===");
        LocalDate birthDateForInsert = LocalDate.of(1997, 8, 24);
        Seller sellerToInsertAndLaterDelete = new Seller.Builder()
                .name("Greg")
                .department(departmentForTests)
                .baseSalary(40000.0)
                .id(null)
                .email("greg@gmail.com")
                .birthDate(birthDateForInsert)
                .build();
        sellerDao.insert(sellerToInsertAndLaterDelete);
        System.out.println("Inserted Seller ID: " + sellerToInsertAndLaterDelete.getId());

        System.out.println("\n=== TEST 5: Seller Update ===");
        Seller sellerToUpdate = sellerDao.findById(1);

        if (sellerToUpdate != null) {
            System.out.println("Seller before update: " + sellerToUpdate);
            sellerToUpdate.setName("Martha W. Updated");
            sellerToUpdate.setEmail("martha.updated@gmail.com");
            sellerToUpdate.setBaseSalary(62500.0);
            sellerToUpdate.setBirthDate(LocalDate.of(1980, 1, 20));
            Department existingDeptForUpdate = new Department(1, null);
            sellerToUpdate.setDepartment(existingDeptForUpdate);

            sellerDao.update(sellerToUpdate);
            System.out.println("Update method called for seller ID: " + sellerToUpdate.getId());

            Seller updatedSeller = sellerDao.findById(sellerToUpdate.getId());
            System.out.println("Seller after update: " + updatedSeller);
        } else {
            System.out.println("Seller with ID 1 not found, cannot perform update test.");
        }

        System.out.println("\n=== TEST 6: Seller DeleteById ===");
        Integer idToDelete;

        if (sellerToInsertAndLaterDelete.getId() != null) {
            idToDelete = sellerToInsertAndLaterDelete.getId();
            System.out.println("Attempting to delete seller with ID: " + idToDelete);

            sellerDao.deleteById(idToDelete);

            Seller foundAfterDelete = sellerDao.findById(idToDelete);

            if (foundAfterDelete == null) {
                System.out.println("SUCCESS: Seller with ID " + idToDelete + " was deleted successfully.");
            } else {
                System.err.println("FAILURE: Seller with ID " + idToDelete + " was still found after attempting delete.");
                System.err.println("Found data: " + foundAfterDelete);
            }
        } else {
            System.out.println("INFO: Skipping delete test because no valid ID was obtained from the seller inserted in TEST 4.");
            System.out.println("Reason: sellerToInsertAndLaterDelete.getId() was null.");
        }
    }
}
