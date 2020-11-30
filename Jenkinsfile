#!groovy

pipeline {
 agent any
  tools {
        //env.M2_HOME = tool name: 'Maven 3.2', type: 'maven'
        maven 'Maven3.6.3'
        }
       
  stages{
       
        stage('SFDA Workflow Engine Code Checkout') {
            steps {
                echo "Cleaning the workspace before checkout"  
                cleanWs()
                git branch: "main", credentialsId: 'sfdaPipe', url: 'https://github.com/jcvalladares/xtreamteam.git'
            }
        }
       
       
        stage('Code Building and Unit Test suite') {
            steps {
                script {    
                        sh "${env.M2_HOME}/bin/mvn -f ${env.WORKSPACE}/sfda/pom.xml clean install surefire-report:report"
                }

            }
        }
   
         stage('Deploy ') {
          when {
                   expression { 
                      return params.Deploy == 'Yes'
                   }
          }
            steps {
                script {    
                       echo "Stopping Curring Instance"
                       sh "jps -v  | grep \\"sfda-0.0.1-SNAPSHOT.jar"\\ | awk '{print $1}'|xargs kill -9"
                       echo "Starting New Instance"
                       sh "/usr/bin/java -jar ${env.WORKSPACE}/sfda/target/sfda-0.0.1-SNAPSHOT.jar"
                       echo "Deployment Successful"
                }
            }
        }

      }
     
}
