# **Functional Programming with Vavr & DDD Using Hexagonal Architecture**

This project was created to gain a deeper **insight into functional programming (FP)** concepts using the **Vavr library** in Java, combined with **Domain-Driven Design (DDD)** principles, and implemented following the **Hexagonal Architecture** (also known as Ports and Adapters).

---

## **Project Goals**

The primary goals of this project are:

1. **Explore Functional Programming with Vavr**:
   - Understand and apply key FP concepts such as **immutability**, **pure functions**, **function composition**, and **currying** in Java.
   - Leverage **Vavr’s functional data types** (`List`, `Option`, `Try`, `Either`, etc.) and its rich set of utilities to write clean, predictable, and side-effect-free code.

2. **Implement DDD Principles**:
   - Model the core domain using **rich domain models**, **value objects**, and **aggregates**.
   - Encapsulate business rules and domain logic within domain services.
   - Maintain a clear separation of concerns between the domain, application, and infrastructure layers.

3. **Adopt Hexagonal Architecture**:
   - Use the **Hexagonal Architecture** to ensure that the core domain logic is independent of external systems (databases, UI, etc.).
   - Define **ports** (interfaces) for interacting with external systems and implement them through **adapters** in the infrastructure layer.
   - Achieve flexibility and testability by isolating the domain logic from technical concerns.
  
## **Technologies Used**

- **Java 17** – Language features like records and pattern matching enhance FP-style coding.
- **Vavr** – Provides immutable collections, functional constructs, and monadic types.
- **Maven** – For build and dependency management.
- **JUnit 5** – For unit and integration testing.
- **Hexagonal Architecture** – Ensures a clear separation between domain logic and external systems.
