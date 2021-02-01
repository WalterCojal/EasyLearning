pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Build apk'
        sh 'gradle assembleDebug'
      }
    }

  }
  tools {
    gradle 'gradle6.8.1'
  }
}