pipeline {
    agent any
    tools {
        maven 'maven-3.9'
        jdk 'JDK-21'       // add this line
    }
    stages {
        stage('Build') {
            steps {
                bat 'mvn clean install -X'   // -X gives verbose output
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }
}
