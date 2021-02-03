pipeline {
  agent any
  stages {
    stage('Sonar') {
      steps {
        echo 'Run SonarQube'
        sh 'mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dlicense.skip=true'
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
    maven 'maven3.6.3'
    gradle 'gradle6.8.1'
  }
  post {
    always {
      archiveArtifacts(artifacts: '**/*.apk', fingerprint: true)
    }

  }
}