pipeline {
  agent any
  stages {
    stage('Build and Test application') {
      agent {
        docker {
          image 'maven:3.8-openjdk-18-slim'
        }
      }
      stages {
        stage('Build') {
          steps {
            sh 'mvn -B -DskipTests clean package'
          }
        }
        stage('Test') {
          steps {
            sh 'mvn test'
          }
        }
      }

    }
    stage('Build and Deploy Docker Image') {
      when {
        branch 'main'
      }
      steps {
        script {
          sh "docker compose build"
          sh 'docker stack deploy --compose-file docker-compose.yaml webshop'
        }
      }
    }
  }
  post {
    always {
      sh 'docker image prune -f'
    }
  }
}