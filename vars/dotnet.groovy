#!/usr/bin/env groovy
void call() {
    String name = "bookstore"
    String runtime = "BookStore.API.dll"
    String publishProject = "src/BookStore.API/BookStore.API.csproj"
    String baseImage     = "mcr.microsoft.com/dotnet/sdk"
    String baseTag       = "7.0"
    String demoRegistry = "demotraining.azurecr.io"
    String checkBranches = "$env.BRANCH_NAME"
    String[] deployBranches = ['main', 'jenkins']
    String sonarToken = "sonarqube"
    String host = \\""http://localhost:9000\\""
    String token = \\""sqp_d39cd36f91111efb2be9a6a1390bfc1d2ddb9bc3\\""
    String namespace = "test"
    String rununitTest = "dotnet test --no-build -l:trx -c Release -p:DOTNET_RUNTIME_IDENTIFIER=linux-x64 --collect:'XPlat Code Coverage' --verbosity minimal --results-directory ./results"

//========================================================================
//========================================================================

//========================================================================
//========================================================================

    stage ('Prepare Package') {
        script {
            writeFile file: '.ci/Dockerfile.SDK', text: libraryResource('dev/demo/flows/dotnet/docker/Dockerfile.SDK')
            writeFile file: '.ci/Dockerfile.Runtime.API', text: libraryResource('dev/demo/flows/dotnet/docker/Dockerfile.Runtime.API')
            writeFile file: '.ci/Dockerfile.SonarBuild', text: libraryResource('dev/demo/flows/dotnet/docker/Dockerfile.SonarBuild')
            writeFile file: '.ci/docker_entrypoint.sh', text: libraryResource('dev/demo/flows/dotnet/script/docker_entrypoint.sh')
            writeFile file: '.ci/deployment.yml', text: libraryResource('deploy/aks/deployment.yml')
            writeFile file: '.ci/service.yml', text: libraryResource('deploy/aks/service.yml')
        }
    }

    stage ("Build Solution") {
        docker.build("demo/${name}-sdk:${BUILD_NUMBER}", "--force-rm --no-cache -f ./.ci/Dockerfile.SDK \
        --build-arg BASEIMG=${baseImage} --build-arg IMG_VERSION=${baseTag} ${WORKSPACE}") 
    }

    stage('SonarQube analysis') {
        script {
            withSonarQubeEnv(credentialsId: sonarToken) {
                withCredentials([string(credentialsId: sonarToken, variable: 'SONAR_TOKEN')]) {
                    sh "dotnet tool install --global dotnet-sonarscanner"
                    sh "dotnet sonarscanner begin /k:${namespace} /d:sonar.host.url=${host}  /d:sonar.token=${token}" 
                }
            }
        }
    }
}

//========================================================================
// Demo CI
// Version: v1.0
// Updated:
//========================================================================
//========================================================================
// Notes:
//
//
//========================================================================
