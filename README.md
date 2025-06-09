# Tatiana's Properties

This project is a web platform for a real estate agency called Tatiana's Properties.  
It allows users to list properties for sale or rent, manage properties from a private employee dashboard, and communicate with clients through a contact form.  
The frontend is built with Astro (TypeScript + TailwindCSS) and the backend with Spring Boot (Java).

---

## Running the Project with Docker

### Requirements

- [Docker](https://docs.docker.com/get-docker/) installed  
- [docker-compose](https://docs.docker.com/compose/install/) installed  

### Steps

1. Clone the repository and navigate into it:

    ```bash
    git clone https://github.com/tatianariera/RealEstate.git
    cd RealEstate
    ```

2. Build and start the containers:

    ```bash
    docker-compose up --build
    ```

3. Access the applications in your browser:

    - Backend Spring Boot: [http://localhost:8081](http://localhost:8081)  
    - Frontend Astro: [http://localhost:4321](http://localhost:4321)  

4. To stop the containers:

    ```bash
    docker-compose down
    ```

5. To run containers in the background (detached mode):

    ```bash
    docker-compose up -d
    ```

---

## Local Development without Docker

If you prefer to run the project locally without Docker, follow these instructions:

### Backend (Spring Boot)

1. Make sure you have JDK 17+ and Maven installed.  
2. Navigate to the backend directory:

    ```bash
    cd backend
    ```

3. Build the project:

    ```bash
    ./mvnw clean install
    ```

4. Run the application:

    ```bash
    ./mvnw spring-boot:run
    ```

5. The backend will run at `http://localhost:8081` (make sure this port is configured in `application.properties`).

### Frontend (Astro)

1. Make sure Node.js (v18 or higher) is installed.  
2. Navigate to the frontend directory:

    ```bash
    cd frontend
    ```

3. Install dependencies:

    ```bash
    npm install
    ```

4. Start the development server on port 4321:

    ```bash
    npm run dev -- --port 4321
    ```

5. Access the frontend at `http://localhost:4321`.
