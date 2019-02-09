# How to test swfanatic

### Save method

```sh
$ curl -i -X POST \
$ -H "Content-Type:application/json" \
$ -d \
$ '{"name": "Alderaan","climate": "temperate","terrain": "grasslands, mountains"}' \
$ 'http://localhost:8080/planet'
```

### Get a planet by id
```sh
 curl -i -X GET \
  'https://localhost:8080/planet/1/'
```

### Get a planet by name

```sh
 curl -i -X GET \
  'http://localhost:8080/planet/?name=Alderaan'
```

### Get all planets
```sh
 curl -i -X GET \
  'http://localhost:8080/planet'
```

### Get planets From [SWAPI]

```sh
curl -i -X GET \
 'http://localhost:8080/planets'
 ```
###  Get planets from [SWAPI] by page 
```sh
http://localhost:8080/planets/1 by page
```


[SWAPI]: <https://swapi.co/documentation#planets>