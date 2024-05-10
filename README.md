# qikserve-task

# API Project: Promotion Service

Welcome to the Promotion Service API project! This project is built using Java with Spring Boot and use mock data to render usable content. The API serves promotional content, allowing users to access various promotions available.

## Features

- **Promotion Retrieval**: Retrieve promotional content through various API endpoints.
- **Flexible Promotion Types**: Supports different types of promotions such as discounts, deals, and offers.
- **Easy Integration**: Integrate seamlessly with other systems and applications through a well-defined API.

## Getting Started

To get started with the Promotion Service API, follow these steps:

1. **Clone the Repository**:

2. **Navigate to the Project Directory**:

3. **Build the Project**:

4. **Run the Application**:

5. **Access the API**:
   Open your web browser or API testing tool and access the endpoints at `http://localhost:8080`.

## Usage

### Endpoints

- **GET /api/products**: Retrieve all available products.
- **POST /api/products/add**: Add a new product.
- **PUT /api/products/edit/{productId}**: Edit an existing product with the specified ID.
- **POST /api/orders**: Create a new order with the products specified in the request body.

### Chosen question

Chosen question: Describe the most innovative or inventive endeavor you've undertaken. This could be your idea for a process change, a new product concept, a unique metric, or a novel customer interface. Do not share confidential information! Provide context to help us understand the innovation. What problem were you addressing, and what were the outcomes? Why was solving this problem important, and what was the impact of the change?

Answer: In one of my most innovative projects, I worked for a newspaper company that launched a sales channel for pet plans. One of the innovations we introduced was to allow customers who already had a connection with the news company to also access this new service in a unified way. This not only simplified the process for customers, but also encouraged brand loyalty, resulting in increased sales and customer satisfaction.

Additionally, at a food service startup, I contributed to the development of QR codes and a user-centric approach. This pioneering implementation allowed customers a more fluid and autonomous experience when placing orders. We were one of the first startups in the country to adopt this technology, and this had a significant impact, improving operational efficiency and providing a more satisfactory experience for users.

# Follow up questions

Question: How long did you spend on the test? What would you add if you had more time?

Answer: 16 hours were spent on the test. If I had more time I would add the use of Swagger UI for API documentation and the use of WireMock.

Question: What was the most useful feature that was added to the latest version of your chosen language? Please include a snippet of code that shows how you've used it.

Answer: Nothing specific.

Question: What did you find most difficult?

Answer: My biggest difficulty was trying to connect an API to the Swagger UI interface.

Question: What mechanism did you put in place to track down issues in production on this code? If you didn’t put anything, write down what you could do.

Answer: I used the SpringToolSuite4 tool debugger, and also logback-classic dependency.

Question: The Wiremock represents one source of information. We should be prepared to integrate with more sources. List the steps that we would need to take to add more sources of items with different formats and promotions.

Answer: I did not use WireMock in the project.
