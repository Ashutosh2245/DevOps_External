pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-21.0.12.1'
        MAVEN_HOME = 'C:\\Users\\Ashutosh Kumar\\Downloads\\apache-maven-3.10.2-bin\\apache-maven-3.10.2'
    }

    stages {

        stage('Java Version') {
            steps {
                bat '''
                    set "PATH=%JAVA_HOME%\\bin;%PATH%"
                    java -version
                    javac -version
                '''
            }
        }

        stage('Maven Version') {
            steps {
                bat '''
                    set "PATH=%JAVA_HOME%\\bin;%PATH%"
                    "%MAVEN_HOME%\\bin\\mvn.cmd" -version
                '''
            }
        }

        stage('Build Application') {
            steps {
                echo 'Building Student Registration Application...'

                bat '''
                    set "PATH=%JAVA_HOME%\\bin;%PATH%"
                    "%MAVEN_HOME%\\bin\\mvn.cmd" clean package
                '''
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