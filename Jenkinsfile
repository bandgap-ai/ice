pipeline {
    agent any
    tools {
      maven 'maven-3.9.5'
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
