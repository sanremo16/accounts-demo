## Accounts Demo Service

## Local profile
1. mvn clean package
2. cd persons; mvn spring-boot:run
3. cd accounts; mvn spring-boot:run

## With docker-compose
1. mvn clean package
2. docker-compose build --no-cache
3. docker-compose up

## Build and Push to local repository
1. docker run -d -p 5000:5000 --name registry registry:2.7
2. docker-compose build --no-cache
3. docker tag accounts localhost:5000/accounts
4. docker push localhost:5000/accounts
5. docker tag persons localhost:5000/persons
6. docker push localhost:5000/persons

### Remove from local machine
1. docker rmi localhost:5000/persons
2. docker rmi localhost:5000/accounts

## With microk8s
1. sudo microk8s status

### Enable kube dashboard for microk8s
1. kubectl port-forward -n kube-system service/kubernetes-dashboard 10443:443

### Start local image regisrty
1. docker run -d -p 5000:5000 --name registry registry:2.7

### Without istio deployment
1. kubectl apply -f src/main/resources/k8s/1-accounts-ns.yaml
2. kubectl apply -f src/main/resources/k8s/2-accounts-cm.yaml
3. kubectl apply -f src/main/resources/k8s/3-accounts-deployment.yaml
4. kubectl apply -f src/main/resources/k8s/4-accounts-service.yaml
5. kubectl apply -f src/main/resources/k8s/without-istio/5-accounts-load-balancer-service.yaml

## With Istio deployment
1. kubectl apply -f kubernetes/istio/1-shaman-gateway.yaml
2. kubectl apply -f kubernetes/istio/2-shaman-main-virtual-service.yaml
3. kubectl apply -f kubernetes/istio/3-shaman-main-destination-rule.yaml

## Get IP addresses
kubectl get services -n accounts-demo

## Get logs
1. kubectl logs -f deployment/accounts-deployment -n accounts-demo
2. kubectl logs -f deployment/persons-deployment -n accounts-demo





