# Acquirer

**Acquirer** is a Spring Boot application designed to process payments by interacting with card issuers and validating card details.

## Table of Contents 
1. [Introduction](#introduction) 
2. [Controllers](#controllers) 
3. [Services](#services) 
4. [Usage](#usage) 
5. [Contributing](#contributing) 
6. [License](#license)

## Introduction 
**Acquirer** is a payment processing application built using Spring Boot. It provides an endpoint to handle payment requests to process transactions.

## Controllers 
The Acquirer application includes the following controller:

### AcquirerController
- **Endpoint:** `/api/v1/acquirer` 
- **Description:** This controller handles payment processing requests. It receives transaction details as input and returns a response containing the transaction status and message. 

## Services 
The Acquirer application includes the following service:

### AcquirerService
- **Description:** This service contains the business logic for processing payments. It generates responses containing transaction status and messages.

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
   - Make a POST request to the `/api/v1/process_payment` endpoint at `http://localhost:8080`.
   - Include the transaction details in the request body, for example:
     ```json
     {
  "cardDetails": {
    "cardNumber": "378734493671000",
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
     	 field": [
                {
                    "codes": [
                        "transactionRequest.cardDetails.expiryDate",
                        "cardDetails.expiryDate"
                    ],
                    "arguments": null,
                    "defaultMessage": "cardDetails.expiryDate",
                    "code": "cardDetails.expiryDate"
                }
            ],
            "code": "ValidExpiryDate",
            "message": "Invalid Expiry Date"
        }
       ```

## Contributing 
Contributions to the Acquirer application are welcome! If you would like to contribute, please follow these guidelines:

1. Fork the repository. 
2. Create a new branch for your feature or bug fix. 
3. Implement your changes. 
4. Write tests to ensure code quality. 
5. Submit a pull request with a clear description of your changes.

## License 
Divergent Software Labs Pvt. Ltd.
