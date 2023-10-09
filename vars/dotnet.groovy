#!/usr/bin/env groovy
void call() {
    String name = "bookstore"
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

    stage ('Build Image') {
        docker.build ("--pull --rm -f ${name} -t src:latest ${tag}")
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
