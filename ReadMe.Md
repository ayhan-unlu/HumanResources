![Java](https://img.shields.io/badge/Java-17%2B-orange) ![License: Free Use](https://img.shields.io/badge/License-Free--Use-green) ![Patika.dev](https://img.shields.io/badge/Patika.dev-Project-blue)  
![GitHub stars](https://img.shields.io/github/stars/ayhan-unlu/PatikaAdvancedJavaProjects?style=social) ![GitHub forks](https://img.shields.io/github/forks/ayhan-unlu/PatikaAdvancedJavaProjects?style=social) ![GitHub last commit](https://img.shields.io/github/last-commit/ayhan-unlu/HumanResourcesProject)


⚠️ **WARNING: PROJECT IN PROGRESS** ⚠️

> This Human Resources Project is currently under development.  
> Features such as user registration, login, employee management, and role-based access are still being implemented.  
> Expect frequent updates and changes. Use at your own discretion.

---

🔧 **Current Status:**
- [x] Project setup with Spring Boot and database connection
- [x] Basic Thymeleaf login page
- [ ] Complete user registration
- [ ] Role-based access control
- [ ] Employee management features
- [ ] Logging and auditing

Please check back for updates as development continues! 🚧

# Patika.dev Java Human Resources Project

## 🧩 Description
A **Spring Boot + Thymeleaf** based web application for managing employees, salaries, and leave requests — developed as part of the **Patika.dev Advanced Java** series. This project is currently **in progress** and will evolve step by step following clean code, layered architecture, and SOLID principles.

## ⚙️ The system will allow
### 👩‍💼 For Admin (Human Resources)
- Register, view, and manage all employees
- Access salary and leave information
- Approve or reject leave requests
- Monitor and log system activity

### 👨‍💻 For Employees (Users)
- Register and securely log in
- View personal details and salary
- Submit leave requests
- Check approval status

## 🚀 Features (Planned)
- Admin & Employee roles with Spring Security
- Password encryption and account blocking after 3 failed logins
- Logging for every major action (login, CRUD, leave request)
- Database relations: `Admin (1) → Employee (N)`
- Paging for employee list and logs
- Data persistence with MySQL + File I/O backup
- UML and EER Diagrams
- OOP, SOLID, and Design Patterns (Factory, Singleton, etc.)

## 🧱 Project Structure (In Progress)
```
com.ayhanunlu.humanresources
 ┣ controller
 ┣ service
 ┃ ┗ impl
 ┣ repository
 ┣ entity
 ┣ dto
 ┣ config
 ┣ exception
 ┣ util
 ┗ HumanResourcesApplication.java
```
### ✅ Current Status
- ✅ Project initialized (Spring Boot 3 + JPA + Security + Thymeleaf)
- ✅ Dependencies configured
- 🔄 Entity and Repository layer — in progress
- 🔄 Security and Login system — coming next
- 🕒 UI (Thymeleaf templates) — pending

## 🧩 Database (Planned Tables)
| Table | Description |
|--------|--------------|
| **Admin** | Stores HR user credentials and info |
| **Employee** | Employee personal and contact details |
| **Salary** | Salary amount and payment date per employee |
| **Leave** | Leave requests and status tracking |
| **Log** | Logs all system actions |

## 🧠 Tech Stack
- **Backend:** Spring Boot, Spring Data JPA, Spring Security
- **Frontend:** Thymeleaf, HTML5, CSS3, Bootstrap
- **Database:** MySQL
- **Java:** 17+
- **Tools:** Lombok, DevTools, Actuator, Validation, Swagger (optional)

## 🧪 Getting Started
### Prerequisites
- Java 17+
- MySQL running locally
- IntelliJ IDEA or VS Code

### Running the Project
1. Clone the repository:
   ```bash
   git clone https://github.com/ayhan-unlu/HumanResourcesProject
   ```
2. Open in IntelliJ IDEA
3. Run the `HumanResourcesApplication` class
4. App starts at: `http://localhost:8080`

## 🧍 Test Accounts (will be added later)
| Username | Password | Role | Status |
|-----------|-----------|------|--------|
| admin | admin | Admin (HR) | Active |
| testuser | test | Employee | Active |

## 🪵 Logging
- All CRUD operations and login attempts are logged automatically.
- Logs are stored in `logs/hrms.log`.

## 🗺 UML & Diagrams
*(Will be added after entity creation)*
- Class Diagram
- Database EER Diagram
- Sequence Diagram

## 🧭 Development Timeline
| Phase | Task | Status |
|--------|------|--------|
| 1 | Project creation & dependency setup | ✅ Done |
| 2 | Entity & Repository design | 🔄 In progress |
| 3 | Service & Controller layers | ⏳ Pending |
| 4 | Security & Login system | ⏳ Pending |
| 5 | Logging & File I/O | ⏳ Pending |
| 6 | Thymeleaf UI | ⏳ Pending |
| 7 | UML & Documentation | ⏳ Pending |

## 🧰 Future Improvements
- Add performance evaluation module
- Add leave balance and salary history
- Add PDF export and email notifications
- Integrate REST API for mobile or React front-end

## 📜 License
This project is **free to use, modify, and distribute.** Attribution is appreciated but not required.
