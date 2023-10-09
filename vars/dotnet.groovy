#!/usr/bin/env groovy
void call() {
    String name = "backend"
    String tag = "backend"
    String runtime = "BookStore.API.dll"
    String publishProject = "src/backend/Dockerfile"
    String baseImage     = "mcr.microsoft.com/dotnet/sdk"
    String baseTag       = "6.0"
    String demoRegistry = "demotraining.azurecr.io"
    String checkBranches = "$env.BRANCH_NAME"
    String[] deployBranches = ['main', 'jenkins']
    String sonarToken = "sonar-token"
    String acrCredential = 'acr-demo-token'
    String k8sCredential = 'akstest'
    String namespace = "demo"
    String rununitTest = "dotnet test --no-build -l:trx -c Release -p:DOTNET_RUNTIME_IDENTIFIER=linux-x64 --collect:'XPlat Code Coverage' --verbosity minimal --results-directory ./results"

//========================================================================
//========================================================================

//========================================================================
//========================================================================

    stage ('Prepare Package') {
        script {
            writeFile file: '.ci/Dockerfile.SDK', text: libraryResource('dev/demo/flows/dotnet/docker/Dockerfile.SDK')
        }
    }

    stage ("Build Solution") {
        docker.build("demo/${name}-sdk:${BUILD_NUMBER}", "--force-rm --no-cache -f ./.ci/Dockerfile.SDK \
        --build-arg BASEIMG=${baseImage} --build-arg IMG_VERSION=${baseTag} ${WORKSPACE}") 
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
