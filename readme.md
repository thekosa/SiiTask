# SiiTask

Spring Boot application for managing charity events, donation boxes, and individual donations.

---

## 🚀 Project Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/thekosa/SiiTask.git
   cd SiiTask
   ```

2. Build the project:

   ```bash
   ./mvnw clean install
   ```

3. Run the application:

   ```bash
   ./mvnw spring-boot:run
   ```

   The app will start at: `http://localhost:8080`


4. Run the resources/test_data script.(optional)

---

## 📦 Project Structure

* `model/` – JPA entities: `Event`, `Box`, `Donation`
* `controller/` – REST controllers
* `repo/` – `JpaRepository` interfaces
* `resources/application.properties` – H2 and JPA configuration

---

## 🔗 REST API Endpoints

### `/events`

* `POST /events/new` – create a new event
* `GET /events` – list all events
* `GET /events/report` – all events financial report

### `/boxes`

* `POST /boxes/new` – create a new collection box
* `GET /boxes` – list all boxes
* `PUT /boxes/{boxId}/assign-event/{eventId}` – assign a collaction box to an event
* `PUT /boxes/transfer/{boxId}` – transfer funds from a box to the event's account
* `PUT /boxes/{eventId}/transfer-all` - transfer funds from all the boxes to the event's account
* `DELETE /boxes/{boxId}` - delete a collection box
* `PUT /boxes/{boxId}/unregister` - unregister a collection box in case of theft

### `/donations`

* `POST /donations/{boxId}/new` – add a donation to a box
* `GET /donations` – list all donations
* `GET /donations/{boxId}` - list all donations belonged to a box

---

## 🧪 Testing

### Create an Event

**Method:** `POST`

**URL:** `http://localhost:8080/events/new`

**Request Body:**

```json
{
  "name": "WOŚP Warsaw",
  "currency": "PLN"
}
```

**Response Body:**

```json
{
  "id": 1,
  "name": "WOŚP Warsaw",
  "currency": "PLN",
  "accountAmount": 0.00
}
```

---

### Create a Donation Box

**Method:** `POST`

**URL:** `http://localhost:8080/boxes/new`

**Request Body:**

```json
{
  "name": "Box 1"
}
```

**Response Body:**

```json
{
  "id": 1,
  "name": "Box 1",
  "empty": true
}
```

---

### Assign a Box to an Event

**Method:** `PUT`

**URL:** `http://localhost:8080/boxes/1/assign-event/1`

**Response Body:**

```json
{
  "id": 1,
  "name": "Box 1",
  "event": {
    "id": 1,
    "name": "WOŚP Warsaw"
  },
  "empty": true
}
```

---

### Add a Donation

**Method:** `POST`

**URL:** `http://localhost:8080/donations/1/new`

**Request Body:**

```json
{
  "amount": 100,
  "currency": "PLN"
}
```

**Response Body:**

```json
{
  "id": 1,
  "amount": 100.00,
  "currency": "PLN",
  "box": {
    "id": 1
  }
}
```

---

### Transfer Funds from Box to Event

**Method:** `PUT`

**URL:** `http://localhost:8080/boxes/transfer/1`

**Response Body:**

```json
{
  "id": 1,
  "name": "WOŚP Warsaw",
  "currency": "PLN",
  "accountAmount": 100.00
}
```

---

### Generate Report

**Method:** `GET`

**URL:** `http://localhost:8080/events/report`

**Response Body:**

```json
[
  {
    "eventName": "WOŚP Warsaw",
    "amount": 100.00,
    "currency": "PLN"
  }
]
```

---


## 📊 Report (GET /events/report)

Returns a list of events with current account balances:

```json
[
  {
    "eventName": "WOŚP Warsaw",
    "amount": 150.00,
    "currency": "PLN"
  },
  {
    "eventName": "Help for Ukraine",
    "amount": 200.00,
    "currency": "EUR"
  }
]
```

---

## 🧾 Additional Information

* The project uses an in-memory H2 database
* Currencies are handled via an enum (`Currency`) and converted using a utility class `CurrencyConverter`
* A box can only be assigned to one event
* Transferring funds empties the box and converts currencies to the event's currency

---

This project meets the requirements of the recruitment assignment.

---
