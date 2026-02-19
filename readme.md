# Bank CLI Application (Java)

A simple command-line banking application built in Java to practice Object-Oriented Programming concepts and application flow.

---

## Features

- **Account Lifecycle Management**:
  - Create new accounts with unique identifiers.
  - Mandatory PIN change on first login for account activation.
  - Deactivate accounts when no longer needed.
- **Security & Authentication**:
  - Secure login using email and PIN.
  - **Account Locking**: Automatically locks after 3 failed PIN attempts.
  - **Operation Guarding**: Sensitive operations are blocked if the account is inactive or locked.
- **Banking Operations**:
  - Deposit and withdraw funds.
  - Real-time balance inquiries.
  - Detailed account information view.
- **Session Management**:
  - Seamless logout and account switching.

## Technologies Used

- **Java** (Core Logic)
- **OOP Principles** (Encapsulation, Abstraction, Modularity)
- **Java Collections Framework** (`ArrayList` for in-memory storage)
- **Scanner API** (Console I/O handling)

## Project Structure

```
bankApp/
│
├── Account.java # Account logic and state management
├── AccountsList.java # In-memory storage and login functionality
└── Main.java # Application flow and user interactionbankApp/
```

## What I Practiced

- Object-oriented design
- Encapsulation and state management
- Application flow control
- Basic authentication logic
- Handling user input and validation
- Designing a small but complete console application

## How to Run

1. Open the project in IntelliJ IDEA or Eclipse
2. Run the `Main` class
3. Follow the console instructions

## Example Flow

- Create account
- Change PIN (required for activation)
- Login
- Perform operations (deposit, withdraw, view balance)
- Logout or deactivate account
