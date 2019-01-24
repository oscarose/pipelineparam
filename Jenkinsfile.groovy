node {
    stage('checkout git') {
        git url: 'https://github.com/oscarose/pipelineparam.git'
    }
    stage(parameters) {
        install_dir="/opt"
        jenkins_dir="/opt/apache-tomcat-8.5.37/webapps"
        java_name="jdk1.8.0_202"
        //string(name: 'Server_IP', defaultValue: '', description: 'node to deploy ansible')
        tomcat_version="apache-tomcat-8.5.37"
        app_user="tomcat"
        //choice(name: 'Ansible_Playbook', choices: ['pipeline.yaml', 'none', 'prof.yaml'], description: 'Playbook to Run')
        ssh_pwd=""
        artifactory_user=""
        artifactory_password=""   
    }
    stage('deploy ansible playbook') {
        sh 'echo running ansible playbook'
        sh 'ansible-playbook -i "${Server_IP}," -k -b --extra-vars "app_user":"'+app_user+'", "artifactory_user":"'+artifactory_user+'", "artifactory_password":"'+artifactory_password+'", "ansible_ssh_pass":"'+ssh_pwd+'", "ansible_become_pass":"'+ssh_pwd+'", "tomcat_version":"'+tomcat_version+'", "install_dir":"'+install_dir+'", "jenkins_dir":"'+jenkins_dir+'", "java_name":"'+java_name+'", "Server_IP":"${Server_IP}" ${WORKSPACE}/pipline.yaml'
    }
}
