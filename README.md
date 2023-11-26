# SwiftSells - Shop Smart.

SwiftSells is a cutting-edge Android application designed to enhance your buying experience by seamlessly connecting you with a diverse range of products. It allows users to browse a list of products and add new products.

## Table of Contents
- [Features](#features)
- [Screenshots](#screenshots)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Tech Stack](#tech-stack)
- [Known Issues](#known-issues)

## Features
- **Product Listing Screen(Home and Search):**
  - Browse a list of products.
  - Search and filter products.
  - Scroll through the product list.
  - Navigate to the Add Product screen.

- **Add Product Screen:**
  - Select product type from a list.
  - Enter product details (name, price, tax).
  - Validate input fields.
  - Submit data using the POST method to the API.

- **API Integration:**
  - Retrieve product data from [API endpoint](https://app.getswipe.in/api/public/get).
  - Add new products using the [POST method](https://app.getswipe.in/api/public/add).

- **User-Friendly Interface:**
  - Clear navigation between screens.
  - Responsive and visually appealing design.

## Screenshots


## Getting Started
### Prerequisites
- Android Studio installed.
- Kotlin and Java Development Kit (JDK) installed.

### Installation
1. Clone the repository: `git clone https://github.com/your-username/SwiftSells.git`
2. Open the project in Android Studio.
3. Run the app on an emulator or physical device.

## Tech Stack
- MVVM Architecture
- Retrofit, OkHttp for REST API
- Dagger/Hilt for Dependency Injection

## Known Issues
When attempting to pick an image from the gallery to add, the app crashes. This issue is will be addressed in future releases.
