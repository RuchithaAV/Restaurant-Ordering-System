# Assessment 2: Advanced Programming Techniques
**University of Colombo | Semester 5**

This repository contains the coursework and implementation files for **Assessment 2 (Advanced Programming Techniques)**. The objective of this assessment is to design, implement, and analyze a Restaurant Management System using Java, comparing a manually written implementation against an AI-generated counterpart.

---

##  Project Structure

```text
Assesment_2/
│
├── src/                                  # Manually created Java implementation (Student)
│   ├── Main.java                         # CLI Controller and Application loop
│   ├── Menu.java                         # Menu management (Catalog)
│   ├── MenuItem.java                     # Model representing a menu item
│   ├── Customer.java                     # Model representing a customer
│   ├── Order.java                        # Model representing an order
│   ├── Bill.java                         # Handles billing calculations
│   └── FileManager.java                  # File utilities (placeholder/skeleton)
│
├── Version B/                            # AI-Generated Java implementation
│   └── src/com/restaurant/
│       ├── app/                          # Main entry points (RestaurantApp, Main)
│       ├── exception/                    # Custom domain exceptions
│       ├── model/                        # Domain models (MenuItem, Order, OrderLine, etc.)
│       ├── repository/                   # Data storage layer & interfaces (InMemoryMenuRepository)
│       ├── service/                      # Business logic layer (MenuService, OrderService, etc.)
│       └── ui/                           # UI formatting & output (ReceiptPrinter)
│
├── Assessment 2 (Advanced Programming Techniques).pdf  # Assignment prompt & guidelines
├── Analyzing the Role of AI.docx          # Analytical report comparing human vs. AI implementations
└── README.md                             # This documentation file
```

---

##  Manual Implementation (`Version A`)

The files located in the root `src/` directory were designed and written manually. 

### Key Features & Design
- **Single-CLI Controller (`Main.java`)**: Handles the command-line menu loop for both **Staff** and **Customers**.
- **Catalogs and Business Entities**:
  - `Menu`: Organizes and searches `MenuItem` entities.
  - `Customer`: Holds customer metadata (ID, Name, Phone).
  - `Order` & `Bill`: Manages individual orders and applies taxes/discounts dynamically to generate totals.
- **Characteristics**: Simple, direct console application. It focuses on OOP fundamentals like encapsulation and association relationships.

---

##  AI-Generated Implementation (`Version B`)

The files under `Version B` were generated using an AI coding assistant. It showcases a highly decoupled, production-grade architecture.

### Architectural Enhancements
- **Layered Architecture (Separation of Concerns)**:
  - **`repository`**: Abstracts data access using interfaces (`MenuRepository`) and in-memory storage (`InMemoryMenuRepository`).
  - **`service`**: Implements business rules (`BillingService`, `OrderService`, `MenuService`) decoupled from user input/output.
  - **`exception`**: Uses robust domain exceptions (e.g., `DuplicateItemException`, `ItemNotFoundException`) rather than inline console error logging.
  - **`ui`**: Encapsulates terminal formatting (e.g., `ReceiptPrinter` formatting).
- **OOP Best Practices**: Adheres strictly to the Single Responsibility Principle (SRP) and Dependency Inversion.

---

## How to Run

### Prerequisite
Ensure you have the Java Development Kit (JDK 8 or higher) installed on your system.

### Running the Manual Version
1. Open your terminal in the `Assesment_2` directory.
2. Compile the source code:
   ```bash
   javac src/*.java
   ```
3. Run the application:
   ```bash
   java -cp src Main
   ```

### Running the AI-Generated Version (Version B)
1. Open your terminal in the `Version B` directory.
2. Compile the source code:
   ```bash
   javac src/com/restaurant/app/*.java src/com/restaurant/model/*.java src/com/restaurant/service/*.java src/com/restaurant/repository/*.java src/com/restaurant/exception/*.java src/com/restaurant/ui/*.java -d out
   ```
3. Run the application:
   ```bash
   java -cp out com.restaurant.app.RestaurantApp
   ```
