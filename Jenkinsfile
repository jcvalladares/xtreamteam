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
                echo "Cleaning workspace before checkout"  
                cleanWs()
                git branch: "main", credentialsId: 'sfdaPipe', url: 'https://github.com/jcvalladares/xtreamteam.git'
            }
        }
       
       
        stage('Code Build and Unit Test suite') {
            steps {
                script {    
                        bat "${env.M2_HOME}/bin/mvn -f ${env.WORKSPACE}/sfda/pom.xml clean install"
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
                        bat "C:\\nssm-2.24\\nssm-2.24\\win64\\restartApp.bat"
                }
            }
        }
   

      }
     
}
