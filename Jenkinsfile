pipeline {
   agent any

   tools {
      jdk "jdk21"
      maven "maven-pic"
   }

   environment {
      MAIN_DOCKER_REGISTRY_HOST = "rg.fr-par.scw.cloud"
      SCW_REGISTRY_NS = "demo-aot"
      IMAGE_REPOSITORY = "fantome-app-backend"
      DOCKER_REGISTRY = "${MAIN_DOCKER_REGISTRY_HOST}/${SCW_REGISTRY_NS}"
      IMAGE_URL = "${DOCKER_REGISTRY}/${IMAGE_REPOSITORY}"
   }

   stages {
      stage('Clone') {
         steps {
            checkout scm
         }
      }

      stage('Build & Test') {
         steps {
            script {
               sh "mvn clean package"
            }
         }
      }

      stage('Quality') {
         steps {
            withSonarQubeEnv('sonarqube AOT') {
               sh "mvn sonar:sonar"
            }
         }
      }

      stage('Build Docker Image') {
         steps {
            script {
               def tag = env.BRANCH_NAME ?: 'latest'
               sh "docker build . -t ${IMAGE_URL}:${tag}"
            }
         }
      }

      stage('Push Docker Image') {
         steps {
            withCredentials([string(credentialsId: 'SCW_PIC_AOT_SK', variable: 'PASSWORD')]) {
               script {
                  def tag = env.BRANCH_NAME ?: 'latest'
                  sh "docker login ${DOCKER_REGISTRY} -u nologin -p $PASSWORD"
                  sh "docker push ${IMAGE_URL}:${tag}"
               }
            }
         }
      }
      
      stage('Deploy Application') {
         steps {
            script {
               echo "🚀 Déploiement en cours..."
               def tag = env.BRANCH_NAME ?: 'latest'
               def deployEnv = env.DEPLOYMENT_ENVIRONMENT ?: 'sandbox'
               build job: 'fantome-app-deploy', parameters: [
                  string(name: 'TAG', value: tag),
                  string(name: 'DEPLOYMENT_ENVIRONMENT', value: deployEnv)
               ]
            }
         }
      }
   }
   
   post {
      always {
         cleanWs()
      }
   }
}
