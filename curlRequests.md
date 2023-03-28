Запросы curl
============
### Meal запросы:
- **`get:` curl --location 'http://localhost:8080/topjava/rest/profile/meals/100003'**
- **`delete:` curl --location --request DELETE 'http://localhost:8080/topjava/rest/profile/meals/100003'**
- **`getAll:` curl --location 'http://localhost:8080/topjava/rest/profile/meals'**
- **`createWithLocation:` curl --location 'http://localhost:8080/topjava/rest/profile/meals' \
  --header 'Content-Type: application/json' \
  --data '{
  "dateTime": "2023-03-27T23:00:00",
  "description": "Перекус",
  "calories": 500
  }'**
- **`update:` curl --location --request PUT 'http://localhost:8080/topjava/rest/profile/meals/100003' \
  --header 'Content-Type: application/json' \
  --data '{
  "id": 100003,
  "dateTime": "2023-01-30T20:00:00",
  "description": "Завтрак",
  "calories": 400,
  "user": null
  }'**
- **`getBetween:` curl --location 'http://localhost:8080/topjava/rest/profile/meals/filter?startDate=2020-01-30&endDate=2020-01-30&startTime=12%3A00&endTime=14%3A00'**