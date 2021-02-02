pipeline {
  agent any
  stages {
    stage('Sonar') {
      steps {
        echo 'Run SonarQube'
        withSonarQubeEnv(credentialsId: 'MySonar', installationName: 'Sonar') {
          sh 'gradle sonarque'
          waitForQualityGate(abortPipeline: true, credentialsId: 'MySonar')
        }

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