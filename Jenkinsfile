pipeline {
    agent any
    tools {
      maven 'apache-maven-3.9.3'
      jdk 'java-17'
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
