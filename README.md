## Results of calling APIs

### The controller hierarchy of example APIs

![Alt text](images/controller-hierarchy.png?raw=true "Controller Hierarchy")


### Versioning results of the A Controller
```
skim:versioning/ (master✗) $ curl localhost:8080/api/v1/a
a of v1
```

```
skim:versioning/ (master✗) $ curl localhost:8080/api/v2/a
a of v1
```

```
skim:versioning/ (master✗) $ curl localhost:8080/api/v3/a
a of v1 was overridden by v3
```

### Versioning results of the B Controller

```
skim:versioning/ (master✗) $ curl localhost:8080/api/v1/b
b of v1
```

```
skim:versioning/ (master✗) $ curl localhost:8080/api/v2/b
b of v2
```

```
skim:versioning/ (master✗) $ curl localhost:8080/api/v3/b
b of v3
```

### Versioning results of the C Controller

```
skim:versioning/ (master✗) $ curl localhost:8080/api/v1/c
c of v1
```

```
skim:versioning/ (master✗) $ curl localhost:8080/api/v2/c
c of v1
```

```
skim:versioning/ (master✗) $ curl localhost:8080/api/v3/c
c of v1
```


## Exception handling

### Invalid versions (BAD REQUEST)

```
skim:versioning/ (master✗) $ curl -I localhost:8080/api/v42/k
HTTP/1.1 400
Content-Language: en-US
Content-Length: 0
Date: Thu, 03 May 2018 08:10:14 GMT
```

### Valid versions, but unimplemented APIs (NOT FOUND)
```
curl -I localhost:8080/api/v3/k
HTTP/1.1 404
Content-Language: en-US
Content-Length: 0
Date: Thu, 03 May 2018 08:09:55 GMT
```

### Faded out versions(GONE)
```
skim:versioning/ (master✗) $ curl -I localhost:8080/api/some-faded-out-version/k
HTTP/1.1 410
Content-Language: en-US
Content-Length: 0
Date: Thu, 03 May 2018 08:09:25 GMT
```
