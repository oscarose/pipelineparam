node {
    stage('checkout git') {
        git url: 'https://github.com/oscarose/hello-world.git'
    }
    parameters {
        string(name: 'install_dir', defaultValue: '/opt', description: 'Tomcat dir')
        string(name: 'jenkins_dir', defaultValue: '/opt/apache-tomcat-8.5.37/webapps', description: 'defining jenkins dir')
        string(name: 'java_name', defaultValue: 'jdk1.8.0_202', description: 'defining java name')
        string(name: 'Server_IP', description: 'node to deploy ansible')
        string(name: 'tomcat_version', defaultValue: 'apache-tomcat-8.5.37', description: 'defining tomcat version')
        string(name: 'app_user', defaulyValue: 'tomcat', description: 'defining app name')
        choice(name: 'Ansible_Playbook', choices: ['pipeline.yaml', 'none', 'prof.yaml'], description: 'Playbook to Run')
        password(name: 'ssh_pwd', description: 'ssh password to log into the serve')
        password(name: 'artifactory_user', description: 'artifact user')
        password(name: 'artifactory_password', description: 'artifacts pass')   
    }
    stage('deploy ansible playbook') {
        sh 'echo running ansible playbook'
        sh 'ansible-playbook -i "${Server_IP}," -k -b --extra-vars "app_user=${app_user} artifactory_user=${artifactory_user} artifactory_password=${artifactory_password} ansible_ssh_pass=${ssh_pwd} ansible_become_pass=${ssh_pwd} tomcat_version=${tomcat_version} install_dir=${install_dir} jenkins_dir=${jenkins_dir} java_name=${java_name} Server_IP=${Server_IP}" ${WORKSPACE}/$Ansible_Playbook'
   }
}
