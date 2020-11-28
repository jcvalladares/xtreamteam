#!groovy
#def branchName = params.branch_name
#def serverName = params.server_name
#def port = params.port
#def userName = params.user_name
#def password = params.password

pipeline {
 agent any
  tools {
        //env.M2_HOME = tool name: 'Maven 3.2', type: 'maven'
        maven 'Maven 3.1'
        }
       
  stages{
       
        stage('SFDA Workflow Engine Code Checkout') {
            steps {
                echo "Cleaning workspace before checkout"  
                cleanWs()
                #git branch: "$branchName",credentialsId: '5ec30aee-8e5b-4be6-842c-b3647530a6d2', url: 'ssh://git@gitrepo.mdevlab.com/regcomp/ngp/tpa/tpa-workflow-engine.git'
                git branch: "main", credentialsId: 'sfdaPipe', url: 'https://github.com/jcvalladares/xtreamteam.git'
            }
        }
       
       
        stage('Code Build and Unit Test suite') {
            steps {
                script {    
                        #sh "${env.M2_HOME}/bin/mvn -f ${env.WORKSPACE}/pom_onprem.xml clean install"
                        bat "${env.M2_HOME}/bin/mvn -f ${env.WORKSPACE}/pom_onprem.xml clean install"
                }

            }
        }

      }
     
}
