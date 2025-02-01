# EcoBuy  
# EcoBuy - A Sustainable Products Marketplace

## Overview
EcoBuy is an e-commerce platform dedicated to promoting eco-friendly and sustainable products. It connects environmentally conscious consumers with vendors selling green products. The platform is designed to facilitate ethical shopping and make it easier for users to find and purchase sustainable items.

## Tech Stack
- **Backend:**  ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
- **Frontend:**  ![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white) ![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
- **Database:** PostgreSQL: ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white) (for storing user, product, and shopping cart data)
- **Deployment:** Oracle Cloud: ![Oracle Cloud](https://img.shields.io/badge/Oracle_Cloud-F80000?style=for-the-badge&logo=oracle&logoColor=white)
- **Payment Integration:** Stripe: ![Stripe](https://img.shields.io/badge/Stripe-008CDD?style=for-the-badge&logo=stripe&logoColor=white)
 (used for checkout)

## Features
- **User Authentication**: Secure registration and login for buyers and sellers
- **Product Listings**: Vendors can list eco-friendly products with descriptions
- **Search & Filter**: Users can search and filter products based on categories
- **Shopping Cart & Checkout**: Complete shopping experience with order management
- **Vendor Profiles & Ratings**: Buyers can view and rate vendors
- **Order Management**: Track orders and statuses
- **Reviews & Ratings**: Users can leave feedback on products
- **Blog Section**: Tips and information on sustainable living

## Development Roadmap

### Backend Development
1. **User Management**
   - Implement User model and repository
   - Create UserController for managing user data
2. **Product Management**
   - Develop Product model, repository, and service
   - Implement CRUD operations for products
3. **Order Management**
   - Create Order and OrderItem models
   - Implement order processing and status updates
4. **Payment Integration**
   - Integrate Stripe API for secure transactions (used for checkout)
5. **Search Functionality**
   - Implement search using Spring Data JPA or Elasticsearch
6. **Authentication & Authorization**
   - Secure endpoints with Spring Security
   - Use JWT for authentication

### Frontend Development
1. **Setup Frontend Environment**
   - Establish project directory and dependencies
2. **Develop Key Components**
   - Vendor dashboard
   - User authentication pages
   - Product listing & details
   - Shopping cart & checkout
   - User profile page
3. **Integrate with Backend**
   - Use Axios/Fetch API for API calls
   - Implement state management (e.g., Redux for React)
4. **UI/UX Enhancements**
   - Use Bootstrap/Tailwind for responsive design
5. **Additional Features**
   - Implement blog system
   - Add newsletter subscription
   - Enable social media sharing

### Testing & Deployment
1. **Testing**
   - Write unit tests with JUnit & Mockito
   - Conduct integration tests for API endpoints
2. **Deployment**
   - Deploy backend on AWS Elastic Beanstalk or EC2

## Development Schedule
**Day 1:** Project setup, database configuration, resolving installation issues

**Day 2:** User model setup, database connectivity, troubleshooting PostgreSQL issues

**Day 3:** Fix database entity issues, create backend structure, implement product repository

**Days 4-5:** Complete user authentication and backend services

**Day 6:** Resolve database connectivity issues, test backend functionality

**Days 7-8:** Debug authentication errors, refine database schema

**Day 9:** Work on dashboard and login functionalities

**Day 10:** Develop frontend structure, implement add-to-cart feature

**Days 11-12:** Finalize shopping cart, create profile, orders, and About Us pages

**Day 13:** Implement cart removal and pop-up confirmation features

**Day 14:** Integrate payment system (Stripe API used for checkout)

**Day 15:** Enhance UI, prepare for cloud deployment

**Day 16:** Final testing, implement search engine if time allows

## Terminal Commands
- `mvn clean compile` – Rebuilds the project and checks for compilation errors
- `mvn spring-boot:run` – Runs the Spring Boot application

## Resources
[Spring Boot](https://spring.io/)
[PostgreSQL](https://www.postgresql.org/)
[Stripe API](https://stripe.com/docs/api)
[Oracle Cloud](https://www.oracle.com/cloud/)

This README provides a structured overview of EcoBuy's development plan, tech stack, and progress tracking for successful implementation.

