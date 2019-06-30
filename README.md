# JsonVat
Test for getting EU countries VAT rates

To run it:

```
mvn clean install
java -jar target/json-vat-1.0-jar-with-dependencies.jar
```

To get countries with the lowest/highest VAT rates (default are 3 items):
```
http://localhost:8080/countries/vat/lowest
http://localhost:8080/countries/vat/highest
```

Optional parameter 'count' to get more or less results (it's max possible value, it can be lower due to the total number of countries):
```
http://localhost:8080/countries/vat/lowest?count=5
```
