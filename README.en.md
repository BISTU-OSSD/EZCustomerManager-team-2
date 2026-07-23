# Java Dual-Interface Customer Information Management System

## Project Overview
This project is an open-source course practice assignment, built on Java object-oriented layered development. It provides two operation interfaces: a **console terminal** and a **browser-based web interface**. It is designed for Java beginners and small offline merchants.

It solves the problems of messy paper-based customer records and cumbersome query/modification processes. The system uses in-memory array storage throughout and has no third-party framework dependencies — it is implemented purely with native JDK. It is ideal for learning layered development, basic HTTP services, and fundamental frontend-backend interaction logic.

## Tech Stack
- Runtime Environment: JDK 8
- Backend: Native Java OOP, in-memory array storage, JDK built-in HttpServer
- Frontend: Embedded HTML + CSS + JavaScript, no additional frontend files or Tomcat server required

## Project File Structure
- Customer.java              // Customer entity class, encapsulating name, gender, age, and other attributes
- CustomerList.java          // Data management layer, implementing core CRUD logic using arrays
- CustomerView.java          // Console menu interaction view
- Utility.java               // Input utility class, providing unified validation for length, numbers, menu selections, etc.
- HttpServerHandler.java     // Web HTTP service, embedding frontend pages and providing web operation APIs
- README.md                  // Project documentation (Chinese)
- LICENSE                    // MIT open-source license

## Core Features
1. Customer Entity Encapsulation
    - Private fields for name, gender, age, phone, and email, with constructors and getters/setters.
    - Age validation (range 0–150) is built in. A unified method outputs complete customer information.

2. Array-Based Data Management
    - Full CRUD operations: capacity checking on addition, copy protection on queries, modification and deletion by ID, automatic array shifting, and bounds checking.

3. Console Menu Interaction
    - A looped main menu supports: Add, Modify, Delete, List All Customers, and Exit.
    - Utility class handles input format and length validation. Delete and Exit actions include a second confirmation to reduce errors.

4. Web-Based Visual Management Page
    - Built-in JDK HTTP service listens on port 8080, serving a styled frontend page.
    - Asynchronous JavaScript calls to backend APIs enable CRUD operations via web forms, with customer data displayed in an auto-generated table.

5. Generic Input Validation Utility
    - Centralized handling of console input: string length limits, numeric validation, menu options, and confirmation prompts.
    - Reduces repetitive code and enforces consistent interactive prompts.

## Complete Run Instructions

### Preparation
1. Place all Java files in the same package. Do not omit any class.
2. Use JDK 8 to run the program. Higher JDK versions may cause HttpServer permission errors.
3. Ensure there are no compilation errors in your IDE before starting.

### Method 1: Console Terminal Version (CustomerView)
1. Run the main method of CustomerView. The console will display the function menu.
2. Enter 1 to add a customer. Fill in name, gender, age, phone, and email sequentially. Invalid inputs will prompt for re-entry.
3. Enter 2 to modify a customer. Input the customer ID; after validation, enter new information to overwrite.
4. Enter 3 to delete a customer. Enter the ID and confirm with Y/N. Upon confirmation, the customer data is removed.
5. Enter 4 to list all customers. A table displays complete information for all customers.
6. Enter 5 to exit. Confirm with Y to shut down the program or N to return to the menu.
7. All customer data is cleared when the program exits.

### Method 2: Web-Based Visual Version (HttpServerHandler)
1. Run the main method of HttpServerHandler. The local service will start on port 8080.
2. Open your browser and visit http://localhost:8080 to access the visual management page.
3. Click the buttons at the top to trigger corresponding forms: fill in information to add a customer, click to view the customer table, or enter an ID to delete/modify a customer.
4. The frontend sends requests to the backend via JavaScript. Success or error messages are displayed at the bottom of the page in real time.
5. The web version and the console version cannot run simultaneously. Their data are independent of each other, and both are cleared upon service restart.

## Open-Source License
This project is licensed under the **MIT License**. Anyone is free to copy, study, modify, and use the code for commercial purposes, provided that the original copyright notice is retained.
License file: LICENSE in the repository root.