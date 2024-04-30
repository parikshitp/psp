# PSP (Payment Service Provider)

**PSP (Payment Service Provider)** is a Spring Boot application designed to process payments efficiently and securely.

## Table of Contents 
1. [Introduction](#introduction) 
2. [Controllers](#controllers) 
3. [Services](#services) 
4. [Repositories](#repositories) 
5. [Usage](#usage) 
6. [Contributing](#contributing) 
7. [License](#license)

## Introduction 
**PSP** is a payment service provider application built using Spring Boot. It provides RESTful APIs for processing payments and managing transaction records.

## Controllers 
The PSP application includes the following controller:

**PspController** 
- **Endpoint:** `/api/v1/psp` 
- **Description:** This controller handles payment processing requests. It receives transaction details as input and returns a response containing the transaction status and message. 

## Services 
The PSP application includes the following service:

**PspService** 
- **Description:** This service contains the business logic for processing payments. It validates card details, generates transaction IDs, updates transaction records, and interacts with external systems for transaction processing. 

## Repositories 
The PSP application includes the following repository:

**TransactionRepo** 
- **Description:** This repository manages transaction records in memory. It provides methods for saving and retrieving transaction details. 

## How to Run PSP Application

### Prerequisites
- Java 17 installed on your system.
- Both the Acquirer and PSP applications successfully built using Maven.

### Setup Steps
1. **Build Acquirer Application:**
   - Import the Acquirer application into any IDE.
   - Execute `mvn install` in the Acquirer application directory to build the application.

2. **Build PSP Application:**
   - Import the PSP application into any IDE.
   - Execute `mvn install` in the PSP application directory to build the application.

3. **Docker Setup:**
   - Locate the directory containing the `docker-compose.yml` file.
   - Open a command line interface in the same directory.

4. **Run Docker Compose:**
   - Execute the following command:
     ```
     docker-compose up --build
     ```

### Usage
Once the applications and Docker setup are completed, follow these steps to process payments:

1. **Send Payment Request:**
   - Make a POST request to the `/api/v1/psp` endpoint at `http://localhost:8080`.
   - Include the transaction details in the request body, for example:
     ```json
     {
       "cardDetails": {
         "cardNumber": "12345678901234523",
         "expiryDate": "12/25",
         "cvv": "123",
         "amount": 100.00,
         "currency": "USD",
         "merchantId": "MERCHANT123"
       }
     }
     ```

2. **Transaction Scenarios:**
   - **Odd Number in Last Card Digit:**
     - **Expected Response (DENIED):**
       ```json
       {
           "transactionId": "TXNMERCHANT123TD1714477059132234523",
           "status": "DENIED",
           "message": "Transaction Denied: Invalid Card Details"
       }
       ```

   - **Even Number in Last Card Digit:**
     - **Expected Response (APPROVED):**
       ```json
       {
           "transactionId": "TXNMERCHANT123TD1714481177508234522",
           "status": "APPROVED",
           "message": "Transaction Approved"
       }
       ```
   - **Wrong date format MM/DD:**    
     - **Expected Response (BAD_REQUEST):**
       ```json
      {
		    "status": "BAD_REQUEST",
		    "timestamp": "30-04-2024 12:46:28",
		    "message": "JSON parse error: error.invalid.expiry.date",
		    "debugMessage": "JSON parse error: error.invalid.expiry.date",
		    "subErrors": null
		}
       ```

### Contributing
Contributions to the PSP application are welcome! Follow these guidelines:
1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Implement your changes.
4. Write tests to ensure code quality.
5. Submit a pull request with a clear description of your changes.

### License
Divergent Software Labs Pvt. Ltd.