pipeline {
    agent any
    tools {
      maven 'apache-maven-3.9.3'
      jdk 'openjdk-jdk17-latest'
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
		sh 'mvn clean package'
            }
        }
        stage('Deploy') {
            steps {
                echo 'No deployment scheduled.'
            }
        }
    }
}
