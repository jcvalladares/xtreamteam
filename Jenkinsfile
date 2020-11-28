pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                echo "SFA Build"
                sh 'mvn -Dmaven.test.failure.ignore=true install'
            }
        }
    }
}
