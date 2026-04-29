# E-Commerce Backend System

A scalable e-commerce backend built with Spring Boot and Spring Security.

## Features
- User Registration and Login with JWT Authentication
- Role-based access control (USER, ADMIN)
- Product management with categories
- Order placement with stock management
- Automatic stock reduction on order
- Order status tracking (PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED)
- MySQL database with 5 related tables

## Tech Stack
- Java 17
- Spring Boot 3.2
- Spring Security
- JSON Web Tokens (JWT)
- MySQL
- Maven
- Lombok

## API Endpoints

### Auth
| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | /api/auth/register | Register user | No |
| POST | /api/auth/register/admin | Register admin | No |
| POST | /api/auth/login | Login and get token | No |

### Products
| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| GET | /api/products | Get all products | No |
| GET | /api/products/{id} | Get product by id | No |
| GET | /api/products/search?name= | Search products | No |
| POST | /api/products/admin | Create product | Admin |
| PUT | /api/products/admin/{id} | Update product | Admin |
| DELETE | /api/products/admin/{id} | Delete product | Admin |

### Orders
| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | /api/orders | Place order | User |
| GET | /api/orders/my-orders | Get my orders | User |
| GET | /api/orders/admin/all | Get all orders | Admin |
| PUT | /api/orders/admin/{id}/status | Update order status | Admin |

## How to Run
1. Clone the repository
2. Create MySQL database: `CREATE DATABASE ecommerce_db;`
3. Update application.properties with your MySQL password
4. Run EcommerceApplication.java
5. Test APIs using Postman on http://localhost:8082
