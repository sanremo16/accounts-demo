## Accounts Demo Service

## Build and run
### Local profile
#### mvn clean package
#### cd persons; mvn spring-boot:run
#### cd accounts; mvn spring-boot:run


### With docker-compose
### mvn clean package
#### docker-compose build --no-cache
#### docker-compose up

### Push to local repository
#### docker run -d -p 5000:5000 --name registry registry:2.7
#### docker-compose build --no-cache
#### docker tag accounts localhost:5000/accounts
#### docker push localhost:5000/accounts

### Remove from local machine
#### docker rmi localhost:5000/persons
#### docker rmi localhost:5000/accounts





