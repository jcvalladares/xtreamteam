#!groovy

node {

	    // reference to maven
	    def mvnHome = tool 'Maven3.6.3'

	    // holds reference to docker image
	    def dockerImage
	    // ip address of the docker private repository(nexus)

	    def dockerImageTag = "sfdadocker${env.BUILD_NUMBER}"
			//	    def dockerImageTag = "devopsexample${env.BUILD_NUMBER}"


	    stage('SFDA Workflow Engine Code Checkout') { // for display purposes
				echo "Cleaning the Workspace Before Checkout"
				cleanWs()
				echo "Getting Main Branch of Git Repository"
				git branch: "main", credentialsId: 'sfdaPipe', url: 'https://github.com/jcvalladares/xtreamteam.git'
	      // Get the Maven tool.
	      mvnHome = tool 'Maven3.6.3'
	    }

	    stage('Code Build and Unit Test suite') {
	      // build project via maven
				echo "Building Project and Running Unit Tests"
				sh "'${mvnHome}/bin/mvn' -f ${env.WORKSPACE}/sfda/pom.xml clean install"
			}

	    stage('Build Docker Image') {
	      // build docker image
				echo "Building Docker Image"
	      dockerImage = docker.build("sfdadocker:${env.BUILD_NUMBER}")
	    }

	    stage('Deploy Docker Image') {
			// Sanity check
			if (params.Deploy == 'Yes')
				// deploy docker image to nexus
				echo "Docker Image Tag Name: ${dockerImageTag}"
				try {
					sh "docker stop sfdadocker"
					sh "docker rm sfdadocker"
				} catch (Exception e) {
					echo 'Exception occurred: ' + e.toString()
				}
				sh "docker run --name sfdadocker -d -p 8080:8080 sfdadocker:${env.BUILD_NUMBER}"
			}
			else {
				echo "Tests and Build succeeded but Deploy Status set to No"
			}
		}
	}
