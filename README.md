# rh-datagrid

From custom fork from  

https://github.com/redhat-developer/redhat-datagrid-tutorials 



## How to use with ansible


NOTE: You need cluster admin account

Connect to our OCP instance : 

    oc login --token=${API_TOKEN} --server=${OCP_INSTANCE_URL_PORT}
    
    
Go to ansible playbook directory

    cd ansible
    
Execute ansible command

    ansible-playbook playbook.yaml    


### Variables 

    project_name: your-project
    application_name: your-app
    datagrid_cluster_name: your-dg-cluster
    datagrid_cluster_rep: 3


### Implementation

This ansible playbook (simple and stupid) creates several K8S objects in addition of the RHDG Operator :

* Namespace / project
* RHdatagrid cluster
* BuildConfig
* ImageStream
* ConfigMap
* DeploymentConfig

DeploymentConfig injects the RHDG certificate into pod for secure connection.
  

## Links 

* https://access.redhat.com/documentation/en-us/red_hat_data_grid/8.3/guide/2c250805-3704-415c-84a6-ebe96f3cc473#_6d35e485-c78f-4fc3-aca1-aa7549590f9d
* https://access.redhat.com/documentation/en-us/red_hat_data_grid/8.0/html/running_data_grid_on_openshift/securing_endpoints
* https://infinispan.org/docs/dev/titles/hotrod_java/hotrod_java.html





## Useful commands


	oc start-build redhat-datagrid-tutorials-git  --follow
	
	oc get secret/myispncluster-cert-secret -o json | jq '.data["tls.crt"]' -r | base64 -d
	