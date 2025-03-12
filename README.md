# CosmoCart

## Overview

This is a fully functional eCommerce mobile application built with Kotlin and Firebase. The app provides a complete shopping experience by allowing users to authenticate securely, browse products by category, search with auto-suggestions, view product details, manage their shopping cart, and update their profile information. The project follows the Model-View-ViewModel (MVVM) design pattern to ensure a clean separation of concerns, maintainability, and efficient data handling across the app.

## Features

- **User Authentication**:  
  Secure sign-in and sign-up functionality using Firebase Authentication with email and password.

- **Product Browsing & Categories**:  
  Users can explore various product categories (e.g., Men’s Shirts, Smart Watches, Sarees) and view all products within a selected category.

- **Search with Auto-Suggestions**:  
  An intuitive search feature allows users to type in a product name and get auto-suggestions, simplifying the search process.

- **Product Details & Cart Management**:  
  Detailed product screens provide images, descriptions, pricing, and available options (e.g., sizes). Users can add multiple items to the cart with real-time updates to the subtotal and total price. Items can be incremented, decremented, or removed from the cart.

- **User Profile Management**:  
  Users can update their personal information such as name and address, as well as log out. *(Note: The screenshot for the user update screen is not included in the PDF.)*

- **Firebase Integration**:  
  - **Cloud Firestore**: Stores product and user data in real time.  
  - **Firebase Storage**: Manages user profile images efficiently.

## Architecture

This project is structured using the MVVM (Model-View-ViewModel) design pattern, which helps in:
- **Separation of Concerns**: Keeping the UI (View) separate from business logic (ViewModel) and data management (Model).
- **Scalability and Maintainability**: Making it easier to test and extend the application by decoupling components.
- **Reactive Data Handling**: Using LiveData or similar reactive components to ensure the UI remains updated with the latest data changes.

## Technology Stack

- **Programming Language**: Kotlin  
- **Mobile Framework**: Android SDK  
- **Design Pattern**: MVVM (Model-View-ViewModel)  
- **Backend Services**: Firebase  
  - **Authentication**: Email/Password sign-in  
  - **Cloud Firestore**: Real-time database for dynamic content  
  - **Firebase Storage**: For storing user profile images  
- **UI/UX**: Jetpack Compose for a responsive and intuitive user interface

### Prerequisites

- **Android Studio**: Latest version recommended.
- **Firebase Account**: To set up Authentication, Cloud Firestore, and Storage.

### Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Kutubuddin-Rasel/CosmoCart.git
   ```

2. **Open in Android Studio**:  
   Open the project in Android Studio. Ensure that you have the required Android SDK and dependencies installed.

3. **Set Up Firebase**:
   - Create a new Firebase project.
   - Add your Android app to the Firebase project.
   - Download the `google-services.json` file and place it in the `app/` directory.
   - Enable Firebase Authentication, Cloud Firestore, and Firebase Storage in your Firebase console.

4. **Build and Run**:  
   Build the project and run it on an emulator or a physical device.

## Additional Information

- This project was developed to showcase my skills in Android development using Kotlin, Firebase, and the MVVM design pattern. It demonstrates robust application architecture and modern design principles.
- Contributions and suggestions are welcome.
