pipeline {
    agent {
        docker {
            image 'maven:3-alpine' 
            args '-p 55555:8080'
        }
    }
    environment {
          HOME = '.'
     }

     stages {
          stage('Source') {
               steps {
                    git branch: 'main',
                        url:    'https://github.com/KasidisGit/atm-web-frontend.git'
               }
          }
          stage('Build') {
               steps {
                    //sh 'mvn compile'
                    echo 'building...'
               }
          }
          stage('Test') {
               steps {
                    //sh 'mvn test'
                    echo 'testing...'
               }
          }
          stage('Deploy') {
               steps {
                    sh 'mvn spring-boot:run'
               }
          }
     }
}
