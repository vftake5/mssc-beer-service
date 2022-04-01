[![CircleCI](https://circleci.com/gh/vftake5/mssc-beer-service/tree/master.svg?style=svg)](https://circleci.com/gh/vftake5/mssc-beer-service/tree/master)
# MSSC Beer Service

Spring Boot Micriservice example

### Reference documentation

CircleCI setup:

Project settings - Environment variables - Add Env. variable

    Name: spring_profiles_active
    Value: circlepsql

ActiveMQ Artemis run on Ubuntu virtual machine.

Needed setup:

Virtual machine NAT, port forwarding 127.0.0.1 61616 and 8161 from the VM ip address.

On VM have to start the artemismq docker image. See: ApacheArtemisInstallAndSetup.txt