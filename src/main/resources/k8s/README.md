## Enable kube dashboard for microk8s
sudo microk8s kubectl port-forward -n kube-system service/kubernetes-dashboard 10443:443

## Start local image regisrty
docker run -d -p 5000:5000 --name registry registry:2.7

## Without istio deployment
1) sudo microk8s kubectl apply -f src/main/resources/k8s/1-accounts-ns.yaml  
2) sudo microk8s kubectl apply -f src/main/resources/k8s/2-accounts-cm.yaml  
3) sudo microk8s kubectl apply -f src/main/resources/k8s/3-accounts-deployment.yaml  
4) sudo microk8s kubectl apply -f src/main/resources/k8s/4-accounts-service.yaml
5) sudo microk8s kubectl apply -f src/main/resources/k8s/without-istio/5-accounts-load-balancer-service.yaml

## With Istio deployment
1) kubectl apply -f kubernetes/istio/1-shaman-gateway.yaml
2) kubectl apply -f kubernetes/istio/2-shaman-main-virtual-service.yaml
3) kubectl apply -f kubernetes/istio/3-shaman-main-destination-rule.yaml