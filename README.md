##Crio Take-Home Submission

# 🛒 Grocery Store API

A RESTful API for managing **customers**, **grocery items**, and **orders** in a simple grocery store application.

---

## 📦 Tech Stack

- Java + Spring Boot
- Spring Web
- Validation (JSR 380)
- Lombok
- REST API Design

---

## 📁 API Endpoints

### 👤 Customers

| Method | Endpoint             | Description             |
|--------|----------------------|-------------------------|
| POST   | `/api/customers`     | Create a new customer   |
| GET    | `/api/customers`     | Get all customers       |
| GET    | `/api/customers/{id}`| Get customer by ID      |
| DELETE | `/api/customers/{id}`| Delete customer by ID   |

---

### 🛍️ Grocery Items

| Method | Endpoint          | Description              |
|--------|-------------------|--------------------------|
| POST   | `/api/items`      | Create a new grocery item|
| GET    | `/api/items`      | Get all items            |
| GET    | `/api/items/{id}` | Get item by ID           |
| DELETE | `/api/items/{id}` | Delete item by ID        |

---

### 📦 Orders

| Method | Endpoint          | Description         |
|--------|-------------------|---------------------|
| POST   | `/api/orders`     | Create a new order  |
| GET    | `/api/orders`     | Get all orders      |
| GET    | `/api/orders/{id}`| Get order by ID     |
| DELETE | `/api/orders/{id}`| Delete order by ID  |

---

## ✅ Request Examples

### Create Customer (POST `/api/customers`)
```json
{
  "name": "test",
  "address": "Delhi, India",
  "phoneNumber": "1234567890"
}
