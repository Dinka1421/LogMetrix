pipeline {

  agent any

  options {
    buildDiscarder(logRotator(numToKeepStr: '3'))
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
