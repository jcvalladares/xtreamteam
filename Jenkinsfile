pipeline {
    agent any
    tools {
        maven 'Maven 3.6.0'
    }
    stages {
        stage('build') {
            steps {
                echo "SFA Build"
                sh 'mvn -Dmaven.test.failure.ignore=true install'
            }
        }
    }
}
