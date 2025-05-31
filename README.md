# JDBC DAO Project (demo-dao-jdbc)

## 📚 Description

This project is a demonstration of the Data Access Object (DAO) pattern implemented in Java using JDBC. It was developed as part of the "Java COMPLETO" course by Professor Nelio Alves. The project showcases CRUD (Create, Read, Update, Delete) operations for `Seller` and `Department` entities, interacting with a MySQL database.

---

## ✨ Features

* **DAO Pattern Implementation**: Clear separation of data access logic from business logic.
* **Seller Management**:
    * Find a seller by ID.
    * Find sellers by department.
    * List all sellers.
    * Insert a new seller.
    * Update an existing seller's information.
    * Delete a seller by ID.
* **Department Entity**: Basic structure for `Department` objects.
* **Database Connection Management**: Utility class (`DB.java`) to handle database connections and resource closing.
* **Custom Exceptions**: Specific exceptions for database-related errors (`DbException`, `DbIntegrityException`).
* **Maven Project**: Managed dependencies and build process using Maven.

---

## 🛠️ Technologies Used

* **Java**: Core programming language.
* **JDBC**: For database connectivity and operations.
* **MySQL**: Relational database used for data storage.
    * MySQL Connector/J (version 8.0.28)
* **Maven**: Dependency management and project build tool.

---

## ⚙️ Setup and Installation

1.  **Clone the repository:**
    ```bash
    git clone <your-repository-url>
    cd demo-dao-jdbc
    ```
2.  **Database Setup:**
    * Ensure you have MySQL server installed and running.
    * Create a database (e.g., `coursejdbc`).
    * Update the `db.properties` file in the root of the project with your MySQL username, password, and database URL:
        ```properties
        user=your_mysql_user
        password=your_mysql_password
        dburl=jdbc:mysql://localhost:3306/your_database_name?useSSL=false&allowPublicKeyRetrieval=true
        useSSL=false
        ```
    * You will need to create the `department` and `seller` tables in your database. Refer to the entity classes (`Department.java`, `Seller.java`) and the `SellerDaoJDBC.java` for the table structure. A basic schema might look like this:

        ```sql
        CREATE DATABASE IF NOT EXISTS coursejdbc;
        USE coursejdbc;

        CREATE TABLE department (
          Id int(11) NOT NULL AUTO_INCREMENT,
          Name varchar(60) DEFAULT NULL,
          PRIMARY KEY (Id)
        );

        CREATE TABLE seller (
          Id int(11) NOT NULL AUTO_INCREMENT,
          Name varchar(60) NOT NULL,
          Email varchar(100) NOT NULL,
          BirthDate datetime NOT NULL,
          BaseSalary double NOT NULL,
          DepartmentId int(11) NOT NULL,
          PRIMARY KEY (Id),
          FOREIGN KEY (DepartmentId) REFERENCES department (Id)
        );

        -- Optional: Insert some initial data for departments if needed by the tests
        INSERT INTO department (Name) VALUES ('Computers');
        INSERT INTO department (Name) VALUES ('Electronics');
        INSERT INTO department (Name) VALUES ('Fashion');
        INSERT INTO department (Name) VALUES ('Books');
        ```

3.  **Build the project using Maven:**
    Open a terminal in the project root directory and run:
    ```bash
    mvn clean install
    ```

---

## 🚀 Usage

The main application entry point is `App.java` located in `src/application/App.java`. This class demonstrates various operations on the `Seller` entity:

* Finding a seller by ID.
* Finding sellers belonging to a specific department.
* Listing all sellers.
* Inserting a new seller.
* Updating an existing seller's details.
* Deleting a seller.

To run the application, execute the `main` method in the `App.java` class from your IDE or using Maven.

---

## 🗃️ Database Configuration

Database connection parameters are configured in the `db.properties` file at the root of the project. The `DB.java` class loads these properties to establish a connection.

* `user`: Your MySQL database username.
* `password`: Your MySQL database password.
* `dburl`: The JDBC URL for your MySQL database. Ensure the database name (e.g., `coursejdbc`) is correct.

---

## 📂 Project Structure

* `.idea/`: IntelliJ IDEA project configuration files.
* `src/`: Source files
    * `application/App.java`: Main class to run demonstrations.
    * `db/`: Database related classes
        * `DB.java`: Handles database connection and utility methods (closing statements, result sets).
        * `DbException.java`: Custom runtime exception for general database errors.
        * `DbIntegrityException.java`: Custom runtime exception for database integrity violation errors.
    * `model/`:
        * `dao/`: Data Access Object interfaces and factory.
            * `DaoFactory.java`: Factory class to create DAO objects.
            * `DepartmentDao.java`: Interface for Department DAO operations.
            * `SellerDao.java`: Interface for Seller DAO operations.
            * `impl/SellerDaoJDBC.java`: JDBC implementation of the SellerDao interface.
        * `entities/`: Entity classes.
            * `Department.java`: Represents the Department entity.
            * `Seller.java`: Represents the Seller entity (includes a Builder pattern).
* `target/`: Maven build output directory.
* `db.properties`: Database connection configuration.
* `pom.xml`: Maven Project Object Model file, defines project dependencies and build settings.
* `README.md`: This file.

---

This README provides a comprehensive overview of the `demo-dao-jdbc` project.
