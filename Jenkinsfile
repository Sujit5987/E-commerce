pipeline {
    agent any
    tools {
        maven 'Maven-3.9'   // same name as in Jenkins Tools config
    }
    stages {
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }
}
