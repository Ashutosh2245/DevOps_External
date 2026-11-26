pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-21.0.12.1'
        MAVEN_HOME = 'C:\\Users\\Ashutosh Kumar\\Downloads\\apache-maven-3.10.2-bin\\apache-maven-3.10.2'
        PATH+JAVA = 'C:\\Program Files\\Java\\jdk-21.0.12.1\\bin'
    }

    stages {

        stage('Java Version') {
            steps {
                bat 'java -version'
                bat 'javac -version'
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