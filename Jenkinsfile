// 젠킨스의 선언형 파이프라인 정의부 시작(Groovy 언어)
pipeline{
    agent any // 어느 젠킨스 서버에서나 실행이 가능
    environment{
        //환경변수 선언 하는 곳.

    }
    stages{
        // 각 작업당위를 스테이지로 나누어서 작성가능.
        stage('Pull Codes from Github'){ // 스테이지 제목 (맘대로 써도됌)
            steps{
                checkout scm //젠킨스와 연결된 소스컨트롤 매니저 (git 등)에서 코드를 가져오는 명령어
            }
        }
        stage('Build Codes by Gradle'){
            steps{
                script{
                    sh """
                    echo "Build Stage Start!"
                    """
                }
            }
        }
    }
}