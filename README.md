Project Overview

This project is a backend application developed using Java and Spring Boot.
It ingests machine events in batch form, applies validation and business rules, stores valid events, and provides statistical insights for machines within a given time range.

The system is designed to handle duplicate events, event updates, invalid inputs, and statistical aggregation in a reliable manner.

Technology Stack

Java
Spring Boot
Spring Data JPA
H2 In-Memory Database
Postman for API testing

Features Implemented

Batch ingestion of machine events
Event deduplication based on eventId and payload
Event update based on newer received time
Rejection of invalid events
Statistics calculation for machines
Basic thread-safe handling using transactions

API Endpoints
1. Batch Event Ingestion

Endpoint
POST /events/batch

Description
Accepts a list of machine events and processes them based on validation and business rules.

Validations
Duration must be within allowed limits
Event time must not be more than 15 minutes in the future

Response Fields
accepted
deduped
updated
rejected
rejections

2. Machine Statistics

Endpoint
GET /stats

Query Parameters
machineId
start
end

Description
Returns statistics for a machine within the given time window.

Response Fields
eventsCount
defectsCount
average defect rate
status

Business Rules

Duplicate events with identical payload are deduplicated
Events with same eventId and newer received time are updated
Events with older received time are ignored
Invalid events are rejected with proper reason
Machine status is Healthy or Warning based on defect rate

Testing

Manual testing was performed using Postman.
Test cases cover normal flow, validation failures, deduplication, updates, and statistics scenarios.
All test cases from TC-01 to TC-08 passed successfully.

How to Run the Project

Clone the repository
Import the project into an IDE
Run the Spring Boot application
Access APIs using http://localhost:8080
