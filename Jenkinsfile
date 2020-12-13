#!groovy

node {

	    // reference to maven
	    def mvnHome = tool 'Maven3.6.3'

	    // holds reference to docker image
	    def dockerImage
	    // ip address of the docker private repository(nexus)

	    def dockerImageTag = "sfdadocker${env.BUILD_NUMBER}"

	    stage('SFDA Workflow Engine Code Checkout') {
		    // for display purposes
		    echo "Cleaning the Workspace"
		    cleanWs()
		    echo "Pull from Main branch of Repository"
		    git branch: "main", credentialsId: 'sfdaPipe', url: 'https://github.com/jcvalladares/xtreamteam.git'
		    // Get the Maven tool.
		    mvnHome = tool 'Maven3.6.3'
	    }

	    stage('Code Build and Unit Test suite') {
		    // build project via maven
		    echo "Building Project"
		    sh "'${mvnHome}/bin/mvn' -f ${env.WORKSPACE}/sfda/pom.xml clean install"
	    }

	    stage('Build Docker Image') {
		    // build docker image from jar
		    dockerImage = docker.build("sfdadocker:${env.BUILD_NUMBER}")
	    }

	    stage('Staging Deployment') {
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
			stage('Production Deployment') {
				// deploy docker image to nexus
				def flag = 0
				if (flag == 0) {
					echo "Docker Image Tag Name: ${dockerImageTag}"
					try {
						sh "docker stop sfdadockerpro"
						sh "docker rm sfdadockerpro"
					} catch (Exception e) {
						echo 'Exception occurred: ' + e.toString()
					}
					sh "docker run --name sfdadockerpro -d -p 80:8080 sfdadocker:${env.BUILD_NUMBER}"
				}
				else {
					echo "Production Deployment Skipped"
				}
				}
			stage("Production Deployment Check") {
			    def ret_code = sh(script: "curl --fail -s -o /dev/null -w '%{http_code}' http://www.www.google.com/", returnStdout: true).trim()
					if (ret_code == "000") {
						echo "Deployment OK"
					}
					else {
						echo "Deployment NOT OK"
					}
			    echo "Post Deployment Check Completed"
		    }

}
