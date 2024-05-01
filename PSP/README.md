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

### PspController
- **Endpoint:** `/api/v1/process_payment` 
- **Description:** This controller handles payment processing requests. It receives transaction details as input and returns a response containing the transaction status and message. 

## Services 
The PSP application includes the following service:

### PspService
- **Description:** This service contains the business logic for processing payments. It validates card details, generates transaction IDs, calls the acquirer, updates transaction records, and interacts with external systems for transaction processing. 

#### TransactionUtil Class
- **Method:** `isValidCardLuhansAlgorithm(String cardNumber)`
  - **Description:** This method validates a credit card number using Luhn's algorithm. It checks if the card number is valid based on its length, format, and Luhn's algorithm.
  - **Parameters:** 
    - `cardNumber` - The credit card number to validate.
  - **Returns:** 
    - `true` if the card number is valid, `false` otherwise.
  - **Example Usage:** 
    ```java
    public static boolean isValidCardLuhansAlgorithm(String cardNumber) {
        // Added provided logic here
    }
    ```
  - **Explanation:**
    This method implements Luhn's algorithm to validate credit card numbers. Luhn's algorithm works by summing up the digits of the card number in a specific way and checking if the resulting sum is divisible by 10. If it is, the card number is considered valid. Otherwise, it's invalid. The provided comments demonstrate examples of valid and invalid card numbers based on their formats and lengths. The method cleans the input card number by removing any non-digit characters, checks if the length of the cleaned number is within the valid range (13-19 digits), ensures that the card number contains only digits, and then performs the Luhn's algorithm calculation. Finally, it returns `true` if the calculated sum is divisible by 10, indicating a valid card number, and `false` otherwise.

#### Sample Inputs For Card Validation:
- '    378282246310005     VALID
- '    5610591081018250    VALID
- '    378734493671000     VALID
- '    3787344936710005    INVALID
- '    4111111111111112    INVALID
- '    5105105105105101    INVALID
- '    378282246310006     INVALID
- '    abcdefghijabcdefgh  INVALID


## Repositories 
The PSP application includes the following repository:

### TransactionRepo
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

## Usage
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
        ]
       ```

## Contributing
Contributions to the PSP application are welcome! Follow these guidelines:
1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Implement your changes.
4. Write tests to ensure code quality.
5. Submit a pull request with a clear description of your changes.

## License
Divergent Software Labs Pvt. Ltd.
