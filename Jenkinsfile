pipeline {
    agent any
    stages {
        stage ('Build Backend') {
            steps {
                bat 'mvn clean package -DskipTests=true'
            }
        }
        stage ('Unit Tests') {
            steps {
                bat 'mvn test'
            }
        }
        stage ('Sonar Analysis') {
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps {
                withSonarQubeEnv('SONAR_LOCAL') {
                    echo "$scannerHome"
                    bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack-Dsonar.host.url=http://localhost:9000-Dsonar.login=53ee3dee03e3a689b673b5a784aad1fd8a071bc9-Dsonar.java.binaries=target-Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**Application.java"
                }
            }
        }
    }
}