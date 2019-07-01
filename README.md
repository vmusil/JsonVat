# JsonVat
Test application for getting EU countries VAT rates sorted by requirements

To run it:
```
mvn clean package

java -jar target/json-vat-1.0.jar
```

To see result in ReactJS:
```http://localhost:8080/```

To get countries with the lowest/highest VAT rates (default are 3 items):
```
http://localhost:8080/countries/vat/lowest
http://localhost:8080/countries/vat/highest
```

Optional parameter 'count' to get more or less results (it's max possible value, it can be lower due to the total number of countries):
```
http://localhost:8080/countries/vat/lowest?count=5
```

To run front-end part separately (for faster UI development):
```
cd src/main/js/ && npm start
```
