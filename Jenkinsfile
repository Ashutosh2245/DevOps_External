pipeline {
    agent any

    environment {
        MAVEN_HOME = 'C:\\Users\\Ashutosh Kumar\\Downloads\\apache-maven-3.10.2-bin\\apache-maven-3.10.2'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
            }
        }

        stage('Maven Version') {
            steps {
                bat '"%MAVEN_HOME%\\bin\\mvn.cmd" -version'
            }
        }

        stage('Build Application') {
            steps {
                echo 'Building Student Registration Application...'
                bat '"%MAVEN_HOME%\\bin\\mvn.cmd" clean package'
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