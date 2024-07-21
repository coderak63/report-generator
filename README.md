# Report Generator Service

## Overview

The Report Generator Service is a Java Spring Boot application designed to ingest CSV feed files from an upstream source, transform them based on configurable rules and reference data, and generate output reports. 
The service supports large input files (up to 1 GB) and can be triggered via a REST API or scheduled to run at specific times. 
The application is designed to be extensible for future enhancements, such as supporting additional input/output formats and modifying transformation rules.

## Features

- Ingest CSV files and transform them according to configurable rules.
- Generate output reports in CSV format.
- Support for large files (up to 1 GB) with fast report generation (within 30 seconds per file).
- REST API to trigger report generation.
- Scheduling support for periodic report generation.
- Extensible architecture for future enhancements (e.g., additional formats, rule changes).

## Getting Started

### Prerequisites

- Java 17 or later
- Maven 3.6.0 or later

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/coderak63/report-generator.git
   cd report-generator
   ```

2. **Configure the application:**

   Update the `application.properties` file in the `src/main/resources` directory with your input/reference/output file paths and other configurations.

3. **Build the project:**

   ```bash
   mvn clean install
   ```

4. **Run the application:**

   ```bash
   mvn spring-boot:run
   ```

### Usage

#### REST API

- **Trigger Report Generation:**

  ```http
  GET /api/reports/generate
  ```


#### Scheduling

Report generation can be scheduled by updating the cron expression in the `application.properties` file or using a dedicated scheduler configuration.

### Transformation Rules

The transformation rules are configurable and can be updated in the application properties or a separate configuration file.

Example rules:

- `outfield1 = field1 + field2`
- `outfield2 = refdata1`
- `outfield3 = refdata2 + refdata3`
- `outfield4 = field3 * max(field5, refdata4)`
- `outfield5 = max(field5, refdata4)`

### Logging

The application includes extensive logging to track the processing and transformation of files. Logs are written to both the console and a file.

### Unit Tests

Unit tests are provided to ensure the correctness of the transformation logic and other functionalities. Tests are written using JUnit 5 and Mockito.

To run the tests, use:

```bash
mvn test
```

### Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes.


## Contact

For any questions or suggestions, please contact Abhishek Kumar Singh at [abhishek.mnnit.mca2k22@gmail.com].
```

This should ensure that the `README.md` file is correctly formatted and all code snippets are properly shown.
