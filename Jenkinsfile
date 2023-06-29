pipeline {

  agent any

  options {
    buildDiscarder(logRotator(numToKeepStr: '3'))
  }
  
  tools {
    jdk 'OpenJDK'
  }
  
  stages {
    stage("test") {

      steps {
        sh 'mvn test'
      }
    }
  }

  post {
    always {
      cleanWs()
    }
  }
}
