pipeline {

    stages {

        stage('Prepare') {
            agent any
            steps {
                script {
                    def containerName = 'chz-mysql-wesay'

                    // 检查是否有同名容器存在
                    def containerExists = sh(script: "docker ps -a --filter 'name=${containerName}' --format '{{.Names}}'", returnStdout: true).trim()

                    if (containerExists) {
                        echo "Container ${containerName} already exists. Skipping creation."
                    } else {
                        // 创建并启动 MySQL 容器
                        echo "Creating and starting MySQL container ${containerName}."
                        sh """
                            docker run -d \
                                --name ${containerName} \
                                -e MYSQL_ROOT_PASSWORD=12345678 \
                                -e MYSQL_DATABASE=wesay \
                                -p 3307:3306 \
                                mysql:8.0
                        """
                    }
                }
            }
        }

        stage ('backend') {
            agent {
                dockerfile true
            }
            steps {
                sh 'docker build -t chz-wesay .'
                sh 'docker run -d -p 8089:8080 --name chz-wesay chz-wesay'
            }
        }
    }

    post {
        always {
            // 在构建结束后清理容器（可选）
            script {
                sh "docker stop chz-mysql-wesay || true"
                sh "docker rm chz-mysql-wesay || true"
                sh "docker stop chz-wesay || true"
                sh "docker rm chz-wesay || true"
                sh "docker image rm chz-mysql-wesay chz-wesay"
            }
        }
    }
}
