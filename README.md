      ___     ___   __   __   ___      ___    ___   
     |   \   | __|  \ \ / /  / _ \    | _ \  / __|  
     | |) |  | _|    \ V /  | (_) |   |  _/  \__ \  
     |___/   |___|   _\_/_   \___/   _|_|_   |___/  

# DevOps Project
This is the devops project and will contain all automation related to CI Architecture. 


Folder Structure
 - resources - Jenkins Library Resources (External libraries may load adjunct files from a resources/ directory using the libraryResource step)
 - vars - Jenkins Libarary Scripts (Only entire pipelines can be defined in shared libraries as of this time. This can only be done in vars/*.groovy, and only in a call method. Only one Declarative Pipeline can be executed in a single build, and if you attempt to execute a second one, your build will fail as a result.)
 - training - Include a guide to implementing ci flow with Groovy script.

# Prerequisite
- have knowledge JenkinsCi tool
- have knowledge of Docker
- have knowledge of K8S

# Requirement
- Jenkins Server has installed some
	- Plugins:
		- Jenkins suggested
 		- Docker PipelineVersion
		- xUnit
		- Cobertura
		- Code Coverage API
		- Pipeline Utility Steps
		- Kubernetes
	- Tools:
		- kubectl cli
		- docker
- Sonar Server
- AKS
- ACR
- MSSQL
- Repositories:
	- https://github.com/nashtech-garage/dotnet-bookstore-api/tree/jenkins

 # Refer
 - https://www.jenkins.io/doc/book/pipeline/shared-libraries/
