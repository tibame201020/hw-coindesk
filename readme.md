## open api
```
localhost:8099/api-docs
```

## currency sql
```
CREATE TABLE Currency (
    coin_category VARCHAR(255) NOT NULL PRIMARY KEY,
    coin_description VARCHAR(255),
    rate DECIMAL(19, 4),
    api_update_time TIMESTAMP,
    create_time TIMESTAMP,
    update_time TIMESTAMP
);
```