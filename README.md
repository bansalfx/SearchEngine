# Product SearchEngine

# Requirement
Java8
Maven

# Run Instruction
Maven Run Command
`mvn spring-boot:run`

Swagger Documentation:
http://localhost:8080/swagger-ui.html#/search-controller/searchProductUsingGET

# _Project Workflow:_

The flow of phases that I followed are as below:

1. Requirement Gathering
    1. Understand and look deeply into requirement
2. Define Use-Case
3. Architecture Diagram
    1. Overall Architecture Diagram
    2. Class Diagram
    3. Sequence Diagram
4. Tech Stack
5. Backlog Design – Epics and Stories
    1. Create the project Setup
    2. Add Model/Class
    3. Try fetching and printing one product from the API Endpoint
    4. Iterate over all the products and store it in database.
    5. Massage the data in the format that it can be queried
    6. Design the indexing logic
    7. Implement the indexing logic using the inverted-Index
    8. Implement the search controller to return a product from H2 database using simple search
    9. Implement the logic around the criteria builder to search based on all the search criteria except the text search.
    10. Implement the logic to handle text search and join on Index Data and main database.
    11. Add caching around the search controller.
    12. Add swagger documentation around the controller for better User interaction
6. Optimization
  1. Cache Implementation
  2. Indexing – Free Form Text search

# Requirement Gathering:

Please take a look at https://mobile-tha-server.firebaseapp.com/. The endpoint returns a list of products. Your task is to build out a REST endpoint that provides Search and Filter capability over the products info. Here are the query parameters/capabilities we expect to see:

1. search
2. minPrice
3. maxPrice
4. minReviewRating
5. maxReviewRating
6. minReviewCount
7. maxReviewCount
8. inStock

The clients of this API should be able to provide any combination of these parameters and get the products matching all the criteria.

Please create a repo for your project. Please keep it tidy as we will go over your code-base. The final submission is a link to this repo. This is your chance to show off your engineering best practices, such as well decomposed code, unit testing, documentation, clean commit messages, etc. Also think about efficiency of your solution, any caching and other considerations. Do take the time you need, and let us know if you have any questions.















# Use Cases:



Based on the requirements above we need to provide a API client that can let user search based on one or more parameters. The parameters can be:

1. search – Accept String
2. minPrice – Accept Number
3. maxPrice – Accept Number
4. minReviewRating – Accept Number between 0 and 5
5. maxReviewRating – Accept Number between 0 and 5
6. minReviewCount – Accept Number
7. maxReviewCount – Accept Number
8. inStock – Accept Boolean





The URL will look like:

1. [search by name](http://localhost:8080/searchengine?search=%7bvalue1%7d)
2. [search by min price](http://localhost:8080/searchengine?minPrice=%7bvalue1%7d)
3. [search by max price](http://localhost:8080/searchengine?maxPrice=%7bvalue1%7d)
4. [search by min review rating](http://localhost:8080/searchengine?minReviewRating=%7bvalue1%7d)
5. [search by max review rating](http://localhost:8080/searchengine?maxReviewRating=%7bvalue1%7d)
6. [search by min review count](http://localhost:8080/searchengine?minReviewCount=%7bvalue1%7d)
7. [search by max review count](http://localhost:8080/searchengine?maxReviewCount=%7bvalue1%7d)
8. [search by instock](http://localhost:8080/searchengine?inStock=%7bvalue1%7d)



or the URL could comprise of any combination of parameter from above list.











# Architecture:



 ![](https://github.com/bansalfx/SearchEngine/blob/master/Archtitecture%20Diagram.png)

# Class Diagram:

I am planning to create mainly 4 classes.

1. Product Class
2. Walmart Product Class
3. Product Index Class
4. Search Body Request Class

 ![](https://github.com/bansalfx/SearchEngine/blob/master/Class%20Diagram.png)
# Tech Stack:

The tech-stack that I have decided to go with are as below:

1. Java Spring Boot Application – Spring Framework
2. H2 In-Mem database for Storing products and Inverted Index
3. TestNG for Unit Testing
4. Swagger for API Documentation
5. Maven for Deployment



# Sample Response:

```
// 20190410224920
// http://localhost:8080/searchengine?search=test

[
  {
    "productId": "0c3480d5-21f8-4422-aceb-b659d9a78b1f",
    "productName": "APC Performance SurgeArrest 11 Outlet",
    "shortDescription": "With APC SurgeArrest, you get maximum protection and convenience for computers, notebooks and other electronics.",
    "longDescription": "Lightning and Surge Protection SurgeArrest components such as MOVs and Thermal fuse ensure instantaneous reaction to lightning strikes and wiring faults. If the surge components are damaged due to power spike or over voltage, excess power cannot reach your equipment. Unlike the APC SurgeArrest products, most surge suppressors continue to let power through even after circuits have been damaged, leaving your equipment exposed to other damaging surges. IEEE Let-Through Rating and UL 1449 Compliance The 'Institute of Electrical and Electronics Engineers' (IEEE) Let-Through Voltage rating is based on a test that subjects a Surge Protector to a 6,000 volt spike. The rating equates to the amount of excess voltage that reaches connected equipment. The lower the number, the better the performance of the Surge Protector is. Underwriter's Laboratory's UL1449 surge protection safety standard uses these ratings to help users gauge performance. UL's best Transient Voltage Surge Suppressor (TVSS) Let-Through Voltage rating is \"300V\". Data-line Protection Protection of data lines (Ethernet, Coaxial and Phone lines) ensures complete protection of your equipment from surges. It is very important to protect your equipment from back door surges traveling through data lines, as they can be as damaging to your equipment as surges traveling over power lines. Noise Filtering Surge Protector attenuates EMI/RFI line noise that can cause data errors and keyboard lockups, ensuring better performance of protected equipment.",
    "price": "$19.98",
    "productImage": "/images/image6.jpeg",
    "reviewRating": 4,
    "reviewCount": 20,
    "inStock": true,
    "floatPrice": 19.98
  }
]
```
