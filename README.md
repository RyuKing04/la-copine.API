# La Copine API

La Copine API is a RESTful API built with Java Spring Boot 3.3.2.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Usage](#usage)
- [Contributing](#contributing)
    - [Commit and PR Message Guidelines](#commit-message-guidelines)

## Technologies Used

- Java 17
- Spring Boot 3.3.2
- Hibernate
- Swagger

## Getting Started

### Prerequisites

- Java 17
- Maven
- Docker (optional, for running the application in a container)

### Installation

1. Clone the repository and navigate to the project directory
2. Build the project using Maven
    ```bash
    ./mvnw clean install
    ```
3. Set your database credentials in `application.properties`
4. (Optional) Build the Docker image
5. Run the application using and IDE or Maven
    ```bash
    ./mvnw spring-boot:run
    ```

### Usage

Once your local dev application is running, you can access the API endpoints via http://localhost:8080. You can also
access the
Swagger UI for API documentation and testing at http://localhost:8080/swagger-ui/index.html

## Contributing

Contributions are welcome! If you are not a direct contributor, please fork the repository and create a pull request
with your changes.

- (If you are not invited) Fork the repository
- Create your feature branch (git checkout -b feature/YourFeature)
- Commit your changes (git commit -m 'Add some feature')
- Push to the branch (git push origin feature/YourFeature)
- Open a pull request

### Commit and PRs Message Guidelines

Every commit message should follow the following format:

```
<type>: <subject>
```

Where `<type>` is one of the following:

- `feat`: A new feature
- `fix`: A bug fix
- `docs`: Documentation only changes
- `style`: Changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, etc)
- `refactor`: A code change that neither fixes a bug nor adds a feature
- `perf`: A code change that improves performance
- `test`: Adding missing tests or correcting existing tests
- `chore`: Changes to the build process or auxiliary tools and libraries such as documentation generation
- `ci`: Changes to our CI configuration files and scripts
- `build`: Changes that affect the build system or external dependencies
- `temp`: Temporary commit that won't be included in the final version
- `revert`: Reverts a previous commit
- `merge`: Merge branch
- `release`: Release commit
