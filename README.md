
# Pizza Ordering System

## Overview

The Pizza Ordering System is a console-based application developed as part of **Programming III - Patterns and Algorithms (Coursework 2)** at **Kingston University**. Designed for pizza enthusiasts and small businesses, the application allows users to:

-   Customize pizzas with various toppings, crusts, and sauces.
-   Place orders and track their status in real-time.
-   Manage user profiles.
-   Earn and redeem loyalty points during payments.

This assignment, created by **Tauseef Ahamed**, showcases advanced object-oriented programming techniques and employs various design patterns to ensure modularity, scalability, and maintainability.


## Features

1.  **Pizza Customization**: Build your own pizza with the `PizzaBuilder` pattern.
2.  **Ordering Process**: Place orders seamlessly with an interactive menu.
3.  **Real-Time Order Tracking**: Track order status changes using the Observer pattern.
4.  **Payment Options**: Choose from cash, credit card, or loyalty points.
5.  **Seasonal Promotions**: Enjoy discounts applied dynamically through the Chain of Responsibility pattern.
6.  **Feedback System**: Add ratings and feedback using the Decorator pattern.
7.  **User Profiles**: Manage profiles and save favourite pizzas for quick reordering.


## Technologies Used

-   **Programming Language**: Java
-   **Design Patterns**: Builder, Observer, Strategy, Chain of Responsibility, State, Command, Decorator
-   **Tools**: Console-based user interface


## Installation

1.  Clone the repository:
    
    ```bash
    git clone https://github.com/tauseef-zc/ku_p3_cw2.git
    
    ```
    
2.  Navigate to the project directory:
    
    ```bash
    cd ku_p3_cw2
    
    ```
    
3.  Run the application:
    
    ```bash
     java src/main/java/com/tauseef/app/Main.java
    
    ```
    

## Usage

1.  **Main Menu**:
    
    -   Register or log in as a customer.
    -   Choose from available options: placing orders, tracking orders, or viewing feedback.
2.  **Customization**:
    
    -   Use the interactive prompts to customize your pizza.
3.  **Payments**:
    
    -   Select a preferred payment method (cash, credit card, or loyalty points).
4.  **Feedback**:
    
    -   Provide ratings and reviews post-order completion.



## Design Patterns in Action

-   **Builder**: Used for creating customized pizzas.
-   **Observer**: Notifies customers about order status updates.
-   **Strategy**: Implements various payment methods.
-   **Chain of Responsibility**: Applies promotional discounts dynamically.
-   **State**: Manages user session states (guest, logged-in, logged-out).
-   **Command**: Handles order actions like placing or cancelling.
-   **Decorator**: Enhances feedback with rating functionality.


## Testing

The system has been rigorously tested with test cases, covering:

-   User registration and login.
-   Pizza customization and ordering.
-   Payment processing and promotions.
-   Order tracking and feedback.


## About

This project is for **Programming III - Patterns and Algorithms (Coursework 2)** as part of the curriculum at **Kingston University**. The application was developed by **Tauseef Ahamed** to demonstrate proficiency in software design patterns and algorithms.


## Contact

For any questions or issues, please contact **Tauseef Ahamed** at [[tauseef.offl@gmail.com](mailto:tauseef.offl@gmail.com)].
