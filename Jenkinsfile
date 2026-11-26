pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }

        stage('Maven Version') {
            steps {
                bat 'mvn -version'
            }
        }

        stage('Build Application') {
            steps {
                echo 'Building Student Registration Application...'
                bat 'mvn clean package'
            }
        }

        stage('Verify JAR') {
            steps {
                bat 'dir target'
            }
        }
    }

    post {
        success {
            echo 'BUILD SUCCESSFUL!'
        }

        failure {
            echo 'BUILD FAILED!'
        }
    }
}