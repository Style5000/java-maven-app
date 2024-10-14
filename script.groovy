def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t style5000/maven-app:1.2 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push style5000/maven-app:1.2'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this //It's important otherwise you can't import in Jenkins file