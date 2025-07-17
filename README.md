# Java IRCTC Ticket Booking Application

## Project Overview

This is a simple console-based Java application that simulates a basic train ticket booking system, reminiscent of IRCTC functionalities. Users can sign up, log in, search for trains, book seats, view their bookings, and cancel tickets. User and train data are persisted in local JSON files.

## Features

*   **User Management:**
    *   Sign Up: Create new user accounts with hashed passwords.
    *   Login: Authenticate existing users.
*   **Train Operations:**
    *   Search Trains: Find trains based on source and destination stations.
    *   View Train Details: Display available trains with their schedules.
*   **Booking Management:**
    *   Book a Seat: Book a ticket on an available train.
    *   Fetch Bookings: View all tickets booked by the logged-in user.
    *   Cancel Booking: Cancel an existing ticket using its booking ID.

## Technologies Used

*   **Java**: The core programming language.
*   **Gradle**: Build automation tool for managing dependencies and building the project.
*   **Jackson**: A high-performance JSON processor for Java, used for data persistence (serializing/deserializing user and train data to/from JSON files).
*   **Spring Security Crypto**: Provides robust password hashing (BCrypt) for secure user authentication.

## Setup Instructions

To get this project up and running on your local machine, follow these steps:

### Prerequisites

*   **Java Development Kit (JDK)**: Version 21 or higher.
*   **Git**: For cloning the repository.

### Cloning the Repository

First, clone the repository to your local machine:

```bash
git clone https://github.com/11-V-SriHarsha/IRCTC_Java-Gradle.git
cd Java_IRCTC
```

### Building the Project

Navigate to the project's root directory (where `gradlew.bat` is located) in your terminal and build the executable JAR file:

```bash
# On Windows
.\gradlew.bat shadowJar

# On macOS/Linux
./gradlew shadowJar
```

This command will compile the project and create a self-contained executable JAR file (e.g., `app.jar`) in the `app/build/libs/` directory, including all necessary dependencies.

## How to Run the Application

After a successful build, you can run the application directly from your terminal:

```bash
java -jar app/build/libs/app.jar
```

The application will launch in your console, presenting you with a main menu. Follow the on-screen prompts to interact with the system.

## Data Persistence

User and train data are stored in JSON files:

*   `users.json`: Stores user account details (username, hashed password, booked tickets). This file is created and updated in the project's root directory during runtime.
*   `trains.json`: Contains the predefined train schedule and details. This file is located in `app/src/main/resources/` and is loaded when the application starts. If you wish to modify train data, edit this JSON file directly.

## Usage Example

1.  **Sign Up (Option 1):** Create a new user account.
    ```
    ==================================== Sign Up ====================================

    Enter username: [your_username]
    
    Enter password: [your_password]

    User signed up successfully!
    ```

2.  **Login (Option 2):** Log in with your registered credentials.
    ```
    ==================================== Login ====================================

    Enter username: [your_username]
    
    Enter password: [your_password]

    Login successful!
    ```

3.  **Search Train (Option 4):** Find available trains.
    ```
    ==================================== Search Train ====================================

    Enter source station: Bengaluru
    
    Enter destination station: Delhi
    
    Available Trains:
      1. Train No: 12345
          Train Id: T001
          Station: Bengaluru, Time: 10:00 AM
          Station: Chennai, Time: 03:00 PM
          Station: Delhi, Time: 10:00 PM

      2. Train No: 67890
          Train Id: T002
          Station: Mumbai, Time: 08:00 AM
          Station: Hyderabad, Time: 02:00 PM
          Station: Delhi, Time: 08:00 PM
    ===================================================================================
    ```

4.  **Book a Seat (Option 5):** Book a ticket on a found train.
    ```
    ==================================== Book a Seat ====================================

    Enter the Train Number (e.g., 12345) to book: 12345
    
    Enter source station for booking: Bengaluru
    
    Enter destination station for booking: Delhi
    
    --- Booking Confirmation ---
    User: [your_username]
    Train Number: 12345
    Source: Bengaluru
    Destination: Delhi
    Booking ID: [generated_booking_id]
    ----------------------------
    ```

5.  **Fetch Bookings (Option 3):** View your booked tickets.
    ```
    =================================== Your Bookings ===================================

    Ticket ID: [generated_booking_id]
      User ID: [your_user_id]
      Source: Bengaluru
      Destination: Delhi
      Date: 2024-12-31
      Train: Train ID: T001 and Train No: 12345
    
    ===================================================================================
    ```

6.  **Cancel Booking (Option 6):** Cancel a ticket using its booking ID.
    ```
    ==================================== Cancel Booking ====================================

    Enter the Booking ID to cancel: [booking_id_from_fetch_bookings]

    Booking with ID [booking_id] cancelled successfully.
    ```

## Contribution

Feel free to fork the repository, open issues, or submit pull requests for any improvements or bug fixes. 