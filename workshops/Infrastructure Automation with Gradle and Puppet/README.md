# Infrastructure automation with Gradle and Puppet

## Workshop preparation

### Install tools

- Gradle 2.+
- VirtualBox 4.3.+
- Vagrant 1.7.+

### Vagrant box

- Download Vagrant box file: http://bit.ly/1M5shfd
- Register box in Vagrant:

        vagrant box add puphpet/ubuntu1404-x64 ub...x.box --force

### Project source

- Source: http://bit.ly/1JLTLUc
- Slides: http://bit.ly/1MvTz1B

### Project setup (step 1)

- Download Tomcat 8 from <http://apache.mirrors.spacedump.net/tomcat/tomcat-8/v8.0.18/bin/apache-tomcat-8.0.18.tar.gz> and put it into the `repository` directory.
- Build the `petclinic` application (`gradle clean build`) in the `application/spring-petclinic` directory and place the `application/spring-petclinic/build/libs/petclinic-0.1-SNAPSHOT.war` archive into the `repository` directory.

### Project setup (step 2)

- Run `vagrant up` to start all the virtual machines.
- Run `gradle initNode` to initialize software needed for provisioning (Puppet) to work.
- Run `gradle puppetapply` to apply Puppet configuration to virtual servers.

### Approx. timing

- Download tomcat ~ 4 minutes 
- spring-petclinic/`gradle clean build` ~ 3 minutes (+ library download)
- `vagrant up` ~ 15 minutes (- vagrant box)
- `gradle initNode` ~ 4 minutes
- `gradle puppetApply` ~ 8 minutes

