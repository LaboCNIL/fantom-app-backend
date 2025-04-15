pipeline {
//    agent { label "dev" }

   environment {
      DOCKER_REGISTRY = "https://${env.MAIN_DOCKER_REGISTRY_HOST}/${env.SCW_REGISTRY_NS}"
      IMAGE_REPOSITORY = "fantome-app-backend"
      REGISTRY_CREDENTIALS = 'SCW_DOCKER_LOGIN'
   }

   stages {
      stage('Clone') {
         steps {
            checkout scm
            stash name: 'scm', includes: '*'
         }
      }

      stage('Test application') {
         steps {
            unstash 'scm'
            script {
               def timezone = '-e TZ=Europe/Paris'
               docker.image('maven:3.9.6-eclipse-temurin-17').inside("${timezone}") {
                  sh 'mvn -version ; mvn test'
               }
            }
         }
      }

      stage('Build application') {
         steps {
            unstash 'scm'
            script {
               def timezone = '-e TZ=Europe/Paris'
               docker.image('maven:3.9.6-eclipse-temurin-17').inside("${timezone}") {
                  sh 'mvn -DskipTests -Pprod package'
               }
            }
         }
      }

      def image

      stage('Build image API') {
         steps {
            script {
               def branchName = "develop"
               //def sanitizedBranch = branchName.replaceAll("/", "-")
               docker.withRegistry(DOCKER_REGISTRY, REGISTRY_CREDENTIALS) {
                  image = docker.build("${IMAGE_REPOSITORY}:${branchName}", ".")
               }
            }
         }
      }

      stage('Push image API') {
         steps {
            script {
               def branchName = "develop"
               //def sanitizedBranch = branchName.replaceAll('/', '-')
               docker.withRegistry(DOCKER_REGISTRY, REGISTRY_CREDENTIALS) {
                  image.push("${branchName}")
               }
            }
         }
      }
   }
}
