pipeline {
  agent any
  stages {
    stage('Sonar') {
      steps {
        echo 'Run SonarQube'
        sh 'gradle sonarqube'
      }
    }

    stage('Build') {
      steps {
        echo 'Build'
        sh 'gradle assembleDebug'
      }
    }

  }
  tools {
    gradle 'gradle6.8.1'
  }
  post {
    always {
      archiveArtifacts(artifacts: '**/*.apk', fingerprint: true)
    }

  }
}