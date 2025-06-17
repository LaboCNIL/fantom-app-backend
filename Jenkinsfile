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
      regression {
         office365ConnectorSend(
            message:"Erreur lors du build de Fantom-App-Backend",
            webhookUrl:"https://actongroupe.webhook.office.com/webhookb2/d44322f9-b245-4929-b720-f8a573d5f65b@f2e2825c-f8d0-44fd-ae55-81c226791777/JenkinsCI/422034bbcb634ebca3cf0478f9a14a92/f1435656-c6b6-40ff-8fa2-91db51a97567/V2pJ_tgc0JSNdMBdB21SKV_rSErsqMc17TmJ72VzusXs01"
         )
      }
      fixed {
         office365ConnectorSend(
            message:"Build de Fantom-App-Backend réparré",
            webhookUrl:"https://actongroupe.webhook.office.com/webhookb2/d44322f9-b245-4929-b720-f8a573d5f65b@f2e2825c-f8d0-44fd-ae55-81c226791777/JenkinsCI/422034bbcb634ebca3cf0478f9a14a92/f1435656-c6b6-40ff-8fa2-91db51a97567/V2pJ_tgc0JSNdMBdB21SKV_rSErsqMc17TmJ72VzusXs01"
         )
      }
      cleanup {
         cleanWs()
      }
   }
}
