pipeline {

  agent any

  options {
    buildDiscarder(logRotator(numToKeepStr: '3'))
  }

  tools {
    maven 'mvn'
    jdk 'Zulu OpenJDK 11'
  }


  stages {
    stage("test") {

      steps {
        sh 'mvn test'
      }

      post {
        always {
          handleResult(currentBuild.result)
        }
      }
    }
  }

  post {
    always {
      cleanWs()
    }
  }

}

def handleResult(result) {
  if (result == 'SUCCESS') {
    updateGitlabCommitStatus name: STAGE_NAME, state: 'success'
  } else if (result == 'FAILURE') {
    updateGitlabCommitStatus name: STAGE_NAME, state: 'failed'
  } else if (result == 'FIXED') {
    updateGitlabCommitStatus name: STAGE_NAME, state: 'fixed'
  }
}
